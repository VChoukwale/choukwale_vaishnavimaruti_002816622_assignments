/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package homework_1;

/**
 *
 * @author Vaishnavi Choukwale
 */
public class Patient {
    
    private String id;
    private String primary_doc;
    private String last_visit;
    private String next_appointment;
    private String allergies;
    private String on_medication;
    private String insurance_coverage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrimary_doc() {
        return primary_doc;
    }

    public void setPrimary_doc(String primary_doc) {
        this.primary_doc = primary_doc;
    }

    public String getLast_visit() {
        return last_visit;
    }

    public void setLast_visit(String last_visit) {
        this.last_visit = last_visit;
    }

    public String getNext_appointment() {
        return next_appointment;
    }

    public void setNext_appointment(String next_appointment) {
        this.next_appointment = next_appointment;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getOn_medication() {
        return on_medication;
    }

    public void setOn_medication(String on_medication) {
        this.on_medication = on_medication;
    }

    public String getInsurance_coverage() {
        return insurance_coverage;
    }

    public void setInsurance_coverage(String insurance_coverage) {
        this.insurance_coverage = insurance_coverage;
    }
    
}
