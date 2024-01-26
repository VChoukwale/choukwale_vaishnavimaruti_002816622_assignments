/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.SolutionOrders;

import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.MarketModel.MarketChannelAssignment;
import TheBusiness.SolutionManagement.SolutionOffer;
import TheBusiness.OrderManagement.OrderItem;

import TheBusiness.ProductManagement.Product;
import TheBusiness.SalesManagement.SalesPersonProfile;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.UUID;

/**
 *
 * @author kal bugrara
 */
public class SolutionOrder {
    
    SolutionOffer selectedsolutionoffer;
    CustomerProfile customer;
    SalesPersonProfile salesperson;
    MarketChannelAssignment marketChannelAssignment; 
     ArrayList<SolutionOrder> solutionorderlist;
     Integer Quantity;
     String status;
     String name;
    //mvchaitanya: added price param
    double price;
    private static int orderIdCounter = 1;

    private String orderId;
    public SolutionOrder(String name,SolutionOffer so,  MarketChannelAssignment mca,double price,int quantity, CustomerProfile cp, SalesPersonProfile ep){
        selectedsolutionoffer = so;
        marketChannelAssignment = mca;
        customer = cp;
        salesperson = ep;
        this.name = generateOrderId();
        //mvchaitanya: added the following
        this.price = price;
        this.Quantity = quantity;
//        customer.addCustomerOrder(this); //we link the order to the customer
//        salesperson.addSalesOrder(this);  
        mca.addSolutionOrder(this);
        this.solutionorderlist = new ArrayList<>();
        
    }
    
     public SolutionOrder newSolutionOrder(String name,SolutionOffer soloffer,  MarketChannelAssignment mca,double price,int quantity,CustomerProfile cp, SalesPersonProfile ep) {

        SolutionOrder so = new SolutionOrder(name, soloffer,mca, price,quantity,cp,ep);
        solutionorderlist.add(so);
        System.out.println("OI" + so);
        soloffer.addSolutionOrder(so);
        return so;

    }
    private static final Logger LOG = Logger.getLogger(SolutionOrder.class.getName());

    public SolutionOffer getSelectedsolutionoffer() {
        return selectedsolutionoffer;
    }

    public void setSelectedsolutionoffer(SolutionOffer selectedsolutionoffer) {
        this.selectedsolutionoffer = selectedsolutionoffer;
    }

    public CustomerProfile getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerProfile customer) {
        this.customer = customer;
    }

    public SalesPersonProfile getSalesperson() {
        return salesperson;
    }
    
   

    public void setSalesperson(SalesPersonProfile salesperson) {
        this.salesperson = salesperson;
    }

    public MarketChannelAssignment getMarketChannelAssignment() {
        return marketChannelAssignment;
    }

    public void setMarketChannelAssignment(MarketChannelAssignment marketChannelAssignment) {
        this.marketChannelAssignment = marketChannelAssignment;
    }

    public ArrayList<SolutionOrder> getSolutionorderlist() {
        return solutionorderlist;
    }

    public void setSolutionorderlist(ArrayList<SolutionOrder> solutionorderlist) {
        this.solutionorderlist = solutionorderlist;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer Quantity) {
        this.Quantity = Quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    public double getSolutionPrice(){
        // mvchaitanya: logic to be updated
        return selectedsolutionoffer.getTargetPrice();
    }
    
    // mvchaitanya: added the following
//    public boolean isActualAboveTarget() {
//        return price - selectedsolutionoffer.getTargetPrice() > 0;
//    }
    
    // mvchaitanya: added the following
    public double pricePerformance() {
        return price - selectedsolutionoffer.getTargetPrice();
    }
    
     public double calculateRevenueImpact() {
        return (getPrice() - getSelectedsolutionoffer().getTargetPrice()) * getQuantity();
    }
    
    public MarketChannelAssignment getMarketChannelCombo(){
        
        return marketChannelAssignment;
                
    }
    
    public void CancelOrder(){
    status = "Cancelled";
    }
    public void Submit(){
        status = "Submitted";
    }
    
     public void deleteItem(SolutionOrder item){
        
       this.solutionorderlist.remove(item);
        
    }
     
     public SolutionOrder findProduct(SolutionOffer selectedsolutionoffer)
    {
        
        for(SolutionOrder oi: this.getSolutionorderlist())
        {
            if(oi.getSelectedsolutionoffer().equals(selectedsolutionoffer))
            {
                return oi;
            }
        }
        
        return null;
        
    }
    
    private String generateOrderId() {
        return "SO" + orderIdCounter++;
    }
     
     public double getOrderItemTotal() {
        return price * Quantity;
    }

//The following calculates what the price gain would have been if products were sold at target price
    public double getOrderItemTargetTotal() {
        return selectedsolutionoffer.getTargetPrice() * Quantity;
    }

    //returns positive if seller is making higher margin than target
    //returns negative if seller is making lower margin than target
    //otherwise zero meaning neutral
    public double calculatePricePerformance() {
        return (price - selectedsolutionoffer.getTargetPrice()) * Quantity;
    }

    public boolean isActualAboveTarget() {
        if (price > selectedsolutionoffer.getTargetPrice()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isActualBelowTarget() {
        if (price < selectedsolutionoffer.getTargetPrice()) {
            
            return true;
        } else {
            return false;
        }

    }

    public boolean isActualATTarget() {
        if (price == selectedsolutionoffer.getTargetPrice()) {
            return true;
        } else {
            return false;
        }

    }

    public SolutionOffer getSolutionOffer() {
        return selectedsolutionoffer;
    }

    public double getActualPrice() {
        return price;

    }

    public void setSolutionoffer(SolutionOffer solutionoffer) {
        this.selectedsolutionoffer = solutionoffer;
    }

    public void setActualPrice(int actualPrice) {
        this.price = actualPrice;
    }

    public void setQuantity(int quantity) {
        this.Quantity = quantity;
    }

//    public int getQuantity() {
//        return Quantity;
//    }
    

   
}
