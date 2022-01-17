package ie.gmit.dip;

import java.util.*;
import java.io.*;
import java.net.URL;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.Random;

public class CloudWriter {

	private WordParser parse;
	private URLParser up;
	private Color colour;
	private Random random = new Random();
	private Graphics graphics;
	private Font font;
	private BufferedImage image;
	private String word;
	private int Height = 0;
	private int Width = 0;
	private float red, blue, green, alpha;

	public CloudWriter(String inputFile, String outputFile, int words) throws Exception {
		parse = new WordParser(inputFile);
		showCanvas(outputFile, words);

	}
	
	//Create URL Constructor
	public CloudWriter(URL urlInput, String outputFile, int words) throws Exception {
		up = new URLParser(urlInput);
		showCanvas(outputFile, words);
		
	}

	/**
	 * @author deanh
	 * 
	 * This will select the font and arrange the
	 * lettering to avoid collision.
	 * 
	 * @param word, items, w, h 
	 * @return Draws the cloud words - return height.
	 * 
	 */

	public int drawCanvas(String word, int items, int w, int h) {

		int size = (int) (Math.log(items) * 5);
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, size);
		graphics.setFont(font);

		FontMetrics metrics = graphics.getFontMetrics(font);
		h = metrics.getHeight();
		graphics.drawString(word + "", Width, Height + metrics.getAscent());

		return h;

	}
	
	/**
	 * @author deanh
	 * 
	 * This is the display method for the Word Cloud.
	 * Using the getTable method from WordParser to 
	 * get the contents of word text file and parsing
	 * it through the parser and streaming it to this
	 * method.
	 * 
	 * See WordParser
	 * 
	 * (Having issues with this method and the URL)
	 * 
	 * @param outputFile from Constructor
	 * @return Image of outputFile
	 * @throws Exception
	 * 
	 */

	private void showCanvas(String outputFile, int words) throws  Exception {
		TreeMap<String, Integer> canvas = new TreeMap<String, Integer>();
		canvas = parse.getTable();
		
		/**
		 * Handles image resolution. 600, 300 - Gives a much smaller image.
		 * I've found 1400, 600 to be the best for a full sized view 
		 * of the canvas.
		 * 
		 */
		
		image = new BufferedImage(1400, 600, BufferedImage.TYPE_4BYTE_ABGR); 
		graphics = image.getGraphics();
		
		/**
		 * Sets the background color and fills
		 * the canvas space
		 * 
		 */

		graphics.setColor(colour.black);
		graphics.fillRect(1, 2, 1440, 600);
		
		/**
		 * Iterates over the words in the text file
		 * Big 0 = 0(n) 
		 * 
		 * As there is only 'n' iteration
		 * inside the loop.
		 * 
		 */

		for (int i = 0; i < words; i++) {

			int j = 0;
			int numIterator = 0;
			
			

			for (String word : canvas.keySet()) { 

				if (canvas.get(word) > 1 && j < canvas.size()) {

					int fontH = drawCanvas(word, canvas.get(word), Width, Height);
					Height += fontH;

					// Set Colours based on random selction
					float red = (random.nextFloat());
					float green = (random.nextFloat());
					float blue = (random.nextFloat());
					Color colour = new Color(red, green, blue);
					graphics.setColor(colour);

					numIterator++;

					if (numIterator >= words) {
						Width += 200;
						Height = 22;
						numIterator = 0;
					}
					
				}
			}
			graphics.dispose(); //Garbage collection
			ImageIO.write(image, "png", new File(outputFile));
		}
		
	}

}
