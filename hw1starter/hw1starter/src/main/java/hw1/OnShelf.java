package hw1;
// TODO HW1 P2
public class OnShelf implements LBState{
    private static OnShelf firstInstance=null;
    private OnShelf(){
        //This contains all the member variables that borrowed is going to need 
    }
    public static OnShelf getInstance(){
        if(firstInstance==null){
            firstInstance=new OnShelf(); //lazy instansiation meaning:
                                               //if the instance isnt needed it isnt created
           // ExpensiveComputeToy.performExpensiveLogSetup();
        }
        return firstInstance;
    }

    @Override
    public LBState shelf() {
        // TODO Auto-generated method stub
        //error handling
        //return null;
        throw new BadOperationException("BadOperation");

    }

    @Override
    public LBState issue() {
        // TODO Auto-generated method stub
        (LibraryLogger.getInstance()).writeLine("Leaving State OnShelf for State Borrowed");
        return Borrowed.getInstance();


        
    }

    @Override
    public LBState extend() {
        // TODO Auto-generated method stub
        //error handling
        //return null;
        throw new BadOperationException("BadOperation");

    }

    @Override
    public LBState returnIt() {
        // TODO Auto-generated method stub
        //error handling
        //return null;
        throw new BadOperationException("BadOperation");

    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "OnShelf";
    }
    
}