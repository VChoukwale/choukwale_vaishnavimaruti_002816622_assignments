/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.SolutionOrders;

import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.MarketModel.Channel;
import TheBusiness.MarketModel.Market;
import TheBusiness.MarketModel.MarketChannelAssignment;
import TheBusiness.SolutionManagement.SolutionOffer;
import TheBusiness.SalesManagement.SalesPersonProfile;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author kal bugrara
 */
public class MasterSolutionOrderList {
       
        ArrayList<SolutionOrder> solutionorderlist;

    public MasterSolutionOrderList() {
        solutionorderlist = new ArrayList();
    }
    
    public List<SolutionOrder> getSolutionorderlist() {
        return solutionorderlist;
    }

        //mvhchaitanya: added price parameter
        public SolutionOrder newSolutionOrder(String name,SolutionOffer soloffer,  MarketChannelAssignment mca,double price,int quantity,CustomerProfile cp, SalesPersonProfile ep) {

        SolutionOrder so = new SolutionOrder(name,soloffer,mca, price,quantity,cp,ep);
        solutionorderlist.add(so);
        System.out.println("OI" + so);
        soloffer.addSolutionOrder(so);
        return so;

    }

    public void setSolutionorderlist(ArrayList<SolutionOrder> solutionorderlist) {
        this.solutionorderlist = solutionorderlist;
    }

    public double getRevenueByMarket(Market m) {
        double sum = 0;
        for(SolutionOrder so: solutionorderlist){
         
         MarketChannelAssignment mcc =   so.getMarketChannelCombo();
         if(mcc.getMarket()==m) sum = sum +so.getSolutionPrice();
           
        }

        return sum;

    }
    public double getRevenueByChannel(Channel c) {
        double sum = 0;
        for(SolutionOrder so: solutionorderlist){
         
         MarketChannelAssignment mcc =   so.getMarketChannelCombo();
         if(mcc.getChannel()==c) sum = sum +so.getSolutionPrice();
           
        }

        return sum;

    }
    public double getRevenueByMarketChannelCombo(MarketChannelAssignment mca) {
        double sum = 0;
        for(SolutionOrder so: solutionorderlist){
         
         MarketChannelAssignment mcc =   so.getMarketChannelCombo();
         if(mcc==mca) sum = sum +so.getSolutionPrice(); 
           
        }
        return sum;

    }
    
    public List<SolutionOrder> getSolutionOrdersByCustomer(CustomerProfile customer) {
        List<SolutionOrder> customerSolutionOrders = new ArrayList<>();
        for (SolutionOrder so : solutionorderlist) {
            if (so.getCustomer() == customer) {
                customerSolutionOrders.add(so);
            }
        }
        return customerSolutionOrders;
    }
    
    public List<SolutionOrder> getSolutionOrdersBySalesperson(SalesPersonProfile salesperson) {
    List<SolutionOrder> salespersonSolutionOrders = new ArrayList<>();
    for (SolutionOrder so : solutionorderlist) {
        if (so.getSalesperson() == salesperson) {
            salespersonSolutionOrders.add(so);
        }
    }
    return salespersonSolutionOrders;
}
    
    
    
public List<SolutionOrder> getSolutionOrdersBySolutionOffer(SolutionOffer solution) {
    List<SolutionOrder> solutionOrders = new ArrayList<>();
    for (SolutionOrder so : solutionorderlist) {
        if (so.getSelectedsolutionoffer() == solution) {
            solutionOrders.add(so);
        }
    }
    return solutionOrders;
}


// public List<SolutionOrder> getTopNegotiatedSolutions() {
//        List<SolutionOrder> sortedSolutions = new ArrayList<>(solutionorderlist);
//        
//        // Sort the solutions based on total revenue impact
//        sortedSolutions.sort(Comparator.comparingDouble(SolutionOrder::calculateRevenueImpact).reversed());
//
//        // Get the top 3 solutions
//        return sortedSolutions.stream().limit(3).collect(Collectors.toList());
//    }

 
 public Map<Market, List<SolutionOrder>> getTopNegotiatedSolutionsPerMarket() {
        Map<Market, List<SolutionOrder>> topSolutionsPerMarket = new HashMap<>();

        // Iterate through all solutions in the master list
        for (SolutionOrder solutionOrder : solutionorderlist) {
            Market market = solutionOrder.getMarketChannelCombo().getMarket();

            // If the market is not already in the map, add it with an empty list
            topSolutionsPerMarket.computeIfAbsent(market, k -> new ArrayList<>());

            // Add the solution to the list for the corresponding market
            topSolutionsPerMarket.get(market).add(solutionOrder);
        }

        // Iterate through the map and sort the lists for each market
        for (Map.Entry<Market, List<SolutionOrder>> entry : topSolutionsPerMarket.entrySet()) {
            List<SolutionOrder> solutions = entry.getValue();

            // Sort the list based on your negotiation criteria (e.g., revenue impact)
            solutions.sort((o1, o2) -> Double.compare(o2.calculateRevenueImpact(), o1.calculateRevenueImpact()));

            // Keep only the top 3 solutions
            if (solutions.size() > 3) {
                solutions.subList(3, solutions.size()).clear();
            }
        }

        return topSolutionsPerMarket;
    }

}
