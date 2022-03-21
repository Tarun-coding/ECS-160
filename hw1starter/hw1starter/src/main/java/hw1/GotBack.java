package hw1;

// TODO HW1 P2
public class GotBack implements LBState{
    private static GotBack firstInstance=null;
    private GotBack(){
        //This contains all the member variables that borrowed is going to need 
    }
    public static GotBack getInstance(){
        if(firstInstance==null){
            firstInstance=new GotBack(); //lazy instansiation meaning:
                                               //if the instance isnt needed it isnt created
           // ExpensiveComputeToy.performExpensiveLogSetup();
        }
        return firstInstance;
    }

    @Override
    public LBState shelf() {
        // TODO Auto-generated method stub
        (LibraryLogger.getInstance()).writeLine("Leaving State GotBack for State OnShelf");
        return OnShelf.getInstance();    }

    @Override
    public LBState issue() {
        // TODO Auto-generated method stub
        throw new BadOperationException("BadOperation");
        //return null;
    }

    @Override
    public LBState extend() {
        // TODO Auto-generated method stub
        throw new BadOperationException("BadOperation");
        //return null;
    }

    @Override
    public LBState returnIt() {
        // TODO Auto-generated method stub
        throw new BadOperationException("BadOperation");
        //return null;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "GotBack";
    }
    
}