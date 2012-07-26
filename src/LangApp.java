
import java.util.Scanner;

public class LangApp {

	/**
	 * This application recognises a language in input text file 
	 * by calculation of an empirical probability based on input
	 * training text files. The calculation is provided within
	 * the train and test methods of <code>Text</code> class.
	 * The application allows to test optional text file 
	 * in running time.
	 * Results are displayed after each testing trial.  
	 * If finding or reading text file error occurs, 
	 * the application terminates.  
	 * 
	 * @param args[0] - text file
	 * @param args[1] - text file
	 * 
	 * @author Marek Dano
	 */
	public static void main(String[] args) {
		
		String answer;
		char ans = ' ';
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("This application recognizes a language in input text file.\n");
		
		// first language
		TextDocument doc1 = new TextDocument();
		String textLang1 = doc1.getFileText(args[0]);
		String lang1Name = "";
		
		if (textLang1 != null && textLang1.length() != 1){
			System.out.print("What language is this text file \"" + args[0] + "\" in?  ");
			lang1Name = input.next();
		
		} else {
			System.out.println("The input text file was not found or is empty. The application is terminated");
			System.exit(0);
		}
				
		// second language
		TextDocument doc2 = new TextDocument(); 
		String textLang2 = doc2.getFileText(args[1]);
		String lang2Name = "";
		
		if (textLang2 != null && textLang2.length() != 1) {
			System.out.print("What language is this text file \"" + args[1] + "\" in?  ");
			lang2Name = input.next();
		
		} else {
			System.out.println("The input text file was not found or is empty. The application is terminated");
			System.exit(0);
		}
		
		// train language text from first text file 
		Text lang1 = new Text();
		lang1.train(textLang1);
				
		// train language text from second text file
		Text lang2 = new Text();
		lang2.train(textLang2);
					
		do {
			// test testing data from input text file  
			System.out.println("\n************************************************");
			System.out.print("Please insert new text file to test:  ");
			String testFile = input.next();
			TextDocument test = new TextDocument();
			String testingText = test.getFileText(testFile);
	    
			if (testingText != null) {
	    	
				// test input text file in all trained languages 	    
				lang1.test(testingText);
				lang2.test(testingText);
								
				double lang1Prob = lang1.getProb();
				double lang2Prob = lang2.getProb();
	    
				System.out.println("\nTested text file: ");
				System.out.println("\"" + testingText + " \"");
				if (lang1Prob > lang2Prob){
					System.out.println("\nThe most probability that the testing text file is in \""
										+ lang1Name.toUpperCase() + "\"");
				} else {
					System.out.println("\nThe most probability that the testing text file is in \"" 
										+ lang2Name.toUpperCase() + "\"");
				}
				
				System.out.println("\nProbability of " + lang1Name.toUpperCase() 
										+ " text: " + (10000 + lang1Prob) + "; has characters: " 
										+ lang1.getCountLetters());
				
				System.out.println("Probability of " + lang2Name.toUpperCase() 
										+ " text: "+ (10000 + lang2Prob) + "; has characters: " 
										+ lang2.getCountLetters());
								
				System.out.print("\n\nWould you like to test another text file? yes/no ");
				answer = input.next().toLowerCase();
				ans = answer.charAt(0);
			
			} 
		} while (ans == 'y');	
		
		System.out.println("\nThe application is terminated.");
	
	}// end main

}// end LangApp class
