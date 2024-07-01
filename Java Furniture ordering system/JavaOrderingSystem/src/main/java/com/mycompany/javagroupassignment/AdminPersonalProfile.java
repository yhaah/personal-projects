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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class AdminPersonalProfile extends JFrame {

    private JTextField usernameField;
    private JTextField idField; // Added ID field
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField phoneField;
    private JButton saveButton;
    private JButton viewButton;
    private JButton backButton;
    private JTable dataTable;

    public AdminPersonalProfile() {
        setTitle("Admin Personal Profile");
        setSize(730, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Admin Personal Profile");
        titleLabel.setFont(new Font("Futura", Font.BOLD, 27));
        titleLabel.setBounds(210, 30, 300, 30);
        panel.add(titleLabel);

        JLabel usernameLabel = new JLabel("Name:");
        usernameLabel.setBounds(150, 100, 140, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(290, 100, 250, 30);
        panel.add(usernameField);

        // Added ID label and text field
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(150, 150, 100, 25);
        panel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(290, 150, 250, 30);
        panel.add(idField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(150, 200, 100, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(290, 200, 250, 30);
        panel.add(passwordField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(150, 250, 100, 25);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(290, 250, 250, 30);
        panel.add(emailField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(150, 300, 100, 25);
        panel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(290, 300, 250, 30);
        panel.add(phoneField);

        saveButton = new JButton("Save");
        saveButton.setBounds(70, 370, 100, 50);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveProfile();
            }
        });
        saveButton.setBackground(new Color(0, 204, 255)); // Change button color
        panel.add(saveButton);

        viewButton = new JButton("View all Profiles");
        viewButton.setBounds(190, 370, 130, 50);
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewProfile();
            }
        });
        viewButton.setBackground(new Color(0, 204, 255)); // Change button color
        panel.add(viewButton);

        backButton = new JButton("Back to Admin Dashboard");
        backButton.setBounds(423, 370, 208, 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new AdminMainPage().setVisible(true);
            }
        });
        backButton.setBackground(new Color(0, 204, 255)); // Change button color
        panel.add(backButton);

        add(panel);
        setVisible(true);
        populateFieldsWithLastRegisteredData();

    }
    private void populateFieldsWithLastRegisteredData() {
    String lastRegisteredName = "";
    String lastRegisteredId = "";
    String lastRegisteredPassword = "";
    String lastRegisteredEmail = "";
    String lastRegisteredPhone = "";

    try (BufferedReader reader = new BufferedReader(new FileReader("D:\\google\\Java4444\\JavaGroupAssignment\\src\\main\\java\\com\\mycompany\\javagroupassignment\\profiles.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length >= 5) {
                lastRegisteredName = data[0];
                lastRegisteredId = data[1];
                lastRegisteredPassword = data[2];
                lastRegisteredEmail = data[3];
                lastRegisteredPhone = data[4];
            }
        }
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error reading profiles.txt: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }

    // Populate the text fields with the last registered data
    usernameField.setText(lastRegisteredName);
    idField.setText(lastRegisteredId);
    passwordField.setText(lastRegisteredPassword);
    emailField.setText(lastRegisteredEmail);
    phoneField.setText(lastRegisteredPhone);
}

private void saveProfile() {
    String username = usernameField.getText();
    String id = idField.getText();
    String password = String.valueOf(passwordField.getPassword());
    String email = emailField.getText();
    String phone = phoneField.getText();

    // Format the data for profiles.txt
    String profileData = username + "," + id + "," + email + ",\"ADMIN\"," + phone + "," + password;

    try {
        // Write to out.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\google\\Java4444\\JavaGroupAssignment\\src\\main\\java\\com\\mycompany\\javagroupassignment\\profiles.txt", true))) {
            writer.write(username + "," + id + "," + password + "," + email + "," + phone + "\n");
            writer.flush();
        }

        // Write to profiles.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\google\\Java4444\\JavaGroupAssignment\\src\\main\\java\\com\\mycompany\\javagroupassignment\\profiles.txt", true))) {
            writer.write(profileData + "\n");
            writer.flush();
        }

        JOptionPane.showMessageDialog(this, "Profile saved successfully.");
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error saving profile: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
}




    private void viewProfile() {
        new ProfileTableView();
    }

    public static void main(String[] args) {
        new AdminPersonalProfile();
    }

    private class ProfileTableView extends JFrame {
        private JButton deleteButton;
        private JTable dataTable;

        public ProfileTableView() {
            setTitle("View Admin Profiles");
            setSize(600, 400);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            dataTable = new JTable();
            JScrollPane scrollPane = new JScrollPane(dataTable);
            panel.add(scrollPane, BorderLayout.CENTER);

            deleteButton = new JButton("Delete");
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deleteProfile();
                }
            });
            deleteButton.setBackground(new Color(0, 204, 255)); // Change button color
            panel.add(deleteButton, BorderLayout.SOUTH);

            add(panel);
            setVisible(true);

            loadProfiles();

            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    saveProfilesToFile((DefaultTableModel) dataTable.getModel());
                }
            });
        }

       private void loadProfiles() {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Name");
    model.addColumn("ID");
    model.addColumn("Password");
    model.addColumn("Email");
    model.addColumn("Phone");

    String lastRegisteredName = "";
    String lastRegisteredId = "";
    String lastRegisteredPassword = "";
    String lastRegisteredEmail = "";
    String lastRegisteredPhone = "";

    try (BufferedReader reader = new BufferedReader(new FileReader("D:\\google\\Java4444\\JavaGroupAssignment\\src\\main\\java\\com\\mycompany\\javagroupassignment\\profiles.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length >= 5) {
                model.addRow(new Object[]{data[0], data[1], data[2], data[3], data[4]});
                lastRegisteredName = data[0];
                lastRegisteredId = data[1];
                lastRegisteredPassword = data[2];
                lastRegisteredEmail = data[3];
                lastRegisteredPhone = data[4];
            }
        }
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error reading profiles.txt: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }

    dataTable.setModel(model);

    // Populate the text fields with the last registered data
    usernameField.setText(lastRegisteredName);
    idField.setText(lastRegisteredId);
    passwordField.setText(lastRegisteredPassword);
    emailField.setText(lastRegisteredEmail);
    phoneField.setText(lastRegisteredPhone);
}


        private void deleteProfile() {
            int selectedRow = dataTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
                model.removeRow(selectedRow);
                saveProfilesToFile(model);
            }
        }

        private void saveProfilesToFile(DefaultTableModel model) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\google\\Java4444\\JavaGroupAssignment\\src\\main\\java\\com\\mycompany\\javagroupassignment\\profiles.txt"))) {
                for (int i = 0; i < model.getRowCount(); i++) {
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
                JOptionPane.showMessageDialog(this, "Profile saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving profile: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
}


