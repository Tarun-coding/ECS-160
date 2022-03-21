package hw1;

public interface Subject {
	// TODO HW1 P3
	void attach(Observer ob); //adding observers to list
	void detach(Observer ob); //deleting observers from list 
	void notifyObservers();   //notifying observers to call update(this) when the book state changes
	String getStateName(); //returns the string of the current state the book is in 
}
