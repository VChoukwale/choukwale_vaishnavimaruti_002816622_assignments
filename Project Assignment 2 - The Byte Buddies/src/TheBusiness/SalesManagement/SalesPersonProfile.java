/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.SalesManagement;

import java.util.ArrayList;
import TheBusiness.OrderManagement.Order;
import TheBusiness.Personnel.Person;
import TheBusiness.Personnel.Profile;
import TheBusiness.SolutionOrders.SolutionOrder;

/**
 *
 * @author kal bugrara
 */
public class SalesPersonProfile extends Profile {
    ArrayList<SolutionOrder> solutionorder;
    Person person;



    public SalesPersonProfile(Person p) {

        super(p); 
        solutionorder = new ArrayList();
        person = p;

    }
    public void addSalesOrder(SolutionOrder o){
        solutionorder.add(o);
    }
    @Override
    public String getRole(){
        return  "Sales";
    }
    @Override
     public Person getPerson() {
        return person;
    }

}
