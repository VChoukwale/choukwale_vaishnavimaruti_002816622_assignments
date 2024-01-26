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
    
    public static void saveUserDetails2(
            int userid, String name,  String address, String contactNumber,String fingerprint_scan,String eye_scan,String role,String domain,
           String enterprise, String organisation, String department,String division, String username, String userPassword,String hashedPassword) {
        try {
            Connection connection = (Connection) DatabaseConnection.getConnection();
   
            String sql = "INSERT INTO users (userid, name, address, contact_number,fingerprint_scan,eye_scan,role,domain,enterprise,organisation,department,division,username,hashedpassword,password) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?)";
         

            // Check if NUID is already taken
            if (isNUIDTaken(userid, connection)) {
                JOptionPane.showMessageDialog(null, "NUID is already taken. Please choose a different NUID.");
                return;
            }

            // Check if the username is already taken
            if (isUsernameTaken(username, connection)) {
                JOptionPane.showMessageDialog(null, "Username is already taken. Please choose a different username.");
                return;
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userid);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, contactNumber);
            preparedStatement.setString(5, fingerprint_scan);
            preparedStatement.setString(6, eye_scan);
            preparedStatement.setString(7, role);
            preparedStatement.setString(8, domain);
            preparedStatement.setString(9, enterprise);
            preparedStatement.setString(10, organisation);
            preparedStatement.setString(11, department);
            preparedStatement.setString(12,division);
            preparedStatement.setString(13, username);
            preparedStatement.setString(14, hashedPassword);
            preparedStatement.setString(15, userPassword);

            int rowsAffected = preparedStatement.executeUpdate();

           

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
    
    private static boolean isNUIDTaken(int userid, Connection connection) throws SQLException {
        String sql = "SELECT userid FROM users WHERE userid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, userid);

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