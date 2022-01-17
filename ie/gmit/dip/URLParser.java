package ie.gmit.dip;

import java.net.URL;

import java.util.TreeMap;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;

public class URLParser extends IgnoreWords {

	// Pull in the same variables from Word Parser (Don't over complicate the issue)
	private TreeMap<String, Integer> urlTable = new TreeMap<String, Integer>();
	private static char CloudWord;
	private static String words;
	private static int nextWord;
	private static int frequency = 0;
	private static BufferedReader br;
	private static StringBuffer StringBuffer;
	
	Scanner scan = new Scanner(System.in);

	public URLParser(URL file) throws Exception {
		inputText(file);

	}

	public TreeMap<String, Integer> inputText(URL file) throws IOException {

		try {
			br = new BufferedReader(new InputStreamReader(file.openStream()));
			StringBuffer = new StringBuffer();

			while ((nextWord = br.read()) != -1) {

				CloudWord = (char) nextWord;

				if (CloudWord >= 'A' && CloudWord <= 'Z' || CloudWord >= 'a' && CloudWord <= 'z' || CloudWord == '\'') {
					scan.useDelimiter(",|\r\n, [0-9],123...");

					StringBuffer.append(CloudWord);
				}

				else { // Switch from Lowercase to Uppercase between Word Parser and Url Parser for some
						// variety
					words = StringBuffer.toString().toUpperCase();
					StringBuffer = new StringBuffer();

					/** Time Frequency **/

					if (!split(words) && words.length() > 0) { //0(1) opertion
						if (urlTable.containsKey(words)) {
							frequency = urlTable.get(words);
						}

						frequency++;
						urlTable.put(words, frequency);
					}
				}
				

			}
			
			br.close(); //Good management to close reader or system will be constantly expecting it
			setMap(urlTable);

		} catch (Exception e) {
			System.out.println("Error inputing URL");
		}
		return urlTable;

	}
	

	private void setMap(TreeMap<String, Integer> wordTable) {
		this.urlTable = wordTable;
	}
	
	

}
