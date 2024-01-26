/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package StudentWorkArea;

import Database.DatabaseConnection;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import ui.LandingJPanel;
import ui.MainJFrame;
import java.text.DecimalFormat;


/**
 *
 * @author Vaishnavi Choukwale
 */
public class CourseSubmitJPanel extends javax.swing.JPanel {
    
    
    private String selectedNUID;
    private String selectedCourse;
    MainJFrame mainframe;
    /**
     * Creates new form CourseSubmitJPanel
     */
    public CourseSubmitJPanel(MainJFrame mainframe,String selectedNUID, String selectedCourse) {
        initComponents();
        this.selectedNUID = selectedNUID;
        this.selectedCourse = selectedCourse;
        this.mainframe = mainframe;
        viewGradeDetails();
        
    }
    
    private void viewGradeDetails() {
//    String NUID = txtNUID.getText();
    
    try {
         Connection connection = (Connection)DatabaseConnection.getConnection();
//        selectedNUID = "1234567890";
        // Define the SQL query to retrieve user details based on NUID
        String sql = "SELECT grade FROM coursesregistered WHERE courseid = ?";
    
            // Create a PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Bind the selectedNUID value to the placeholder
            preparedStatement.setString(1, selectedCourse);
           

        ResultSet resultSet = preparedStatement.executeQuery();
//         System.out.println(resultSet.getString("Name") + "hello");
        if (resultSet.next()) {
            // Populate the text fields with retrieved data
       
              String grade = resultSet.getString("grade");
        // Now, populate your UI fields with the retrieved data
          txtGrade.setText(grade);
  
     
//        for (int i = 0; i < SemesterComboBox.getItemCount(); i++) {
//            String item = SemesterComboBox.getItemAt(i);
//            if (item.equals(retrievedSemester)) {
//                SemesterComboBox.setSelectedIndex(i);
//                break; // Exit the loop once a match is found
//            }
//        }

            
            
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
    
    public double[] updateAndRetrieveRatings(String selectedCourse, String selectedProfRating, String selectedCourseRating) {
    double[] updatedRatings = new double[2]; // Array to store updated ratings [profRating, courseRating]

    try {
        Connection connection = (Connection) DatabaseConnection.getConnection();

        // Retrieve existing ratings from coursesregistered table
        String sqlRetrieveRatings = "SELECT courseid, prof_rating, course_rating FROM coursesregistered WHERE courseid = ?";
        PreparedStatement retrieveRatingsStatement = connection.prepareStatement(sqlRetrieveRatings);
        retrieveRatingsStatement.setInt(1, Integer.parseInt(selectedCourse));
        ResultSet resultSet = retrieveRatingsStatement.executeQuery();

        double totalProfRating = 0.0;
        double totalCourseRating = 0.0;
        int rowCount = 0;

        // Calculate the average ratings
        while (resultSet.next()) {
            double existingProfRating = resultSet.getDouble("prof_rating");
            double existingCourseRating = resultSet.getDouble("course_rating");

            totalProfRating += existingProfRating;
            totalCourseRating += existingCourseRating;
            rowCount++;
        }

        resultSet.close();
        retrieveRatingsStatement.close();

        // Calculate the new average ratings including the student's ratings
        double newProfRating = (totalProfRating + Double.parseDouble(selectedProfRating)) / (rowCount + 1);
        double newCourseRating = (totalCourseRating + Double.parseDouble(selectedCourseRating)) / (rowCount + 1);

        // Update the courses table with the new averages
        String sqlUpdateCoursesTable = "UPDATE courses SET my_rating = ?, course_rating = ? WHERE courseid = ?";
        PreparedStatement updateCoursesStatement = connection.prepareStatement(sqlUpdateCoursesTable);
        DecimalFormat df = new DecimalFormat("0.00");
        updateCoursesStatement.setDouble(1, Double.parseDouble(df.format(newProfRating)));
        updateCoursesStatement.setDouble(2, Double.parseDouble(df.format(newCourseRating)));
        updateCoursesStatement.setInt(3, Integer.parseInt(selectedCourse));

        int rowsUpdated = updateCoursesStatement.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Update of courses table was successful.");
        } else {
            System.out.println("No rows were updated in courses table. Check the WHERE condition.");
        }

        updateCoursesStatement.close();

        // Store the updated ratings in the array
        
        updatedRatings[0] = Double.parseDouble(df.format(newProfRating));
        System.out.print(Double.parseDouble(df.format(newProfRating)));
        updatedRatings[1] = Double.parseDouble(df.format(newCourseRating));
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle any SQL exceptions that may occur.
    }

    return updatedRatings;
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRateProf = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        txtGrade = new javax.swing.JTextField();
        lblGrade = new javax.swing.JLabel();
        lblRateProf = new javax.swing.JLabel();
        lblRateCourse = new javax.swing.JLabel();
        RateProfComboBox = new javax.swing.JComboBox<>();
        RateCourseComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(255, 255, 255));

        btnRateProf.setBackground(new java.awt.Color(0, 102, 0));
        btnRateProf.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        btnRateProf.setForeground(new java.awt.Color(255, 255, 255));
        btnRateProf.setText("Submit Rating!");
        btnRateProf.setBorder(null);
        btnRateProf.setBorderPainted(false);
        btnRateProf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRateProfActionPerformed(evt);
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
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        txtGrade.setEditable(false);
        txtGrade.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N

        lblGrade.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        lblGrade.setForeground(new java.awt.Color(255, 255, 102));
        lblGrade.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGrade.setText("Your Grade:");

        lblRateProf.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        lblRateProf.setForeground(new java.awt.Color(255, 255, 102));
        lblRateProf.setText("Rate Professor:");

        lblRateCourse.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        lblRateCourse.setForeground(new java.awt.Color(255, 255, 102));
        lblRateCourse.setText("Rate Course :");

        RateProfComboBox.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        RateProfComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--", "1", "2", "3", "4", "5" }));
        RateProfComboBox.setBorder(null);
        RateProfComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        RateCourseComboBox.setFont(new java.awt.Font("Segoe UI Variable", 1, 13)); // NOI18N
        RateCourseComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--", "1", "2", "3", "4", "5" }));
        RateCourseComboBox.setBorder(null);
        RateCourseComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 34)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Course Completed Successfully!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRateProf, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(338, 338, 338)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblRateCourse)
                            .addComponent(lblRateProf)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(lblGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RateCourseComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RateProfComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(158, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(159, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGrade))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRateProf, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RateProfComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRateCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RateCourseComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addComponent(btnRateProf, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(219, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:

        StudentLandingJPanel stulandingpage = new StudentLandingJPanel(mainframe,selectedNUID);
        mainframe.setRightComponent(stulandingpage);

    }//GEN-LAST:event_btnBackActionPerformed

    private void btnRateProfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRateProfActionPerformed
        // TODO add your handling code here:
        String selectedProfRating = RateProfComboBox.getSelectedItem().toString();
        String selectedCourseRating = RateCourseComboBox.getSelectedItem().toString();
        
         try {
            Connection connection = (Connection) DatabaseConnection.getConnection();

            // Define the SQL INSERT query
            String sql = "UPDATE coursesregistered SET prof_rating = ?, course_rating = ? WHERE courseid = ? AND nuid = ?";

            
            // Create a PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Bind the values to the placeholders
           
            System.out.println(selectedProfRating + "selectedProfRating");
                    
            preparedStatement.setInt(1, Integer.parseInt(selectedProfRating));
            preparedStatement.setInt(2, Integer.parseInt(selectedCourseRating));
            preparedStatement.setInt(3, Integer.parseInt(selectedCourse));
            preparedStatement.setInt(4, Integer.parseInt(selectedNUID));
            
            int rowsUpdated = preparedStatement.executeUpdate();
    
            if (rowsUpdated > 0) {
                System.out.println("Update was successful.");
            } else {
                System.out.println("No rows were updated. Check the WHERE conditions.");
            }
            preparedStatement.close();
            updateAndRetrieveRatings(selectedCourse, selectedProfRating,selectedCourseRating);
//            DatabaseConnection.closeConnection(co);

            // Display a success message
             JOptionPane.showMessageDialog(mainframe, "Rating submitted successfully.", "Save Successful", JOptionPane.INFORMATION_MESSAGE);
            
           }catch (SQLException ex) {
            ex.printStackTrace();
            // Display an error message if there's an issue with saving the data
            JOptionPane.showMessageDialog(mainframe, "Error saving data.", "Save Failed", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnRateProfActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> RateCourseComboBox;
    private javax.swing.JComboBox<String> RateProfComboBox;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnRateProf;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblGrade;
    private javax.swing.JLabel lblRateCourse;
    private javax.swing.JLabel lblRateProf;
    private javax.swing.JTextField txtGrade;
    // End of variables declaration//GEN-END:variables
}
