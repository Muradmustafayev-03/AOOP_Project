package server;

import classes.Assignment;
import classes.Destination;
import classes.Student;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private final List<Destination> destinations = getDestinations();
    private List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("server.Server started. Listening on port 5000...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void addStudent(Student student) {
        students.add(student);
    }

    private synchronized List<Assignment> generateAssignments() {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(students, destinations, 100, 0.7);
        return geneticAlgorithm.run(100);
    }

    private synchronized List<Destination> getDestinations() {
        List<Destination> destinations = new ArrayList<>();

        destinations.add(new Destination("Paris", 5));
        destinations.add(new Destination("Strasbourg", 6));
        destinations.add(new Destination("Zurich", 4));
        destinations.add(new Destination("CERN", 4));
        destinations.add(new Destination("Oxford", 3));
        destinations.add(new Destination("Cambridge", 4));
        destinations.add(new Destination("Berlin", 5));
        destinations.add(new Destination("Munich", 4));
        destinations.add(new Destination("New York", 2));
        destinations.add(new Destination("Boston", 3));

        return destinations;
    }

    private class ClientHandler extends Thread {
        private final Socket clientSocket;
        private ObjectInputStream inputStream;
        private ObjectOutputStream outputStream;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                inputStream = new ObjectInputStream(clientSocket.getInputStream());

                // Send the list of destinations to the client
                outputStream.writeObject(destinations);
                outputStream.flush();

                // Receive the student object from the client
                Student student = (Student) inputStream.readObject();

                // Add the student to the list
                addStudent(student);

                // Handle assignment request from the client
                while (true) {
                    try {
                        Object request = inputStream.readObject();
                        if (request instanceof String && request.equals("ASSIGNMENT_REQUEST")) {
                            // Generate assignments
                            List<Assignment> assignments = generateAssignments();

                            // Send assignments to the client
                            outputStream.writeObject(assignments);
                            outputStream.flush();
                        }
                    }
                    catch (EOFException e) {break;}
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) inputStream.close();
                    if (outputStream != null) outputStream.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
