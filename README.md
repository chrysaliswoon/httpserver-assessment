## Assessment Tasks

### Task 1 - Completed

- [X] Generate a Maven project
- [X] Create a remote git repo.
- [X] Add link to the Maven project to a remote git repo
- [X] Perform an initial commit and push of your project

### Task 2 - Completed

**HTTP Folder**

- [X] Main: Web server's main class
- [X] HttpServer: Main web server class
- [X] HttpClientConnection: Class handles the request and response comm. between the server and the client (browser)

**Root**
- [X] Main: Starting class to execute the HTTP server with the 'java -jar' option

### Task 3 - Completed

Web server should accept the following command line options when starting:

- if port is not specified, it will default to port 3000
  
```
--port <port number>
```

- one or more directories where the HTML, CSS and JS files and images are stored. If not specified, default to static directory in the current path
  
```
--docRoot <colon delimited list of directories>
```

- [X] start the server on port 3000; docRoot directory is ./target

```
java -cp ./myserver.jar 
```

- [X] start the server on port 8080; docRoot directory is ./target

```
java -cp ./myserver.jar --port 8080
```


- [X] start the server on port 3000; the docRoot directories will be ./target and /opt/tmp/www

```
 java -cp ./myserver.jar --docRoot ./target:/opt/tmp/www
```


- [X] start the server on port 8080; docRoot directories will be ./target and /opt/tmp/www

```
 java -cp ./myserver.jar --port 8080 --docRoot 
 ./target:/opt/tmp/www 
```


### Task 4 - Completed

When HTTP server starts, perform the following:

- [X] Open a TCP connection and listen on the port from the port option
- [X] Check each of the docRoot path; for each path verify that
  - [X] path exists
  - [X] path is a directory
  - [X] path is readable by the server

- [X] If any of the above conditions fail, print the failure reason on the console, stop the server and exit the program with System.exit(1).

### Task 5 - Completed

- [X] Create a thread pool with 3 threads
- [X] Server will listen on the specified port
- [X] Server will accept incoming connections from the browser
- [X] When new connection is established, this connection should be handled by a thread from the threadpool.
- [X] Main control thread (server) should go back to waiting for new incoming connections


### Task 6

Client thread (handling the client connection) should perform the following tasks:
- [] Read the first line from the incoming request and perfom one of the following actions:

**Action 1 - Not a GET Method**

- [X] If the request method is NOT a GET method, it will send the following response back to the client:
```
HTTP/1.1 405 Method Not Allowed \r\n
\r\n
<method name> not supported\r\n
```
- [] Close the connection and exit the thread 

**Action 2 - Resources does not exist**

- [] If the requested resource is not found, send the following back to the client:

```
HTTP/1.1 404 Not Found\r\n
\r\n
<resource name> not found\r\n
```

- [] Close the connection and exit the thread 
- [] If resource name is /, replace it with /index.html before performing the file search

**Action 3 - Resource exist**

- [] If resource is found in any of the docRoot directories, send the resource contents as bytes back to the client in the following response

```
HTTP/1.1 200 OK\r\n
\r\n
<resource contents as bytes>
```

- [] Close the connection and exit the thread 

**Action 4 - Resources exist and is a PNG image**

- [] If the resource exits and the name ends with a .png suffix, then the resource is a PNG image. Send the following response back to the client

```
HTTP/1.1 200 OK\r\n
Content-Type: image/png\r\n
\r\n
<resource contents as bytes>
```

- [] Close the connection and exit the thread 


### Task 7

- [X] Create directory called static at the root of project folder

- [X] Write a HTML document called index.html with the following content:
  - [X] Any PNG image. This PNG image must be in the static folder. Reference the image as <img href="/mypic.png"> 
  - [X] Header line (h1) with any text
  - [X] Link to another HTML document in static directory
  - [X] Any text resources (e.g. CSS, JS) referenced by index.html should also be placed in the static directory
  - [X] Image, text and link must be positioned in the center (horizontally and vertically) of the browser's viewport(window)
  - [X] Image should remain at the center when the browser's window is resized
  
- [] Run HTTP server and test if can access the HTML document from the browser:
  - [] http://localhost:3000
  - [] http://localhost:3000/index.html



## ServerSocket API
The ServerSocketclass is used to implement a server program. Here are the typical steps involve in developing a server program:
1. Create a server socket and bind it to a specific port number

2. Listen for a connection from the client and accept it. This results in a client socket is created for the connection.

3. Read data from the client via an InputStream obtained from the client socket.

4. Send data to the client via the client socket’s OutputStream.

5. Close the connection with the client.

The steps 3 and 4 can be repeated many times depending on the protocol agreed between the server and the client.
The steps 1 to 5 can be repeated for each new client. And each new connection should be handled by a separate thread.


## Breakdown of codes in class files

### Session

### HTTP Server
This is the Main Web Server class. It makes a ServerSocket and waits for Client requests. When it gets a Client request, the Server makes a new Socket connection to that Client. The Server makes a PrintWriter (using the Socket's output stream) and sends a message to the client.

**How it works**
1. The Server application makes a ServerSocket, on a specific port. This starts the server application to listen for Client requests coming in from that port number.
2. The Client then makes a Socket connection to the Server application using an IP address and TCP port. 
3. The Server makes a new Socket to communicate with this Client using an accept() method. 
   - The accept() method blocks (just sits there) while it's waiting for a Client Socket connection. 
   - When a Client finally tries to connect, the method returns a Socket (on a different part) that knows how to communicate with the client. (i.e. knows the Client's IP address and port number). 
   - The Socket is on a different port than the ServerSocket, so the ServerSocket can go back to waiting for other clients.

- Opens a TCP connection using the ServerSocket, and makes the server application "listen" for client requests on a specific port on the machine this code is running on.
```java
    ServerSocket server = new ServerSocket(PORT);
```

- Server goes into a permanent loop, waitinf for (and servicing) client requests
```java
    while (true) {
        .
        .
        .
    }

```

- The accept method blocks (just sits there) until a request comes in, and then the method returns a Socket (on a port) for communicating with the client
```java
    Socket socket = server.accept();
```

- We use the Socket connection to the client to make a PrintWriter and send it. 
- We then close the Socket as we are done with the client
```java
    PrintWriter writer = new PrintWriter(socket.getOutputStream());
    /*
    * Insert code to get info and print the info out
    */
    writer.close();
```


### HTTP Client Connection

- Makes a Socket connection to whatever is running on the PORT on the same host this code is running in, which in this case, is the localhost
```java
    Socket socket = new Socket(IP, PORT);
```

- Chain a BufferedReader to an InputStreamReader to the input stream from the Socket
```java
    InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
    BufferedReader reader = new BufferedReader(streamReader);
```

- 

### HTTP Writer



## Terminal Commands

```
- java -cp ./target/httpserver-1.0-SNAPSHOT.jar assessment.core.Main
```

### Resources:
- Head First Java Book
- https://www.codejava.net/java-se/networking/java-socket-server-examples-tcp-ip
- https://javarevisited.blogspot.com/2015/06/how-to-create-http-server-in-java-serversocket-example.html#axzz7WpITPG4F

