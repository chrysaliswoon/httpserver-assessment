package assessment.core;

/**
 * This is the web server’s main class viz. the entrypoint of the HTTP server
 *
 */

public class Main {
    public static void main(String[] args)

    {
        String docRoot;
        Integer PORT;
        // HttpServer server;

        if (args.length == 0) {
            docRoot = "./target";
            PORT = 3000; 
        } else if (args.length == 2){
            docRoot = "./target";
            PORT = Integer.parseInt(args[1]);
        } else if (args.length > 0 && args[0].contains("--port")) {
            docRoot = args[2].replace("--docRoot", "");
            PORT = Integer.parseInt(args[1]);
        } else {
            docRoot = args[0].replace("--docRoot", "");
            PORT = 3000;
        }
        FileRepo files = new FileRepo(docRoot, PORT);
        files.checkFile();
    }
}
