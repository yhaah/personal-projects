package com.mycompany.javagroupassignment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SalesOrderPage extends JFrame implements ActionListener {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnBack, btnAdd, btnModify; // Button for adding entries and modifying entries
    private String filePath = "D:\\google\\Java4444\\JavaGroupAssignment\\src\\main\\java\\com\\mycompany\\javagroupassignment\\IKEA_DATASET";

    public SalesOrderPage() {
        setTitle("Sales Orders");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make cells non-editable for now; change as per requirements
                return false;
            }
        };

        table = new JTable(tableModel);
        loadTableData();

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        btnBack = new JButton("Back");
        btnBack.addActionListener(this);

        btnAdd = new JButton("Add New Entry");
        btnAdd.addActionListener(this);

        btnModify = new JButton("Modify Selected Entry"); // Create Modify button
        btnModify.addActionListener(this); // Add action listener

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(btnBack);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnModify); // Add Modify button to panel

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadTableData() {
        File file = new File(filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if (line != null) {
                String[] columnNames = line.split(","); // Assumes CSV header exists
                tableModel.setColumnIdentifiers(columnNames);
            }

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addNewEntry() {
        JPanel panel = new JPanel(new GridLayout(0, 2));
        JTextField[] textFields = new JTextField[tableModel.getColumnCount()];
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            panel.add(new JLabel(tableModel.getColumnName(i)));
            JTextField textField = new JTextField();
            textFields[i] = textField;
            panel.add(textField);
        }

        int result = JOptionPane.showConfirmDialog(null, panel, "Enter values for new entry", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < textFields.length; i++) {
                sb.append(textFields[i].getText().trim());
                if (i < textFields.length - 1) {
                    sb.append(",");
                }
            }
            String[] data = sb.toString().split(",");
            tableModel.addRow(data);
            appendDataToFile(sb.toString()); // Append only the new entry
        }
    }

    private void modifyEntry() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to modify.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPanel panel = new JPanel(new GridLayout(0, 2));
        JTextField[] textFields = new JTextField[tableModel.getColumnCount()];
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            panel.add(new JLabel(tableModel.getColumnName(i)));
            Object cellValue = table.getValueAt(selectedRow, i);
            String textValue = cellValue != null ? cellValue.toString() : ""; // Handle null values
            JTextField textField = new JTextField(textValue);
            textFields[i] = textField;
            panel.add(textField);
        }

        int result = JOptionPane.showConfirmDialog(null, panel, "Modify selected entry", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // Update the table model with the modified data
            for (int i = 0; i < textFields.length; i++) {
                tableModel.setValueAt(textFields[i].getText(), selectedRow, i);
            }
            saveDataToFile(); // Save the entire dataset to the file
        }
    }

    private void appendDataToFile(String data) {
        // Check if the file is empty or not to decide on adding a newline before appending
        boolean fileIsEmpty = new File(filePath).length() == 0;

        try (FileWriter fw = new FileWriter(filePath, true); // Append mode true
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            // If the file is not empty, prepend a newline to separate from the last line
            if (!fileIsEmpty) {
                out.print("\n" + data); // Add a newline before the data
            } else {
                out.print(data); // No need for a newline at the beginning of the file
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data to file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

   private void saveDataToFile() {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
        // Write the header row first
        for (int j = 0; j < tableModel.getColumnCount(); j++) {
            writer.print(tableModel.getColumnName(j));
            if (j < tableModel.getColumnCount() - 1) {
                writer.print(",");
            }
        }
        writer.println(); // Move to the next line after writing the header row

        // Write the data rows
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                writer.print(tableModel.getValueAt(i, j));
                if (j < tableModel.getColumnCount() - 1) {
                    writer.print(",");
                }
            }
            writer.println(); // Move to the next line for the next row
        }
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error saving data to file.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            // Dispose the current frame and open the Salesperson Page
            this.dispose();
            new SalesPerson().setVisible(true); // Assuming SalespersonPage has a default constructor and is a JFrame
        } else if (e.getSource() == btnAdd) {
            addNewEntry();
        } else if (e.getSource() == btnModify) {
            modifyEntry();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SalesOrderPage::new);
    }
}