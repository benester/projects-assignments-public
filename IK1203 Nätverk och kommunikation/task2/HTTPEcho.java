import java.net.*;
import java.io.*;
/**
 * This is an HTTP Echo server, that will respond with 200 OK, followed by the users HTTP request. 
 * @author Benjamin Gafvelin 
 * @version 2.0
 * @since 1.0
 */
public class HTTPEcho {
    public static void main( String[] args) {
        try{
            //Get the port from the first commandline argument provided when running "java HTTPEcho <argument>"
            int port = Integer.parseInt(args[0]);

            //Create a headerline for when a connection is made.
            String httpHeaders = "HTTP/1.1 200 OK\r\n\r\n";
            try{
                //Open the socket on the specified port
                ServerSocket serverSocket = new ServerSocket(port);
                while(serverSocket.isBound()){
                    //Accept incomming connection, and set up in/output streams.
                    Socket clientSocket = serverSocket.accept();
                    //Create bufferedReader/Writer for I/O
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    //Create empty string
                    String httpRequest = "";
                    //Read the first line from the connection
                    String line = in.readLine();
                    //Continue to read and append lines from the bufferedreader until an empty line appears.
                    while(!line.isEmpty()){
                        httpRequest += (line + "\r\n");
                        line = in.readLine(); 
                    }
                    //Send the users request back to the user with a correct HTTP header
                    out.write(httpHeaders + httpRequest);
                    out.flush();
                    //in.close();
                    out.close();
                    //Close the client connection 
                    clientSocket.close();
                }
                //Close the serverSocket
                serverSocket.close();
            }
            catch(IOException ex){
                System.out.println(ex);
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
}