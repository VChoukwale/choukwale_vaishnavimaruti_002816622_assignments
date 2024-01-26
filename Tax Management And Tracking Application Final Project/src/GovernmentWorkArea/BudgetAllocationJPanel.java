/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GovernmentWorkArea;

import Database.DatabaseConnection;
import LandingUI.MainJFrame;
import LandingUI.MenuPH;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abhinav Uni
 */
public class BudgetAllocationJPanel extends javax.swing.JPanel {
    
    private MainJFrame mainframe;
    private int selectedNUID;
    private String Domainname;
    private String Budget;
    private String enterpriseValue;
    private String organisationValue;
    private String departmentValue;
    private String divisionValue;
    
    private String name;
    
    /**
     * Creates new form BudgetAllocation
     */
    public BudgetAllocationJPanel(MainJFrame mainframe,int selectedNUID) {
       initComponents();
        
        this.mainframe = mainframe;
        this.selectedNUID = selectedNUID;
        String Domain = Domain(selectedNUID);
        
        Domainname = Domain;
        System.out.println(Domain + "Domainname");
         if(Domain.equals("president"))
        {
            
          
            lbldepartment.setVisible(false);
            lbldivision.setVisible(false);
            lblenterprise.setVisible(false);
            lblorganisation.setVisible(false);
            txtdepartment.setVisible(false);
            txtdivision.setVisible(false);
            txtenterprise.setVisible(false);
            txtorganisation.setVisible(false);
            btnSubmit.setText("Submit");
            String amount = String.valueOf(getSumOfTaxCalculated());
            txttaxamount.setText(amount);
        
            displayBudgetAllocatedDataForPresident();
            displayBudgetDataForPresident();
           

            
        }
         else if(Domain.equals("enterprise"))
        {
            lbldepartment.setVisible(false);
            lbldivision.setVisible(false);
            lblenterprise.setVisible(true);
            lblorganisation.setVisible(false);
            txtdepartment.setVisible(false);
            txtdivision.setVisible(false);
            txtenterprise.setVisible(true);
            txtorganisation.setVisible(false);
            displayBudgetDataForPresident();
            displayBudgetAllocatedDataForPresident();

            
        }
         
         else if(Domain.equals("organisation"))
        {
            lbldepartment.setVisible(false);
            lbldivision.setVisible(false);
            lblenterprise.setVisible(true);
            lblorganisation.setVisible(true);
            txtdepartment.setVisible(false);
            txtdivision.setVisible(false);
            txtenterprise.setVisible(true);
            txtorganisation.setVisible(true);
            displayBudgetDataForPresident();
            displayBudgetAllocatedDataForPresident();

            
        }
         
         else if(Domain.equals("department"))
        {
            lbldepartment.setVisible(true);
            lbldivision.setVisible(false);
            lblenterprise.setVisible(true);
            lblorganisation.setVisible(true);
            txtdepartment.setVisible(true);
            txtdivision.setVisible(false);
            txtenterprise.setVisible(true);
            txtorganisation.setVisible(true);
            displayBudgetDataForPresident();

             displayBudgetAllocatedDataForPresident();
        }
         
         else if(Domain.equals("division"))
        {
            lbldepartment.setVisible(true);
            lbldivision.setVisible(true);
            lblenterprise.setVisible(true);
            lblorganisation.setVisible(true);
            txtdepartment.setVisible(true);
            txtdivision.setVisible(true);
            txtenterprise.setVisible(true);
            txtorganisation.setVisible(true);
            lblbody.setVisible(false);
            txtbody.setVisible(false);
           
             
      
            
           
            btnmodify.setVisible(false);
            btnSubmit.setVisible(false);
            lblbudget.setVisible(false);
            txtbudget.setVisible(false);
            displayBudgetDataForPresident();

            
        }
         
         
        tblbudgetallocated.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
               if (!e.getValueIsAdjusting()) {
                   handleRowSelection();
               }
        });
        
        tblbudget.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
           if (!e.getValueIsAdjusting() && tblbudget.getSelectedRow() != -1) {
                    int selectedRow = tblbudget.getSelectedRow();
                    Budget = String.valueOf(tblbudget.getValueAt(selectedRow, 3));

                    // Now 'valueInSecondColumn' contains the value in the 2nd column of the selected row
                    System.out.println("Value in the 2nd column: " + Budget);

                    // Call your method or perform actions with 'valueInSecondColumn' here
                    handleRowSelection2();
                }
        });
        
         tblbudget.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Handle selection changes in tblbudget
                }
            }
        });

        // Add ListSelectionListener to tblbudgetallocated
        tblbudgetallocated.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Handle selection changes in tblbudgetallocated
                }
            }
        });
        
        tblbudget.addMouseListener((MouseListener) new DoubleClickListener(tblbudget));
        tblbudgetallocated.addMouseListener((MouseListener) new DoubleClickListener(tblbudgetallocated));
        
        

    }
    
     public static double getSumOfTaxCalculated() {
        double sum = 0;

        try {
             Connection connection = (Connection) DatabaseConnection.getConnection();
            String sql = "SELECT taxcalculated FROM tax";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                sum += Double.parseDouble(resultSet.getString("taxcalculated"));
            }

            resultSet.close();
            preparedStatement.close();
        
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle SQLException as needed
        }

        return sum;
    }
    
    
    
         private class DoubleClickListener extends MouseAdapter {
        private JTable table;

        public DoubleClickListener(JTable table) {
            this.table = table;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {  // Detect double-click
                clearTableSelection(table);
                 if(table.equals(tblbudget))
                {
                    txttaxamount.setText("");
                    txtbudgetid.setText("");
                    displayBudgetAllocatedDataForPresident();
                }
                else if(table.equals(tblbudgetallocated))
                {
                    txtbody.setText("");
                    txtbudget.setText("");
                    
                           
                }
            }
        }
    }

    // Method to clear the selection of a table
    private void clearTableSelection(JTable table) {
        table.clearSelection();
    }
    
     public String isStatusInDepartmentTable(int budgetid, String enterprise, String organisation) {
        try {
           
             Connection connection = (Connection) DatabaseConnection.getConnection();
            String sql = "SELECT status FROM department WHERE budgetid = ? AND enterprise = ? AND organisation = ?";
            try (PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, budgetid);
                preparedStatement.setString(2, enterprise);
                preparedStatement.setString(3, organisation);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // If a record is found, return true
                   return resultSet.getString("status");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException as needed
        }

        // Return false if no record is found or an exception occurs
       return "";
    }

     
     
       public String isStatusInDivisionTable(int budgetid, String enterprise, String organisation,String division) {
        try {
           
             Connection connection = (Connection) DatabaseConnection.getConnection();
            String sql = "SELECT status FROM division WHERE budgetid = ? AND enterprise = ? AND organisation = ? AND department = ?";
            try (PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, budgetid);
                preparedStatement.setString(2, enterprise);
                preparedStatement.setString(3, organisation);
                preparedStatement.setString(4, division);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // If a record is found, return true
                   return resultSet.getString("status");
                }
                
              
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException as needed
        }

        // Return false if no record is found or an exception occurs
       return "";
    }
    
    
    
    
    @SuppressWarnings("unchecked")
     private String Domain(int userId) {
        try {
            Connection connection = (Connection) DatabaseConnection.getConnection();

            String sql = "SELECT name, enterprise,organisation,department,division FROM users WHERE userid = ?";
            
            try (PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql)) {
                statement.setInt(1, userId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                         name = resultSet.getString("name");
                        enterpriseValue = resultSet.getString("enterprise");
                        organisationValue = resultSet.getString("organisation");
                        departmentValue = resultSet.getString("department");
                        divisionValue = resultSet.getString("division");
                        
                        String divisionValue = resultSet.getString("division");
                        
                        txtname.setText(name);
                        
                        if(enterpriseValue.equals("--Select--"))
                        {
                            return "president";
                        }
                        else if (organisationValue.equals("--Select--"))
                        {
                            txtenterprise.setText(enterpriseValue);
                            return "enterprise";
                        }
                         else if (departmentValue.equals("--Select--"))
                        {
                             txtenterprise.setText(enterpriseValue);
                             
                             txtorganisation.setText(organisationValue);
                            return "organisation";
                        }
                         else if (divisionValue.equals("--Select--"))
                        {
                            txtenterprise.setText(enterpriseValue);
                            txtorganisation.setText(organisationValue);
                            txtdepartment.setText(departmentValue);
                            return "department";
                        }
                        
                         else{
                            txtenterprise.setText(enterpriseValue);
                            txtorganisation.setText(organisationValue);
                            txtdepartment.setText(departmentValue);
                            txtdivision.setText(divisionValue);
                         }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in a real application
        }

        return "division";
    }
     
    private void displayBudgetDataForPresident() {
    try {
        Connection connection = (Connection) DatabaseConnection.getConnection();
        Integer userId = selectedNUID;  // Assuming selectedNUID is the user's ID
        

        // Check if the user's domain is "president"
        if (Domainname.equals("president")) {
            String sql = "SELECT budgetid, budgetpercentageallocated, budgetamountallocated, status FROM president WHERE userid = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblbudget.getModel();

            // Clear any existing rows in the table
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            // Add rows from the result set to the table
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultSet.getString("budgetid"));
                row.add("President");
                row.add(resultSet.getString("budgetpercentageallocated"));
                row.add(resultSet.getString("budgetamountallocated"));
                row.add(resultSet.getString("status"));
                model.addRow(row);
            }

            resultSet.close();
            preparedStatement.close();
        } 
        else if(Domainname.equals("enterprise"))
        {
            System.out.println("welcome to enterprise");
             String sql = "SELECT budgetid, budgetpercentageallocated, budgetamountallocated, status FROM enterprise WHERE enterprisename = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, enterpriseValue);

            ResultSet resultSet = preparedStatement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblbudget.getModel();

            // Clear any existing rows in the table
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            // Add rows from the result set to the table
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultSet.getString("budgetid"));
                row.add(enterpriseValue);
                row.add(resultSet.getString("budgetpercentageallocated"));
                row.add(resultSet.getString("budgetamountallocated"));
                row.add(resultSet.getString("status"));
                model.addRow(row);
            }

            resultSet.close();
            preparedStatement.close();
            
        }
        
        else if(Domainname.equals("organisation"))
        {
              System.out.println("welcome to Organisation");
             String sql = "SELECT budgetid, budgetpercentageallocated, budgetamountallocated, status FROM organisation WHERE organisation = ? ";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, organisationValue);
         
            
            System.out.println(organisationValue);
            ResultSet resultSet = preparedStatement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblbudget.getModel();

            // Clear any existing rows in the table
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            // Add rows from the result set to the table
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultSet.getString("budgetid"));
                Integer budgetid2;
                  budgetid2 = Integer.parseInt(resultSet.getString("budgetid"));
                row.add(organisationValue);
                row.add(resultSet.getString("budgetpercentageallocated"));
                row.add(resultSet.getString("budgetamountallocated"));
                if(isStatusInDepartmentTable( budgetid2 ,enterpriseValue,organisationValue).equals("Approved"))
                {
                row.add("Approved");
                } else if(isStatusInDepartmentTable( budgetid2 ,enterpriseValue,organisationValue).equals("Pending")) 
                        {
                            
                            row.add("Pending");
                        }
                row.add("Open");
                model.addRow(row);
            }

            resultSet.close();
            preparedStatement.close();
            
        }
        else if(Domainname.equals("department"))
        {
            
            System.out.println("welcome to Department");
             String sql = "SELECT budgetid, budgetpercentageallocated, budgetamountallocated, status FROM department WHERE department = ? ";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, departmentValue);
           
            
            System.out.println(departmentValue);
            ResultSet resultSet = preparedStatement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblbudget.getModel();

            // Clear any existing rows in the table
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            // Add rows from the result set to the table
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultSet.getString("budgetid"));
                row.add(departmentValue);
                row.add(resultSet.getString("budgetpercentageallocated"));
                row.add(resultSet.getString("budgetamountallocated"));
                 Integer budgetid2;
                 budgetid2 = Integer.parseInt(resultSet.getString("budgetid"));
                 if(isStatusInDivisionTable( budgetid2 ,enterpriseValue,organisationValue,departmentValue).equals("Approved"))
                {
                    System.out.println("pending approved");
                row.add("Approved");
                } else if(isStatusInDivisionTable( budgetid2 ,enterpriseValue,organisationValue,departmentValue).equals("Pending")) 
                        {
                           System.out.println("pending status");
                            row.add("Pending");
                        }
                 System.out.println("pending open");
                row.add("Open");
            
                model.addRow(row);
            }

            resultSet.close();
            preparedStatement.close();
            
        }
        else if (Domainname.equals("division"))
        {
             System.out.println("welcome to Division");
             String sql = "SELECT budgetid, budgetpercentageallocated, budgetamountallocated, status FROM division WHERE division = ? AND status = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, divisionValue);
            preparedStatement.setString(2, "Approved");
            
            System.out.println(departmentValue);
            ResultSet resultSet = preparedStatement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblbudget.getModel();

            // Clear any existing rows in the table
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            // Add rows from the result set to the table
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultSet.getString("budgetid"));
                row.add(departmentValue);
                row.add(resultSet.getString("budgetpercentageallocated"));
                row.add(resultSet.getString("budgetamountallocated"));
                row.add("Allocated");
                model.addRow(row);
            }

            resultSet.close();
            preparedStatement.close();
            
        }
        else {
            // Handle the case where the user's domain is not "president"
            JOptionPane.showMessageDialog(this, "User does not have enterprise domain.", "Warning", JOptionPane.WARNING_MESSAGE);
        }

        // Close the database connection (if needed)
        // DatabaseConnection.closeConnection(connection);

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error retrieving budget data.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void displayBudgetAllocatedDataForPresident() {
    try {
        Connection connection = (Connection) DatabaseConnection.getConnection();
        Integer userId = selectedNUID;  // Assuming selectedNUID is the user's ID
        

        // Check if the user's domain is "president"
        if (Domainname.equals("president")) {
            String sql = "SELECT DISTINCT enterprise FROM users";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            

            ResultSet resultSet = preparedStatement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblbudgetallocated.getModel();

            // Clear any existing rows in the table
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            // Add rows from the result set to the table
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                if(!resultSet.getString("enterprise").equals("--Select--"))
                {
                row.add(resultSet.getString("enterprise"));
                row.add("0");
                model.addRow(row);
                }
              
               
            }

            resultSet.close();
            preparedStatement.close();
        } else if (Domainname.equals("enterprise"))
        {
            String sql = "SELECT DISTINCT organisation FROM users where enterprise = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, enterpriseValue);

            ResultSet resultSet = preparedStatement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblbudgetallocated.getModel();

            // Clear any existing rows in the table
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            // Add rows from the result set to the table
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                if(!resultSet.getString("organisation").equals("--Select--"))
                {
                row.add(resultSet.getString("organisation"));
                row.add("0");
                model.addRow(row);
                }
              
               
            }

            resultSet.close();
            preparedStatement.close();
        }
        else if(Domainname.equals("organisation"))
        {
              String sql = "SELECT DISTINCT department FROM users where enterprise = ? AND organisation = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, enterpriseValue);
            preparedStatement.setString(2, organisationValue);

            ResultSet resultSet = preparedStatement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblbudgetallocated.getModel();

            // Clear any existing rows in the table
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            // Add rows from the result set to the table
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                if(!resultSet.getString("department").equals("--Select--"))
                {
                row.add(resultSet.getString("department"));
                row.add("0");
                model.addRow(row);
                }
              
               
            }

            resultSet.close();
            preparedStatement.close();
            
        }
        else if(Domainname.equals("department"))
        {
            
             String sql = "SELECT DISTINCT division FROM users where enterprise = ? AND organisation = ? AND department = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, enterpriseValue);
            preparedStatement.setString(2, organisationValue);
            preparedStatement.setString(3, departmentValue);

            ResultSet resultSet = preparedStatement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblbudgetallocated.getModel();

            // Clear any existing rows in the table
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            // Add rows from the result set to the table
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                if(!resultSet.getString("division").equals("--Select--"))
                {
                row.add(resultSet.getString("division"));
                row.add("0");
                model.addRow(row);
                }
              
               
            }

            resultSet.close();
            preparedStatement.close();
            
        }
        
        else {
            // Handle the case where the user's domain is not "president"
            JOptionPane.showMessageDialog(this, "User does not have president domain.", "Warning", JOptionPane.WARNING_MESSAGE);
        }

        // Close the database connection (if needed)
        // DatabaseConnection.closeConnection(connection);

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error retrieving budget allocated data.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    
      private void displayBudgetAllocatedDataForPresidentauto(int budgetid) {
    try {
        Connection connection = (Connection) DatabaseConnection.getConnection();
        Integer userId = selectedNUID;  // Assuming selectedNUID is the user's ID
        

        // Check if the user's domain is "president"
        if (Domainname.equals("president")) {
            String sql = "SELECT DISTINCT enterprise FROM users";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            

            ResultSet resultSet = preparedStatement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblbudgetallocated.getModel();

            // Clear any existing rows in the table
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            // Add rows from the result set to the table
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                if(!resultSet.getString("enterprise").equals("--Select--"))
                {
                row.add(resultSet.getString("enterprise"));
                row.add("0");
                model.addRow(row);
                }
              
               
            }

            resultSet.close();
            preparedStatement.close();
        } else if (Domainname.equals("enterprise"))
        {
            String sql = "SELECT  organisation, budgetpercentageallocated, status FROM organisation where enterprise = ? AND budgetid = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, enterpriseValue);
            preparedStatement.setInt(2, budgetid);

            ResultSet resultSet = preparedStatement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblbudgetallocated.getModel();

            // Clear any existing rows in the table
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            // Add rows from the result set to the table
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                if(!resultSet.getString("organisation").equals("--Select--"))
                {
                row.add(resultSet.getString("organisation"));
                row.add(resultSet.getString("budgetpercentageallocated"));
                model.addRow(row);
                }
              
               
            }

            resultSet.close();
            preparedStatement.close();
        }
        else if(Domainname.equals("organisation"))
        {
            
            String sql = "SELECT  department, budgetpercentageallocated, status FROM department where enterprise = ? AND organisation = ? AND budgetid = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
             preparedStatement.setString(1, enterpriseValue);
            preparedStatement.setString(2, organisationValue);
             preparedStatement.setInt(3, budgetid);

            ResultSet resultSet = preparedStatement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblbudgetallocated.getModel();

            // Clear any existing rows in the table
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            // Add rows from the result set to the table
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                if(!resultSet.getString("department").equals("--Select--"))
                {
                row.add(resultSet.getString("department"));
                row.add(resultSet.getString("budgetpercentageallocated"));
                model.addRow(row);
                }
              
               
            }

            resultSet.close();
            preparedStatement.close();
            
        }
        else if (Domainname.equals("department"))
        {
             String sql = "SELECT  division, budgetpercentageallocated, status FROM division where enterprise = ? AND organisation = ? AND department = ? AND budgetid = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, enterpriseValue);
            preparedStatement.setString(2, organisationValue);
            preparedStatement.setString(3, departmentValue);
            System.out.println("pk");
            preparedStatement.setInt(4, budgetid);

            ResultSet resultSet = preparedStatement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblbudgetallocated.getModel();

            // Clear any existing rows in the table
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            // Add rows from the result set to the table
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                if(!resultSet.getString("division").equals("--Select--"))
                {
                row.add(resultSet.getString("division"));
                row.add(resultSet.getString("budgetpercentageallocated"));
                model.addRow(row);
                }
              
               
            }

            resultSet.close();
            preparedStatement.close();
        }
        else {
            // Handle the case where the user's domain is not "president"
            JOptionPane.showMessageDialog(this, "Budget has not been allocated Yet.", "Warning", JOptionPane.WARNING_MESSAGE);
        }

        // Close the database connection (if needed)
        // DatabaseConnection.closeConnection(connection);

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error retrieving budget allocated data.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    
      private void handleRowSelection() {
        int selectedRow = tblbudgetallocated.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tblbudgetallocated.getModel();

            // Assuming the first column is at index 0 and the second column is at index 1
            Object valueInFirstColumn = model.getValueAt(selectedRow, 0);
            Object valueInSecondColumn = model.getValueAt(selectedRow, 1);
             
            txtbody.setText(valueInFirstColumn.toString());
            txtbudget.setText(valueInSecondColumn.toString());
        }
    }
      
       private void handleRowSelection2() {
        int selectedRow = tblbudget.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tblbudget.getModel();

            // Assuming the first column is at index 0 and the second column is at index 1
            Object valueInFirstColumn = model.getValueAt(selectedRow, 0);
            Object valueInSecondColumn = model.getValueAt(selectedRow, 3);
            Object valueInfiifthColumn = model.getValueAt(selectedRow, 4);
            Integer budgetid = Integer.parseInt(valueInFirstColumn.toString());
            String status = valueInfiifthColumn.toString();
            // Display the values in the text fields
            if(status.equals("Open"))
            {
            displayBudgetAllocatedDataForPresident();
            }
            else{
               displayBudgetAllocatedDataForPresidentauto(budgetid);
            }
            // Display the values in the text fields
            txtbudgetid.setText(valueInFirstColumn.toString());
            txttaxamount.setText(valueInSecondColumn.toString());
        }
    }
      
      
      
     private void modifyBudget() {
        int selectedRow = tblbudgetallocated.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tblbudgetallocated.getModel();

            // Assuming the second column is at index 1
            int columnIndex = 1;
            
            // Update the value in the selected row and column with the value from txtbudget
            model.setValueAt(txtbudget.getText(), selectedRow, columnIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to modify.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
      
      
    

    public boolean isBudgetOrganisationCombinationExists(int budgetid, String organisation) {
    boolean exists = false;

    try {
        Connection connection = (Connection) DatabaseConnection.getConnection();
        String sql = "SELECT COUNT(*) FROM organisation WHERE budgetid = ? AND organisation = ?";
        try (PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, budgetid);
            preparedStatement.setString(2, organisation);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    exists = count > 0;
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle SQLException as needed
    }

    return exists;
}

     public void  updateEnterpiseTable(int budgetid, String enterprise, String status) {
   try {
            Connection connection = (Connection) DatabaseConnection.getConnection();
        String sql = "UPDATE enterprise SET  status = ? WHERE budgetid = ? AND enterprisename= ?";
       try (PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql)) {
          
           preparedStatement.setString(1, status);
           preparedStatement.setInt(2, budgetid);
           preparedStatement.setString(3, enterprise);
          
           System.out.println(status);
             System.out.println(budgetid);
             System.out.println(enterprise);

             
           int rowsAffected = preparedStatement.executeUpdate();
           
           if (rowsAffected > 0) {
               System.out.println("Row updated successfully in enterprise table.");
           } else {
               System.out.println("Failed to update row in enterprise table.");
           }
       }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle SQLException as needed
    }
}
     
     
    public void updateOrganisationTable(int budgetid, String organisation, String status) {
   
        try {
            Connection connection = (Connection) DatabaseConnection.getConnection();
        String sql = "UPDATE organisation SET  status = ? WHERE budgetid = ? AND organisation= ?";
       try (PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql)) {
          
           preparedStatement.setString(1, status);
           preparedStatement.setInt(2, budgetid);
           preparedStatement.setString(3, organisation);
          
           System.out.println(status);
             System.out.println(budgetid);
             System.out.println(organisationValue);

             
           int rowsAffected = preparedStatement.executeUpdate();
           
           if (rowsAffected > 0) {
               System.out.println("Row updated successfully in enterprise table.");
           } else {
               System.out.println("Failed to update row in enterprise table.");
           }
       }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle SQLException as needed
    } 
}
    
    
            
          public void updateDepartmentTable(int budgetid, String department, String status) {
   
        try {
            Connection connection = (Connection) DatabaseConnection.getConnection();
        String sql = "UPDATE department SET  status = ? WHERE budgetid = ? AND department= ?";
       try (PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql)) {
          
           preparedStatement.setString(1, status);
           preparedStatement.setInt(2, budgetid);
           preparedStatement.setString(3, department);
          
           System.out.println(status);
             System.out.println(budgetid);
             System.out.println(departmentValue);

             
           int rowsAffected = preparedStatement.executeUpdate();
           
           if (rowsAffected > 0) {
               System.out.println("Row updated successfully in enterprise table.");
           } else {
               System.out.println("Failed to update row in enterprise table.");
           }
       }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle SQLException as needed
    } 
}
    
    
       public void updateDepartmentTable(int budgetid, String department, String budgetamountall, String status) {
   try {
            Connection connection = (Connection) DatabaseConnection.getConnection();
        String sql = "UPDATE organisation SET budgetpercentageallocated  = ?, status = ? WHERE budgetid = ? AND organisation= ?";
       try (PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql)) {
           preparedStatement.setString(1, budgetamountall);
           preparedStatement.setString(2, status);
           preparedStatement.setInt(3, budgetid);
           preparedStatement.setString(4, department);
           System.out.println( budgetamountall);
           System.out.println( department);
           
           int rowsAffected = preparedStatement.executeUpdate();
           
           if (rowsAffected > 0) {
               System.out.println("Row updated successfully in Organisation table.");
           } else {
               System.out.println("Failed to update row in Organisation table.");
           }
       }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle SQLException as needed
    }
}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
   
     

    
    
     
     
     
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backbtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblbody = new javax.swing.JLabel();
        txtbody = new javax.swing.JTextField();
        lblbudget = new javax.swing.JLabel();
        txtbudget = new javax.swing.JTextField();
        btnmodify = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        allbudgetsp = new javax.swing.JScrollPane();
        tblbudgetallocated = new javax.swing.JTable();
        lbltaxamount = new javax.swing.JLabel();
        txttaxamount = new javax.swing.JTextField();
        lblenterprise = new javax.swing.JLabel();
        txtenterprise = new javax.swing.JTextField();
        lblorganisation = new javax.swing.JLabel();
        txtorganisation = new javax.swing.JTextField();
        lbldepartment = new javax.swing.JLabel();
        txtdepartment = new javax.swing.JTextField();
        txtdivision = new javax.swing.JTextField();
        lbldivision = new javax.swing.JLabel();
        txtbudgetid = new javax.swing.JTextField();
        lblbudgetid = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        spbudget = new javax.swing.JScrollPane();
        tblbudget = new javax.swing.JTable();

        setBackground(new java.awt.Color(0, 102, 0));

        backbtn.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        backbtn.setForeground(new java.awt.Color(255, 255, 255));
        backbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow.png"))); // NOI18N
        backbtn.setText("Home");
        backbtn.setBorder(null);
        backbtn.setBorderPainted(false);
        backbtn.setContentAreaFilled(false);
        backbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtnActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/US logo.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 55)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("United States Government");

        jLabel3.setFont(new java.awt.Font("Segoe UI Variable", 3, 21)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 0));
        jLabel3.setText("Department of Financial Affairs, Washington DC");

        jLabel5.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Please report any critical incidents that may be  challenging the peace and justice of the country. These incidents include, and are not limited to, problems in providing Healthcare,");

        jLabel6.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Education, Safety and basic utilities to the citizens of the United States. Everybody is expected to abide by the constitution regardless of the position.");

        jLabel4.setFont(new java.awt.Font("Segoe UI Variable", 3, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("View the state money allocation within government :");

        lblbody.setBackground(new java.awt.Color(153, 153, 153));
        lblbody.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        lblbody.setForeground(new java.awt.Color(255, 255, 255));
        lblbody.setText("Governing Body :");
        lblbody.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        txtbody.setFont(new java.awt.Font("Segoe UI Black", 1, 21)); // NOI18N
        txtbody.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtbody.setBorder(null);

        lblbudget.setBackground(new java.awt.Color(153, 153, 153));
        lblbudget.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        lblbudget.setForeground(new java.awt.Color(255, 255, 255));
        lblbudget.setText("Budget Allocated :");
        lblbudget.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        txtbudget.setFont(new java.awt.Font("Segoe UI Black", 1, 21)); // NOI18N
        txtbudget.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtbudget.setBorder(null);
        txtbudget.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbudgetActionPerformed(evt);
            }
        });

        btnmodify.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        btnmodify.setForeground(new java.awt.Color(153, 153, 0));
        btnmodify.setText("Modify Budget");
        btnmodify.setBorder(null);
        btnmodify.setBorderPainted(false);
        btnmodify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodifyActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Logged in as :");

        txtname.setBackground(new java.awt.Color(0, 102, 0));
        txtname.setFont(new java.awt.Font("Segoe UI Variable", 1, 24)); // NOI18N
        txtname.setForeground(new java.awt.Color(255, 255, 255));
        txtname.setBorder(null);

        tblbudgetallocated.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Governing Body", "Budget Allocated"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        allbudgetsp.setViewportView(tblbudgetallocated);

        lbltaxamount.setBackground(new java.awt.Color(153, 153, 153));
        lbltaxamount.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        lbltaxamount.setForeground(new java.awt.Color(255, 255, 255));
        lbltaxamount.setText("Total Tax Money Accumulated");
        lbltaxamount.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        txttaxamount.setBackground(new java.awt.Color(0, 102, 0));
        txttaxamount.setFont(new java.awt.Font("Segoe UI Black", 1, 21)); // NOI18N
        txttaxamount.setForeground(new java.awt.Color(255, 255, 255));
        txttaxamount.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txttaxamount.setBorder(null);
        txttaxamount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttaxamountActionPerformed(evt);
            }
        });

        lblenterprise.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        lblenterprise.setForeground(new java.awt.Color(153, 153, 153));
        lblenterprise.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblenterprise.setText("Enterprise:");

        txtenterprise.setBackground(new java.awt.Color(0, 102, 0));
        txtenterprise.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        txtenterprise.setForeground(new java.awt.Color(255, 255, 255));
        txtenterprise.setBorder(null);
        txtenterprise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtenterpriseActionPerformed(evt);
            }
        });

        lblorganisation.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        lblorganisation.setForeground(new java.awt.Color(153, 153, 153));
        lblorganisation.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblorganisation.setText("Organisation:");

        txtorganisation.setBackground(new java.awt.Color(0, 102, 0));
        txtorganisation.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        txtorganisation.setForeground(new java.awt.Color(255, 255, 255));
        txtorganisation.setBorder(null);

        lbldepartment.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        lbldepartment.setForeground(new java.awt.Color(153, 153, 153));
        lbldepartment.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbldepartment.setText("Department:");

        txtdepartment.setBackground(new java.awt.Color(0, 102, 0));
        txtdepartment.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        txtdepartment.setForeground(new java.awt.Color(255, 255, 255));
        txtdepartment.setBorder(null);

        txtdivision.setBackground(new java.awt.Color(0, 102, 0));
        txtdivision.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        txtdivision.setForeground(new java.awt.Color(255, 255, 255));
        txtdivision.setBorder(null);

        lbldivision.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        lbldivision.setForeground(new java.awt.Color(153, 153, 153));
        lbldivision.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbldivision.setText("Division:");

        txtbudgetid.setBackground(new java.awt.Color(0, 102, 0));
        txtbudgetid.setFont(new java.awt.Font("Segoe UI Black", 1, 21)); // NOI18N
        txtbudgetid.setForeground(new java.awt.Color(255, 255, 255));
        txtbudgetid.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtbudgetid.setBorder(null);
        txtbudgetid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbudgetidActionPerformed(evt);
            }
        });

        lblbudgetid.setBackground(new java.awt.Color(153, 153, 153));
        lblbudgetid.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        lblbudgetid.setForeground(new java.awt.Color(255, 255, 255));
        lblbudgetid.setText("Budget ID:");
        lblbudgetid.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        btnSubmit.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        btnSubmit.setForeground(new java.awt.Color(0, 153, 0));
        btnSubmit.setText("Request Budget Change");
        btnSubmit.setBorder(null);
        btnSubmit.setBorderPainted(false);
        btnSubmit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        tblbudget.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "BudgetID", "Governing Body", "BudgetPercentage Allocated", "BudgetAmountAllocated", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spbudget.setViewportView(tblbudget);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblbody)
                                    .addGap(69, 69, 69)
                                    .addComponent(txtbody, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(64, 64, 64)
                                    .addComponent(lblbudget)
                                    .addGap(31, 31, 31)
                                    .addComponent(txtbudget, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(29, 29, 29)
                                    .addComponent(btnmodify, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lbltaxamount)
                                            .addGap(13, 13, 13)
                                            .addComponent(txttaxamount, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel6)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(lblbudgetid)
                                                        .addGap(60, 60, 60)
                                                        .addComponent(txtbudgetid, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jLabel5))))
                                        .addComponent(jLabel4))
                                    .addGap(107, 107, 107)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(spbudget, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(allbudgetsp, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblenterprise)
                            .addGap(5, 5, 5)
                            .addComponent(txtenterprise, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(5, 5, 5)
                            .addComponent(lblorganisation, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(5, 5, 5)
                            .addComponent(txtorganisation, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(5, 5, 5)
                            .addComponent(lbldepartment)
                            .addGap(5, 5, 5)
                            .addComponent(txtdepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(5, 5, 5)
                            .addComponent(lbldivision)
                            .addGap(5, 5, 5)
                            .addComponent(txtdivision, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(backbtn)
                            .addGap(377, 377, 377)
                            .addComponent(jLabel13)
                            .addGap(13, 13, 13)
                            .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(440, 440, 440)
                        .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(338, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backbtn)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblenterprise)
                    .addComponent(txtenterprise, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblorganisation)
                    .addComponent(txtorganisation, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbldepartment)
                    .addComponent(txtdepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbldivision)
                    .addComponent(txtdivision, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttaxamount, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbltaxamount)
                    .addComponent(txtbudgetid, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblbudgetid))
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(allbudgetsp, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(spbudget, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblbody)
                    .addComponent(txtbody, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbudget, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblbudget)
                    .addComponent(btnmodify, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 139, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        // TODO add your handling code here:
        
     GovernmentLandingJPanel govtland = new GovernmentLandingJPanel(mainframe,selectedNUID);
        mainframe.setRightComponent(govtland);
    }//GEN-LAST:event_backbtnActionPerformed

    private void btnmodifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodifyActionPerformed
            // TODO add your handling code here:
          modifyBudget();
    }//GEN-LAST:event_btnmodifyActionPerformed

    private void txttaxamountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttaxamountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttaxamountActionPerformed

    private void txtbudgetidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbudgetidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbudgetidActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
         int total = calculateTotalInSecondColumn();
        if(Domainname.equals("president"))
        {
            
        // Check if the total is equal to 90
        if (total == 90) {
            // Retrieve values from the first two rows of tblbudgetallocated
            int budgetid = Integer.parseInt(txtbudgetid.getText());
            
            String enterpriseValues = getValueFromRow(tblbudgetallocated, 0);
            String[] valuesArray = enterpriseValues.split(",");
            String enterprisePercentValues = getValueFromRow(tblbudgetallocated, 1);
             String[] valuesArray2 = enterprisePercentValues.split(",");
            String budgetpercentageallocated = "90";
            String budgetamountallocated = txttaxamount.getText();
            
            
            String Status = "Approved";
            
            
            // Insert the values into the president table (you need to implement this part)
//            insertIntoPresidentTable(budgetid, selectedNUID, valuesArray[0],valuesArray[1],valuesArray[2], valuesArray[3],valuesArray2[0],valuesArray2[1],valuesArray2[2],valuesArray2[3],budgetpercentageallocated,budgetamountallocated , Status);
            int rowCount = tblbudgetallocated.getRowCount();
            insertIntoPresidentTable(budgetid, selectedNUID, valuesArray[0], valuesArray[1], valuesArray[2], valuesArray[3], valuesArray2[0], valuesArray2[1], valuesArray2[2], valuesArray2[3], budgetpercentageallocated, budgetamountallocated, Status);
            JOptionPane.showMessageDialog(this, "Values inserted into the president table.", "Success", JOptionPane.INFORMATION_MESSAGE);
            for (int i = 0; i < rowCount; i++) {
                    String enterpriseValuesRow = getValueFromRow2(tblbudgetallocated, i);
                    String[] valuesArrayRow = enterpriseValuesRow.split(",");
                 

                    // Check if valuesArrayRow[1] is not empty before parsing
                    if (!valuesArrayRow[1].isEmpty()) {
                       
                          double parsedValue = Double.parseDouble(valuesArrayRow[1]);
                      
                          double parsedvalue2 = Double.parseDouble(txttaxamount.getText());
                         
                         
                        String budgetamountall = Double.toString((parsedValue * parsedvalue2 ) / 100.0);
                       
             
                       
                        insertIntoEnterpriseTable(budgetid, selectedNUID, "Employee", valuesArrayRow[0], valuesArrayRow[1], budgetamountall, "Open");
                    } else {
                        
                    }
                }

            JOptionPane.showMessageDialog(this, "Values inserted into the enterprise table.", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Display a success message or perform any other actions
            
            displayBudgetDataForPresident();
        } else {
            // Display a message indicating that the total is not equal to 90
            JOptionPane.showMessageDialog(this, "Total is not equal to 90.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        }
        
        else if(Domainname.equals("enterprise"))
        {
            
              // Check if the total is equal to 90
        if (total == 100) {
    int budgetid = Integer.parseInt(txtbudgetid.getText());
    int rowCount = tblbudgetallocated.getRowCount();

    for (int i = 0; i < rowCount; i++) {
        String enterpriseValuesRow = getValueFromRow2(tblbudgetallocated, i);
        String[] valuesArrayRow = enterpriseValuesRow.split(",");
     

        // Check if valuesArrayRow[1] is not empty before parsing
        if (!valuesArrayRow[1].isEmpty()) {
       
            double parsedValue = Double.parseDouble(valuesArrayRow[1]);
           
            double parsedvalue2 = Double.parseDouble(txttaxamount.getText());
       

            String budgetamountall = Double.toString((parsedValue * parsedvalue2) / 100.0);
          

            // Check if the combination of budgetid and organisation already exists
            if (isBudgetOrganisationCombinationExists(budgetid, valuesArrayRow[0])) {
                // Update the existing row
                
//                updateOrganisationTable(budgetid, valuesArrayRow[0],valuesArrayRow[1] , "Pending");
            } else {
                // Insert a new row
                insertIntoOrganisationTable(budgetid, selectedNUID, "Employee", valuesArrayRow[0], valuesArrayRow[1], budgetamountall, "Pending");
                System.out.println("update enterprise");
                updateEnterpiseTable(budgetid,enterpriseValue , "Pending");
            }
        } else {
            System.out.println("Skipping row because valuesArrayRow[1] is empty.");
        }
    }

    JOptionPane.showMessageDialog(this, "Values inserted into the Organisation table.", "Success", JOptionPane.INFORMATION_MESSAGE);
    displayBudgetDataForPresident();
} else {
            // Display a message indicating that the total is not equal to 90
            JOptionPane.showMessageDialog(this, "Total is not equal to 100.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        }
   else if(Domainname.equals("organisation"))
   {
             // Check if the total is equal to 90
        if (total == 100) {
    int budgetid = Integer.parseInt(txtbudgetid.getText());
    int rowCount = tblbudgetallocated.getRowCount();

    for (int i = 0; i < rowCount; i++) {
        String enterpriseValuesRow = getValueFromRow2(tblbudgetallocated, i);
        String[] valuesArrayRow = enterpriseValuesRow.split(",");
     

        // Check if valuesArrayRow[1] is not empty before parsing
        if (!valuesArrayRow[1].isEmpty()) {
       
            double parsedValue = Double.parseDouble(valuesArrayRow[1]);
           
            double parsedvalue2 = Double.parseDouble(txttaxamount.getText());
       

            String budgetamountall = Double.toString((parsedValue * parsedvalue2) / 100.0);
          

            // Check if the combination of budgetid and organisation already exists
           
                // Insert a new row
                updateOrganisationTable(budgetid,organisationValue , "Pending");
                insertIntoDepartmentTable(budgetid, selectedNUID, "Employee", valuesArrayRow[0], valuesArrayRow[1], budgetamountall, "Pending");
            
        } else {
            System.out.println("Skipping row because valuesArrayRow[1] is empty.");
        }
    }

    JOptionPane.showMessageDialog(this, "Values inserted into the Organisation table.", "Success", JOptionPane.INFORMATION_MESSAGE);
    displayBudgetDataForPresident();
       
   }
        else {
           // Display a message indicating that the total is not equal to 90
           JOptionPane.showMessageDialog(this, "Total is not equal to 100.", "Warning", JOptionPane.WARNING_MESSAGE);
       }
   }
   else if(Domainname.equals("department"))
   {
              // Check if the total is equal to 90
        if (total == 100) {
    int budgetid = Integer.parseInt(txtbudgetid.getText());
    int rowCount = tblbudgetallocated.getRowCount();

    for (int i = 0; i < rowCount; i++) {
        String enterpriseValuesRow = getValueFromRow2(tblbudgetallocated, i);
        String[] valuesArrayRow = enterpriseValuesRow.split(",");
     

        // Check if valuesArrayRow[1] is not empty before parsing
        if (!valuesArrayRow[1].isEmpty()) {
       
            double parsedValue = Double.parseDouble(valuesArrayRow[1]);
           
            double parsedvalue2 = Double.parseDouble(txttaxamount.getText());
       

            String budgetamountall = Double.toString((parsedValue * parsedvalue2) / 100.0);
          

            // Check if the combination of budgetid and organisation already exists
           
                // Insert a new row
                  updateDepartmentTable(budgetid,departmentValue , "Pending");
                insertIntoDivisionTable(budgetid, selectedNUID, "Employee", valuesArrayRow[0], valuesArrayRow[1], budgetamountall, "Pending");
            
        } else {
            System.out.println("Skipping row because valuesArrayRow[1] is empty.");
        }
    }

    
    displayBudgetDataForPresident();
       
   }
   } else {
            // Display a message indicating that the total is not equal to 90
            JOptionPane.showMessageDialog(this, "Total is not equal to 100.", "Warning", JOptionPane.WARNING_MESSAGE);
        }

        
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void txtbudgetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbudgetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbudgetActionPerformed

    private void txtenterpriseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtenterpriseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtenterpriseActionPerformed

    private int calculateTotalInSecondColumn() {
        DefaultTableModel model = (DefaultTableModel) tblbudgetallocated.getModel();
        int rowCount = model.getRowCount();
        int total = 0;

        for (int i = 0; i < rowCount; i++) {
            Object value = model.getValueAt(i, 1); // Assuming the second column is at index 1

            // Convert the value to integer and add to the total
            total += Integer.parseInt(value.toString());
        }

        return total;
    }
    
     private String getValueFromRow(JTable table, int column) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        StringBuilder values = new StringBuilder();

        for (int i = 0; i < rowCount; i++) {
            Object value = model.getValueAt(i,column);
            values.append(value).append(", ");
        }

        // Remove the trailing comma and space
        values.setLength(values.length() - 2);

        return values.toString();
    }
     
       private String getValueFromRow2(JTable table,int row) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        StringBuilder values = new StringBuilder();

      
            Object value = model.getValueAt(row,0);
            Object value2 = model.getValueAt(row,1);
            values.append(value).append(", ");
            values.append(value2).append(", ");
       

        // Remove the trailing comma and space
        values.setLength(values.length() - 2);

        return values.toString();
    }
     
     private void insertIntoPresidentTable(int budgetid, int userid,String enterprise1,String enterprise2,String  enterprise3, String enterprise4, String  enterprise1percent,String  enterprise2percent,String  enterprise3percent,String  enterprise4percent,String budgetpercentageallocated, String budgetamountallocated, String  status) {
    try {
        Connection connection = (Connection) DatabaseConnection.getConnection();
        // Adjust the SQL statement based on the actual columns in your president table
        String sql = "INSERT INTO president (budgetid,userid,enterprise1,enterprise2,enterprise3,enterprise4,enterprise1percent,enterprise2percent,enterprise3percent,enterprise4percent,budgetpercentageallocated,budgetamountallocated,status) VALUES (?, ?,?, ?, ?, ?,?, ?, ?, ?, ?, ?,?)";

        PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
        preparedStatement.setInt(1, budgetid);
        preparedStatement.setInt(2, userid);
        preparedStatement.setString(3, enterprise1);
        preparedStatement.setString(4, enterprise2);
        preparedStatement.setString(5, enterprise3);
        preparedStatement.setString(6, enterprise4);
        preparedStatement.setString(7, enterprise1percent);
        preparedStatement.setString(8, enterprise2percent);
        preparedStatement.setString(9, enterprise3percent);
        preparedStatement.setString(10, enterprise4percent);
        preparedStatement.setString(11, budgetpercentageallocated);
        preparedStatement.setString(12, budgetamountallocated);
        preparedStatement.setString(13, status);

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Values inserted into the president table.");
            System.out.println("Values inserted into the president table.");
        } else {
            System.out.println("Failed to insert values into the president table.");
            JOptionPane.showMessageDialog(null, "Failed to insert values into the president table.");
        }

        preparedStatement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
     
     
     
     private void insertIntoEnterpriseTable(int budgetid, int userid,String role,String enterprisename, String budgetpercentageallocated, String budgetamountallocated, String  status) {
    try {
        Connection connection = (Connection) DatabaseConnection.getConnection();
        // Adjust the SQL statement based on the actual columns in your president table
        String sql = "INSERT INTO enterprise (budgetid, userid,role,enterprisename, budgetpercentageallocated, budgetamountallocated, status) VALUES (?, ?,?, ?, ?, ?,?)";

        PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
        preparedStatement.setInt(1, budgetid);
        preparedStatement.setInt(2, userid);
        preparedStatement.setString(3, role);
        preparedStatement.setString(4,enterprisename);
        preparedStatement.setString(5, budgetpercentageallocated);
        preparedStatement.setString(6, budgetamountallocated);
        preparedStatement.setString(7, status);

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            
            System.out.println("Values inserted into the Enterprise table.");
        } else {
            System.out.println("Failed to insert values into the Enterprise table.");
            JOptionPane.showMessageDialog(null, "Failed to insert values into the Enterprise table.");
        }

        preparedStatement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
     
     
 private void insertIntoOrganisationTable(int budgetid, int userid,String role,String organisationname,String budgetpercentageallocated, String budgetamountallocated, String  status) {
    try {
        Connection connection = (Connection) DatabaseConnection.getConnection();
        // Adjust the SQL statement based on the actual columns in your president table
        String sql = "INSERT INTO organisation (budgetid, userid,name,role,enterprise,organisation,budgetpercentageallocated, budgetamountallocated, status) VALUES (?, ?,?,?, ?, ?, ?,?, ?)";

        PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
        preparedStatement.setInt(1, budgetid);
        preparedStatement.setInt(2, userid);
        preparedStatement.setString(3, name);
        preparedStatement.setString(4, role);
         preparedStatement.setString(5, enterpriseValue);
        preparedStatement.setString(6,organisationname);
       
     
        preparedStatement.setString(7, budgetpercentageallocated);
        preparedStatement.setString(8, budgetamountallocated);
        preparedStatement.setString(9, status);

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            
            System.out.println("Values inserted into the Organisation table.");
        } else {
            System.out.println("Failed to insert values into the Organisation table.");
            JOptionPane.showMessageDialog(null, "Failed to insert values into the Organisation table.");
        }

        preparedStatement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}    
    private void insertIntoDepartmentTable(int budgetid, int userid,String role,String departmentname,String budgetpercentageallocated, String budgetamountallocated, String  status) {
    try {
        Connection connection = (Connection) DatabaseConnection.getConnection();
        // Adjust the SQL statement based on the actual columns in your president table
        String sql = "INSERT INTO department (budgetid, userid,name,role,enterprise,organisation,department,budgetpercentageallocated, budgetamountallocated, status) VALUES (?, ?,?,?, ?, ?, ?,?, ?,?)";

        PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
        preparedStatement.setInt(1, budgetid);
        preparedStatement.setInt(2, userid);
        preparedStatement.setString(3, name);
        preparedStatement.setString(4, role);
         preparedStatement.setString(5, enterpriseValue);
        preparedStatement.setString(6,organisationValue);
        preparedStatement.setString(7,departmentname);
       
     
        preparedStatement.setString(8, budgetpercentageallocated);
        preparedStatement.setString(9, budgetamountallocated);
        preparedStatement.setString(10, status);

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            
            System.out.println("Values inserted into the Department table.");
        } else {
            System.out.println("Failed to insert values into the Department table.");
            JOptionPane.showMessageDialog(null, "Failed to insert values into the Organisation table.");
        }

        preparedStatement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}  
    
    
     private void insertIntoDivisionTable(int budgetid, int userid,String role,String divisionname,String budgetpercentageallocated, String budgetamountallocated, String  status) {
    try {
        Connection connection = (Connection) DatabaseConnection.getConnection();
        // Adjust the SQL statement based on the actual columns in your president table
        String sql = "INSERT INTO division (budgetid, userid,name,role,enterprise,organisation,department,division,budgetpercentageallocated, budgetamountallocated, status) VALUES (?, ?,?,?, ?, ?, ?,?, ?,?,?)";

        PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
        preparedStatement.setInt(1, budgetid);
        preparedStatement.setInt(2, userid);
        preparedStatement.setString(3, name);
        preparedStatement.setString(4, role);
         preparedStatement.setString(5, enterpriseValue);
        preparedStatement.setString(6,organisationValue);
         preparedStatement.setString(7,departmentValue);
        preparedStatement.setString(8,divisionname);
       
     
        preparedStatement.setString(9, budgetpercentageallocated);
        preparedStatement.setString(10, budgetamountallocated);
        preparedStatement.setString(11, status);

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            
            System.out.println("Values inserted into the Division table.");
        } else {
            System.out.println("Failed to insert values into the Division table.");
            JOptionPane.showMessageDialog(null, "Failed to insert values into the Division table.");
        }

        preparedStatement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
} 
     
     
     private void populateTblBudgetAllocated() {
    try {
        // Assuming you have a DatabaseConnection class to handle database connections
        Connection connection = (Connection) DatabaseConnection.getConnection();

        // Get the selected row and budgetid from tblbudget
        int selectedRow = tblbudget.getSelectedRow();

        if (selectedRow != -1) {
            int budgetid = Integer.parseInt(tblbudget.getValueAt(selectedRow, 0).toString());

            // Query the president table based on the selected budgetid
            String sql = "SELECT enterprise1, enterprise1percent, " +
                         "enterprise2, enterprise2percent, " +
                         "enterprise3, enterprise3percent, " +
                         "enterprise4, enterprise4percent " +
                         "FROM president WHERE budgetid = ?";
            
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setInt(1, budgetid);

            ResultSet resultSet = preparedStatement.executeQuery();

            DefaultTableModel model = (DefaultTableModel) tblbudgetallocated.getModel();

            // Clear any existing rows in tblbudgetallocated
            model.setRowCount(0);

            // Populate tblbudgetallocated with the retrieved values
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultSet.getString("enterprise1"));
                row.add(resultSet.getString("enterprise1percent"));
                model.addRow(row);
                Vector<String> row2 = new Vector<>();
                row2.add(resultSet.getString("enterprise2"));
                row2.add(resultSet.getString("enterprise2percent"));
                model.addRow(row2);
                Vector<String> row3 = new Vector<>();
                row3.add(resultSet.getString("enterprise3"));
                row3.add(resultSet.getString("enterprise3percent"));
                model.addRow(row3);
                Vector<String> row4 = new Vector<>();
                row4.add(resultSet.getString("enterprise4"));
                row4.add(resultSet.getString("enterprise4percent"));
                 model.addRow(row4);
                
            }

            resultSet.close();
            preparedStatement.close();
        }
        
        // Close the database connection (if needed)
        // DatabaseConnection.closeConnection(connection);

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error populating tblbudgetallocated.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane allbudgetsp;
    private javax.swing.JButton backbtn;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnmodify;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblbody;
    private javax.swing.JLabel lblbudget;
    private javax.swing.JLabel lblbudgetid;
    private javax.swing.JLabel lbldepartment;
    private javax.swing.JLabel lbldivision;
    private javax.swing.JLabel lblenterprise;
    private javax.swing.JLabel lblorganisation;
    private javax.swing.JLabel lbltaxamount;
    private javax.swing.JScrollPane spbudget;
    private javax.swing.JTable tblbudget;
    private javax.swing.JTable tblbudgetallocated;
    private javax.swing.JTextField txtbody;
    private javax.swing.JTextField txtbudget;
    private javax.swing.JTextField txtbudgetid;
    private javax.swing.JTextField txtdepartment;
    private javax.swing.JTextField txtdivision;
    private javax.swing.JTextField txtenterprise;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtorganisation;
    private javax.swing.JTextField txttaxamount;
    // End of variables declaration//GEN-END:variables
}
