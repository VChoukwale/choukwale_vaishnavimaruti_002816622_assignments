/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserProfiles;

import Database.DatabaseConnection;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;


/**
 *
 * @author abhilashkumargorle
 */
public class createuserprofile {
    
    public static void saveUserDetails(
            int NUID, String name, String email, String address, String contactNumber,
            String profileType, String username, String userPassword,String hashedPassword) {
        try {
            Connection connection = (Connection) DatabaseConnection.getConnection();
            String sql = "INSERT INTO users (nuid, name, email, address, contact_number, profile_type, username, password,hashedpassword) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
            String insertStudent = "INSERT INTO student2 (nuid) VALUES (?)";

            // Check if NUID is already taken
            if (isNUIDTaken(NUID, connection)) {
                JOptionPane.showMessageDialog(null, "NUID is already taken. Please choose a different NUID.");
                return;
            }

            // Check if the username is already taken
            if (isUsernameTaken(username, connection)) {
                JOptionPane.showMessageDialog(null, "Username is already taken. Please choose a different username.");
                return;
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, NUID);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, contactNumber);
            preparedStatement.setString(6, profileType);
            preparedStatement.setString(7, username);
            preparedStatement.setString(8, userPassword);
            preparedStatement.setString(9, hashedPassword);

            int rowsAffected = preparedStatement.executeUpdate();

            PreparedStatement studentPreparedStatement = connection.prepareStatement(insertStudent);
            studentPreparedStatement.setInt(1, NUID);

            studentPreparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "User details saved successfully!");
                System.out.println("User details saved successfully!");
            } else {
                System.out.println("Failed to save user details.");
                JOptionPane.showMessageDialog(null, "Failed to save user details.");
            }

            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static boolean isNUIDTaken(int NUID, Connection connection) throws SQLException {
        String sql = "SELECT nuid FROM users WHERE nuid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, NUID);

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }

    private static boolean isUsernameTaken(String username, Connection connection) throws SQLException {
        String sql = "SELECT username FROM users WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }
    
    public String hashPassword(String password) {
    // Generate a salt for BCrypt
    String salt = BCrypt.gensalt(12); // You can adjust the number of rounds as needed

    // Hash the password using the generated salt
    String hashedPassword = BCrypt.hashpw(password, salt);

    return hashedPassword;
}
    
    public boolean checkPassword(String enteredPassword, String hashedPassword) {
    // Check if the entered password matches the hashed password
    System.out.println("enteredPassword check password " + enteredPassword );
    System.out.println("hashedPassword check password " + hashedPassword );
    
    boolean passwordMatches = BCrypt.checkpw(enteredPassword, hashedPassword);

    

    return passwordMatches;
}

    
  

}

