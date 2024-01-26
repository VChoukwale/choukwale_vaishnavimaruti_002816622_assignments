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
public class updateuserprofile {
    public static void updateUserDetails(int NUID, String name, String email, String address, String contactNumber, String username, String password) {
    try {
        Connection connection = (Connection)DatabaseConnection.getConnection();

        // Define the SQL update query to update the user's information based on NUID
        String sql = "UPDATE users SET name = ?, email = ?, address = ?, contact_number = ?, username = ?, hashedpassword = ? WHERE nuid = ?";

        // Create a PreparedStatement
        

            // Check if the username is already taken
            if (isUsernameTaken(username, connection)) {
                JOptionPane.showMessageDialog(null, "Username is already taken. Please choose a different username.");
                return;
           }
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        

        // Bind the updated values and the NUID
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, address);
        preparedStatement.setString(4, contactNumber);
        preparedStatement.setString(5, username);
        preparedStatement.setString(6, password);
        preparedStatement.setInt(7, NUID);

        int rowsUpdated = preparedStatement.executeUpdate();

        preparedStatement.close();

        if (rowsUpdated > 0) {
            // Update was successful
            JOptionPane.showMessageDialog( null,"User information updated.", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // No rows were updated, which may indicate a problem
            JOptionPane.showMessageDialog(null,"Update failed.", "Update Failed", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
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
    

    
}


