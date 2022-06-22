package assessment.core;

/**
 * This is the web server’s main class viz. the entrypoint of the HTTP server
 *
 */

public class Main 
{
    public static void main( String[] args )
    {
        Session session = new Session();
        session.start(args);
    }
}
