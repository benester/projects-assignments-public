package Iv1350.kth.pos.integration;

/**
 * Will be thrown if an item id is scanned, that does not exist.
 */
public class InvalidItemIDException extends Exception{
    /**
     * constructor for the exception
     * @param msg the exception message
     */

    public InvalidItemIDException(String msg){
        super(msg);
    }
}
