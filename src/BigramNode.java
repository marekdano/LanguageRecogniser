
/**
 * The BigramNode class instantiates the node. 
 * The node is characterised by key - string, 
 * valueFreq - integer and valueProb - double.
 * The class contains getting and setting methods for all 
 * instance variables of this class      
 * 
 * @author Marek Dano
 */

public class BigramNode {
	private String key;	    	// bigram of letters
	private int valueFreq; 	    // frequency of bigram
	private double valueProb;	// probability of bigram
	
	// -------------------- constructor -----------------------------
	/**
	 * The constructor initialises the instance variables
	 * of BigamNode class. 
	 * 
	 * @param key - string of two characters
	 * @param valueFreq - integer 
	 * @param valueProb - double			
	 */
	public BigramNode(String key, int valueFreq, double valueProb) {
		this.key = key;
		this.valueFreq = valueFreq;
		this.valueProb = valueProb;
	}
		
	// -------------------- methods ---------------------------------
	/**
	 * Set method for setting instance variable key
	 * @param str - String
	 */
	public void setKey(String str) {
		key = str;
	}
	
	/**
	 * Set method for setting instance variable valueFreq
	 * @param value - integer 
	 */
	public void setValueFreq(int value) {
		valueFreq = value;
	}
	
	/**
	 * Set method for setting instance variable valueProb
	 * @param value - double
	 */
	public void setValueProb(double value) {
		valueProb = value;
	}
		
	/**
	 * Get method for getting instance variable key
	 * @return String
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * Get method for getting instance variable valueFreq
	 * @return integer
	 */
	public int getValueFreq() {
		return valueFreq;
	}
	
	/**
	 * Get method for getting instance variable valueProb
	 * @return double
	 */
	public double getValueProb() {
		return valueProb;
	}
	
}// end BigramNode class