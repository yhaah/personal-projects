/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javagroupassignment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AdminEditWorkerProfile extends JFrame {
    private JButton addButton, cancelButton, viewButton, helloButton; 
    private JLabel nameLabel, idLabel, emailLabel, roleLabel, phoneLabel, passwordLabel, titleLabel; 
    private JTextField nameField, idField, emailField, roleField, phoneField, passwordField;
    private JTable profileTable;

    public AdminEditWorkerProfile() {
        setTitle("Admin Manage Worker Profile"); // Set the title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 450);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        setLayout(new GridLayout(0, 2, 10, 10));

        // Add the big label
        titleLabel = new JLabel("Admin Manage Workers Profiles");
        titleLabel.setFont(new Font("Century Gothic", Font.BOLD, 24)); // Set bold font
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center alignment
        add(titleLabel);
        add(new JLabel()); // Placeholder for the second column

        nameLabel = new JLabel("Name:");
        idLabel = new JLabel("ID:");
        emailLabel = new JLabel("Email:");
        roleLabel = new JLabel("Role:");
        phoneLabel = new JLabel("Phone:");
        passwordLabel = new JLabel("Password:");

        // Set font for labels
        Font labelFont = new Font("Future Lt BT", Font.BOLD, 17);
        nameLabel.setFont(labelFont);
        idLabel.setFont(labelFont);
        emailLabel.setFont(labelFont);
        roleLabel.setFont(labelFont);
        phoneLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);

        nameField = new JTextField();
        idField = new JTextField();
        emailField = new JTextField();
        roleField = new JTextField();
        phoneField = new JTextField();
        passwordField = new JTextField();

        addButton = new JButton("Add Worker");
        cancelButton = new JButton("Cancel");
        viewButton = new JButton("View all Profiles");
        helloButton = new JButton("Back to Admin Dashboard"); // Added helloButton

        // Set font for buttons
        Font buttonFont = new Font("Future Lt BT", Font.PLAIN, 14);
        addButton.setFont(buttonFont);
        cancelButton.setFont(buttonFont);
        viewButton.setFont(buttonFont);
        helloButton.setFont(buttonFont); // Set font for helloButton

        // Set button color
        Color buttonColor = new Color(0, 204, 255);
        addButton.setBackground(buttonColor);
        cancelButton.setBackground(buttonColor);
        viewButton.setBackground(buttonColor);
        helloButton.setBackground(buttonColor); // Set button color

        add(nameLabel);
        add(nameField);
        add(idLabel);
        add(idField);
        add(emailLabel);
        add(emailField);
        add(roleLabel);
        add(roleField);
        add(phoneLabel);
        add(phoneField);
        add(passwordLabel);
        add(passwordField);
        add(addButton);
        add(cancelButton);
        add(viewButton);
        add(helloButton); // Added helloButton

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addWorker();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewProfiles();
            }
        });

        helloButton.addActionListener(new ActionListener() { // ActionListener for helloButton
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AdminMainPage().setVisible(true);
            }
        });

        setVisible(true);
    }

    private void addWorker() {
    String name = nameField.getText();
    String id = idField.getText();
    String email = emailField.getText();
    String role = roleField.getText();
    String phone = phoneField.getText();
    String password = passwordField.getText();

    if (!name.isEmpty() && !id.isEmpty() && !email.isEmpty() && !role.isEmpty() && !phone.isEmpty() && !password.isEmpty()) {
        String data = name + "," + id + "," + email + "," + role + "," + phone + "," + password;
        String filePath = "D:\\google\\Java4444\\JavaGroupAssignment\\src\\main\\java\\com\\mycompany\\javagroupassignment\\profiles.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(data);
            writer.newLine();
            writer.flush();
            JOptionPane.showMessageDialog(this, "Worker added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error adding worker: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


private void viewProfiles() {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Name");
    model.addColumn("ID");
    model.addColumn("Email");
    model.addColumn("Role");
    model.addColumn("Phone");
    model.addColumn("Password");

    String filePath = "D:\\google\\Java4444\\JavaGroupAssignment\\src\\main\\java\\com\\mycompany\\javagroupassignment\\profiles.txt";
    
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length >= 6) {
                model.addRow(new Object[]{data[0], data[1], data[2], data[3], data[4], data[5]});
            }
        }
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error reading profiles.txt: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    profileTable = new JTable(model);

    JScrollPane scrollPane = new JScrollPane(profileTable);
    JButton deleteProfileButton = new JButton("Delete Profile");
    deleteProfileButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            deleteProfile();
        }
    });

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(deleteProfileButton);

    JPanel panel = new JPanel(new BorderLayout());
    panel.add(scrollPane, BorderLayout.CENTER);
    panel.add(buttonPanel, BorderLayout.SOUTH);

    JOptionPane.showMessageDialog(this, panel, "Profiles Data", JOptionPane.INFORMATION_MESSAGE);
}


private void deleteProfile() {
    int selectedRow = profileTable.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a profile to delete.", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        DefaultTableModel model = (DefaultTableModel) profileTable.getModel();
        model.removeRow(selectedRow);
        saveProfilesToFile(model);
    }
}

private void saveProfilesToFile(DefaultTableModel model) {
    String filePath = "D:\\google\\Java4444\\JavaGroupAssignment\\src\\main\\java\\com\\mycompany\\javagroupassignment\\profiles.txt";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
        int rowCount = model.getRowCount();
        if (rowCount > 0) {
            for (int i = 0; i < rowCount; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < model.getColumnCount(); j++) {
                    line.append(model.getValueAt(i, j));
                    if (j < model.getColumnCount() - 1) {
                        line.append(",");
                    }
                }
                writer.write(line.toString());
                writer.newLine();
            }
            writer.flush();
            JOptionPane.showMessageDialog(this, "Profile deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No profiles to save.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error deleting profile: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            new AdminEditWorkerProfile();
        }
    });
}
}
