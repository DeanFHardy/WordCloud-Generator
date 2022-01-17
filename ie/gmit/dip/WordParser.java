package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class WordParser extends IgnoreWords {


	private TreeMap<String, Integer> table = new TreeMap<String, Integer>();
	private static char CloudWord;
	private static String words;
	private static int nextWord;
	private static int frequency = 0;
	private static BufferedReader br;
	private static StringBuffer StringBuffer;
	
	Scanner scan = new Scanner(System.in);
	
	public WordParser(String file) throws Exception {
		inputText(file);
	}
	
	/**
	 * Reads file input word for word, through BufferedReader 
	 * using InputStreamReader
	 * 
	 * 
	 * @param file the file that's input
	 * @return TreeMap 'table'
	 * @throws Exception
	 */
	
	public TreeMap<String, Integer> inputText(String file) throws Exception {
		
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			StringBuffer = new StringBuffer();
			
			while((nextWord = br.read()) != -1) {
				
				CloudWord = (char)nextWord;
				
				if (CloudWord >= 'A' && CloudWord <= 'Z' || CloudWord >= 'a' && CloudWord <= 'z' || CloudWord == '\'' ) {
					scan.useDelimiter(",|\r\n, [0-9],123...");
					
					StringBuffer.append(CloudWord);
				}
				else {
					
					//This will be the process of the frequency table for the Cloud Image
					
					words = StringBuffer.toString().toLowerCase();
					StringBuffer = new StringBuffer();
					
					if(!split(words) && words.length() > 0) {
						
						if(table.containsKey(words)) { //0(1) as it is a Key based search operation
							frequency = table.get(words);						}
					}
					frequency++;
					table.put(words, frequency); //Pass in the (key, value)
					
				}
			}
		}
		
		catch (Exception e) {
			System.out.println("Error inputing file");
		}
		
		return table;
	}
	
	public TreeMap<String, Integer> getTable(){
		return table;
	}
	
	private void setTable(TreeMap<String, Integer> wordTable) {
		this.table = wordTable;
	}
	
	
	

}
