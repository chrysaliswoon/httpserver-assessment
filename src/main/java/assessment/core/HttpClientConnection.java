package assessment.core;

import java.io.BufferedReader;
import java.io.File;
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
import java.util.Optional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * this class handles the the request and
 * response communication between the server and a client (browser)
 */

public class HttpClientConnection implements Runnable {

    private Socket socket;
    private String docRoot;
    // private InputStream is;
    // private ObjectInputStream ois;
    // private OutputStream os;
    // private ObjectOutputStream oos;
    private String line;
    private String httpResponse;
    private String URL;
    private String[] lineArr;
    private String path;
    private String content;
    private String method;
    private BufferedImage bi;

    public HttpClientConnection(String docRoot, Socket socket) throws IOException {
        this.docRoot = docRoot;
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

        // System.out.println("Loading contents of path: " + docRoot);

        // ? Create input and output streams to read from and write to the server
        PrintStream out = new PrintStream(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // ImageIO.write(bi, "png", socket.getOutputStream());
        // BufferedImage image = null;
        // File inputImage = new File(path);
        // image = ImageIO.read(inputImage);

        // ? Read data from server
        line = in.readLine();
        lineArr = null; // ? Declare empty string array
        lineArr = line.split(" "); // ? Convert using String.split() method

        // while (line != null) {
            System.out.println(line);
            line = in.readLine();
            method = lineArr[0];
            

            if (method.equals("GET")) {
                if (lineArr[1].equals("/")) {
                    URL = lineArr[1].replace("/", "/index.html");
                } else {
                    URL = lineArr[1];
                }
                path = docRoot + URL;
                String fileType = "";
                int i = URL.lastIndexOf('.');
                if (i > 0) {
                    fileType = URL.substring(i + 1);
                }
                if (fileType.equals("png")) {
                    byte[] imageContent = Files.readAllBytes(Paths.get(path));
                    httpResponse = "HTTP/1.1 200 OK\r\n" + "Content-Type: image/png\r\n\r\n";
                    socket.getOutputStream().write(httpResponse.getBytes());
                    socket.getOutputStream().write(imageContent);


                } else if (fileType.equals("html") || fileType.equals("css")){
                    content = Files.readString(Paths.get(path));
                    httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + content;
                    socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
                } else {
                    httpResponse = "HTTP/1.1 404 Not Found\r\n\r\n" + path + " not found\r\n";
                    socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
                }

            } else {
                httpResponse = "HTTP/1.1 405 METHOD\r\n\r\n" + method + " not supported\r\n";
                socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
            }
        // }

        // ? Close the streams
        in.close();
        out.close();
        socket.close();

    }

    // private String read() throws IOException {
    //     return ois.readUTF();
    // }

    // private void write(String outputstream) throws IOException {
    //     oos.writeUTF(outputstream);
    //     oos.flush();
    // }

    // private void initializeStreams(Socket socket) throws IOException {
    //     is = socket.getInputStream();
    //     ois = new ObjectInputStream(is);
    //     os = socket.getOutputStream();
    //     oos = new ObjectOutputStream(os);
    // }

    // private void close() throws IOException {
    //     is.close();
    //     os.close();
    // }
}
