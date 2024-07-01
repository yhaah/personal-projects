/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javagroupassignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainPage extends JFrame {

    public AdminMainPage() {
        setTitle("Admin Dashboard");
        setSize(908, 570); // Adjusted frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create buttons
        JButton btnManagePersonalProfile = new JButton("Manage Personal Profile");
        JButton btnManageSalesPersonProfile = new JButton("Manage Sales Person Profile");
        JButton btnManageOfficerProfile = new JButton("Manage Officer Profile");
        JButton btnAddWorker = new JButton("Manage Workers"); // New button
        JButton btnGenerateReports = new JButton("Generate Reports");
        JButton btnBack = new JButton("Back to Login");

        // Set button font
        Font buttonFont = new Font("Future Lt BT", Font.PLAIN, 15);
        btnManagePersonalProfile.setFont(buttonFont);
        btnManageSalesPersonProfile.setFont(buttonFont);
        btnManageOfficerProfile.setFont(buttonFont);
        btnAddWorker.setFont(buttonFont);
        btnGenerateReports.setFont(buttonFont);
        btnBack.setFont(buttonFont);

        // Set button color
        Color buttonColor = new Color(0, 204, 255);
        btnManagePersonalProfile.setBackground(buttonColor);
        btnManageSalesPersonProfile.setBackground(buttonColor);
        btnManageOfficerProfile.setBackground(buttonColor);
        btnAddWorker.setBackground(buttonColor);
        btnGenerateReports.setBackground(buttonColor);
        btnBack.setBackground(buttonColor);

        // Add action listeners to buttons
        btnManagePersonalProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open manage personal profile page
                // Replace this with actual code to open the manage personal profile page
                setVisible(false); // Hide main admin page
                new AdminPersonalProfile().setVisible(true);
            }
        });

        btnManageSalesPersonProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Hide main admin page
                new ADMIN_ManageProfile().setVisible(true);
            }
        });

        btnManageOfficerProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Hide main admin page
                new ADMIN_OfficerPage().setVisible(true);
            }
        });

        btnAddWorker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add worker action
                // Replace this with actual code to add a worker
                setVisible(false); // Hide main admin page
                new AdminEditWorkerProfile().setVisible(true);
            }
        });

        btnGenerateReports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Hide main admin page
                new AdminReportGeneratorForm().setVisible(true);
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Hide main admin page
                new LoginPage().setVisible(true);
            }
        });

        // Create panel and add buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2)); // Adjusted layout
        JPanel leftPanel = new JPanel(new GridLayout(3, 1));
        leftPanel.add(btnManagePersonalProfile);
        leftPanel.add(btnManageSalesPersonProfile);
        leftPanel.add(btnManageOfficerProfile);
        JPanel rightPanel = new JPanel(new GridLayout(3, 1));
        rightPanel.add(btnAddWorker);
        rightPanel.add(btnGenerateReports);
        rightPanel.add(btnBack);

        // Set background color
        panel.setBackground(new Color(204, 229, 255)); // Light blue background
        leftPanel.setBackground(new Color(204, 229, 255));
        rightPanel.setBackground(new Color(204, 229, 255));

        // Add buttons to panels
        panel.add(leftPanel);
        panel.add(rightPanel);

        // Add panel to frame
        add(panel, BorderLayout.CENTER);

        // Create and add label
        JLabel titleLabel = new JLabel("Welcome to Admin Dashboard");
        titleLabel.setFont(new Font("Future Lt BT", Font.BOLD, 18)); // Bold font
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center alignment
        add(titleLabel, BorderLayout.NORTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdminMainPage();
            }
        });
    }
}
