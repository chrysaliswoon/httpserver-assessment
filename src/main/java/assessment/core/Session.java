package assessment.core;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * This is connected to the HttpServer
 */

public class Session {

    public static Integer PORT;
    public static String startServerMsg = "Web Server is starting up, listening at port " + PORT + ". \n";
    public static String accessServerMsg = "You can access http://localhost:" + PORT + " now.";

    public void start(String[] args) {
        HttpServer server = new HttpServer();
        
        switch (args.length) {
            case 0:
                PORT = 3000;
                server.startServer(PORT);
                break;

            case 2:
                if (args[0].contains("--port")) {
                    PORT = Integer.parseInt(args[1]);
                    server.startServer(PORT);
                }
                break;

        }
    }
}
