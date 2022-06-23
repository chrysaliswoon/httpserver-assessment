package assessment.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * this class handles the the request and
response communication between the server and a client (browser)
 */

public class HttpClientConnection implements Runnable{

    private Socket clientSocket;
    private InputStream is;
    private ObjectInputStream ois;
    private OutputStream os;
    private ObjectOutputStream oos;

    public HttpClientConnection (Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void start() throws IOException {

        /*Insert code for what the threads will do - Task 6*/
    }

    private String read() throws IOException {
        return ois.readUTF();
    }

    private void write(String outputstream) throws IOException {
        oos.writeUTF(outputstream);
        oos.flush();
    }

    private void initializeStreams(Socket clientSocket) throws IOException {
        is = clientSocket.getInputStream();
        ois = new ObjectInputStream(is);
        os = clientSocket.getOutputStream();
        oos = new ObjectOutputStream(os);
    }

    private void close() throws IOException {
        is.close();
        os.close();
    }
}
