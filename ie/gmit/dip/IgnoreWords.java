package ie.gmit.dip;

import java.io.BufferedReader;
import java.util.*;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class IgnoreWords {
	
	private static ArrayList<String> ignorewords = new ArrayList<String>();
	private static char NextWord;

	public IgnoreWords() throws Exception {
		ignore("./ignorewords.txt"); // "ignorewords.txt" is in the project Dir.
	}
	
	/**
	 * @author deanh
	 * 
	 * Method that reads ignorewords.txt
	 * and the txt File input from user
	 * and cross references both txts and
	 * removes the undesirable words from
	 * user input File
	 * 
	 * 
	 * @param FileName takes in Filename read in by Menu
	 * @throws Exception
	 * 
	 * See Menu
	 */
	
	public void ignore(String FileName) throws Exception {
		
		// A buffered reader to stream in the text
		
 		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FileName)));
 		StringBuffer StringB = new StringBuffer();
 		
 		int i;
 		
 		while((i = br.read()) != -1)
 		{
 			NextWord = (char) i;
 			
 			if(NextWord != '\n')
 				StringB.append(NextWord);
 			
 			else{
 				//Here we are adding any Strings that are in "ignorewords" to the ignorewords Arraylist
 				String ignore = StringB.toString().toUpperCase();
 				StringB = new StringBuffer();
 				ignorewords.add(ignore);
 			}
 		}
 		br.close();
	}
	
	public boolean split(String word){
		if(ignorewords.contains(word))
			return true;	
		else {
			return false;
		}
	}

}
