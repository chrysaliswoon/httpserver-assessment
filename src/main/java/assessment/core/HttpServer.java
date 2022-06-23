package assessment.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
this is the main web server class
 */

public class HttpServer {

    public static Integer PORT;
    public static String IP = "localhost";

    public void startServer(Integer PORT) {
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.printf("Web Server is starting up on " + IP + ", listening at port " + PORT + ". \n");
            System.out.printf("You can access http://" + IP + ":" + PORT + " now. \n");

            while (true) {
                Socket socket = server.accept(); // Once a ServerSocket instance is created,
                System.out.printf("Connected to PORT: %d \n", PORT);

                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                /*
                 * Insert code to get info and print the info out
                 */
                writer.close();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
