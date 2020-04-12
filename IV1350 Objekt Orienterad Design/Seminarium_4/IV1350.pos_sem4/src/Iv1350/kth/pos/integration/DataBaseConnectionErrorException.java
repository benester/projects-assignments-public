package Iv1350.kth.pos.integration;

/**
 * Will be thrown if something goes wrong with the connection to the database (Simulated)
 */
public class DataBaseConnectionErrorException extends RuntimeException {
    /**
     * Constructor for exception
     * @param msg the exception message that will be logged
     */
    public DataBaseConnectionErrorException(String msg){
        super(msg);
    }
}
