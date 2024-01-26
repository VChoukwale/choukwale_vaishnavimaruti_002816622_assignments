/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import Database.DatabaseConnection;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import ui.LandingJPanel;
import ui.MainJFrame;
import UserProfiles.createuserprofile;
import Loginmenus.LoginMenu;

/**
 *
 * @author abhilashkumargorle
 */
public class CreateUserJPanel extends javax.swing.JPanel {
    
    private MainJFrame mainframe;

    /**
     * Creates new form CreateProfileJPanel
     */
    public CreateUserJPanel(MainJFrame mainframe) {
        
        this.mainframe = mainframe;
        initComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        CreateProfileJPanel = new javax.swing.JPanel();
        CreateProfile = new javax.swing.JLabel();
        perName = new javax.swing.JLabel();
        perEmail = new javax.swing.JLabel();
        btnsave = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        perAddress = new javax.swing.JLabel();
        perContactNum = new javax.swing.JLabel();
        perName1 = new javax.swing.JLabel();
        txtNUID = new javax.swing.JTextField();
        perContactNum1 = new javax.swing.JLabel();
        txtProfileType = new javax.swing.JComboBox<>();
        perUsername = new javax.swing.JLabel();
        perPassword = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtContactNumber = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        stubtnBack2 = new javax.swing.JButton();

        jCheckBox1.setText("jCheckBox1");

        setBackground(new java.awt.Color(0, 0, 0));

        CreateProfileJPanel.setBackground(new java.awt.Color(0, 0, 0));
        CreateProfileJPanel.setForeground(new java.awt.Color(255, 255, 255));

        CreateProfile.setFont(new java.awt.Font("Segoe UI Black", 1, 34)); // NOI18N
        CreateProfile.setForeground(new java.awt.Color(255, 255, 255));
        CreateProfile.setText("Create Profile");

        perName.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        perName.setForeground(new java.awt.Color(102, 102, 102));
        perName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        perName.setText("Name :");

        perEmail.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        perEmail.setForeground(new java.awt.Color(102, 102, 102));
        perEmail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        perEmail.setText("email :");

        btnsave.setBackground(new java.awt.Color(0, 102, 0));
        btnsave.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        btnsave.setForeground(new java.awt.Color(255, 255, 255));
        btnsave.setText("Save");
        btnsave.setBorder(null);
        btnsave.setBorderPainted(false);
        btnsave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        txtName.setBackground(new java.awt.Color(0, 0, 0));
        txtName.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        txtName.setForeground(new java.awt.Color(255, 255, 255));
        txtName.setBorder(null);

        perAddress.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        perAddress.setForeground(new java.awt.Color(102, 102, 102));
        perAddress.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        perAddress.setText("Address :");

        perContactNum.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        perContactNum.setForeground(new java.awt.Color(102, 102, 102));
        perContactNum.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        perContactNum.setText("Contact Number :");

        perName1.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        perName1.setForeground(new java.awt.Color(102, 102, 102));
        perName1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        perName1.setText("NUID :");

        txtNUID.setBackground(new java.awt.Color(0, 0, 0));
        txtNUID.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        txtNUID.setForeground(new java.awt.Color(255, 255, 255));
        txtNUID.setBorder(null);
        txtNUID.setFocusTraversalPolicyProvider(true);

        perContactNum1.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        perContactNum1.setForeground(new java.awt.Color(102, 102, 102));
        perContactNum1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        perContactNum1.setText("Profile Type :");

        txtProfileType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Selected--", "Student", "Professor", "Employer" }));
        txtProfileType.setBorder(null);
        txtProfileType.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtProfileType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProfileTypeActionPerformed(evt);
            }
        });

        perUsername.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        perUsername.setForeground(new java.awt.Color(102, 102, 102));
        perUsername.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        perUsername.setText("Username :");

        perPassword.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        perPassword.setForeground(new java.awt.Color(102, 102, 102));
        perPassword.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        perPassword.setText("Password :");

        txtEmail.setBackground(new java.awt.Color(0, 0, 0));
        txtEmail.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(255, 255, 255));
        txtEmail.setBorder(null);

        txtAddress.setBackground(new java.awt.Color(0, 0, 0));
        txtAddress.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        txtAddress.setForeground(new java.awt.Color(255, 255, 255));
        txtAddress.setBorder(null);

        txtContactNumber.setBackground(new java.awt.Color(0, 0, 0));
        txtContactNumber.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        txtContactNumber.setForeground(new java.awt.Color(255, 255, 255));
        txtContactNumber.setBorder(null);

        txtUsername.setBackground(new java.awt.Color(0, 0, 0));
        txtUsername.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(255, 255, 255));
        txtUsername.setBorder(null);

        txtPassword.setBackground(new java.awt.Color(0, 0, 0));
        txtPassword.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword.setBorder(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/student.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 3)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("____________________________________________________________________________________________________________________________________________________________________________________________________");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 3)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("____________________________________________________________________________________________________________________________________________________________________________________________________");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 3)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("__________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 3)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("_______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 3)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("____________________________________________________________________________________________________________________________________________________________________________________________________");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 3)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("____________________________________________________________________________________________________________________________________________________________________________________________________");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 3)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 153, 153));
        jLabel9.setText("____________________________________________________________________________________________________________________________________________________________________________________________________");

        javax.swing.GroupLayout CreateProfileJPanelLayout = new javax.swing.GroupLayout(CreateProfileJPanel);
        CreateProfileJPanel.setLayout(CreateProfileJPanelLayout);
        CreateProfileJPanelLayout.setHorizontalGroup(
            CreateProfileJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CreateProfileJPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CreateProfileJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(CreateProfileJPanelLayout.createSequentialGroup()
                        .addComponent(perName1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CreateProfileJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtNUID, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(CreateProfileJPanelLayout.createSequentialGroup()
                        .addComponent(perName, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CreateProfileJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(CreateProfileJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CreateProfileJPanelLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(perEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(468, 468, 468))
                    .addGroup(CreateProfileJPanelLayout.createSequentialGroup()
                        .addGroup(CreateProfileJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CreateProfileJPanelLayout.createSequentialGroup()
                                .addGap(233, 233, 233)
                                .addComponent(jLabel1))
                            .addGroup(CreateProfileJPanelLayout.createSequentialGroup()
                                .addGap(157, 157, 157)
                                .addComponent(jLabel4))
                            .addGroup(CreateProfileJPanelLayout.createSequentialGroup()
                                .addGap(157, 157, 157)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(79, 79, 79))))
            .addGroup(CreateProfileJPanelLayout.createSequentialGroup()
                .addGroup(CreateProfileJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CreateProfileJPanelLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(CreateProfile))
                    .addGroup(CreateProfileJPanelLayout.createSequentialGroup()
                        .addGroup(CreateProfileJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(CreateProfileJPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(perUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(CreateProfileJPanelLayout.createSequentialGroup()
                                .addComponent(perContactNum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtContactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(61, 61, 61)
                        .addGroup(CreateProfileJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CreateProfileJPanelLayout.createSequentialGroup()
                                .addComponent(perContactNum1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtProfileType, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(CreateProfileJPanelLayout.createSequentialGroup()
                                .addComponent(perPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(CreateProfileJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(CreateProfileJPanelLayout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel6))
                    .addGroup(CreateProfileJPanelLayout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(CreateProfileJPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(perAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CreateProfileJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CreateProfileJPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CreateProfileJPanelLayout.setVerticalGroup(
            CreateProfileJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreateProfileJPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(CreateProfileJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CreateProfileJPanelLayout.createSequentialGroup()
                        .addComponent(CreateProfile)
                        .addGap(55, 55, 55)
                        .addGroup(CreateProfileJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(perName1)
                            .addComponent(txtNUID, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jLabel2))
                    .addComponent(jLabel1))
                .addGap(40, 40, 40)
                .addGroup(CreateProfileJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(perName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(perEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(CreateProfileJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(34, 34, 34)
                .addGroup(CreateProfileJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(perAddress)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jLabel5)
                .addGap(34, 34, 34)
                .addGroup(CreateProfileJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(perContactNum1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CreateProfileJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(perContactNum)
                        .addComponent(txtContactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtProfileType, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(jLabel6)
                .addGap(23, 23, 23)
                .addGroup(CreateProfileJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(perUsername)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(perPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(CreateProfileJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(55, 55, 55)
                .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(370, Short.MAX_VALUE))
        );

        stubtnBack2.setBackground(new java.awt.Color(0, 0, 0));
        stubtnBack2.setForeground(new java.awt.Color(255, 255, 255));
        stubtnBack2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back (1).png"))); // NOI18N
        stubtnBack2.setText("Back");
        stubtnBack2.setBorder(null);
        stubtnBack2.setBorderPainted(false);
        stubtnBack2.setContentAreaFilled(false);
        stubtnBack2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        stubtnBack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stubtnBack2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(stubtnBack2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CreateProfileJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CreateProfileJPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(stubtnBack2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
       private void saveUserDetails() {
    try {
        Integer NUID = Integer.parseInt(txtNUID.getText());
        String name = txtName.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();
        String contactNumber = txtContactNumber.getText();
        String profileType = txtProfileType.getSelectedItem().toString();
        String username = txtUsername.getText();
        String password =txtPassword.getText();
        createuserprofile userProfile = new createuserprofile();


        // Convert the char array to a String
        String hashedPassword = userProfile.hashPassword(password);
        
        userProfile.saveUserDetails(NUID, name, email, address, contactNumber, profileType, username, password,hashedPassword);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid NUID. Please enter a valid numeric value.");
    }
}
    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:
        saveUserDetails();
    }//GEN-LAST:event_btnsaveActionPerformed

    private void stubtnBack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stubtnBack2ActionPerformed
        // TODO add your handling code here:
        LandingJPanel landingpanel = new LandingJPanel(mainframe);
           mainframe.setRightComponent(landingpanel);
           LoginMenu loginmenu = new LoginMenu(mainframe);
        mainframe.setLeftComponent(loginmenu);

        
    }//GEN-LAST:event_stubtnBack2ActionPerformed

    private void txtProfileTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProfileTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProfileTypeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CreateProfile;
    private javax.swing.JPanel CreateProfileJPanel;
    private javax.swing.JButton btnsave;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel perAddress;
    private javax.swing.JLabel perContactNum;
    private javax.swing.JLabel perContactNum1;
    private javax.swing.JLabel perEmail;
    private javax.swing.JLabel perName;
    private javax.swing.JLabel perName1;
    private javax.swing.JLabel perPassword;
    private javax.swing.JLabel perUsername;
    private javax.swing.JButton stubtnBack2;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtContactNumber;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNUID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JComboBox<String> txtProfileType;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
