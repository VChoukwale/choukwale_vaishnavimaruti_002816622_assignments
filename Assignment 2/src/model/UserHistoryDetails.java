/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import org.mindrot.jbcrypt.BCrypt;
import java.util.ArrayList;

/**
 *
 * @author Vaishnavi Choukwale
 */
public class UserHistoryDetails {
    private ArrayList<UserInfo> userHist;
    
    
    
   public UserHistoryDetails(){
        //this.perHist = new ArrayList<PersonInfo>();
        this.userHist = new ArrayList<UserInfo>();
    }
   
   public ArrayList<UserInfo> getUserHistory(){
        return userHist;
    }
    
    public void setUserHistory(ArrayList<UserInfo> userHistory) {
       this.userHist = userHistory; 
    }
    
    public UserInfo addNewUser(){
        UserInfo newUser = new UserInfo();
        userHist.add(newUser);
        return newUser;
    }
    
    public void deleteUser(UserInfo usr){
        userHist.remove(usr);
    }
    
    public UserInfo AuthUser(String username, String password){
        for(UserInfo ur: userHist){
            if(ur.getUserName().equals(username)){
                if(ur.pwAuth(ur.getPasswordList().get(ur.getSize()), password)){
                    return ur;
                }
            }
        }
        return null;

    }
}
