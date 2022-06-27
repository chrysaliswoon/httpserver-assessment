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

    private Socket socket;
    private String server;
    private InputStream is;
    private ObjectInputStream ois;
    private OutputStream os;
    private ObjectOutputStream oos;
    private String httpResponse;

    public HttpClientConnection (String server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
    }

    public void run() {
        try {
            start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void start() throws IOException {
        System.out.println("Loading contents of URL: " + server);

        //? Create input and output streams to read from and write to the server
        PrintStream out = new PrintStream(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        //? Read data from server
        String line = in.readLine();
        System.out.println(line);

        while (line != null) {
            if (line.contains("GET")) {
                String test = 
                "<h1>Test</h1>"
                ;
                httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + test;
                socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
            } else {
                httpResponse = "HTTP/1.1 405 Method Not Allowed\r\n\r\n";
            }
        }

        //? Close the streams
        in.close();
        out.close();
        socket.close();
    }

    private String read() throws IOException {
        return ois.readUTF();
    }

    private void write(String outputstream) throws IOException {
        oos.writeUTF(outputstream);
        oos.flush();
    }

    private void initializeStreams(Socket socket) throws IOException {
        is = socket.getInputStream();
        ois = new ObjectInputStream(is);
        os = socket.getOutputStream();
        oos = new ObjectOutputStream(os);
    }

    private void close() throws IOException {
        is.close();
        os.close();
    }
}
