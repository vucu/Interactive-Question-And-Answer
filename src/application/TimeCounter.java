package application;

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
		time = 60;
		current = time;
	}
}
