package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TimeCounter {
	int time;
	int current;

	public int reset() {
		current = time;
		return current;
	}

	public int drop() {
		current--;
		if (current<0) current=0;

		return current;
	}

	// Set the maximum time here
	TimeCounter() {
		// The name of the file to open.
		String fileName = "Timer.txt";

		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = 
					new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = 
					new BufferedReader(fileReader);

			while((line = bufferedReader.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					time = Integer.parseUnsignedInt(line);
					current = time;
					break;
				}
			}   

			// Always close files.
			bufferedReader.close();         
		}
		catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open file '" + 
							fileName + "'");                
		}
		catch(IOException ex) {
			System.out.println(
					"Error reading file '" 
							+ fileName + "'");                  
		}
	}
}
