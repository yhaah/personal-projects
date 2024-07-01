package com.mycompany.javagroupassignment;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.io.BufferedWriter;
    import java.io.FileWriter;
    import java.io.IOException;

    public class ManageProfilePage extends JFrame {
        private JTextField nameField;
        private JTextField emailField;
        private JTextField phoneField;
        private JTextField idField;
        private JPasswordField passwordField;
        private JButton saveButton;
        private JButton backButton;

        public ManageProfilePage() {
            setTitle("Manage Profile");
            setSize(800, 600); // Increased window size
            getContentPane().setBackground(new Color(173, 216, 230)); // Light blue background
            setLayout(new GridBagLayout());
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            initUI();
        }

        private void initUI() {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 50, 10, 50);

            nameField = new JTextField(20);
            emailField = new JTextField(20);
            phoneField = new JTextField(20);
            idField = new JTextField(20);
            passwordField = new JPasswordField(20);
            saveButton = new JButton("Save");
            backButton = new JButton("Back");

            addField(gbc, "Name:", nameField);
            addField(gbc, "Email:", emailField);
            addField(gbc, "Phone:", phoneField);
            addField(gbc, "ID:", idField);
            addField(gbc, "Password:", passwordField);

            gbc.gridy++;
            add(saveButton, gbc);

            gbc.gridy++;
            add(backButton, gbc);

            // Action listeners
            saveButton.addActionListener(this::saveProfile);
            backButton.addActionListener(e -> backAction());
        }

        private void addField(GridBagConstraints gbc, String label, Component field) {
            gbc.gridy++;
            add(new JLabel(label), gbc);
            gbc.gridy++;
            add(field, gbc);
        }

        private void saveProfile(ActionEvent e) {
            // Check if any field is empty
            if (nameField.getText().trim().isEmpty() ||
                    emailField.getText().trim().isEmpty() ||
                    phoneField.getText().trim().isEmpty() ||
                    idField.getText().trim().isEmpty() ||
                    new String(passwordField.getPassword()).trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Format the profile information
            String profileInfo = String.format("%s, %s, %s, \"%s\", %s, %s",
                    nameField.getText().trim(),
                    idField.getText().trim(),
                    emailField.getText().trim(),
                    "SalesPerson",
                    phoneField.getText().trim(),
                    new String(passwordField.getPassword()).trim());

            // Specify the absolute file path
            String filePath = "D:\\google\\Java4444\\JavaGroupAssignment\\src\\main\\java\\com\\mycompany\\javagroupassignment\\profiles.txt";

            // Save the formatted profile information to the existing text file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write(profileInfo);
                writer.newLine();
                JOptionPane.showMessageDialog(this, "Profile saved successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving profile.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }




        private void backAction() {
            dispose();
            // Ensure SalesPerson class exists and is accessible
            new SalesPerson().setVisible(true);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                ManageProfilePage manageProfilePage = new ManageProfilePage();
                manageProfilePage.setVisible(true);
            });
        }
    }