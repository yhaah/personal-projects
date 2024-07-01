package com.mycompany.javagroupassignment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.ImageIcon;
import java.awt.Image;



public class ADMIN_OfficerPage extends JFrame implements ActionListener {

    
    private JTable table;
    private DefaultTableModel tableModel;
    private String filePath = "D:\\google\\Java4444\\JavaGroupAssignment\\src\\main\\java\\com\\mycompany\\javagroupassignment\\IKEA_DATASET";
   
    private JButton  btnSearch, btnModify;
    private JButton btnSubmitForProduction, btnIssueInvoice, btnSaveApproval;
    private JButton btnSaveProfile,btnSwitchToOrder;
    private JButton btnGenerateReport;
    private JButton btnCheckProductStatus;
    private JTextField txtName, txtID, txtEmail, txtPhone;
    private JPasswordField txtPassword;
    private JDialog approvalDialog;
    private JRadioButton approveRadioButton, rejectRadioButton;
    private JLabel lblPhoto ;
    private ImageIcon originalIcon;



public ADMIN_OfficerPage() {
        setTitle("Admin Officer management");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        txtName = new JTextField();
        txtID = new JTextField();
        txtEmail = new JTextField();
        txtPhone = new JTextField();
        txtPassword = new JPasswordField();
  
       
        
        btnSwitchToOrder = new JButton("Back to Admin Main Page");

        createProfilePanel();
        setLocationRelativeTo(null);
        setVisible(true);
        approvalDialog = createApprovalDialog();
        btnSaveProfile.addActionListener(this); 
        btnSwitchToOrder.addActionListener(this);

    }

    private void createProfilePanel() {
        JPanel profilePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        profilePanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 30, 50));

        // Set the background color for the two sides
        profilePanel.setBackground(Color.LIGHT_GRAY); 

        // Left side (Gray)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;

        // Placeholder label for the profile photo with an image
        JLabel photoLabel = new JLabel();
        ImageIcon originalIcon = new ImageIcon("icky-hladynets-uyaTT9u6AvI-unsplash.jpg"); 
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        photoLabel.setIcon(new ImageIcon(resizedImage));
        photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        photoLabel.setVerticalAlignment(SwingConstants.CENTER);
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        photoLabel.setForeground(Color.WHITE);
        photoLabel.setFont(new Font("Arial", Font.BOLD, 15));
        profilePanel.add(photoLabel, gbc);

        // Right side (White)
        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBackground(Color.LIGHT_GRAY);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;

        // Font settings for labels
        Font labelFont = new Font("Arial", Font.PLAIN, 10);

        // Initialize text fields and buttons
        txtName = new JTextField();
        txtID = new JTextField();
        txtEmail = new JTextField();
        txtPhone = new JTextField();
        txtPassword = new JPasswordField();
        btnSaveProfile = new JButton("Save Profile");
        
        btnSwitchToOrder = new JButton("Back to Admin Main Page");

        // Add information and buttons using the addRow method
        gbc.insets = new Insets(0, 10, 5, 0); // Adjusted insets for spacing
        addRow(infoPanel, gbc, labelFont, "Name:", txtName);
        addRow(infoPanel, gbc, labelFont, "ID:", txtID);
        addRow(infoPanel, gbc, labelFont, "Email:", txtEmail);
        addRow(infoPanel, gbc, labelFont, "Phone:", txtPhone);
        addRow(infoPanel, gbc, labelFont, "Password:", txtPassword);


         gbc.insets = new Insets(10, 10, 5, 0); // Reset insets for the buttons
         addRow(infoPanel, gbc, labelFont, "", btnSaveProfile);
         addRow(infoPanel, gbc, labelFont, "", btnSwitchToOrder);

        profilePanel.add(infoPanel, gbc);

        getContentPane().add(profilePanel, BorderLayout.CENTER);
    }


    // The addRow method to add components to the panel
    private void addRow(JPanel panel, GridBagConstraints gbc, Font labelFont, String labelText, JComponent component) {
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Check the type of the component and handle accordingly
        if (component instanceof JTextField || component instanceof JPasswordField) {
            panel.add(component, gbc);
        } else if (component instanceof JButton) {
            // For buttons, use different grid constraints
            gbc.gridx = 1;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.WEST;
            panel.add(component, gbc);
        }
          gbc.gridy++;
    }

    private void createSalesOrderPanel() {


        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        loadTableData();

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        

        btnSearch = new JButton("Search Sale Order");
        btnSearch.addActionListener(this);

        btnModify = new JButton("Modify Sale Order");
        btnModify.addActionListener(this);



        btnSubmitForProduction = new JButton("Submit for Production");
        btnSubmitForProduction.addActionListener(this);

        btnIssueInvoice = new JButton("Issue Sale Invoice");
        btnIssueInvoice.addActionListener(this);


        btnCheckProductStatus = new JButton("Check Product Status");
        btnCheckProductStatus.addActionListener(this);

        btnGenerateReport = new JButton("Generate Report");
        btnGenerateReport.addActionListener(this);

      
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnModify);

        buttonPanel.add(btnSubmitForProduction);
        buttonPanel.add(btnIssueInvoice);

        buttonPanel.add(btnCheckProductStatus);
        buttonPanel.add(btnGenerateReport);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
    

    
