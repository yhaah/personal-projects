package com.mycompany.javagroupassignment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class test extends JFrame {
    private JButton generateApprovedClosedSaleReportButton;
    private JLabel titleLabel;
    private JTable salesTable;
    private JScrollPane tableScrollPane;

    public test() {
        setTitle("Admin Reports");
        setSize(940, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on screen

        titleLabel = new JLabel("Approved/Closed Sale Report");
        titleLabel.setFont(new Font("Future Lt BT", Font.BOLD, 24)); // Bold font
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center alignment

        generateApprovedClosedSaleReportButton = new JButton("Generate Approved/Closed Sale Report");

        // Set button font and color
        Font buttonFont = new Font("Future Lt BT", Font.PLAIN, 15);
        generateApprovedClosedSaleReportButton.setFont(buttonFont);
        generateApprovedClosedSaleReportButton.setBackground(new Color(0, 204, 255)); // Light blue

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(generateApprovedClosedSaleReportButton);

        salesTable = new JTable();
        tableScrollPane = new JScrollPane(salesTable);

        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(tableScrollPane, BorderLayout.SOUTH);

        generateApprovedClosedSaleReportButton.addActionListener(e -> generateApprovedClosedSaleReport());
    }

    private void generateApprovedClosedSaleReport() {
        // Read data from CSV and display all rows
        File file = new File("C:\\Users\\odayb\\Downloads\\JavaGroupAssignment\\src\\main\\java\\com\\mycompany\\javagroupassignment\\IKEA_SA_Furniture_Web_Scrapings_sss.csv");
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Sales Order");
        model.addColumn("Item ID");
        model.addColumn("Name");
        model.addColumn("Category");
        model.addColumn("Price");
        model.addColumn("Old Price");
        model.addColumn("Sellable Online");
        model.addColumn("Link");
        model.addColumn("Other Colors");
        model.addColumn("Short Description");
        model.addColumn("Designer");
        model.addColumn("Depth");
        model.addColumn("Height");
        model.addColumn("Width");
        model.addColumn("Invoice");
        model.addColumn("Approval");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                model.addRow(data);
            }
            salesTable.setModel(model);
            JOptionPane.showMessageDialog(this, "All data from IKEA_SA_Furniture_Web_Scrapings_sss.csv displayed successfully.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error generating report: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new test().setVisible(true));
    }
}
