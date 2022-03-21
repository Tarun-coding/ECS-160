package hw1;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class StudentTest_916065016 {
    @Test
    public void loggiInformationForMultipleObservers() //two source observers(observing the same subject) must log the same exact information when the LibraryBook(the subject) 
    //calls update.
    {
        //making  sure there is no lines in the library logger
        LibraryLogger.getInstance().clearWriteLog();

        //two source observers(observing the same subject) must log the same exact information when the LibraryBook(the subject) 
        //calls update.

        //creating a libraryBook(subject)
        LibraryBook book = new LibraryBook("Seven Eves");
        SourceObserver sourceObserverOne = new SourceObserver("SrcObserverOne");
        SourceObserver sourceObserverTwo=new SourceObserver("SrcObserverTwo");

        book.attach(sourceObserverOne);
        book.attach(sourceObserverTwo);
        
        //now since both observers are watching the same book, they must be updated with the same information
        book.issue();
        book.returnIt();
        book.shelf();

        //Even if the observer might be detached, since the information it has about the subject still stays intact. If we reattach it
        //before calling any new updates, it should behave such as it never detached.
        book.detach(sourceObserverTwo);
        book.attach(sourceObserverTwo);

        book.issue(); //testing our above theory with this action: both observers must
        assertArrayEquals(
			new String [] {
                "SrcObserverOne is now watching Seven Eves",
                "SrcObserverTwo is now watching Seven Eves",
                "Leaving State OnShelf for State Borrowed",
                "SrcObserverOne OBSERVED Seven Eves LEAVING STATE: UNOBSERVED",
                "SrcObserverTwo OBSERVED Seven Eves LEAVING STATE: UNOBSERVED",
                "Leaving State Borrowed for State GotBack",
                "SrcObserverOne OBSERVED Seven Eves LEAVING STATE: Borrowed",
                "SrcObserverTwo OBSERVED Seven Eves LEAVING STATE: Borrowed",
                "Leaving State GotBack for State OnShelf",
                "SrcObserverOne OBSERVED Seven Eves LEAVING STATE: GotBack",
                "SrcObserverTwo OBSERVED Seven Eves LEAVING STATE: GotBack",
                "SrcObserverTwo is no longer watching Seven Eves",
                "SrcObserverTwo is now watching Seven Eves",
                "Leaving State OnShelf for State Borrowed",
                "SrcObserverOne OBSERVED Seven Eves LEAVING STATE: OnShelf",
                "SrcObserverTwo OBSERVED Seven Eves LEAVING STATE: OnShelf"





			}, 
			LibraryLogger.getInstance().getWrittenLines()
		);





    }

}
