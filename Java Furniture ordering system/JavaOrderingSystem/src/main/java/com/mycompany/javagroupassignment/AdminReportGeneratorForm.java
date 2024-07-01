package com.mycompany.javagroupassignment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.print.*;

public class AdminReportGeneratorForm extends JFrame {
    private final String filePath = "D:\\google\\Java4444\\JavaGroupAssignment\\src\\main\\java\\com\\mycompany\\javagroupassignment\\IKEA_DATASET";
    private JButton generateReportButton;
    private JButton backButton;
    private JButton closeButton;
    private JLabel titleLabel;
    private JTable salesTable;
    private JScrollPane tableScrollPane;
    private DefaultTableModel tableModel;

    private static final int ORDER_ID_COLUMN_INDEX = 0;
    private static final int INVOICE_COLUMN_INDEX = 15;
    private static final int ACCEPT_REJECT_COLUMN_INDEX = 16;

    public AdminReportGeneratorForm() {
        setTitle("Admin Reports");
        setSize(940, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on screen

        titleLabel = new JLabel("Admin Reports");
        titleLabel.setFont(new Font("Future Lt BT", Font.BOLD, 24)); // Bold font
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center alignment

        generateReportButton = new JButton("Generate Report");
        backButton = new JButton("Back to Admin Dashboard");
        closeButton = new JButton("Close");

        // Set button font
        Font buttonFont = new Font("Future Lt BT", Font.PLAIN, 15);
        generateReportButton.setFont(buttonFont);
        backButton.setFont(buttonFont);
        closeButton.setFont(buttonFont);

        // Set button color
        Color buttonColor = new Color(0, 204, 255); // Light blue
        generateReportButton.setBackground(buttonColor);
        backButton.setBackground(buttonColor);
        closeButton.setBackground(buttonColor);

        // Set preferred size for buttons
        Dimension buttonSize = new Dimension(210, 40);
        generateReportButton.setPreferredSize(buttonSize);
        backButton.setPreferredSize(buttonSize);
        closeButton.setPreferredSize(buttonSize);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(generateReportButton);
        buttonPanel.add(backButton);
        buttonPanel.add(closeButton);

        salesTable = new JTable();
        tableScrollPane = new JScrollPane(salesTable);

        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        add(tableScrollPane, BorderLayout.CENTER);

        generateReportButton.addActionListener(e -> generateReport());
        backButton.addActionListener(e -> {
            setVisible(false);
            new AdminMainPage().setVisible(true);
        });
        closeButton.addActionListener(e -> dispose());

        // Load table data
        loadTableData();
    }

    private void loadTableData() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Sales Order");
        tableModel.addColumn("Item ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Category");
        tableModel.addColumn("Price");
        tableModel.addColumn("Old Price");
        tableModel.addColumn("Sellable Online");
        tableModel.addColumn("Link");
        tableModel.addColumn("Other Colors");
        tableModel.addColumn("Short Description");
        tableModel.addColumn("Designer");
        tableModel.addColumn("Depth");
        tableModel.addColumn("Height");
        tableModel.addColumn("Width");
        tableModel.addColumn("Check sale");
        tableModel.addColumn("Invoice");
        tableModel.addColumn("Accept/Reject");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip header
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(data);
            }
            salesTable.setModel(tableModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("Sale Order Report\n");
        report.append("------------------------------\n");

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String orderId = (String) tableModel.getValueAt(i, ORDER_ID_COLUMN_INDEX);
            String acceptRejectStatus = (String) tableModel.getValueAt(i, ACCEPT_REJECT_COLUMN_INDEX);
            String invoice = (String) tableModel.getValueAt(i, INVOICE_COLUMN_INDEX);

            if (acceptRejectStatus != null && !acceptRejectStatus.isEmpty()) {
                report.append("Order ID: ").append(orderId).append("\n");
                report.append("Accept/Reject: ").append(acceptRejectStatus).append("\n");
                report.append("Invoice: ").append(invoice).append("\n");
                report.append("------------------------------\n");
            }
        }

        JTextArea reportTextArea = new JTextArea(report.toString());
        reportTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportTextArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(this, scrollPane, "Generated Report", JOptionPane.INFORMATION_MESSAGE);

        // Ask user if they want to print the report
        int print = JOptionPane.showConfirmDialog(this, "Do you want to print this report?", "Print Report", JOptionPane.YES_NO_OPTION);
        if (print == JOptionPane.YES_OPTION) {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setJobName("Sale Order Report");

            job.setPrintable((graphics, pageFormat, pageIndex) -> {
                if (pageIndex > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                // Simulate line break for long text
                String[] lines = report.toString().split("\n");
                int y = 50;
                for (String line : lines) {
                    graphics.drawString(line, 100, y);
                    y += 20; // Adjust spacing between lines
                }

                return Printable.PAGE_EXISTS;
            });

            if (job.printDialog()) {
                try {
                    job.print();
                } catch (PrinterException ex) {
                    JOptionPane.showMessageDialog(this, "Error while printing: " + ex.getMessage(), "Print Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminReportGeneratorForm().setVisible(true));
    }
}
