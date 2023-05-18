# How to Compile and Launch the server.Server and client.Client Applications
To compile and launch the server and client applications, follow these steps:

1. Set up your development environment:
   - Ensure you have Java Development Kit (JDK) installed on your machine. You can download it from the Oracle website or use OpenJDK, which is an open-source alternative. 
   - Install a Java Integrated Development Environment (IDE) such as Eclipse, IntelliJ IDEA, or NetBeans. This step is optional but recommended for easier development and debugging.
2. Create the source files:
   - Open a text editor or your preferred IDE. 
   - Copy and paste the server code into a file named server.Server.java. 
   - Copy and paste the client code into a file named client.Client.java.
3. Compile the source files:
   - Open a command prompt or terminal.
   - Navigate to the directory where you saved the source files.
     - Compile the server and client files separately using the following commands:
```javac server.Server.java```
```javac client.Client.java```
4. Launch the server:
   - In the same command prompt or terminal, while still in the directory containing the compiled files, run the following command:
```java server.Server```
   - The server application will start and display the message "server.Server started. Listening on port 5000..." indicating it's listening for client connections.
5. Launch the client:
   - Open a new command prompt or terminal.
   - Navigate to the directory containing the compiled files.
   - Run the following command:
```java client.Client```
