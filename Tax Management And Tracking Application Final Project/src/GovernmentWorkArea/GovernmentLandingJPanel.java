/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GovernmentWorkArea;

import Database.DatabaseConnection;
import LandingUI.MainJFrame;
import Menus.GovtMenu;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Abhinav Uni
 */
public class GovernmentLandingJPanel extends javax.swing.JPanel {
    
    private MainJFrame mainframe;
    private int  selectedNUID;

    /**
     * Creates new form LandingJPanel
     */
    public GovernmentLandingJPanel(MainJFrame mainframe,int selectedNUID) {
        initComponents();
        
        this.mainframe = mainframe;
        this.selectedNUID = selectedNUID;
        
        boolean adminUser = isAdminUser(selectedNUID);
        if(!adminUser)
        {
            lbladmintext.setVisible(false);
            lbladmin.setVisible(false);
        }
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
      public boolean isAdminUser(int selectedNUID) {
          Connection connection = (Connection) DatabaseConnection.getConnection();
        String sql = "SELECT role FROM users WHERE userid = ?";
        try (PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql)) {
            statement.setInt(1, selectedNUID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String userRole = resultSet.getString("role");
                    // Assuming role is a case-sensitive string, adjust accordingly
                    return "Admin".equals(userRole);
                }
            }
        } catch (SQLException e) {
            // Handle SQLException
            e.printStackTrace();
        }

        // Return false if user not found or in case of an exception
        return false;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblorganisation = new javax.swing.JLabel();
        lblenterprise = new javax.swing.JLabel();
        txtenterprise = new javax.swing.JTextField();
        txtorganisation = new javax.swing.JTextField();
        lbldepartment = new javax.swing.JLabel();
        txtdepartment = new javax.swing.JTextField();
        lbldivision = new javax.swing.JLabel();
        txtdivision = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        manageresbtn = new javax.swing.JButton();
        budgetallocbtn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        citcomplaintbtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        govtfeedbackbtn = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        logininfo = new javax.swing.JTextField();
        lbladmin = new javax.swing.JButton();
        lbladmintext = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        dashboardbtn = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        dashboardbtn1 = new javax.swing.JButton();
        lblorganisation1 = new javax.swing.JLabel();
        lblenterprise1 = new javax.swing.JLabel();
        txtenterprise1 = new javax.swing.JTextField();
        txtorganisation1 = new javax.swing.JTextField();
        lbldepartment1 = new javax.swing.JLabel();
        txtdepartment1 = new javax.swing.JTextField();
        lbldivision1 = new javax.swing.JLabel();
        txtdivision1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        dashboardbtn2 = new javax.swing.JButton();

        lblorganisation.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        lblorganisation.setForeground(new java.awt.Color(153, 153, 153));
        lblorganisation.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblorganisation.setText("Organisation:");

        lblenterprise.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        lblenterprise.setForeground(new java.awt.Color(153, 153, 153));
        lblenterprise.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblenterprise.setText("Enterprise:");

        txtenterprise.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        txtenterprise.setBorder(null);

        txtorganisation.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        txtorganisation.setBorder(null);

        lbldepartment.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        lbldepartment.setForeground(new java.awt.Color(153, 153, 153));
        lbldepartment.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbldepartment.setText("Department:");

        txtdepartment.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        txtdepartment.setBorder(null);

        lbldivision.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        lbldivision.setForeground(new java.awt.Color(153, 153, 153));
        lbldivision.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbldivision.setText("Division:");

        txtdivision.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        txtdivision.setBorder(null);

        setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/US logo.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 55)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("United States Government");

        jLabel3.setFont(new java.awt.Font("Segoe UI Variable", 3, 21)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 0));
        jLabel3.setText("Department of Internal Affairs, Washington DC");

        jLabel5.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Please report any critical incidents that may be  challenging the peace and justice of the country. These incidents include, and are not limited to, problems in providing Healthcare,");

        jLabel6.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Education, Safety and basic utilities to the citizens of the United States. Everybody is expected to abide by the constitution regardless of the position.");

        jLabel7.setBackground(new java.awt.Color(0, 102, 51));
        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 1, 34)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Lorem ipsum dolor  sit ");

        jLabel8.setBackground(new java.awt.Color(0, 102, 51));
        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 1, 34)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("<one liner desc or tagline>.");

        jLabel4.setBackground(new java.awt.Color(0, 102, 51));
        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 34)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Lorem ipsum.  dolor sit. ");

        jLabel9.setFont(new java.awt.Font("Segoe UI Variable", 3, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 153, 153));
        jLabel9.setText("view government structure");

        manageresbtn.setBackground(new java.awt.Color(0, 102, 51));
        manageresbtn.setFont(new java.awt.Font("Segoe UI Variable", 1, 21)); // NOI18N
        manageresbtn.setForeground(new java.awt.Color(255, 255, 255));
        manageresbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/right-arrow.png"))); // NOI18N
        manageresbtn.setText("Manage Resources   ");
        manageresbtn.setBorder(null);
        manageresbtn.setBorderPainted(false);
        manageresbtn.setContentAreaFilled(false);
        manageresbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        manageresbtn.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        manageresbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageresbtnActionPerformed(evt);
            }
        });

        budgetallocbtn.setBackground(new java.awt.Color(0, 102, 51));
        budgetallocbtn.setFont(new java.awt.Font("Segoe UI Variable", 1, 21)); // NOI18N
        budgetallocbtn.setForeground(new java.awt.Color(255, 255, 255));
        budgetallocbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/right-arrow.png"))); // NOI18N
        budgetallocbtn.setText("Budget Allocation    ");
        budgetallocbtn.setBorder(null);
        budgetallocbtn.setBorderPainted(false);
        budgetallocbtn.setContentAreaFilled(false);
        budgetallocbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        budgetallocbtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        budgetallocbtn.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        budgetallocbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                budgetallocbtnActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Variable", 3, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setText("view & update budget");

        citcomplaintbtn.setBackground(new java.awt.Color(0, 102, 51));
        citcomplaintbtn.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        citcomplaintbtn.setForeground(new java.awt.Color(255, 255, 255));
        citcomplaintbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/right-arrow.png"))); // NOI18N
        citcomplaintbtn.setText("Citizen Complaints");
        citcomplaintbtn.setBorder(null);
        citcomplaintbtn.setBorderPainted(false);
        citcomplaintbtn.setContentAreaFilled(false);
        citcomplaintbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        citcomplaintbtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        citcomplaintbtn.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        citcomplaintbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                citcomplaintbtnActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI Variable", 3, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(153, 153, 153));
        jLabel11.setText("View Complaints");

        govtfeedbackbtn.setBackground(new java.awt.Color(0, 102, 51));
        govtfeedbackbtn.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        govtfeedbackbtn.setForeground(new java.awt.Color(255, 255, 255));
        govtfeedbackbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/right-arrow.png"))); // NOI18N
        govtfeedbackbtn.setText("Internal Feedback");
        govtfeedbackbtn.setBorder(null);
        govtfeedbackbtn.setBorderPainted(false);
        govtfeedbackbtn.setContentAreaFilled(false);
        govtfeedbackbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        govtfeedbackbtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        govtfeedbackbtn.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        govtfeedbackbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                govtfeedbackbtnActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI Variable", 3, 10)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setText("Give Feedback");

        jLabel13.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setText("logged in as :");

        logininfo.setBackground(new java.awt.Color(0, 0, 0));
        logininfo.setFont(new java.awt.Font("Segoe UI Variable", 1, 21)); // NOI18N
        logininfo.setForeground(new java.awt.Color(255, 255, 255));
        logininfo.setBorder(null);

        lbladmin.setBackground(new java.awt.Color(0, 102, 51));
        lbladmin.setFont(new java.awt.Font("Segoe UI Variable", 1, 21)); // NOI18N
        lbladmin.setForeground(new java.awt.Color(255, 255, 255));
        lbladmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/right-arrow.png"))); // NOI18N
        lbladmin.setText("Admin Portal             ");
        lbladmin.setBorder(null);
        lbladmin.setBorderPainted(false);
        lbladmin.setContentAreaFilled(false);
        lbladmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbladmin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbladmin.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lbladmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbladminActionPerformed(evt);
            }
        });

        lbladmintext.setFont(new java.awt.Font("Segoe UI Variable", 3, 13)); // NOI18N
        lbladmintext.setForeground(new java.awt.Color(153, 153, 153));
        lbladmintext.setText("view internal requests");

        jLabel15.setFont(new java.awt.Font("Segoe UI Variable", 3, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 153, 153));
        jLabel15.setText("get insights");

        dashboardbtn.setBackground(new java.awt.Color(0, 102, 51));
        dashboardbtn.setFont(new java.awt.Font("Segoe UI Variable", 1, 21)); // NOI18N
        dashboardbtn.setForeground(new java.awt.Color(255, 255, 255));
        dashboardbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/right-arrow.png"))); // NOI18N
        dashboardbtn.setText("Dashboard                ");
        dashboardbtn.setBorder(null);
        dashboardbtn.setBorderPainted(false);
        dashboardbtn.setContentAreaFilled(false);
        dashboardbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboardbtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dashboardbtn.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        dashboardbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardbtnActionPerformed(evt);
            }
        });

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/us govt icon.png"))); // NOI18N
        jLabel17.setText("jLabel17");

        jLabel16.setFont(new java.awt.Font("Segoe UI Variable", 3, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 153, 153));
        jLabel16.setText("manage emergency funds");

        dashboardbtn1.setBackground(new java.awt.Color(0, 102, 51));
        dashboardbtn1.setFont(new java.awt.Font("Segoe UI Variable", 1, 21)); // NOI18N
        dashboardbtn1.setForeground(new java.awt.Color(255, 255, 255));
        dashboardbtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/right-arrow.png"))); // NOI18N
        dashboardbtn1.setText("Emergency Funds     ");
        dashboardbtn1.setBorder(null);
        dashboardbtn1.setBorderPainted(false);
        dashboardbtn1.setContentAreaFilled(false);
        dashboardbtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboardbtn1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dashboardbtn1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        dashboardbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardbtn1ActionPerformed(evt);
            }
        });

        lblorganisation1.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        lblorganisation1.setForeground(new java.awt.Color(153, 153, 153));
        lblorganisation1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblorganisation1.setText("Organisation:");

        lblenterprise1.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        lblenterprise1.setForeground(new java.awt.Color(153, 153, 153));
        lblenterprise1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblenterprise1.setText("Enterprise:");

        txtenterprise1.setBackground(new java.awt.Color(0, 0, 0));
        txtenterprise1.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        txtenterprise1.setForeground(new java.awt.Color(255, 255, 255));
        txtenterprise1.setBorder(null);

        txtorganisation1.setBackground(new java.awt.Color(0, 0, 0));
        txtorganisation1.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        txtorganisation1.setForeground(new java.awt.Color(255, 255, 255));
        txtorganisation1.setBorder(null);

        lbldepartment1.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        lbldepartment1.setForeground(new java.awt.Color(153, 153, 153));
        lbldepartment1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbldepartment1.setText("Department:");

        txtdepartment1.setBackground(new java.awt.Color(0, 0, 0));
        txtdepartment1.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        txtdepartment1.setForeground(new java.awt.Color(255, 255, 255));
        txtdepartment1.setBorder(null);

        lbldivision1.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        lbldivision1.setForeground(new java.awt.Color(153, 153, 153));
        lbldivision1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbldivision1.setText("Division:");

        txtdivision1.setBackground(new java.awt.Color(0, 0, 0));
        txtdivision1.setFont(new java.awt.Font("Segoe UI Variable", 1, 8)); // NOI18N
        txtdivision1.setForeground(new java.awt.Color(255, 255, 255));
        txtdivision1.setBorder(null);

        jLabel18.setFont(new java.awt.Font("Segoe UI Variable", 3, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(153, 153, 153));
        jLabel18.setText("view citizen tax history");

        dashboardbtn2.setBackground(new java.awt.Color(0, 102, 51));
        dashboardbtn2.setFont(new java.awt.Font("Segoe UI Variable", 1, 21)); // NOI18N
        dashboardbtn2.setForeground(new java.awt.Color(255, 255, 255));
        dashboardbtn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/right-arrow.png"))); // NOI18N
        dashboardbtn2.setText("Tax History");
        dashboardbtn2.setBorder(null);
        dashboardbtn2.setBorderPainted(false);
        dashboardbtn2.setContentAreaFilled(false);
        dashboardbtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboardbtn2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dashboardbtn2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        dashboardbtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardbtn2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(citcomplaintbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(89, 89, 89)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(govtfeedbackbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dashboardbtn1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dashboardbtn)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(manageresbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbladmintext, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbladmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(budgetallocbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(74, 74, 74)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dashboardbtn2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblenterprise1)
                        .addGap(5, 5, 5)
                        .addComponent(txtenterprise1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(lblorganisation1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(txtorganisation1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(lbldepartment1)
                        .addGap(5, 5, 5)
                        .addComponent(txtdepartment1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(lbldivision1)
                        .addGap(5, 5, 5)
                        .addComponent(txtdivision1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(13, 13, 13)
                        .addComponent(logininfo, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(341, 341, 341))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(logininfo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblenterprise1)
                    .addComponent(txtenterprise1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblorganisation1)
                    .addComponent(txtorganisation1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbldepartment1)
                    .addComponent(txtdepartment1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbldivision1)
                    .addComponent(txtdivision1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel3)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel5)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel6)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(78, 78, 78)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(citcomplaintbtn))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(govtfeedbackbtn)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(lbladmintext)
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbladmin)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel9)
                                .addGap(0, 0, 0)
                                .addComponent(manageresbtn)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel10)
                                .addGap(0, 0, 0)
                                .addComponent(budgetallocbtn)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel15)
                                .addGap(0, 0, 0)
                                .addComponent(dashboardbtn))
                            .addComponent(jLabel17))
                        .addGap(21, 21, 21)
                        .addComponent(jLabel16)
                        .addGap(0, 0, 0)
                        .addComponent(dashboardbtn1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addGap(0, 0, 0)
                        .addComponent(dashboardbtn2))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void manageresbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageresbtnActionPerformed
        // TODO add your handling code here:

       ManageResourceJPanel manres = new ManageResourceJPanel(mainframe,selectedNUID);
       mainframe.setRightComponent(manres);
       GovtMenu menu = new GovtMenu(mainframe);
       mainframe.setLeftComponent(menu);

    }//GEN-LAST:event_manageresbtnActionPerformed

    private void budgetallocbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_budgetallocbtnActionPerformed
        // TODO add your handling code here:
        
        BudgetAllocationJPanel budalloc = new BudgetAllocationJPanel(mainframe,selectedNUID);
        mainframe.setRightComponent(budalloc);
        GovtMenu menu = new GovtMenu(mainframe);
       mainframe.setLeftComponent(menu);
    }//GEN-LAST:event_budgetallocbtnActionPerformed

    private void citcomplaintbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_citcomplaintbtnActionPerformed
        // TODO add your handling code here:
        
        ComplaintJPanel complaints = new ComplaintJPanel(mainframe,selectedNUID);
        mainframe.setRightComponent(complaints);
        GovtMenu menu = new GovtMenu(mainframe);
       mainframe.setLeftComponent(menu);
        
    }//GEN-LAST:event_citcomplaintbtnActionPerformed

    private void govtfeedbackbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_govtfeedbackbtnActionPerformed
        // TODO add your handling code here:
        
        FeedbackJPanel feedback = new FeedbackJPanel(mainframe,selectedNUID);
        mainframe.setRightComponent(feedback);
        GovtMenu menu = new GovtMenu(mainframe);
       mainframe.setLeftComponent(menu);
    }//GEN-LAST:event_govtfeedbackbtnActionPerformed

    private void lbladminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbladminActionPerformed
        // TODO add your handling code here:
        
        AdminJPanel admin = new AdminJPanel(mainframe,selectedNUID);
        mainframe.setRightComponent(admin);
        GovtMenu menu = new GovtMenu(mainframe);
       mainframe.setLeftComponent(menu);
        
    }//GEN-LAST:event_lbladminActionPerformed

    private void dashboardbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardbtnActionPerformed
        // TODO add your handling code here:
        
        DashboardJPanel dash = new DashboardJPanel(mainframe,selectedNUID);
        mainframe.setRightComponent(dash);
        GovtMenu menu = new GovtMenu(mainframe);
       mainframe.setLeftComponent(menu);
    }//GEN-LAST:event_dashboardbtnActionPerformed

    private void dashboardbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardbtn1ActionPerformed
        // TODO add your handling code here:
        
        EmergencyFundsJPanel emer = new EmergencyFundsJPanel(mainframe,selectedNUID);
        mainframe.setRightComponent(emer);
        GovtMenu menu = new GovtMenu(mainframe);
       mainframe.setLeftComponent(menu);
    }//GEN-LAST:event_dashboardbtn1ActionPerformed

    private void dashboardbtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardbtn2ActionPerformed
        // TODO add your handling code here:
        
        CitizenTaxHistory cithis = new CitizenTaxHistory(mainframe);
        mainframe.setRightComponent(cithis);
    }//GEN-LAST:event_dashboardbtn2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton budgetallocbtn;
    private javax.swing.JButton citcomplaintbtn;
    private javax.swing.JButton dashboardbtn;
    private javax.swing.JButton dashboardbtn1;
    private javax.swing.JButton dashboardbtn2;
    private javax.swing.JButton govtfeedbackbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton lbladmin;
    private javax.swing.JLabel lbladmintext;
    private javax.swing.JLabel lbldepartment;
    private javax.swing.JLabel lbldepartment1;
    private javax.swing.JLabel lbldivision;
    private javax.swing.JLabel lbldivision1;
    private javax.swing.JLabel lblenterprise;
    private javax.swing.JLabel lblenterprise1;
    private javax.swing.JLabel lblorganisation;
    private javax.swing.JLabel lblorganisation1;
    private javax.swing.JTextField logininfo;
    private javax.swing.JButton manageresbtn;
    private javax.swing.JTextField txtdepartment;
    private javax.swing.JTextField txtdepartment1;
    private javax.swing.JTextField txtdivision;
    private javax.swing.JTextField txtdivision1;
    private javax.swing.JTextField txtenterprise;
    private javax.swing.JTextField txtenterprise1;
    private javax.swing.JTextField txtorganisation;
    private javax.swing.JTextField txtorganisation1;
    // End of variables declaration//GEN-END:variables
}
