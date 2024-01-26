/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.SolutionManagement;

import TheBusiness.MarketModel.Market;
import TheBusiness.MarketModel.MarketChannelAssignment;
import TheBusiness.OrderManagement.OrderItem;
import java.util.ArrayList;
import TheBusiness.ProductManagement.Product;
import TheBusiness.SolutionOrders.SolutionOrder;
import TheBusiness.SolutionOrders.MasterSolutionOrderList;

/**
 *
 * @author kal bugrara
 */
public class SolutionOffer {
    //mvchaitanya: added the following
    String name;
    ArrayList<SolutionOrder> solutionorderlist;

    public ArrayList<SolutionOrder> getOrderitems() {
        return solutionorderlist;
    }

    public void setOrderitems(ArrayList<OrderItem> orderitems) {
        this.orderitems = orderitems;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public MarketChannelAssignment getMarketchannelcomb() {
        return marketchannelcomb;
    }

    public void setMarketchannelcomb(MarketChannelAssignment marketchannelcomb) {
        this.marketchannelcomb = marketchannelcomb;
    }

    public ArrayList<SolutionOrder> getSolutionorders() {
        return solutionorders;
    }

    public void setSolutionorders(ArrayList<SolutionOrder> solutionorders) {
        this.solutionorders = solutionorders;
    }
    ArrayList<OrderItem> orderitems;

    public void setName(String name) {
        this.name = name;
    }
    
    
    ArrayList<Product> products;
    //mvchaitanya: added the following
    int floorPrice;
    double targetPrice;
    int ceilingPrice;
    String ad;
    MarketChannelAssignment marketchannelcomb;
    ArrayList<SolutionOrder> solutionorders;
    //mvchaitanya: added the following
    int noOfSalesAboveTarget = 0;
    int totalPricePerformance = 0;
    
    public SolutionOffer(MarketChannelAssignment m){
        
        marketchannelcomb = m;
        products = new ArrayList();
        solutionorders = new ArrayList();
        m.addSolutionOffer(this); 
       
    } 

    public int getFloorPrice() {
        return floorPrice;
    }
    
     public String getName() {
        return name;
    }

    public void setFloorPrice(int floorPrice) {
        this.floorPrice = floorPrice;
    }

    public double getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(double targetPrice) {
        this.targetPrice = targetPrice;
    }
    
     public void addOrderItem(OrderItem oi){     
        orderitems.add(oi);
    }

    public int getCeilingPrice() {
        return ceilingPrice;
    }

    public void setCeilingPrice(int ceilingPrice) {
        this.ceilingPrice = ceilingPrice;
    }
    
    public void addProduct(Product p){
        products.add(p);
    }
    
    // mvchaitanya: commented the following
//    public int getSolutionPrice(){
//        return price;
//    }
    
    public double getRevenues(){
        double sum = 0;
        for(SolutionOrder so: solutionorders){
            sum = sum + so.getSolutionPrice();
            
        }
        return sum;
    }
    
    public void addSolutionOrder(SolutionOrder so){
        solutionorders.add(so);
        //mvchaitanya: added the following
        if (so.isActualAboveTarget()) noOfSalesAboveTarget++;
        totalPricePerformance+= so.pricePerformance();
    }

    //mvchaitanya: added the following
    public int getNoOfSalesAboveTarget() {
        return noOfSalesAboveTarget;
    }

    //mvchaitanya: added the following
    public int getTotalPricePerformance() {
        return totalPricePerformance;
    }
    
     public int getNumberOfProductSalesAboveTarget(){
        int sum = 0;
        for (SolutionOrder oi: solutionorders){
            if(oi.isActualAboveTarget()==true) sum = sum +1;
        }
        return sum;
    }
    public int getNumberOfProductSalesBelowTarget(){
        int sum = 0;
        for (SolutionOrder oi: solutionorders){
            if(oi.isActualBelowTarget()==true) sum = sum +1;
        }
        return sum;
    }    
    
        public boolean isProductAlwaysAboveTarget(){
        
        for (SolutionOrder oi: solutionorders){
            if(oi.isActualAboveTarget()==false) return false; //
        }
        return true;
    }
    //calculates the revenues gained or lost (in relation to the target)
    //For example, if target is at $2000 and actual is $2500 then revenue gained
    // is $500 above the expected target. If the actual is $1800 then the lose will be $200
    // Add all these difference to get the total including wins and loses
    
        public double getOrderPricePerformance() {
        double sum = 0;
        for (SolutionOrder oi: solutionorders) {
            sum = sum + oi.calculatePricePerformance();     //positive and negative values       
        }
        return sum;
    }
        public double getSalesVolume() {
        double sum = 0;
        for (SolutionOrder oi: solutionorders) {
            sum = sum + oi.getOrderItemTotal();     //positive and negative values       
        }
        return sum;
    }
    
    
    
    // this will allow one to retrieve all offers meant for this market/channel combo
    public boolean isSolutionOfferMatchMarketChannel(MarketChannelAssignment mca){
        
        if (marketchannelcomb==mca) return true;
        else return false;
    }
    public String getAd(){
        return ad;
    }
    public void setAd(String a){ //this an amazing solution for people like
        ad = a;
    }
    
    public boolean isSolutionOfferMatchMarket(Market customerMarket) {
    // Assuming that you have a method to get the market associated with the solution offer
    Market solutionOfferMarket = marketchannelcomb.getMarket();

    // Compare the market of the solution offer with the market of the customer
    return solutionOfferMarket.equals(customerMarket);
}
    
    public SolutionOffer findSolutionOfferByName(String name) {
    for (SolutionOrder solutionOrder : solutionorders) {
        System.out.println("solutionOffer" + solutionOrder.getSelectedsolutionoffer().getName());
        if (solutionOrder.getSelectedsolutionoffer().getName().equals(name)) {
            return solutionOrder.getSelectedsolutionoffer();
        }
    }
    return null; // Return null if no SolutionOffer with the specified name is found
}
    
    @Override
    public String toString(){
        return name;
    }

    
    

    //mvchaitanya: added by me
//    public int numberOfSalesAboveTarget() {
//        int sum = 0;
//        for (SolutionOrder solutionOrder: solutionorders) {
//           if(solutionOrder.isActualAboveTarget()){
//               sum++;
//           }
//        }
//        return sum;
//    }

    
}
