/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.CustomerManagement;

import java.util.ArrayList;
import TheBusiness.MarketModel.Market;
import TheBusiness.OrderManagement.Order;
import TheBusiness.Personnel.Person;
import TheBusiness.Personnel.Profile;
import TheBusiness.SolutionOrders.SolutionOrder;

/**
 *
 * @author kal bugrara
 */
public class CustomerProfile extends Profile {

    ArrayList<SolutionOrder> solutionorder;
    Market market;

    Person person;

    public CustomerProfile(Person p,Market m) {
        super(p);
        person = p;
        market = m;
        solutionorder = new ArrayList();

    }

    public ArrayList<SolutionOrder> getSolutionorder() {
        return solutionorder;
    }

    public void setSolutionorder(ArrayList<SolutionOrder> solutionorder) {
        this.solutionorder = solutionorder;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }
    
    @Override
    public String getRole(){
        return "Sales";
    }
    
    

    public int getTotalPricePerformance() {

        //for each order in the customer orderlist 
        //calculate order price performance and add it to the sum
        return 0;
    }

//    public int getNumberOfOrdersAboveTotalTarget() {
//        //for each order in the customer order list 
//        //calculate if order is positive (actual order total is greater than sum of item targets
//        //if yes then add 1 to total 
//        int sum = 0;
//        for (SolutionOrder o : solutionorder) {
//            if (o.isOrderAboveTotalTarget() == true) {
//                sum = sum + 1;
//            }
//        }
//
//        return sum;
//    }

    public int getNumberOfOrdersBelowTotalTarget() {
        return 0;
    }
    //for each order in the customer order list 
    //calculate if order is negative
    //if yes then add 1 to total 

    public boolean isMatch(String id) {
        if (person.getPersonId().equals(id)) {
            return true;
        }
        return false;
    }

    public void addCustomerOrder(SolutionOrder o) {
        solutionorder.add(o);
    }

    @Override
    public String toString() {
        return person.getPersonId();
    }

    public String getCustomerId() {
        return person.getPersonId();
    }
    
    @Override
    public Person getPerson() {
        return person;
    }

}
