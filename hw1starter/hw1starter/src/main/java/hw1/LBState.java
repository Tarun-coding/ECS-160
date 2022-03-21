package hw1;

// TODO HW1 P2
public interface LBState{
    
    //These are all the functions for each state
    LBState shelf();
    LBState issue();
    LBState extend();
    LBState returnIt(); 

    //returning the string name of the state
    String toString();




}