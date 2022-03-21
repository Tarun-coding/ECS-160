
package hw1;
import java.util.ArrayList;
// TODO HW1 P2

public class LibraryBook implements Subject {
    //                   ^ uncomment for P3
    private String name; //This is the name of the book
    private ArrayList<Observer> observers;//keeping an array of observers 
    LibraryLogger loggerInstance;
    // TODO? Are other instance variables needed for this pattern?

    //all other instance variables are going to be the states that we can be in
    private LBState currentState;
    //having an arrayList of observers

    
    
    public LibraryBook(String name) {
        // TODO HW1 P2
        this.name=name;
        currentState=OnShelf.getInstance();
        observers=new ArrayList<Observer>();
        loggerInstance=LibraryLogger.getInstance();
        
    }
    public LBState getState(){
        return currentState;
    }
    
   
    
    public void returnIt() {
        // TODO?
    
        try {
           LBState nextState=currentState.returnIt();
           currentState=nextState;
           notifyObservers();
            
        } catch (BadOperationException e) {
            //TODO: handle exception
            //logging
            //LibraryLogger loggerInstance=LibraryLogger.getInstance();
            loggerInstance.writeLine("BadOperationException - Can't use returnIt in "+getStateName()+" state");


        }
        
    }
    
    public void shelf() {
        // TODO?
        try {
            LBState nextState=currentState.shelf();
            currentState=nextState;
             notifyObservers();
         } catch (BadOperationException e) {
             //TODO: handle exception
             //logging
             //LibraryLogger loggerInstance=LibraryLogger.getInstance();
             loggerInstance.writeLine("BadOperationException - Can't use shelf in "+getStateName()+" state");
 
 
         }
    }
    
    public void extend() {
        // TODO?
        try {
            LBState nextState=currentState.extend();
            currentState=nextState;
             notifyObservers();
         } catch (BadOperationException e) {
             //TODO: handle exception
             //logging
             //LibraryLogger loggerInstance=LibraryLogger.getInstance();
             loggerInstance.writeLine("BadOperationException - Can't use extend in "+getStateName()+" state");
             //BadOperationException - Can't use returnIt in OnShelf state
 
         }
    }
    
    public void issue() {
        // TODO?
        try {
            LBState nextState=currentState.issue();
            currentState=nextState;
             notifyObservers();
         } catch (BadOperationException e) {
             //TODO: handle exception
             //logging
             //LibraryLogger loggerInstance=LibraryLogger.getInstance();
             loggerInstance.writeLine("BadOperationException - Can't use issue in "+currentState.toString()+" state");
 
 
         }
    }
    public String toString() {
        // TODO?
        return name;
    }
    @Override
    public void attach(Observer newObserver) {
        // TODO Auto-generated method stub
        if(observers.contains(newObserver)){
            loggerInstance.writeLine(newObserver.toString()+" is already attached to "+name);
        }else{
            observers.add(newObserver);
            loggerInstance.writeLine(newObserver.toString()+" is now watching "+name);
        }
        
    }
    @Override
    public void detach(Observer deleteObserver) {
        // TODO Auto-generated method stub
        if(observers.contains(deleteObserver)){
            observers.remove(deleteObserver);
            loggerInstance.writeLine(deleteObserver.toString()+" is no longer watching "+name);
        }
        
    }
    @Override
    public void notifyObservers() {
        // TODO Auto-generated method stub
        for(Observer observer: observers){ //lopping through each observer
            observer.update(this);
        }
        
    }
    @Override
    public String getStateName() {
        // TODO Auto-generated method stub
        return currentState.toString();
    }
    
    
    
}