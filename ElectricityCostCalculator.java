import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Appliance {
    String name;
    double wattage;
    double hoursUsed;
    
    public Appliance(String name, double wattage, double hoursUsed) {
        this.name = name;
        this.wattage = wattage;
        this.hoursUsed = hoursUsed;
    }
    
    public double getKilowattHours() {
        return (wattage * hoursUsed) / 1000;
    }
    
    public double getCost(double ratePerKWh) {
        return getKilowattHours() * ratePerKWh;
    }
}

public class ElectricityCostCalculator extends JFrame {
    private ArrayList<Appliance> appliances;
    private DefaultTableModel tableModel;
    
    private JTextField rateField;
    private JTextField nameField;
    private JTextField wattageField;
    private JTextField hoursField;
    private JTable applianceTable;
    private JLabel totalDailyLabel;
    private JLabel totalMonthlyLabel;
    private JLabel totalYearlyLabel;
    private JLabel totalKWhLabel;
    
    public ElectricityCostCalculator() {
        appliances = new ArrayList<>();
        initComponents();
        setTitle("Electricity Cost Calculator");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        
        // Top Panel - Rate Input
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        topPanel.setBorder(BorderFactory.createTitledBorder("Electricity Rate"));
        topPanel.setBackground(new Color(240, 248, 255));
        
        topPanel.add(new JLabel("Rate per kWh (PHP):"));
        rateField = new JTextField("10.50", 10);
        rateField.addActionListener(e -> calculateTotal());
        topPanel.add(rateField);
        
        add(topPanel, BorderLayout.NORTH);
        
        // Center Panel - Main Content
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Input Panel
        JPanel inputPanel = createInputPanel();
        centerPanel.add(inputPanel, BorderLayout.NORTH);
        
        // Table Panel
        JPanel tablePanel = createTablePanel();
        centerPanel.add(tablePanel, BorderLayout.CENTER);
        
        add(centerPanel, BorderLayout.CENTER);
        
        // Bottom Panel - Results
        JPanel bottomPanel = createResultsPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Add Appliance"));
        panel.setBackground(new Color(245, 255, 250));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Appliance Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Appliance Name:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        nameField = new JTextField(15);
        panel.add(nameField, gbc);
        
        // Wattage
        gbc.gridx = 2;
        gbc.weightx = 0;
        panel.add(new JLabel("Wattage (W):"), gbc);
        
        gbc.gridx = 3;
        gbc.weightx = 0.5;
        wattageField = new JTextField(8);
        panel.add(wattageField, gbc);
        
        // Hours
        gbc.gridx = 4;
        gbc.weightx = 0;
        panel.add(new JLabel("Hours/Day:"), gbc);
        
        gbc.gridx = 5;
        gbc.weightx = 0.5;
        hoursField = new JTextField(8);
        panel.add(hoursField, gbc);
        
        // Buttons
        gbc.gridx = 6;
        gbc.weightx = 0;
        JButton addButton = new JButton("Add");
        addButton.setBackground(new Color(76, 175, 80));
        addButton.setForeground(Color.BLACK);
        addButton.setFocusPainted(false);
        addButton.addActionListener(e -> addAppliance());
        panel.add(addButton, gbc);
        
        gbc.gridx = 7;
        JButton clearButton = new JButton("Clear All");
        clearButton.setBackground(new Color(244, 67, 54));
        clearButton.setForeground(Color.BLACK);
        clearButton.setFocusPainted(false);
        clearButton.addActionListener(e -> clearAll());
        panel.add(clearButton, gbc);
        
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Appliances List"));
        
        String[] columns = {"Appliance", "Wattage (W)", "Hours/Day", "kWh/Day", "Daily Cost (PHP)", "Monthly Cost (PHP)"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        applianceTable = new JTable(tableModel);
        applianceTable.setRowHeight(25);
        applianceTable.getTableHeader().setReorderingAllowed(false);
        
        JScrollPane scrollPane = new JScrollPane(applianceTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Delete button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton deleteButton = new JButton("Delete Selected");
        deleteButton.setBackground(new Color(255, 152, 0));
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setFocusPainted(false);
        deleteButton.addActionListener(e -> deleteSelected());
        buttonPanel.add(deleteButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createResultsPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 20, 10));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Total Cost Summary"),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        panel.setBackground(new Color(255, 248, 220));
        
        totalKWhLabel = new JLabel("Total Daily Usage: 0.00 kWh");
        totalKWhLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(totalKWhLabel);
        
        totalDailyLabel = new JLabel("Total Daily Cost: PHP 0.00");
        totalDailyLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(totalDailyLabel);
        
        totalMonthlyLabel = new JLabel("Total Monthly Cost: PHP 0.00");
        totalMonthlyLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(totalMonthlyLabel);
        
        totalYearlyLabel = new JLabel("Total Yearly Cost: PHP 0.00");
        totalYearlyLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(totalYearlyLabel);
        
        return panel;
    }
    
    private void addAppliance() {
        try {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter appliance name", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double wattage = Double.parseDouble(wattageField.getText().trim());
            double hours = Double.parseDouble(hoursField.getText().trim());
            
            if (wattage <= 0 || hours <= 0 || hours > 24) {
                JOptionPane.showMessageDialog(this, "Please enter valid values (Wattage > 0, Hours 0-24)", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Appliance appliance = new Appliance(name, wattage, hours);
            appliances.add(appliance);
            updateTable();
            
            // Clear input fields
            nameField.setText("");
            wattageField.setText("");
            hoursField.setText("");
            nameField.requestFocus();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for wattage and hours", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteSelected() {
        int selectedRow = applianceTable.getSelectedRow();
        if (selectedRow >= 0) {
            appliances.remove(selectedRow);
            updateTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void clearAll() {
        if (appliances.isEmpty()) return;
        
        int confirm = JOptionPane.showConfirmDialog(this, "Clear all appliances?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            appliances.clear();
            updateTable();
        }
    }
    
    private void updateTable() {
        tableModel.setRowCount(0);
        
        try {
            double rate = Double.parseDouble(rateField.getText().trim());
            
            for (Appliance app : appliances) {
                double kWh = app.getKilowattHours();
                double dailyCost = app.getCost(rate);
                double monthlyCost = dailyCost * 30;
                
                Object[] row = {
                    app.name,
                    String.format("%.0f", app.wattage),
                    String.format("%.1f", app.hoursUsed),
                    String.format("%.2f", kWh),
                    String.format("%.2f", dailyCost),
                    String.format("%.2f", monthlyCost)
                };
                tableModel.addRow(row);
            }
            
            calculateTotal();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid rate", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void calculateTotal() {
        try {
            double rate = Double.parseDouble(rateField.getText().trim());
            double totalKWh = 0;
            double totalCost = 0;
            
            for (Appliance app : appliances) {
                totalKWh += app.getKilowattHours();
                totalCost += app.getCost(rate);
            }
            
            totalKWhLabel.setText(String.format("Total Daily Usage: %.2f kWh", totalKWh));
            totalDailyLabel.setText(String.format("Total Daily Cost: PHP %.2f", totalCost));
            totalMonthlyLabel.setText(String.format("Total Monthly Cost: PHP %.2f", totalCost * 30));
            totalYearlyLabel.setText(String.format("Total Yearly Cost: PHP %.2f", totalCost * 365));
            
        } catch (NumberFormatException ex) {
            // Invalid rate, keep current values
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            ElectricityCostCalculator calculator = new ElectricityCostCalculator();
            calculator.setVisible(true);
        });
    }
}