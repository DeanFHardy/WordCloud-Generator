package ie.gmit.dip;

import java.util.Scanner;


import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

public class Menu {

	Scanner scan1 = new Scanner(System.in);
	Scanner scan2 = new Scanner(System.in);
	int choice = 0;
	String fileName = "default";
	String url = "";
	String inputTextFile = "";
	int wordCount = 0;

	private boolean keepRunning = true; // Boolean loop to keep menu operating

	public void startMenu() throws Exception { //https://stackoverflow.com/questions/37739773/java-how-to-link-menu-and-read-file
		while (keepRunning) {
			menuHeader(); // Display Menu Heading
			showOptions(); // Display Menu options for User input
			int choice = getInput();

			if(choice == 1) {
				
				//Get File
				System.out.println("Enter the File Path: ");
				inputTextFile = scan2.nextLine(); 
				File file = new File(inputTextFile);
				
				//Pass the input text file through the Parser 
				WordParser wp = new WordParser(inputTextFile);
				wp.ignore(inputTextFile);
				
				//Set File & Word Count
				System.out.println("Enter the File Name: ");
				fileName = scan1.next();
				
				System.out.println("How many words to display ?");
				wordCount = scan1.nextInt();
				
				CloudWriter cloudFile = new CloudWriter(inputTextFile, fileName + ".png", wordCount);
				
				
			}
			
			else if(choice == 2) {
				System.out.println("Enter the URL: ");
				url = scan2.nextLine();
				URL urlInputFile = new URL(url);
				
				//Same as above with Word Parser: Pass the input text file into the Parser
				URLParser up = new URLParser(urlInputFile);
				//up.ignore(url); //Can't locate file when I use this call
				
				//Set File & Word Count
				System.out.println("Enter the File Name: ");
				fileName = scan1.next();
				
				System.out.println("How many words to display ?");
				wordCount = scan1.nextInt();
				
				CloudWriter urlFile = new CloudWriter(urlInputFile, fileName + ".png", wordCount);
				
			}
			else if(choice == 3) {
				keepRunning = false;
				System.out.println("You have Quit the program.");
			}
				
			
			
				}

			

}

	

	private void menuHeader() { // Displays Menu banner
		System.out.println(Colours.BLUE_BRIGHT);
		System.out.println("***************************************************");
		System.out.println("* GMIT - Dept. Computer Science & Applied Physics *");
		System.out.println("*                                                 *");
		System.out.println("*           Word Cloud Generator V0.1             *");
		System.out.println("*     H.Dip in Science (Software Development)     *");
		System.out.println("                   By - Dean Hardy                *");
		System.out.println("*                                                 *");
		System.out.println("***************************************************");
		System.out.print(Colours.RESET);
	}

	private void showOptions() { // Displays menu
		System.out.println("1) Enter name of Text File");
		System.out.println("2) Enter URL Name");
		System.out.println("3) Quit Program ");

	}

	private int getInput() { // Method to receive a selection input that also guards against invalid input by
								// returning to menu.

		int choice = 0;
		while (choice < 1 || choice > 4) {
			try {
				System.out.print("\nPlease select an option:");
				choice = Integer.parseInt(scan1.next());
			} catch (NumberFormatException e) {
				System.out.println(Colours.YELLOW);
				System.out.println("Invalid selection. Try Again.");
				System.out.println(Colours.RESET);
			}
		}

		return choice;
	}

}
