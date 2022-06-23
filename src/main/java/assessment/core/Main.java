package assessment.core;

/**
 * This is the web server’s main class viz. the entrypoint of the HTTP server
 *
 */

public class Main 
{
    public static void main( String[] args )
    
    {
        String docRoot = args[0];
        Integer PORT = Integer.parseInt(args[1]);

        HttpServer server = new HttpServer(docRoot, PORT);
        server.start();

        // switch (args.length) {
        //     case 0:
        //         PORT = 3000;
        //         server.start(PORT);
        //         break;

        //     case 2:
        //         if (args[0].contains("--port")) {
        //             PORT = Integer.parseInt(args[1]);
        //             server.start(PORT);
        //         }
        //         break;

        // }
    }
}
