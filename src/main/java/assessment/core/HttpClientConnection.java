package assessment.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * this class handles the the request and
response communication between the server and a client (browser)
 */

public class HttpClientConnection {

    public static Integer PORT;
    public static String IP = "localhost";

    public void start() {
        try {
            Socket socket = new Socket(IP, PORT);
            
            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);

            String readDocument = reader.readLine();
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
