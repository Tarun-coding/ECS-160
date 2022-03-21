package hw1;
import java.util.HashMap;

// TODO HW1 P3

public class SourceObserver implements Observer {
	private String name;
	private HashMap<Subject, String> subjectToPastStateName;//each subject map to the state that the source observer thinks it is in
	LibraryLogger loggerInstance;
	public SourceObserver(String n) {
		// TODO?
		name=n;
		subjectToPastStateName=new HashMap<Subject,String>();
		loggerInstance=LibraryLogger.getInstance();
	}

	@Override
	public void update(Subject updatedSubject) {
		// TODO?
		//1.you will check for the subject is in the map. 
		//2. You will check if the subject exists. 
		//If the state is null(initially initialized): log the subject is leaving state UNOBSERVED
		//else log subject leaving state (map value for subject)
		//Then we change the map value to the currentState in the Subject
		if(subjectToPastStateName.containsKey(updatedSubject)){
			loggerInstance.writeLine(name+" OBSERVED "+updatedSubject.toString()+" LEAVING STATE: "+subjectToPastStateName.get(updatedSubject));
		}else{
			loggerInstance.writeLine(name+" OBSERVED "+updatedSubject.toString()+" LEAVING STATE: UNOBSERVED");
		}
		subjectToPastStateName.put(updatedSubject,updatedSubject.getStateName());


	}

	@Override
	public String toString() {
		return name;
	}
}
