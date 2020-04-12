package Iv1350.kth.pos.controller;

/**
 * A more general exception for the viewer.
 */
public class DataBaseErrorException extends RuntimeException{
    /**
     * Constructor
     * @param msg message to show the user about the exception.
     */
    public DataBaseErrorException(String msg){
        super(msg);
    }
}
