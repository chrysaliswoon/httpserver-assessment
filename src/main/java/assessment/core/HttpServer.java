package assessment.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
this is the main web server class
 */

public class HttpServer {

    private String IP = "localhost";
    private Integer PORT;
    private String docRoot;

    public HttpServer(String docRoot, Integer PORT) {
        this.docRoot = docRoot;
        this.PORT = PORT;
    }

    public void start() {
        FileRepo files = new FileRepo(docRoot, PORT);
        files.checkFile();
        ServerSocket server;
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        
        try {
            server = new ServerSocket(PORT);
            System.out.printf("Web Server is starting up on " + IP + ", listening at port " + PORT + ". \n");
            System.out.printf("You can access http://" + IP + ":" + PORT + " now. \n");

            while (true) {
                Socket clientSocket = server.accept(); // Once a ServerSocket instance is created,
                System.out.printf("Connected to PORT: %d \n", PORT);
                String req = "";

                HttpClientConnection clientConnection = new HttpClientConnection(req, clientSocket);
                threadPool.execute(clientConnection);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
