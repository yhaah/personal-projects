package com.mycompany.javagroupassignment;

import javax.swing.*;
import java.awt.*;


public class SalesPerson extends JFrame {
    private JButton manageProfileButton;
    private JButton manageSalesOrderButton;
    private JButton listSalesOrdersButton;

    public SalesPerson() {
        setTitle("SalesPerson Dashboard");
        setSize(600, 400);
        getContentPane().setBackground(new Color(173, 216, 230)); // Light blue background
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
// Assuming you have a JFrame with GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

// Specify the constraints for the component
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 45;
        c.gridy = 35;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);

        manageProfileButton = new JButton("Manage Profile");
        manageSalesOrderButton = new JButton("Manage Sales Order");
        listSalesOrdersButton = new JButton("List Sales Orders");

        manageProfileButton.addActionListener(e -> openManageProfile());
        manageSalesOrderButton.addActionListener(e -> openManageSalesOrder());
        listSalesOrdersButton.addActionListener(e -> openListSalesOrders());

        add(manageProfileButton, gbc);
        add(manageSalesOrderButton, gbc);
        add(listSalesOrdersButton, gbc);
    }

    private static JPanel getjPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.BLACK);
        headerPanel.setPreferredSize(new Dimension(800, 100)); // Set the preferred size for the header

        // Header label
        JLabel headerLabel = new JLabel("SalesPerson", JLabel.CENTER);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Times New Roman", Font.BOLD, 24)); // Set the font size and style

        // Adding the label to the header panel
        headerPanel.add(headerLabel);
        return headerPanel;
    }

    private void openManageProfile() {
        ManageProfilePage manageProfilePage = new ManageProfilePage();
        manageProfilePage.setVisible(true);
        this.dispose();
    }

    private void openManageSalesOrder() {
        SalesOrderPage salesOrderPage = new SalesOrderPage();
        salesOrderPage.setVisible(true);
        this.dispose();
    }

    private void openListSalesOrders() {
        ListSalesOrderPage listSalesOrderPage = new ListSalesOrderPage();
        listSalesOrderPage.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SalesPerson ex = new SalesPerson();
            ex.setVisible(true);
        });
    }
}