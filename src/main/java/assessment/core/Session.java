package assessment.core;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * This is connected to the HttpServer class and has all of the logic for detecting the args
 */

public class Session {

    public static Integer PORT;
    
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
