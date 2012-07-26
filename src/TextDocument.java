
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The TextDocument class contains methods for reading 
 * lines from a text file, assigning that lines 
 * to instance variable "text". The occurred errors 
 * accessing and reading from text file are handled in 
 * readFile method. 
 *    
 * @author Marek Dano
 */
public class TextDocument {
	
	/** string extracted from text file */  
	private String text = null;
	
	
	// ------------------------ methods -------------------
	/**
	 * The method reads the lines from text file and replace
	 * all punctuation, accented letter with unaccented version.
	 * It also replace all capitals to lower case letters.
	 * If an error is occurred with reading from the text file, 
	 * the null string is return.       
	 * 
	 * @param fileText - String
	 * @return String text - instance variable of this class 
	 */
	public String getFileText(String fileText) { 
		
		readFile(fileText);
		
		if (text != null)
			convertText();
		
		return text;
	}
	
	/**
	 * The method reads lines from a text file. The file not
	 * found exception is thrown if the there is a problem 
	 * finding the text file.
	 * The IO exception is throw if the error occurred 
	 * when reading lines from the text file.
	 * 
	 * @param fileText
	 * @throws FileNotFoundException, IOException
	 */
	private void readFile(String fileText) {
		
		String line = null;
		BufferedReader br = null;
	
		// open and read a file
		try {

			FileReader fr = new FileReader(fileText);
			br = new BufferedReader(fr);
		
			line = br.readLine();
			StringBuffer stb = new StringBuffer();
	
			while (line != null){

				stb.append(" " + line);
				line = br.readLine();
			}
			
			text = stb.toString();
			
		} catch(FileNotFoundException e) { 
            System.out.println("Error opening input file ");
                      
		} catch(IOException e) {
			System.out.println("Error reading file");
		}
		
		finally { 
       		
				try { 
					if (br != null) 
						br.close(); 
				} 

				catch (IOException e) {
					e.printStackTrace();
				}
		} 	
    }// end readFile method
	
	/**
	 * The method converts all letters in "text" instance variable 
	 * to small ones. It also replaces accented characters to 
	 * unaccented ones defined in Unicode. The string 
	 * <code>text</code> contains 
	 * 27 characters (a-z and 'space'). 
	 */
	private void convertText(){
		
		text = text.toLowerCase();
		//System.out.println("lower case letters: " + text);
		text = text.replaceAll("[ľĺ]", "l").replaceAll("[áä]", "a");
		text = text.replaceAll("š", "s").replaceAll("č", "c");
		text = text.replaceAll("ž", "z").replaceAll("ý", "y");
		text = text.replaceAll("í", "i").replaceAll("é", "e");
		text = text.replaceAll("ú", "u").replaceAll("ť", "t");
		text = text.replaceAll("ň", "n").replaceAll("[ôó]", "o");
		text = text.replace("ď", "d").replace("ŕ", "r");
		
		text = text.replaceAll("[^a-z ]", "");
	
	}// end convertText
	
}// end TextDocument class
