/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Vaishnavi Choukwale
 */
public class PersonInfo {
    private String FirstName;
    private String LastName;
    //private long NUID;
    private String Address;
    private String EmailID;
    private long ContactNumber;
    //private String DOB;



        public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

//    public long getNUID() {
//        return NUID;
//    }
//
//    public void setNUID(long NUID) {
//        this.NUID = NUID;
//    }
    
    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String EmailID) {
        this.EmailID = EmailID;
    }

    public long getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(long ContactNumber) {
        this.ContactNumber = ContactNumber;
    }
    
    @Override
    public String toString(){
        return FirstName;
    }

}
