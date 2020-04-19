package EpicVotingSystem;

import javax.swing.JOptionPane;
import java.io.*;
import java.io.IOException;

public class WriteToFile {

	public static void main(String[] args) {
		
	
		try {
		
		//initiate array staffNames
		String [] staffNames = new String[] {"000000, cat & cheese, 0", "\n", "333333, dog & butter, 0", "\n"};
		
		//initiate a reader writer to file
		BufferedWriter read = new BufferedWriter(new FileWriter("E:\\Software Development\\Assignment 1\\staff.txt", true));
		//read the array line
		read.write("\r\n");
		for (String staff: staffNames) {
			read.write(staff + " ");
		}
		
		//print staffName array to file
		System.out.println();
		
		//display a message if successfully written to file
		JOptionPane.showMessageDialog(null,  "staffNmae array successfully added to file");
		read.close();
		}
		
		//display an error message if unsuccessfully written
		catch(IOException ioex){
			System.out.println(ioex.getMessage() + "Error reading the file");
		}
		

	}


}
