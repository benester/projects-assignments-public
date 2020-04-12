import java.net.*;
import java.io.*;
import tcpclient.TCPClient;
import java.nio.charset.StandardCharsets;

/**
 * This program is a HTTP ask server. If it's given a correct HTTP request, with a query string it will make a tcp requeust to the the specified server and return the result.
 * @author Benjamin Gafvelin
 * @version 1.0
 * @since 1.0
 */
public class HTTPAsk {
    public static void main( String[] args) {
        try{
            //Get the port from the first commandline argument provided when running "java HTTPEcho <argument>"
            int port = Integer.parseInt(args[0]);

            //Create a headerline for when a connection is made.
            try{
                //Open the socket on the specified port
                ServerSocket serverSocket = new ServerSocket(port);
                while(serverSocket.isBound()){

                    //Create a headerline for when a connection is made.
                    String httpHeaders = "HTTP/1.1 200 OK\r\nContent-Type: text/plain; charset=utf-8\r\n\r\n";

                    //Accept incomming connection, and set up in/output streams.
                    Socket clientSocket = serverSocket.accept();
                    //Create bufferedReader/Writer for I/O
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    OutputStreamWriter out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
                    //Create empty string
                    String httpRequest = "";
                    //Read the first line from the connection
                    String line = in.readLine();
                    //Continue to read and append lines from the bufferedreader until an empty line appears.
                    while(!line.isEmpty())
                    {
                        httpRequest += (line + "\r\n");
                        line = in.readLine(); 
                    }
                    String tcpAskResponse = "";
                    //Good request?
                    if(httpRequest.contains("GET /ask?hostname=") && httpRequest.contains("port=") && httpRequest.indexOf("/ask?") == 4 && httpRequest.indexOf("hostname=") == 9)
                    {
                        String cmdUrl = httpRequest.split("GET ", 0)[1].split(" HTTP", 0)[0];
                        String query = cmdUrl.split("ask\\?")[1];
                        
                        String destHost = query.split("hostname=")[1].split("&")[0];
                        int destPort = Integer.parseInt(query.split("port=")[1].split("&")[0]);
                        String destString = "";
                        
                        if(query.contains("string=")){
                            destString = URLDecoder.decode(query.split("string=",0)[1].split("&")[0], "UTF-8");
                        }
                        try{
                            tcpAskResponse = TCPClient.askServer(destHost, destPort, destString);
                        }
                        catch(UnknownHostException e){
                            tcpAskResponse = "Could not resolve: " + destHost;
                        }
                        catch(ConnectException e){
                            httpHeaders="HTTP/1.1 408 Request Timeout";
                        }
                    }
                    else if(httpRequest.indexOf("/") == 4 && !httpRequest.subSequence(5, 8).equals("ask")){
                        httpHeaders = "HTTP/1.1 404 Not Found";
                    }
                    else{
                        httpHeaders = "HTTP/1.1 400 Bad Request \r\n\r\n";
                    }
                    
                    out.write(httpHeaders + tcpAskResponse);
                    
                    out.flush();
                    in.close();
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