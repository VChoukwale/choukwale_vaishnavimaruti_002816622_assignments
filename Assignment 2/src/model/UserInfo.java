/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Vaishnavi Choukwale
 */
public class UserInfo extends PersonInfo {
    private long NUID;
    private String UserName;
    private ArrayList<String>  PasswordList = new ArrayList<>();
  
    private final String salt = BCrypt.gensalt();

    public ArrayList<String> getPasswordList() {
        return PasswordList;
    }

    public int getSize(){
        return PasswordList.size()-1;
    }
    
    public void setPasswordList(String Password) {
        String hashedPassword = BCrypt.hashpw(Password, salt);
        this.PasswordList.add(hashedPassword);
    }
    
    public boolean pwAuth(String oldpw, String newpw){
        return BCrypt.checkpw(newpw, oldpw);
    }
    
        public long getNUID() {
        return NUID;
    }

    public void setNUID(long NUID) {
        this.NUID = NUID;
    }


    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
   
}
