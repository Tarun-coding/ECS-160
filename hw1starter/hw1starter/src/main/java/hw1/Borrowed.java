package hw1;

// TODO HW1 P2
public class Borrowed implements LBState{
    private static Borrowed firstInstance=null;
    private Borrowed(){
        //This contains all the member variables that borrowed is going to need 
    }
    public static Borrowed getInstance(){
        if(firstInstance==null){
            firstInstance=new Borrowed(); //lazy instansiation meaning:
                                               //if the instance isnt needed it isnt created
           // ExpensiveComputeToy.performExpensiveLogSetup();
        }
        return firstInstance;
    }

    

    @Override
    public LBState shelf() {
        // TODO Auto-generated method stub
        //exception handling
        throw new BadOperationException("BadOperation");
        
           
                
        
    }

    @Override
    public LBState issue() {
        // TODO Auto-generated method stub
        //exception handling
        //return null;
        throw new BadOperationException("BadOperation");



        
    }

    @Override
    public LBState extend() {
        // TODO Auto-generated method stub
        (LibraryLogger.getInstance()).writeLine("Leaving State Borrowed for State Borrowed");
        return Borrowed.getInstance();
        
    }

    @Override
    public LBState returnIt() {
        // TODO Auto-generated method stub
        (LibraryLogger.getInstance()).writeLine("Leaving State Borrowed for State GotBack");
        return GotBack.getInstance();
    }
    public String toString(){
        return "Borrowed";
    }
    
}