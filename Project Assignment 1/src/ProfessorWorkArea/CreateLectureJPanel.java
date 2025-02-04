/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ProfessorWorkArea;

import Database.DatabaseConnection;
import ProfessorWorkArea.CreateCourseJPanel;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import ui.MainJFrame;

/**
 *
 * @author Abhinav Uni
 */
public class CreateLectureJPanel extends javax.swing.JPanel {

    MainJFrame mainframe;
    private String selectedNUID;
    private Integer selectedCourse;
    /**
     * Creates new form AddLectureJPanel
     */
    public CreateLectureJPanel(MainJFrame mainframe,Integer selectedCourse, String selectedNUID) {
        initComponents();
        this.selectedNUID = selectedNUID;
        this.mainframe = mainframe;
        this.selectedCourse = selectedCourse;
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
        btnLecture = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnCreateCourse = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 34)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Add Lectures");

        lblLectureName1.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        lblLectureName1.setForeground(new java.awt.Color(153, 153, 153));
        lblLectureName1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLectureName1.setText("Lecture Name :");

        txtLectureName.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        txtLectureName.setBorder(null);

        lbllecturelink1.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        lbllecturelink1.setForeground(new java.awt.Color(153, 153, 153));
        lbllecturelink1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbllecturelink1.setText("lecture Link :");

        txtLectureLink.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
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

        txtLectureDesc.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        txtLectureDesc.setBorder(null);

        btnLecture.setForeground(new java.awt.Color(0, 102, 102));
        btnLecture.setText("Add Lecture");
        btnLecture.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLecture.setBorderPainted(false);
        btnLecture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLectureActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(255, 102, 51));
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back (1).png"))); // NOI18N
        btnBack.setText("Back");
        btnBack.setBorder(null);
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnCreateCourse.setBackground(new java.awt.Color(0, 102, 0));
        btnCreateCourse.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateCourse.setText("Save Course");
        btnCreateCourse.setBorder(null);
        btnCreateCourse.setBorderPainted(false);
        btnCreateCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateCourseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCreateCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(txtLectureName, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(282, Short.MAX_VALUE))
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
                .addGap(21, 21, 21)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addComponent(txtLectureName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addComponent(btnLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnCreateCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
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
                    .addContainerGap(192, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtLectureLinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLectureLinkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLectureLinkActionPerformed

    private void btnLectureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLectureActionPerformed
        // TODO add your handling code here:
        String LectureName = txtLectureName.getText();
        String LectureLink = txtLectureLink.getText();
       
        String LectureDesc = txtLectureDesc.getText();

        
        try {
            Connection connection = (Connection) DatabaseConnection.getConnection();

            // Define the SQL INSERT queries
            String insertCourseSql = "INSERT INTO lectures (courseid,lecturename,lecture_desc,lecture_link)";
            insertCourseSql += "VALUES (?, ?,?, ?)";

            

            // Create a PreparedStatement for the course insert
            PreparedStatement coursePreparedStatement = connection.prepareStatement(insertCourseSql);

            // Bind the values to the placeholders for the course
             coursePreparedStatement.setInt(1, selectedCourse);
             coursePreparedStatement.setString(2, LectureName);
            
             coursePreparedStatement.setString(3, LectureLink);
            coursePreparedStatement.setString(4, LectureDesc);
           

            coursePreparedStatement.executeUpdate();

          
            coursePreparedStatement.close();
           

            // Display a success message
            JOptionPane.showMessageDialog(mainframe, "Lecture  saved successfully.", "Save Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Display an error message if there's an issue with saving the data
            JOptionPane.showMessageDialog(mainframe, "Error saving data.", "Save Failed", JOptionPane.ERROR_MESSAGE);
        }



        
        
    }//GEN-LAST:event_btnLectureActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:

        CreateCourseJPanel createcourse= new CreateCourseJPanel(mainframe, selectedNUID);
        mainframe.setRightComponent(createcourse);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnCreateCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateCourseActionPerformed
        // TODO add your handling code here:
        MyCoursesJPanel mycourses = new MyCoursesJPanel(mainframe,selectedNUID);
        mainframe.setRightComponent(mycourses);
    }//GEN-LAST:event_btnCreateCourseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreateCourse;
    private javax.swing.JButton btnLecture;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblLectureDesc1;
    private javax.swing.JLabel lblLectureName1;
    private javax.swing.JLabel lbllecturelink1;
    private javax.swing.JTextField txtLectureDesc;
    private javax.swing.JTextField txtLectureLink;
    private javax.swing.JTextField txtLectureName;
    // End of variables declaration//GEN-END:variables
}
