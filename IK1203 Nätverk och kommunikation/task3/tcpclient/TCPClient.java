package tcpclient;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.io.*;
/**
 * A tcp client that connects to a host given its hostname and a port. 
 * Can either establish a connection and print a result, or establish a connection, send some data then print a result.
 * @author Benjamin Gafvelin
 * @version 2.0
 * @since 1.0
 */
public class TCPClient {
    public static String askServer(String hostname, int port, String ToServer) throws  IOException {
        //Init a String variable to store the response from the servers in
        String stringResponse = "";
        //If we only have two arguments, call the other askServer method
        if(ToServer == null){
            stringResponse = askServer(hostname, port);
        }
        //If we have three arguments, go here
        else{
            //try-catch block, to handle any eventual exception that might be thrown. 
            try{
                //In case the user did not add a newline character. to avoid error 143
                ToServer += '\n';
                //A byte array to convert the string we are to send to the server
                byte [] toServer = ToServer.getBytes(StandardCharsets.UTF_8);
                //Open a socket connection with the server, at the given adress & port number
                Socket socket = new Socket(hostname, port);
                socket.setSoTimeout(10000);
                //Create a output stream, to be able to send the arguments to the server
                OutputStream out = socket.getOutputStream();
                //Send the data to the server
                out.write(toServer);
                //Create an inputstream to be able to read data that the server sends back
                InputStream in = socket.getInputStream();
                //Create a buffer of size 2048 bytes
                byte[] responsArray = new byte[2048];
                //Read the respons, save the legnth in variable lengthOfInput
                int lengthOfInput = in.read(responsArray);
                //Convert the byte array to a String
                stringResponse = new String(responsArray,0, lengthOfInput, StandardCharsets.UTF_8);
                //Close the connection to the server
                socket.close();
            }
            //If an exception is thrown, catch it here
            catch(IOException e){
                throw e;
            }
        }
        return stringResponse;
    }
    //Method overloading
    public static String askServer(String hostname, int port) throws  IOException {
        //Init a String to store the returned data from the server.
        String response = "";
        //Try-catch block to handle any errors
        try{
            //Open a socketconnection with the server
            Socket socket = new Socket(hostname, port);
            //Create an Inputstream to be able to read the respons from the server
            InputStream in = socket.getInputStream();
            //Create a buffer of size 2048 bytes
            byte[] responsArray = new byte[2048];
            //Read the respons, save the legnth in variable lengthOfInput
            int lengthOfInput = in.read(responsArray);
            //Convert the byte array to a String
            response = new String(responsArray,0, lengthOfInput, StandardCharsets.UTF_8);
            //Close the socket connection to the server.
            socket.close();
        }
        //Handle any eventual exception
        catch(IOException e){
            throw e;
        }
        return response;
    }
}