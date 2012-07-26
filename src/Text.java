
/**
 * The <code>Text</code> class provides the methods 
 * to do manipulation with any input text string. 
 * The class manipulates with the text string contains 
 * characters from a to z and ' ' (space). It contains
 * get and set methods for accessing to instance variables.
 * The train method of this class extracts bigrams
 * and unigrams from input text string and do counts
 * of the bigrams and unigrams. This method also calculates 
 * probabilities of bigrams in array <code>bigrams</code>.
 * The test method extracts all bigrams from input string
 * and calculates empirical probability for this string based 
 * on each bigram probability in array <code>bigrams</code>.   
 * 
 * @author Marek Dano
 */
public class Text {
	
	/** unigram counts of all characters in a text */
	private int[] unigrams;
	/** probability and frequency of all bigrams in a text */
	private BigramNode[] bigrams;
	/** set of small letters (a-z) and space between letters */
	private final int numOfUnigrams = 27;
	/** all bigrams from "__", "_a",...,"zz" */
	private final int numOfBigrams = 729;
	/** all letters and spaces in a string */
	private int count = 0;
	/** probability of test data bigrams */
	private double prob;
	/** ASCI code for "space" */
	private final int SPACE = 32;
	/** the length of the array containing letter 
	 * from a-z and "space" */
	private final int lenghtOfTheArray = 27;
	/** ASCI code of letter "a" kept in first slot 
	 * of the array, which is "a"(97) - asciCode(96) */
	private final int ASCICODE = 96;
	
		
	// ---------------------- constructor ------------------------
	/**
	 * The constructor instantiates the object by two
	 * set methods; 
	 * <p>setUnigrams - setting instance variable 
	 * <code>unigrams</code>,
	 * <p>setBigrams - setting instance variable <code>bigrams</code>. 
	 */
	public Text(){
		setUnigrams();
		setBigrams();
	}
	
	// ----------------------- methods --------------------------
	/**
	 * Set method for setting <code>unigrams</code>.
	 */
	private void setUnigrams() {
		unigrams = new int[numOfUnigrams];
	}
	
	/**
	 * Set method for setting <code>bigrams</code>.
	 * The method fills <code>bigrams</code> with 
	 * object of <code>BigramNode</code> class.
	 */
	private void setBigrams() {
		// initialise bigram for pairs of letters 
		// from "__", "_a", "_b",...,"zy", "zz" 
		bigrams = new BigramNode[numOfBigrams];
		
		for (int i=0; i<numOfBigrams; i++) {
			bigrams[i] = new BigramNode(null, 0, 0);  
		}
		
	}
	
	/** 
	 * Get method for getting instance variable 
	 * <code>count</code>.
	 * 
	 * @return count - integer
	 */
	public int getCountLetters(){
		return count;
	}
	
	/**
	 * Get method for getting instance variable 
	 * <code>prob</code>.
	 * 
	 * @return prob - double
	 */
	public double getProb() {
		return prob;
	}
	
	/**
	 * The method calls countFreq method to count 
	 * occurrences of the characters (unigrams) and
	 * frequencies of two characters (bigrams) in the String.
	 * The calcProb method is called to calculate 
	 * probabilities of all bigrams. 
	 */
	public void train(String text) {
		
		countFreq(text);
		calcProb();
	}
	
	/**
	 * The test method extracts all bigrams from input string
	 * and calculates probability for this string based 
	 * on each bigram probability in array <code>bigrams</code>. 
	 * @param text - String
	 */
	public void test(String text) {
		// initialise each time of new testing
		prob = 0;
				
		for(int i=0; i<text.length()-1; i++){
					
			int j = i+2;	
			String bigram = text.substring(i,j);
			int key = hash(bigram);
						
			if (key != 0)
				prob += Math.log(bigrams[key].getValueProb());  
		
		}// end for loop 		
	}// end test method
	
	/**
	 * The method counts occurrences of the characters (unigrams),
	 * frequencies of two characters (bigrams) in the String. 
	 * The counts are assigned to <code>unigrams</code> and
	 * <code>bigrams</code>.   
	 * 
	 * @param langText - String of characters a-z and 'space'.
	 */
	private void countFreq(String langText) {
		
		int unigram,  unigramKey, unigramValue;
		String bigram;
				
		// loop counts all bigrams and unigrams except the last letter 
		for (int i=0; i<langText.length()-1; i++) {
				
			unigram = langText.charAt(i);
			
			if (unigram == SPACE){
				// space "_" stores in zero slot
				unigramKey = 0;
				addUnigram(unigramKey);
				
			} else {
				// all letters, store in 1-26 slot in array				
				unigramKey = unigram - ASCICODE ; 
				// add unigram to array
				addUnigram(unigramKey);
			}
					
			int j = i+2;
			bigram = langText.substring(i, j);
			// call method to add bigram to array
			addBigram(bigram);
											
			count++;
			
		}// end for loop
		
		// last letter in a text string 
		unigramKey = langText.charAt(langText.length()-1);
		if (unigramKey != SPACE){
			unigramValue = unigrams[unigramKey - ASCICODE];
			unigrams[unigramKey - ASCICODE] = ++unigramValue;
		
			count++;
		}
		
	}// end countFreq method for counting frequency of letters
	
	/**
	 * The method calculates relative probabilities 
	 * of all bigrams. The Laplace smoothing is used 
	 * for possible zero counts in bigrams.  
	 */
	private  void calcProb() {
		
		int unigramValue;
		double bigramValue, bigramProb;
		
		int j = 0;
		// no calculation for first element (string "  ") 
		// in bigrams array
		for (int i=1; i<bigrams.length; i++){
			
			bigramValue = bigrams[i].getValueFreq();
			
			if (i % lenghtOfTheArray != 0) {
				unigramValue = unigrams[j];
			} else {
				j += 1;
				unigramValue = unigrams[j];
			}
			
			// Laplace smoothing
			bigramProb = (bigramValue + 1) / (unigramValue + numOfUnigrams);
			bigrams[i].setValueProb(bigramProb);
		}// end for loop 
		
	}// end calcProb method 
	
	/**
	 * The method calculates the value of input string
	 * and returns this value.
	 *  
	 * @param str - String
	 * @return integer - number 0-728
	 */
	private int hash(String str) {
		
		int key;
		int c1,c2;
		
		// first character in the string
		c1 = str.charAt(0);						
		if (c1 == SPACE) 
			c1 = 0;
		else 
			c1 -= ASCICODE;
		
		// second character in a string
		c2 = str.charAt(1);
		if (c2 == SPACE) 
			c2 = 0;
		else
			c2 -= ASCICODE;
		
		// calculate key
		key = c1*lenghtOfTheArray  + c2;
				
		return key;
	}
	
	/**
	 * The method updates the bigram value of frequency
	 * in <code>bigrams</code> array. The access is based 
	 * on hashing the input string.
	 *   
	 * @param bigram - string
	 */
	private void addBigram(String bigram) {
		int bigramValue;
		int bigramKey;
		
		bigramKey = hash(bigram);
		bigramValue = bigrams[bigramKey].getValueFreq();
		bigrams[bigramKey].setKey(bigram);
		bigrams[bigramKey].setValueFreq(++bigramValue);
	}
	
	/**
	 * The method updates the value of character (unigram)
	 * in <code>unigrams</code> array.
	 * 
	 * @param index - integer, index in the array.
	 */
	private void addUnigram(int index) {
		int unigramValue;
		
		unigramValue = unigrams[index];
		unigrams[index] = ++unigramValue;
	}
	
}// end Text class
