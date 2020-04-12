import java.net.*;
import java.io.*;
/**
 * @author Benjamin Gafvelin
 * @version 1.0
 */
public class ConcHTTPAsk {
    public static void main( String[] args) {
        try{
            //Get the port from the first commandline argument provided when running "java HTTPEcho <argument>"
            int port = Integer.parseInt(args[0]);
            ServerSocket serverSocket = new ServerSocket(port);
            while(true){
                Socket clientSocket = serverSocket.accept();
                MyRunnable r = new MyRunnable(clientSocket);
                new Thread(r).start();
            }
        }
        catch(IOException e ){
            System.out.println(e);
        }
    }
}