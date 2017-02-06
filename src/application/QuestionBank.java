package application;

import java.util.ArrayList;

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
	
	// Add add question & answer here
	QuestionBank() {
		add("What is the first letter?", "a");
		add("What eats a mouse?", "cat");
		add("What do dogs eat?", "bone");
		add("What year is this?", "2017");
		add("What is the president name?", "Trump");
	}
}
