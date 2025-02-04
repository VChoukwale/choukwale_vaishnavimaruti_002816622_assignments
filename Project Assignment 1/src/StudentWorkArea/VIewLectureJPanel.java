/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package StudentWorkArea;

import Database.DatabaseConnection;
import StudentWorkArea.*;
import ProfessorWorkArea.CreateCourseJPanel;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ui.MainJFrame;

/**
 *
 * @author Abhinav Uni
 */
public class ViewLectureJPanel extends javax.swing.JPanel {

    MainJFrame mainframe;
    private String selectedNUID;
    private String selectedCourse;
    private Integer Lectureid;
    /**
     * Creates new form AddLectureJPanel
     */
    public ViewLectureJPanel(MainJFrame mainframe,Integer Lectureid,String selectedNUID,String selectedCourse) {
        initComponents();
        this.Lectureid =  Lectureid;
        this.mainframe = mainframe;
        this.selectedCourse = selectedCourse;
        
        this.selectedNUID = selectedNUID;
        viewCourseDetails();
    }
    
    
    private void viewCourseDetails() {
//    String NUID = txtNUID.getText();
    
    try {
         Connection connection = (Connection)DatabaseConnection.getConnection();
//        selectedNUID = "1234567890";
        // Define the SQL query to retrieve user details based on NUID
        String sql = "SELECT lecturename, lecture_desc, lecture_link FROM lectures WHERE lectureid = ?";
    
            // Create a PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println(Lectureid + " lectureid from viewstudent");
            // Bind the selectedNUID value to the placeholder
            preparedStatement.setInt(1,  Lectureid);
           

        ResultSet resultSet = preparedStatement.executeQuery();
//         System.out.println(resultSet.getString("Name") + "hello");
        if (resultSet.next()) {
            // Populate the text fields with retrieved data
        String LectureName = resultSet.getString("lecturename");
        String LectureLink = resultSet.getString("lecture_link");
        String LectureDesc = resultSet.getString("lecture_desc");
        

        // Now, populate your UI fields with the retrieved data
       
  
        txtLectureName.setText(LectureName);
        txtLectureLink.setText(LectureLink);
        txtLectureDesc.setText(LectureDesc);
        

            
            
            // ... set the other fields similarly
        } else {
            System.out.println("User not found.");
        }

        resultSet.close();
        preparedStatement.close();
       
    } catch (SQLException e) {
        e.printStackTrace();
    }finally {
//        DatabaseConnection.closeConnection();
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

        jLabel1 = new javax.swing.JLabel();
        lblLectureName1 = new javax.swing.JLabel();
        txtLectureName = new javax.swing.JTextField();
        lbllecturelink1 = new javax.swing.JLabel();
        txtLectureLink = new javax.swing.JTextField();
        lblLectureDesc1 = new javax.swing.JLabel();
        txtLectureDesc = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 34)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("View Lecture");

        lblLectureName1.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        lblLectureName1.setForeground(new java.awt.Color(153, 153, 153));
        lblLectureName1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLectureName1.setText("Lecture Name :");

        txtLectureName.setBorder(null);

        lbllecturelink1.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        lbllecturelink1.setForeground(new java.awt.Color(153, 153, 153));
        lbllecturelink1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbllecturelink1.setText("lecture Link :");

        txtLectureLink.setBorder(null);
        txtLectureLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLectureLinkActionPerformed(evt);
            }
        });

        lblLectureDesc1.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        lblLectureDesc1.setForeground(new java.awt.Color(153, 153, 153));
        lblLectureDesc1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLectureDesc1.setText("Lecture Desc :");

        txtLectureDesc.setBorder(null);

        jButton1.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 102));
        jButton1.setText("Watch Lecture");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(255, 102, 51));
        btnBack.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back (1).png"))); // NOI18N
        btnBack.setText("Back");
        btnBack.setBorder(null);
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(txtLectureName, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(262, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(lblLectureDesc1)
                            .addGap(18, 18, 18)
                            .addComponent(txtLectureDesc))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(lbllecturelink1)
                            .addGap(18, 18, 18)
                            .addComponent(txtLectureLink, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblLectureName1)
                            .addGap(367, 547, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(jLabel1)
                .addGap(62, 62, 62)
                .addComponent(txtLectureName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(222, 222, 222)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbllecturelink1)
                        .addComponent(txtLectureLink, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLectureName1))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblLectureDesc1)
                        .addComponent(txtLectureDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(182, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtLectureLinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLectureLinkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLectureLinkActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:

        ViewStudentCourse createcourse = new ViewStudentCourse(mainframe,selectedNUID,selectedCourse);
        mainframe.setRightComponent(createcourse);
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblLectureDesc1;
    private javax.swing.JLabel lblLectureName1;
    private javax.swing.JLabel lbllecturelink1;
    private javax.swing.JTextField txtLectureDesc;
    private javax.swing.JTextField txtLectureLink;
    private javax.swing.JTextField txtLectureName;
    // End of variables declaration//GEN-END:variables
}
