package EpicVotingSystem;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ReadToFile {
	
	public static void main (String [] args)
{
		//Declare a scanner and a reader 
		Scanner scanner = new Scanner (System.in);
		BufferedReader read = null;
		
		//Read the file line by line
		String line;
		
		System.out.println(" Please enter the file to read: ");
		
		
		//Initiate a Try/Catch block
		try {
			
			//Initiate a reader writer to file 
			read = new BufferedReader (new FileReader("Q:\\Software Development\\Assignment 1\\" + scanner.next()));
			}
		
		catch (FileNotFoundException fnfx) {
				System.out.println(fnfx.getMessage() + " The file was not found"); 
				System.exit(0);
		}
		
		
		try {
			while ((line = read.readLine() ) !=null)
			{
				System.out.println(line);
			}
		}
		
		//initiate IOException if !successful
		catch (IOException ioex) {
			System.out.println(ioex.getMessage() + "Error reading the file");
			
  		}
		finally {
			System.exit(0);
		}
		
}
	

}
