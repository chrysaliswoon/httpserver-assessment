package assessment.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.http.HttpResponse;
import java.util.Date;

/**
 * this class handles the the request and
response communication between the server and a client (browser)
 */

public class HttpClientConnection implements Runnable{

    private Socket clientSocket;
    private String clientRequest;
    private InputStream is;
    private ObjectInputStream ois;
    private OutputStream os;
    private ObjectOutputStream oos;
    private String httpResponse;

    public HttpClientConnection (String clientRequest, Socket clientSocket) throws IOException {
        this.clientRequest = clientRequest;
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
        System.out.println("Received a connection from server");

        //? Create input and output streams to read from and write to the server
        PrintStream out = new PrintStream(clientSocket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


        //? Read data from server
        String line = in.readLine();

        while (line != null) {
            if (line.contains("GET")) {
                String test = 
                "<h1>Test</h1>"
                ;
                // line = in.readLine();
                httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + test;
                clientSocket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
            } else {
                httpResponse = "HTTP/1.1 405 Method Not Allowed\r\n\r\n";
            }
        }

        //? Close the streams
        in.close();
        out.close();
        clientSocket.close();
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
