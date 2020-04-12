package Iv1350.kth.pos.controller;

/**
 * A more general exception to show the user
 */
public class NoItemWithIDException extends Exception {
    /**
     * Constructor
     * @param msg The message that will be shown to the user if this exception is thrown.
     */
    public NoItemWithIDException(String msg){
        super(msg);
    }
}
