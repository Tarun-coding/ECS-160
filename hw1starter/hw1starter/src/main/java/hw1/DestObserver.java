package hw1;

// TODO HW1 P3
// the dest observer is far simpler since all I'm doing is logging one specific line
public class DestObserver implements Observer{
    LibraryLogger loggerInstance;
    private String name;

	public DestObserver(String n) {
		// TODO?
		name=n;
		loggerInstance=LibraryLogger.getInstance();
	}
    @Override
    public void update(Subject updatedSubject) {
        // TODO Auto-generated method stub
        loggerInstance.writeLine(name+" OBSERVED "+updatedSubject.toString()+" REACHING STATE: "+updatedSubject.getStateName());        
    }
    public String toString() {
		return name;
	}

}
