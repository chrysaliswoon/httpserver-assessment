package assessment.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/*
this is the main web server class
 */

public class HttpServer {

    public static Integer PORT;
    // public static String startServerMsg = "Web Server is starting up, listening at port " + PORT + ". \n";
    // public static String accessServerMsg = "You can access http://localhost:" + PORT + " now.";

    public void startServer(Integer PORT) {
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Web Server is starting up, listening at port " + PORT + ".");
            System.out.println("You can access http://localhost:" + PORT + " now.");

            while (true) {
                Socket socket = server.accept(); // Once a ServerSocket instance is created,
                System.out.printf("Connected to PORT: %d \n", PORT);

            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }
}
