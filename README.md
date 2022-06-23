## Assessment Tasks

### Task 1

- [X] Generate a Maven project
- [X] Create a remote git repo.
- [X] Add link to the Maven project to a remote git repo
- [X] Perform an initial commit and push of your project

### Task 2

**HTTP Folder**

- [X] Main: Web server's main class
- [X] HttpServer: Main web server class
- [X] HttpClientConnection: Class handles the request and response comm. between the server and the client (browser)

**Root**
- [X] Main: Starting class to execute the HTTP server with the 'java -jar' option

### Task 3

Web server should accept the following command line options when starting:

- [] if port is not specified, it will default to port 3000
  
```
--port <port number>
```

- [] one or more directories where the HTML, CSS and JS files and images are stored. If not specified, default to static directory in the current path
  
```
--docRoot <colon delimited list of directories>
```

- [] start the server on port 3000; docRoot directory is ./target

```
java -cp
```

- [] start the server on port 8080; docRoot directory is ./target

```
java -cp ./myserver.jar --port 8080
```


- [] start the server on port 3000; the docRoot directories will be ./target and /opt/tmp/www

```
 java -cp ./myserver.jar --docRoot ./target:/opt/tmp/www
```


- [] start the server on port 8080; docRoot directories will be ./target and /opt/tmp/www

```
 java -cp ./myserver.jar --port 8080 --docRoot 
 ./target:/opt/tmp/www 
```


### Task 4

When HTTP server starts, perform the following:

- [X] Open a TCP connection and listen on the port from the port option
- [] Check each of the docRoot path; for each path verify that
  - [] path exists
  - [] path is a directory
  - [] path is readable by the server

- [] If any of the above conditions fail, print the failure reason on the console, stop the server and exit the program with System.exit(1).

### Task 5

- [X] Create a thread pool with 3 threads
- [X] Server will listen on the specified port
- [X] Server will accept incoming connections from the browser
- [] When new connection is established, this connection should be handled by a thread from the threadpool.
- [] Main control thread (server) should go back to waiting for new incoming connections


### Task 6

Client thread (handling the client connection) should perform the following tasks:
- [] Read the first line from the incoming request and perfom one of the following actions:

**Action 1 - Not a GET Method**

- [] If the request method is NOT a GET method, it will send the following response back to the client:
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

- [] If resource is found in any of the docRoot directories, send the resource contens as bytes back to the client in the following response

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


## Terminal Commands

- java -cp ./target/httpserver-1.0-SNAPSHOT.jar assessment.core.Main

### Resources:
- https://www.codejava.net/java-se/networking/java-socket-server-examples-tcp-ip
- https://javarevisited.blogspot.com/2015/06/how-to-create-http-server-in-java-serversocket-example.html#axzz7WpITPG4F

