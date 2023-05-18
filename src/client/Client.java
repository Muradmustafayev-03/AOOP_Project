package client;

import classes.Assignment;
import classes.Destination;
import classes.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private JFrame frame;
    private JTextField idField;
    private JTextField nameField;
    private DefaultListModel<Destination> availableDestinationsModel;
    private DefaultListModel<Destination> chosenDestinationsModel;
    private JButton submitButton;
    private JButton requestAssignmentsButton;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Client client = new Client();
                client.createAndShowGUI();
                client.connectToServer(5000);
            }
        });
    }

    public void createAndShowGUI() {
        frame = new JFrame("classes.Student client.Destination classes.Assignment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = createInputPanel();
        JPanel destinationPanel = createDestinationPanel();
        JPanel buttonPanel = createButtonPanel();

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(destinationPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        return inputPanel;
    }

    private JPanel createDestinationPanel() {
        JPanel destinationPanel = new JPanel();
        destinationPanel.setLayout(new GridLayout(1, 2));

        availableDestinationsModel = new DefaultListModel<>();
        JList<Destination> availableDestinationsList = new JList<>(availableDestinationsModel);
        JScrollPane availableDestinationsScrollPane = new JScrollPane(availableDestinationsList);

        chosenDestinationsModel = new DefaultListModel<>();
        JList<Destination> chosenDestinationsList = new JList<>(chosenDestinationsModel);
        JScrollPane chosenDestinationsScrollPane = new JScrollPane(chosenDestinationsList);

        destinationPanel.add(availableDestinationsScrollPane);
        destinationPanel.add(chosenDestinationsScrollPane);

        // Add mouse listeners to move destinations between lists
        availableDestinationsList.addMouseListener(new ListMouseListener(availableDestinationsList, chosenDestinationsModel));
        chosenDestinationsList.addMouseListener(new ListMouseListener(chosenDestinationsList, availableDestinationsModel));

        return destinationPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitButton.setEnabled(false);
                requestAssignmentsButton.setEnabled(true);

                // Create a student object
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                List<Destination> preferences = new ArrayList<>();
                for (int i = 0; i < chosenDestinationsModel.size(); i++) {
                    preferences.add(chosenDestinationsModel.getElementAt(i));
                }
                Student student = new Student(id, name, preferences);

                // Send the student object to the server
                try {
                    outputStream.writeObject(student);
                    outputStream.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        requestAssignmentsButton = new JButton("Request Assignments");
        requestAssignmentsButton.setEnabled(false);
        requestAssignmentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Request assignments from the server
                try {
                    outputStream.writeObject("ASSIGNMENT_REQUEST");
                    outputStream.flush();

                    // Receive assignments from the server
                    List<Assignment> assignments = (List<Assignment>) inputStream.readObject();

                    // Display assignments in a dialog
                    StringBuilder assignmentText = new StringBuilder();
                    for (Assignment assignment : assignments) {
                        assignmentText.append("classes.Student ID: ").append(assignment.student().getId())
                                .append(", Name: ").append(assignment.student().getName())
                                .append(", client.Destination: ").append(assignment.destination().name())
                                .append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, assignmentText.toString(), "Assignments", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        buttonPanel.add(submitButton);
        buttonPanel.add(requestAssignmentsButton);

        return buttonPanel;
    }

    private void connectToServer(int port) {
        try {
            Socket socket = new Socket("localhost", port);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            // Receive the list of destinations from the server
            List<Destination> destinations = (List<Destination>) inputStream.readObject();

            // Update the available destinations list
            availableDestinationsModel.clear();
            for (Destination destination : destinations) {
                availableDestinationsModel.addElement(destination);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private class ListMouseListener extends MouseAdapter {
        private JList<Destination> sourceList;
        private DefaultListModel<Destination> targetListModel;

        public ListMouseListener(JList<Destination> sourceList, DefaultListModel<Destination> targetListModel) {
            this.sourceList = sourceList;
            this.targetListModel = targetListModel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                Destination selectedDestination = sourceList.getSelectedValue();
                if (selectedDestination != null) {
                    sourceList.clearSelection();
                    targetListModel.addElement(selectedDestination);
                    sourceList.setModel(availableDestinationsModel);
                }
            }
        }
    }
}

