package assessment.core;

import java.net.ServerSocket;

/*
this is the main web server class
 */

public class HttpServer {

    public static Integer PORT;
    // public static String startServerMsg = "Web Server is starting up, listening at port " + PORT + ". \n";
    // public static String accessServerMsg = "You can access http://localhost:" + PORT + " now.";

    public void startServer(Integer PORT) {
        try {
            // ? Set up the server socket and listen to a port number
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Web Server is starting up, listening at port " + PORT + ".");
            System.out.println("You can access http://localhost:" + PORT + " now.");

        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }
}
