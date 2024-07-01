package com.mycompany.javagroupassignment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.Vector;

public class ListSalesOrderPage extends JFrame {
    private DefaultTableModel tableModel;
    private JTable ordersTable;
    private JButton viewUnapprovedButton, viewApprovedButton, viewAllButton, backButton, modifyButton;
    private Vector<Vector<Object>> allOrders;
    private Vector<String> columnNames;
    private String filePath = "D:\\google\\Java4444\\JavaGroupAssignment\\src\\main\\java\\com\\mycompany\\javagroupassignment\\IKEA_DATASET";

    public ListSalesOrderPage() {
        setTitle("List Sales Orders");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(173, 216, 230));
        initUI();
        setLocationRelativeTo(null);
    }

    private void initUI() {
        setupColumns();
        loadDataFromCSV(filePath);

        tableModel = new DefaultTableModel(allOrders, columnNames);
        ordersTable = new JTable(tableModel);

        setupButtons();
        addComponents();
    }

    private void setupColumns() {
        columnNames = new Vector<>();
        columnNames.add("sales_order");
        columnNames.add("item_id");
        columnNames.add("name");
        columnNames.add("category");
        columnNames.add("price");
        columnNames.add("old_price");
        columnNames.add("sellable_online");
        columnNames.add("link");
        columnNames.add("other_colors");
        columnNames.add("short_description");
        columnNames.add("designer");
        columnNames.add("depth");
        columnNames.add("height");
        columnNames.add("width");
        columnNames.add("Check sale");
        columnNames.add("Invoice");
        columnNames.add("Accept/Reject");
    }

    private void loadDataFromCSV(String filePath) {
        allOrders = new Vector<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // Improved CSV parsing regex
                Vector<Object> row = new Vector<>();
                for (String value : data) {
                    row.add(value);
                }
                allOrders.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data from file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupButtons() {
        viewUnapprovedButton = new JButton("View Unapproved Sales Orders");
        viewApprovedButton = new JButton("View Approved Sales Orders");
        viewAllButton = new JButton("View All Sales Orders");
        backButton = new JButton("Back");
        modifyButton = new JButton("Modify");

        viewUnapprovedButton.addActionListener(e -> filterOrders(false));
        viewApprovedButton.addActionListener(e -> filterOrders(true));
        viewAllButton.addActionListener(e -> tableModel.setDataVector(allOrders, columnNames));
        backButton.addActionListener(e -> dispose());
        modifyButton.addActionListener(this::modifyCell);
    }

    private void addComponents() {
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(viewUnapprovedButton);
        buttonsPanel.add(viewApprovedButton);
        buttonsPanel.add(viewAllButton);
        buttonsPanel.add(backButton);
        buttonsPanel.add(modifyButton);

        add(new JScrollPane(ordersTable), BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void filterOrders(boolean approved) {
    Vector<Vector<Object>> filteredData = new Vector<>();
    for (Vector<Object> row : allOrders) {
        String acceptRejectStatus = (String) row.get(16); // Assuming "Accept/Reject" is at index 16
        if ((approved && "Accepted".equals(acceptRejectStatus)) || (!approved && !"Accepted".equals(acceptRejectStatus))) {
            filteredData.add(row);
        }
    }
    tableModel.setDataVector(filteredData, columnNames);
}
    private void modifyCell(java.awt.event.ActionEvent e) {
        int selectedRow = ordersTable.getSelectedRow();
        int selectedColumn = ordersTable.getSelectedColumn();
        if (selectedRow == -1 || selectedColumn == -1) {
            JOptionPane.showMessageDialog(this, "Please select a cell to modify.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String newValue = JOptionPane.showInputDialog(this, "Enter new value:");
        if (newValue != null) {
            tableModel.setValueAt(newValue, selectedRow, selectedColumn);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ListSalesOrderPage().setVisible(true));
    }
}
