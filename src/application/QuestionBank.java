package application;

import java.util.ArrayList;
import java.io.*;

public class QuestionBank {
	ArrayList<String> questions = new ArrayList<String>();
	ArrayList<String> answers = new ArrayList<String>();
	int count = 0;
	int current = -1;

	private void add(String question, String answer) {
		questions.add(question);
		answers.add(answer);
		count++;
	}

	public String nextQuestion() {
		if (current>=count-1) {
			current++;
			return "No more questions";
		} 

		current++;
		return questions.get(current);
	}

	public String checkAnswer(String answer) {
		if (current>count-1) {
			return "No more questions";
		}

		String a = answers.get(current);
		if (a.equals(answer)) {
			return "Correct!";
		} else {
			return "Incorrect! Correct answer is: " + answers.get(current);
		}
	}

	public boolean isOutOfQuestion() {
		return (current>count-1);
	}

	QuestionBank() {
		// The name of the file to open.
		String fileName = "QuestionBank.txt";

		// This will reference one line at a time
		String line = null;
		String q = null;
		String a = null;
		boolean isQ = true;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = 
					new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = 
					new BufferedReader(fileReader);

			while((line = bufferedReader.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					if (isQ) {
						q = line;
						isQ = false;
					} else {
						a = line;
						add(q,a);
						isQ = true;
					}
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