private void saveProfile() {
    String name = txtName.getText();
    String id = txtID.getText();
    String email = txtEmail.getText();
    String officer = "Officer";
    String phone = txtPhone.getText(); // Get phone number from text field
    String password = new String(txtPassword.getPassword());  

    try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\google\\Java4444\\JavaGroupAssignment\\src\\main\\java\\com\\mycompany\\javagroupassignment\\profiles.txt", true))) {
        writer.write("" + name + ", " + id + ", " + email + ", \"" + officer + "\", " + phone + ", " + password + "");
        writer.newLine();
        JOptionPane.showMessageDialog(this, "Profile saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error saving profile data to file.", "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}




    private void loadTableData() {
        File file = new File(filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if (line != null) {
                String[] columnNames = line.split(",");
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

    private JDialog createApprovalDialog() {
        JDialog dialog = new JDialog(this, "Approval/Rejection", true);
        dialog.setSize(300, 150);
        dialog.setLayout(new GridLayout(3, 1));

        approveRadioButton = new JRadioButton("Approve");
        rejectRadioButton = new JRadioButton("Reject");

        ButtonGroup group = new ButtonGroup();
        group.add(approveRadioButton);
        group.add(rejectRadioButton);

        btnSaveApproval = new JButton("Save");
        btnSaveApproval.addActionListener(this);

        dialog.add(approveRadioButton);
        dialog.add(rejectRadioButton);
        dialog.add(btnSaveApproval);

        return dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSaveProfile) {
            saveProfile();
        } else if (e.getSource() == btnSwitchToOrder) {
           new AdminMainPage().setVisible(true);
       
        } else if (e.getSource() == btnSearch) {
            searchSaleOrder();
        } else if (e.getSource() == btnModify) {
            modifySaleOrder();

        } else if (e.getSource() == btnSubmitForProduction) {
            showApprovalDialog();
        } else if (e.getSource() == btnSaveApproval) {
            saveApproval();
        } else if (e.getSource() == btnIssueInvoice) {
            issueSaleInvoice();
        } else if (e.getSource() == btnGenerateReport) {
            generateReport();
        } else if (e.getSource() == btnCheckProductStatus) {
            checkProductStatus();
        }
    }



    private void showApprovalDialog() {
        approveRadioButton.setSelected(false);
        rejectRadioButton.setSelected(false);
        approvalDialog.setVisible(true);
    }

   

    private void switchToSalesOrderPanel() {
        getContentPane().removeAll();
        createSalesOrderPanel();
        revalidate();
        repaint();
    }





    private void saveApproval() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to approve or reject.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String approvalStatus;
        if (approveRadioButton.isSelected()) {
            approvalStatus = "Approved";
        } else if (rejectRadioButton.isSelected()) {
            approvalStatus = "Rejected";
        } else {
            JOptionPane.showMessageDialog(this, "Please select Approve or Reject.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update the approval status in the table model
        tableModel.setValueAt(approvalStatus, selectedRow, tableModel.getColumnCount() - 1);

        // Close the approval dialog
        approvalDialog.setVisible(false);

        // Save table data to file after approval is received
        saveTableDataToFile();
    }

    private void issueSaleInvoice() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to issue sale invoice.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String approvalStatus = (String) tableModel.getValueAt(selectedRow, tableModel.getColumnCount() - 1); // Assuming approval status is in the last column
        String result;

        if ("Approved".equals(approvalStatus)) {
            result = "Sale Invoice Issued. Status set to: Sale Invoice Issued";
            // Assuming the invoice status is in the second last column, update this index as per your table structure
            tableModel.setValueAt("Invoice Issued", selectedRow, tableModel.getColumnCount() - 2);
        } else if ("Rejected".equals(approvalStatus)) {
            result = "Cannot issue invoice for a rejected order.";


            tableModel.setValueAt("null", selectedRow, tableModel.getColumnCount() - 2);
        } else {
            result = "Approval status is unclear. Cannot issue invoice.";
        }

        JOptionPane.showMessageDialog(this, result, "Issue Sale Invoice", JOptionPane.INFORMATION_MESSAGE);
    }


    private void modifySaleOrder() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to modify.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object[] rowData = new Object[tableModel.getColumnCount()];
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            rowData[i] = table.getValueAt(selectedRow, i);
        }

        JPanel panel = new JPanel(new GridLayout(0, 2));
        JTextField[] textFields = new JTextField[tableModel.getColumnCount()];
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            panel.add(new JLabel(tableModel.getColumnName(i)));
            JTextField textField = new JTextField(String.valueOf(rowData[i]));
            textFields[i] = textField;
            panel.add(textField);
        }

        int result = JOptionPane.showConfirmDialog(null, panel, "Modify values for selected entry", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            for (int i = 0; i < textFields.length; i++) {
                table.setValueAt(textFields[i].getText().trim(), selectedRow, i);
            }
            saveTableDataToFile();
        }
    }

    private void saveTableDataToFile() {
        File file = new File(filePath);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            // Writing column headers
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                bw.write(tableModel.getColumnName(i));
                if (i < tableModel.getColumnCount() - 1) {
                    bw.write(",");
                } else {
                    bw.newLine();
                }
            }
            // Writing data
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    bw.write(tableModel.getValueAt(i, j).toString());
                    if (j < tableModel.getColumnCount() - 1) {
                        bw.write(",");
                    } else {
                        bw.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data to file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void searchSaleOrder() {
        String itemId = JOptionPane.showInputDialog(this, "Enter Item ID to search:");

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(itemId)) {
                table.setRowSelectionInterval(i, i);
                table.scrollRectToVisible(table.getCellRect(i, 0, true));
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Sale order not found for Item ID: " + itemId, "Search Result", JOptionPane.INFORMATION_MESSAGE);
    }
    private void submitForProduction() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to submit for production.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Show the approval dialog
        showApprovalDialog(selectedRow);
    }




    private void showApprovalDialog(int selectedRow) {
        JDialog approvalDialog = new JDialog(this, "Approval Dialog", true);
        approvalDialog.setSize(300, 200);
        approvalDialog.setLayout(new BorderLayout());

        JPanel approvalPanel = new JPanel(new GridLayout(3, 1));
        JRadioButton approveRadioButton = new JRadioButton("Approve");
        JRadioButton rejectRadioButton = new JRadioButton("Reject");
        JButton saveButton = new JButton("Save");

        approvalPanel.add(approveRadioButton);
        approvalPanel.add(rejectRadioButton);
        approvalPanel.add(saveButton);

        ButtonGroup group = new ButtonGroup();
        group.add(approveRadioButton);
        group.add(rejectRadioButton);

        approvalDialog.add(approvalPanel, BorderLayout.CENTER);

        saveButton.addActionListener(e -> {
            // Save the approval status to the "Approve/Reject" column, assuming it's before the last column
            String approvalStatus = approveRadioButton.isSelected() ? "Approved" : rejectRadioButton.isSelected() ? "Rejected" : "";
            // Assuming the "Approve/Reject" column is the second last column
            tableModel.setValueAt(approvalStatus, selectedRow, tableModel.getColumnCount() - 2);

            // Close the approval dialog
            approvalDialog.setVisible(false);
        });

        approvalDialog.setVisible(true);

    }
    private void checkProductStatus() {
        int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a sale order to check product status.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    String productStatus = (String) tableModel.getValueAt(selectedRow, PRODUCT_STATUS_COLUMN_INDEX);
    JOptionPane.showMessageDialog(this, "Product Status: " + productStatus, "Product Status", JOptionPane.INFORMATION_MESSAGE);
}
    
    private static final int INVOICE_COLUMN_INDEX = 14;
private static final int ACCEPT_REJECT_COLUMN_INDEX = 15;

// Assuming the "Accept/Reject" column is where the product status is stored
private static final int PRODUCT_STATUS_COLUMN_INDEX = ACCEPT_REJECT_COLUMN_INDEX;

// Assuming the "Sales Order" column is where the order ID is stored
private static final int ORDER_ID_COLUMN_INDEX = 0;

// Assuming the "Invoice" column is where the invoice status is stored
private static final int INVOICE_STATUS_COLUMN_INDEX = INVOICE_COLUMN_INDEX;

    private void generateReport() {
        StringBuilder report = new StringBuilder();
    report.append("Sale Order Report\n");
    report.append("------------------------------\n");
    for (int i = 0; i < tableModel.getRowCount(); i++) {
        String orderId = (String) tableModel.getValueAt(i, ORDER_ID_COLUMN_INDEX);
        String status = (String) tableModel.getValueAt(i, PRODUCT_STATUS_COLUMN_INDEX);
        String invoiceStatus = (String) tableModel.getValueAt(i, INVOICE_STATUS_COLUMN_INDEX);

        report.append("Order ID: ").append(orderId).append("\n");
        report.append("Product Status: ").append(status).append("\n");
        report.append("Invoice Status: ").append(invoiceStatus).append("\n");
        report.append("------------------------------\n");
    }
     JTextArea reportTextArea = new JTextArea(report.toString());
    reportTextArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(reportTextArea);
    scrollPane.setPreferredSize(new Dimension(400, 300));
    JOptionPane.showMessageDialog(this, scrollPane, "Generated Report", JOptionPane.INFORMATION_MESSAGE);
    }
      public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        ADMIN_OfficerPage officerPage0 = new ADMIN_OfficerPage();
        officerPage0.setVisible(true);
    });
      }
}