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
public class AdminJPanel extends javax.swing.JPanel {
    
    private MainJFrame mainframe;
    private int selectedNUID;
    private String Domainname;
    private String Budget;
    private String enterpriseValue;
   
    private String organisationValue;
    private String departmentValue;
     private String divisionValue;
    /**
     * Creates new form AdminJPanel
     */
    public AdminJPanel(MainJFrame mainframe,int selectedNUID) {
        
         initComponents();
        
        this.mainframe = mainframe;
        this.selectedNUID = selectedNUID;
        String Domain = Domain(selectedNUID);
        Domainname = Domain;
       
          if(Domain.equals("enterprise"))
        {
            lbldepartment.setVisible(false);
            lbldivision.setVisible(false);
            lblenterprise.setVisible(true);
            lblorganisation.setVisible(false);
            txtdepartment.setVisible(false);
            txtdivision.setVisible(false);
            txtenterprise.setVisible(true);
            txtorganisation.setVisible(false);
            displayBudgetDataForAdmin();
           

            
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
             displayBudgetDataForAdmin();
            
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
             displayBudgetDataForAdmin();
             
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
        
        tblbudget.addMouseListener((MouseListener) new AdminJPanel.DoubleClickListener(tblbudget));
        tblbudgetallocated.addMouseListener((MouseListener) new AdminJPanel.DoubleClickListener(tblbudgetallocated));
   
        
       

           
      
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
                }
                else if(table.equals(tblbudgetallocated))
                {
//                    txtbody.setText("");
//                    txtbudget.setText("");
//                    
                           
                }
            }
        }
    }
    
    
       private void handleRowSelection() {
        int selectedRow = tblbudgetallocated.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tblbudgetallocated.getModel();

            // Assuming the first column is at index 0 and the second column is at index 1
            Object valueInFirstColumn = model.getValueAt(selectedRow, 0);
            Object valueInSecondColumn = model.getValueAt(selectedRow, 1);

            // Display the values in the text fields
//            txtbody.setText(valueInFirstColumn.toString());
//            txtbudget.setText(valueInSecondColumn.toString());
        }
    }
      
       private void handleRowSelection2() {
        int selectedRow = tblbudget.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tblbudget.getModel();

            // Assuming the first column is at index 0 and the second column is at index 1
            Object valueInFirstColumn = model.getValueAt(selectedRow, 0);
            Object valueInSecondColumn = model.getValueAt(selectedRow, 1);
            Object valueInThirdColumn = model.getValueAt(selectedRow, 2);
            Object valueInFourthColumn = model.getValueAt(selectedRow, 3);

            // Display the values in the text fields
            Integer budgetid = Integer.parseInt(valueInFirstColumn.toString());
            String governingbody = valueInSecondColumn.toString();
            String name = valueInThirdColumn.toString();
            String status = valueInFourthColumn.toString();
            
            displayBudgetAllocatedDataForAdmin(budgetid, governingbody, name, status);
        }
    }

    


    // Method to clear the selection of a table
    private void clearTableSelection(JTable table) {
        table.clearSelection();
    }
    
    
    private void displayBudgetDataForAdmin() {
    try {
        Connection connection = (Connection) DatabaseConnection.getConnection();
        Integer userId = selectedNUID;  // Assuming selectedNUID is the user's ID
        System.out.println(selectedNUID);

        // Check if the user's domain is "president"
        if (Domainname.equals("enterprise")) {
            String sql = "SELECT DISTINCT budgetid, enterprise, name,status FROM organisation WHERE enterprise = ?";
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
                row.add(resultSet.getString("name"));
                row.add(resultSet.getString("status"));
                model.addRow(row);
            }

            resultSet.close();
            preparedStatement.close();
        } 
        else if(Domainname.equals("organisation"))
        {
            System.out.println("welcome to organisation");
             String sql = "SELECT DISTINCT  budgetid, name,status FROM department WHERE enterprise = ? AND organisation = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, enterpriseValue);
             preparedStatement.setString(2, organisationValue);

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
                row.add(organisationValue);
                row.add(resultSet.getString("name"));
                row.add(resultSet.getString("status"));
              
                model.addRow(row);
            }

            resultSet.close();
            preparedStatement.close();
            
        }
        else if (Domainname.equals("department"))
        {
            System.out.println("welcome to Department");
             String sql = "SELECT DISTINCT budgetid, name,status FROM division WHERE enterprise = ? AND organisation = ? AND department = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, enterpriseValue);
            preparedStatement.setString(2, organisationValue);
            preparedStatement.setString(3, departmentValue);

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
                row.add(resultSet.getString("name"));
                row.add(resultSet.getString("status"));
                model.addRow(row);
            }

            resultSet.close();
            preparedStatement.close(); 
        }
        else {
            // Handle the case where the user's domain is not "president"
            JOptionPane.showMessageDialog(this, "User does not have  domain.", "Warning", JOptionPane.WARNING_MESSAGE);
        }

        // Close the database connection (if needed)
        // DatabaseConnection.closeConnection(connection);

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error retrieving budget data.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
    private void displayBudgetAllocatedDataForAdmin(int budgetid,String governingbody,String name, String Status) {
    try {
        Connection connection = (Connection) DatabaseConnection.getConnection();
        Integer userId = selectedNUID;  // Assuming selectedNUID is the user's ID
        

        // Check if the user's domain is "president"
        if (Domainname.equals("enterprise")) {
            String sql = "SELECT budgetid,organisation,budgetpercentageallocated,budgetamountallocated FROM organisation WHERE enterprise = ? AND budgetid = ? AND name = ? AND Status = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
              
             preparedStatement.setString(1, enterpriseValue);
             preparedStatement.setInt(2, budgetid);
             preparedStatement.setString(3, name);
             preparedStatement.setString(4, Status);

            ResultSet resultSet = preparedStatement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblbudgetallocated.getModel();

            // Clear any existing rows in the table
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            // Add rows from the result set to the table
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultSet.getString("budgetid"));
                row.add(resultSet.getString("organisation"));
                row.add(resultSet.getString("budgetpercentageallocated"));
                row.add(resultSet.getString("budgetamountallocated"));
              
                model.addRow(row);
              
               
            }

            resultSet.close();
            preparedStatement.close();
        } else if (Domainname.equals("organisation"))
        {
           String sql = "SELECT budgetid,department,budgetpercentageallocated,budgetamountallocated FROM department WHERE enterprise = ? AND organisation = ? AND budgetid = ? AND name = ? AND Status = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
              System.out.println("og");
             preparedStatement.setString(1, enterpriseValue);
              preparedStatement.setString(2, organisationValue);
             preparedStatement.setInt(3, budgetid);
             preparedStatement.setString(4, name);
             preparedStatement.setString(5, Status);
              System.out.println("enterpriseValue" +  enterpriseValue);
              System.out.println("organisationValue" + organisationValue);
              System.out.println(" budgetid" +   budgetid);
              System.out.println("name" +  name);
              System.out.println("Status" + Status);
            ResultSet resultSet = preparedStatement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblbudgetallocated.getModel();

            // Clear any existing rows in the table
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            // Add rows from the result set to the table
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultSet.getString("budgetid"));
                row.add(resultSet.getString("department"));
                row.add(resultSet.getString("budgetpercentageallocated"));
                row.add(resultSet.getString("budgetamountallocated"));
              
                model.addRow(row);
              
               
            }

            resultSet.close();
            preparedStatement.close();
        }
        
        else if (Domainname.equals("department"))
        {
            
             String sql = "SELECT budgetid,division,budgetpercentageallocated,budgetamountallocated FROM division WHERE enterprise = ? AND organisation = ? AND department = ? AND budgetid = ? AND name = ? AND Status = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
           
             preparedStatement.setString(1, enterpriseValue);
              preparedStatement.setString(2, organisationValue);
              preparedStatement.setString(3, departmentValue);
             preparedStatement.setInt(4, budgetid);
             preparedStatement.setString(5, name);
             preparedStatement.setString(6, Status);
              System.out.println("enterpriseValue" +  enterpriseValue);
              System.out.println("organisationValue" + organisationValue);
               System.out.println("departmentValue" + departmentValue);
              System.out.println(" budgetid" +   budgetid);
              System.out.println("name" +  name);
              System.out.println("Status" + Status);
            ResultSet resultSet = preparedStatement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblbudgetallocated.getModel();

            // Clear any existing rows in the table
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            // Add rows from the result set to the table
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultSet.getString("budgetid"));
                row.add(resultSet.getString("division"));
                row.add(resultSet.getString("budgetpercentageallocated"));
                row.add(resultSet.getString("budgetamountallocated"));
              
                model.addRow(row);
              
               
            }

            resultSet.close();
            preparedStatement.close();
            
        }
        
        else {
            // Handle the case where the user's domain is not "president"
            JOptionPane.showMessageDialog(this, "Budget has not been set Yet", "Warning", JOptionPane.WARNING_MESSAGE);
        }

        // Close the database connection (if needed)
        // DatabaseConnection.closeConnection(connection);

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error retrieving budget allocated data.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
      private String Domain(int userId) {
        try {
            Connection connection = (Connection) DatabaseConnection.getConnection();

            String sql = "SELECT name, enterprise,organisation,department,division FROM users WHERE userid = ?";
            
            try (PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql)) {
                statement.setInt(1, userId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String name = resultSet.getString("name");
                        enterpriseValue = resultSet.getString("enterprise");
                         organisationValue = resultSet.getString("organisation");
                         departmentValue = resultSet.getString("department");
                         divisionValue = resultSet.getString("division");
                        
                        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbudgetallocated = new javax.swing.JTable();
        btnApprove = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txttaxamount = new javax.swing.JTextField();
        lblenterprise = new javax.swing.JLabel();
        txtenterprise = new javax.swing.JTextField();
        lblorganisation = new javax.swing.JLabel();
        txtorganisation = new javax.swing.JTextField();
        lbldepartment = new javax.swing.JLabel();
        txtdepartment = new javax.swing.JTextField();
        lbldivision = new javax.swing.JLabel();
        txtdivision = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtbudgetid = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblbudget = new javax.swing.JTable();

        setBackground(new java.awt.Color(102, 0, 0));
        setForeground(new java.awt.Color(102, 102, 0));

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
        jLabel3.setText("Department of State Affairs, Washington DC");

        jLabel5.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Please report any critical incidents that may be  challenging the peace and justice of the country. These incidents include, and are not limited to, problems in providing Healthcare,");

        jLabel6.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Education, Safety and basic utilities to the citizens of the United States. Everybody is expected to abide by the constitution regardless of the position.");

        jLabel4.setFont(new java.awt.Font("Segoe UI Variable", 3, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("View the state budget change requests:");

        tblbudgetallocated.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Budget ID", "Governing Body", "Budget Percentage Allocated", "Budget Amount Allocated"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblbudgetallocated);

        btnApprove.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        btnApprove.setForeground(new java.awt.Color(0, 153, 0));
        btnApprove.setText("Approve");
        btnApprove.setBorder(null);
        btnApprove.setBorderPainted(false);
        btnApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 0, 0));
        jButton3.setText("Decline");
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);

        jLabel13.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setText("logged in as :");

        txtname.setBackground(new java.awt.Color(102, 0, 0));
        txtname.setFont(new java.awt.Font("Segoe UI Variable", 1, 36)); // NOI18N
        txtname.setForeground(new java.awt.Color(255, 255, 255));
        txtname.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total Tax Money Accumulated :");

        txttaxamount.setBackground(new java.awt.Color(102, 0, 0));
        txttaxamount.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        txttaxamount.setForeground(new java.awt.Color(255, 255, 255));
        txttaxamount.setBorder(null);

        lblenterprise.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        lblenterprise.setForeground(new java.awt.Color(153, 153, 153));
        lblenterprise.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblenterprise.setText("Enterprise:");

        txtenterprise.setBackground(new java.awt.Color(102, 0, 0));
        txtenterprise.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        txtenterprise.setForeground(new java.awt.Color(255, 255, 255));
        txtenterprise.setBorder(null);

        lblorganisation.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        lblorganisation.setForeground(new java.awt.Color(153, 153, 153));
        lblorganisation.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblorganisation.setText("Organisation:");

        txtorganisation.setBackground(new java.awt.Color(102, 0, 0));
        txtorganisation.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        txtorganisation.setForeground(new java.awt.Color(255, 255, 255));
        txtorganisation.setBorder(null);

        lbldepartment.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        lbldepartment.setForeground(new java.awt.Color(153, 153, 153));
        lbldepartment.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbldepartment.setText("Department:");

        txtdepartment.setBackground(new java.awt.Color(102, 0, 0));
        txtdepartment.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        txtdepartment.setForeground(new java.awt.Color(255, 255, 255));
        txtdepartment.setBorder(null);

        lbldivision.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        lbldivision.setForeground(new java.awt.Color(153, 153, 153));
        lbldivision.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbldivision.setText("Division:");

        txtdivision.setBackground(new java.awt.Color(102, 0, 0));
        txtdivision.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        txtdivision.setForeground(new java.awt.Color(255, 255, 255));
        txtdivision.setBorder(null);

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("BudgetID:");

        txtbudgetid.setBackground(new java.awt.Color(102, 0, 0));
        txtbudgetid.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        txtbudgetid.setForeground(new java.awt.Color(255, 255, 255));
        txtbudgetid.setBorder(null);

        tblbudget.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Budget ID", "Governing Body", "Employee", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblbudget);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 52, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(335, 335, 335))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
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
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txttaxamount, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(121, 121, 121)
                                    .addComponent(jLabel8)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtbudgetid, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(663, 663, 663)
                                .addComponent(btnApprove, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(218, 218, 218))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backbtn)
                        .addGap(377, 377, 377)
                        .addComponent(jLabel13)
                        .addGap(13, 13, 13)
                        .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 392, Short.MAX_VALUE)))
                .addContainerGap(173, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblenterprise)
                    .addComponent(txtenterprise, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblorganisation)
                    .addComponent(txtorganisation, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbldepartment)
                    .addComponent(txtdepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbldivision)
                    .addComponent(txtdivision, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel3)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel5)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel6)))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txttaxamount, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtbudgetid, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jLabel4)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnApprove, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addContainerGap(77, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        // TODO add your handling code here:
        
         GovernmentLandingJPanel govtland = new GovernmentLandingJPanel(mainframe,selectedNUID);
        mainframe.setRightComponent(govtland);
    }//GEN-LAST:event_backbtnActionPerformed
    
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
    
    
    
            
            public void  updateOrganisationStatus(int budgetid, String enterprise, String status,String organisation) {
   try {
            Connection connection = (Connection) DatabaseConnection.getConnection();
        String sql = "UPDATE organisation SET  status = ? WHERE budgetid = ? AND enterprise= ? AND organisation = ?";
       try (PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql)) {
          
           preparedStatement.setString(1, status);
           preparedStatement.setInt(2, budgetid);
           preparedStatement.setString(3, enterprise);
            preparedStatement.setString(4, organisation);
          
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
            
            
             
                     
                  public void  updateDepartmentStatus(int budgetid, String enterprise, String status,String organisation, String department) {
   try {
            Connection connection = (Connection) DatabaseConnection.getConnection();
        String sql = "UPDATE organisation SET  status = ? WHERE budgetid = ? AND enterprise= ? AND organisation = ? AND department = ?";
       try (PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql)) {
          
           preparedStatement.setString(1, status);
           preparedStatement.setInt(2, budgetid);
           preparedStatement.setString(3, enterprise);
            preparedStatement.setString(4, organisation);
             preparedStatement.setString(5, department);
          
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
        public void updateOrganisationTable(int budgetid, String enterprise, String user) {
   try {
            Connection connection = (Connection) DatabaseConnection.getConnection();
        String sql = "UPDATE organisation SET  status = ? WHERE budgetid = ? AND name= ? AND enterprise =? ";
       try (PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql)) {
           preparedStatement.setString(1,"Approved" );
           preparedStatement.setInt(2, budgetid);
           preparedStatement.setString(3, user);
           preparedStatement.setString(4, enterprise);
           
           int rowsAffected = preparedStatement.executeUpdate();
           
           if (rowsAffected > 0) {
               System.out.println("Row updated successfully in Organisation table.");
                 displayBudgetDataForAdmin();
           } else {
               System.out.println("Failed to update row in Organisation table.");
           }
       }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle SQLException as needed
    }
}
        
        
           public void updateDepartmentTable(int budgetid, String enterprise, String user) {
   try {
            Connection connection = (Connection) DatabaseConnection.getConnection();
        String sql = "UPDATE department SET  status = ? WHERE budgetid = ? AND name= ? AND enterprise =? AND organisation = ? ";
       try (PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql)) {
           preparedStatement.setString(1,"Approved" );
           preparedStatement.setInt(2, budgetid);
           preparedStatement.setString(3, user);
           preparedStatement.setString(4, enterpriseValue);
           preparedStatement.setString(5, organisationValue);
           
           int rowsAffected = preparedStatement.executeUpdate();
           
           if (rowsAffected > 0) {
               System.out.println("Row updated successfully in Organisation table.");
                 displayBudgetDataForAdmin();
           } else {
               System.out.println("Failed to update row in Organisation table.");
           }
       }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle SQLException as needed
    }
}
           
           
             public void updateDivisionTable(int budgetid, String enterprise, String user) {
   try {
            Connection connection = (Connection) DatabaseConnection.getConnection();
        String sql = "UPDATE division SET  status = ? WHERE budgetid = ? AND name= ? AND enterprise =? AND organisation = ? AND department = ?";
       try (PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql)) {
           preparedStatement.setString(1,"Approved" );
           preparedStatement.setInt(2, budgetid);
           preparedStatement.setString(3, user);
           preparedStatement.setString(4, enterpriseValue);
           preparedStatement.setString(5, organisationValue);
           preparedStatement.setString(6, departmentValue);
           int rowsAffected = preparedStatement.executeUpdate();
           
           if (rowsAffected > 0) {
               System.out.println("Row updated successfully in Organisation table.");
                 displayBudgetDataForAdmin();
           } else {
               System.out.println("Failed to update row in Organisation table.");
           }
       }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle SQLException as needed
    }
}
    private void btnApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveActionPerformed
         // TODO add your handling code here:
     
        if (Domainname.equals("enterprise")) {
    int selectedRow = tblbudget.getSelectedRow();

    if (selectedRow == -1) {
        // Display a notification to select a row first
        JOptionPane.showMessageDialog(this, "Please select a row before approving.", "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }

    DefaultTableModel model = (DefaultTableModel) tblbudget.getModel();

    // Retrieve data from the selected row
    String bud = String.valueOf(model.getValueAt(selectedRow, 0));
    int budgetid = Integer.valueOf(bud);
    
  
    String enterprise = (String) model.getValueAt(selectedRow, 1);
     String  user  = String.valueOf(model.getValueAt(selectedRow, 2));

    // Update the status in the organisation table to "Approved"
    updateOrganisationTable(budgetid,  enterprise, user);
     updateEnterpiseTable(budgetid,enterpriseValue , "Approved");
    // Display a success message or perform other actions
    JOptionPane.showMessageDialog(this, "Status updated to Approved.", "Success", JOptionPane.INFORMATION_MESSAGE);
}

    
       
        else if(Domainname.equals("organisation"))
        {
             int selectedRow = tblbudget.getSelectedRow();

    if (selectedRow == -1) {
        // Display a notification to select a row first
        JOptionPane.showMessageDialog(this, "Please select a row before approving.", "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }

    DefaultTableModel model = (DefaultTableModel) tblbudget.getModel();

    // Retrieve data from the selected row
    String bud = String.valueOf(model.getValueAt(selectedRow, 0));
    int budgetid = Integer.valueOf(bud);
    
  
    String enterprise = (String) model.getValueAt(selectedRow, 1);
     String  user  = String.valueOf(model.getValueAt(selectedRow, 2));

    // Update the status in the organisation table to "Approved"
    updateDepartmentTable(budgetid,  enterprise, user);
    updateOrganisationStatus(budgetid,enterpriseValue , "Approved",departmentValue);
    
     
     System.out.println("hello");
    // Display a success message or perform other actions
    JOptionPane.showMessageDialog(this, "Status updated to Approved.", "Success", JOptionPane.INFORMATION_MESSAGE);
            
        }
        
        else if (Domainname.equals("department"))
        {
            int selectedRow = tblbudget.getSelectedRow();

    if (selectedRow == -1) {
        // Display a notification to select a row first
        JOptionPane.showMessageDialog(this, "Please select a row before approving.", "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }

    DefaultTableModel model = (DefaultTableModel) tblbudget.getModel();

    // Retrieve data from the selected row
    String bud = String.valueOf(model.getValueAt(selectedRow, 0));
    int budgetid = Integer.valueOf(bud);
    
  
    String enterprise = (String) model.getValueAt(selectedRow, 1);
     String  user  = String.valueOf(model.getValueAt(selectedRow, 2));

    // Update the status in the organisation table to "Approved"
    updateDivisionTable(budgetid,  enterprise, user);
     updateDepartmentStatus(budgetid,enterpriseValue , "Approved",departmentValue,divisionValue);

    // Display a success message or perform other actions
    JOptionPane.showMessageDialog(this, "Status updated to Approved.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
else {
            // Display a message indicating that the total is not equal to 90
            JOptionPane.showMessageDialog(this, "Total is not equal to 100.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
           
    }//GEN-LAST:event_btnApproveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backbtn;
    private javax.swing.JButton btnApprove;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbldepartment;
    private javax.swing.JLabel lbldivision;
    private javax.swing.JLabel lblenterprise;
    private javax.swing.JLabel lblorganisation;
    private javax.swing.JTable tblbudget;
    private javax.swing.JTable tblbudgetallocated;
    private javax.swing.JTextField txtbudgetid;
    private javax.swing.JTextField txtdepartment;
    private javax.swing.JTextField txtdivision;
    private javax.swing.JTextField txtenterprise;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtorganisation;
    private javax.swing.JTextField txttaxamount;
    // End of variables declaration//GEN-END:variables
}
