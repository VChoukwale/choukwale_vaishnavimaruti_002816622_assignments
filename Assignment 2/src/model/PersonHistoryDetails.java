/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;


/**
 *
 * @author Vaishnavi Choukwale
 */
public class PersonHistoryDetails {
    private ArrayList<PersonInfo> perHist;
    
    //private ArrayList<PersonInfo> pwdHist;
    public PersonHistoryDetails(){
        this.perHist = new ArrayList<PersonInfo>();
       // this.userHist = new ArrayList<UserInfo>();
    }
      

    
    public ArrayList<PersonInfo> getPersonHistory() {
        return perHist;
    }
    
    public void setPersonHistory(ArrayList<PersonInfo> userHistory) {
        this.perHist = perHist;
    }
    
    
    
    public PersonInfo addNewPerson() {
        PersonInfo newPerson = new PersonInfo();
        perHist.add(newPerson);
        return newPerson;
    }
    
    public void deletePerson(PersonInfo prs){
        perHist.remove(prs);
    }
       

    
}
