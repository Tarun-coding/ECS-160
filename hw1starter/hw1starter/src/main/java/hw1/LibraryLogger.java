package hw1;
import java.util.ArrayList;

public class LibraryLogger {
    // TODO?
    
    private static LibraryLogger firstInstance=null;
    ArrayList<String> writtenLines;
    private LibraryLogger(){
        writtenLines=new ArrayList<String>();
    }

    public void writeLine(String line) {
        // TODO?
        System.out.println("LibraryLogger."+line+"\n");
        writtenLines.add(line);
    }

    public String[] getWrittenLines() {
        // TODO?
        //return null;
        String[] arrayOfWrittenLines = new String[writtenLines.size()];
        arrayOfWrittenLines = writtenLines.toArray(arrayOfWrittenLines); //The parameter is used to tell the type of the array
        return arrayOfWrittenLines;

    }

    public void clearWriteLog() {
        // TODO?
        writtenLines.clear();
    }
    
    public static LibraryLogger getInstance() {
        // TODO?
        //return null;
        if(firstInstance==null){
            firstInstance=new LibraryLogger(); //lazy instansiation meaning:
                                               //if the instance isnt needed it isnt created
            ExpensiveComputeToy.performExpensiveLogSetup();
        }
        return firstInstance;
    }
}
