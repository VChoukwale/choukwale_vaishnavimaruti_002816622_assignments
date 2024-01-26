/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness;

import MarketingManagement.MarketingPersonDirectory;
import MarketingManagement.MarketingPersonProfile;
import TheBusiness.Business.Business;
import TheBusiness.MarketModel.ChannelCatalog;
import TheBusiness.CustomerManagement.CustomerDirectory;
import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.MarketModel.Channel;
import TheBusiness.MarketModel.Market;
import TheBusiness.MarketModel.MarketCatalog;
import TheBusiness.MarketModel.MarketChannelAssignment;
import TheBusiness.MarketModel.MarketChannelComboCatalog;
import TheBusiness.SolutionManagement.SolutionOffer;
import TheBusiness.SolutionManagement.SolutionOfferCatalog;
//import TheBusiness.OrderManagement.MasterOrderList;
import TheBusiness.SolutionOrders.MasterSolutionOrderList;
import TheBusiness.OrderManagement.Order;
import TheBusiness.OrderManagement.OrderItem;
import TheBusiness.Personnel.Person;
import TheBusiness.Personnel.PersonDirectory;
import TheBusiness.ProductManagement.Product;
import TheBusiness.ProductManagement.ProductSummary;
import TheBusiness.ProductManagement.ProductCatalog;
import TheBusiness.SalesManagement.SalesPersonDirectory;
import TheBusiness.SalesManagement.SalesPersonProfile;
import TheBusiness.SolutionOrders.SolutionOrder;
import TheBusiness.Supplier.Supplier;
import TheBusiness.Supplier.SupplierDirectory;
import TheBusiness.UserAccountManagement.UserAccount;
import TheBusiness.UserAccountManagement.UserAccountDirectory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import TheBusiness.ReadDataFromFiles;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author kal bugrara
 */
class ConfigureABusiness {

    static Business initializeMarkets() {
        //mvchaitanya: Definging the business
        Business business = new Business("Xerox");
        ReadDataFromFiles readfile = new ReadDataFromFiles(business);
        readfile.readData();
// Create Persons
        PersonDirectory persondirectory = business.getPersonDirectory();
// person representing sales organization        
        Person xeroxsalesperson001 = persondirectory.newPerson("Xerox salesguy1");
        Person xeroxsalesperson002 = persondirectory.newPerson("Xerox salesguy2");
        Person xeroxmarketingperson001 = persondirectory.newPerson("Xerox marketing");
//        

// Create Sales people
        SalesPersonDirectory salespersondirectory = business.getSalesPersonDirectory();
        SalesPersonProfile salespersonprofile = salespersondirectory.newSalesPersonProfile(xeroxsalesperson001);
        SalesPersonProfile salespersonprofile2 = salespersondirectory.newSalesPersonProfile(xeroxsalesperson002);
//

MasterSolutionOrderList msol = business.getMasterSolutionOrderList();
List<SolutionOrder> allNegotiatedSolutions = msol.getSolutionorderlist();

// Create a map to store revenue impact for each solution name and market
Map<String, Map<String, Double>> solutionRevenueMap = new HashMap<>();

// Populate the map with the best-negotiated solutions for each market
for (SolutionOrder solutionOrder : allNegotiatedSolutions) {
    String solutionName = solutionOrder.getSelectedsolutionoffer().getName();
    String market = solutionOrder.getMarketChannelAssignment().getMarket().getName(); // Assuming there's a method to get the market name
    double revenueImpact = solutionOrder.calculateRevenueImpact();
    

    // If the market is not in the map, add it
    solutionRevenueMap.putIfAbsent(market, new HashMap<>());

    // Check if the solution is not already in the market map or if the current solution has a greater revenue impact
    if (!solutionRevenueMap.get(market).containsKey(solutionName) || revenueImpact > solutionRevenueMap.get(market).get(solutionName)) {
        solutionRevenueMap.get(market).put(solutionName, revenueImpact);
    }
}

// Print the top 3 solutions for each market
System.out.println("Top 3 Best Negotiated Solutions for Each Market:");
solutionRevenueMap.forEach((market, marketMap) -> {
    System.out.println(market);
    List<Map.Entry<String, Double>> topSolutions = marketMap.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .collect(Collectors.toList());

    // Print the top 3 solutions
    for (int i = 0; i < Math.min(topSolutions.size(), 3); i++) {
        Map.Entry<String, Double> entry = topSolutions.get(i);
        String solutionName = entry.getKey();
        Double revenueImpact = entry.getValue();

        System.out.println("Market: " + market);
        System.out.println("Solution #" + (i + 1));
        System.out.println("Product: " + solutionName);
        System.out.println("Revenue Impact: " + revenueImpact);
        System.out.println("---------------------------");
    }
});



Map<String, Double> customerRevenueMap = new HashMap<>();

// Populate the map with the cumulative impact for each customer
for (SolutionOrder solutionOrder : allNegotiatedSolutions) {
    CustomerProfile customerProfile = solutionOrder.getCustomer();

    // Skip orders with null customers
    if (customerProfile == null) {
        continue;
    }

    String customer = customerProfile.getCustomerId();
    double revenueImpact = solutionOrder.calculateRevenueImpact();

    // If the customer is not in the map, add it
    customerRevenueMap.putIfAbsent(customer, 0.0);

    // Update the cumulative impact for the customer
    customerRevenueMap.put(customer, customerRevenueMap.get(customer) + revenueImpact);
}

// Get the top 3 customers based on cumulative revenue impact
List<Map.Entry<String, Double>> top3Customers = customerRevenueMap.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .limit(3)
        .collect(Collectors.toList());

System.out.println("Top 3 Customers with Highest Cumulative Revenue Impact:");
for (int i = 0; i < top3Customers.size(); i++) {
    Map.Entry<String, Double> entry = top3Customers.get(i);
    String customer = entry.getKey();
    Double cumulativeRevenueImpact = entry.getValue();

    System.out.println("Customer #" + (i + 1));
    System.out.println("Customer Name: " + customer);
    System.out.println("Cumulative Revenue Impact: " + cumulativeRevenueImpact);
    System.out.println("---------------------------");
}



  

Map<String, Double> salespersonRevenueMap = new HashMap<>();

// Populate the map with the cumulative impact for each salesperson
for (SolutionOrder solutionOrder : allNegotiatedSolutions) {
    SalesPersonProfile salesPerson = solutionOrder.getSalesperson();

    // Skip orders with null salespersons
    if (salesPerson == null) {
         System.out.println(  "hello");
        continue;
    }

    String salespersonName = salesPerson.getPerson().getPersonId(); // Replace with the actual method to get the salesperson name
    double revenueImpact = solutionOrder.calculateRevenueImpact();

    // If the salesperson is not in the map, add them
    salespersonRevenueMap.putIfAbsent(salespersonName, 0.0);
   

    // Update the cumulative impact for the salesperson
    salespersonRevenueMap.put(salespersonName, salespersonRevenueMap.get(salespersonName) + revenueImpact);
}

// Get the top 3 salespersons based on cumulative revenue impact
List<Map.Entry<String, Double>> top3Salespersons = salespersonRevenueMap.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .limit(3)
        .collect(Collectors.toList());

System.out.println("Top 3 Salespersons with Highest Cumulative Revenue Impact:");
for (int i = 0; i < top3Salespersons.size(); i++) {
    Map.Entry<String, Double> entry = top3Salespersons.get(i);
    String salespersonName = entry.getKey();
    Double cumulativeRevenueImpact = entry.getValue();

    System.out.println("Salesperson #" + (i + 1));
    System.out.println("Salesperson Name: " + salespersonName);
    System.out.println("Cumulative Revenue Impact: " + cumulativeRevenueImpact);
    System.out.println("---------------------------");
}


// Create maps to store marginal revenue above and below expected target for each market
Map<String, Double> TargetMap = new HashMap<>();

// Populate the maps with marginal revenue for each market
for (SolutionOrder solutionOrder : allNegotiatedSolutions) {
    String market = solutionOrder.getMarketChannelAssignment().getMarket().getName(); // Assuming there's a method to get the market name
    double actual = solutionOrder.getActualPrice();
    double revenueImpact = solutionOrder.calculateRevenueImpact();
   

    // Determine whether the marginal revenue is above or below expected target
   
        // Above target
        TargetMap.putIfAbsent(market, 0.0);
        TargetMap.put(market, TargetMap.get(market) + revenueImpact);
    

// Print the total marginal revenue above and below expected target for each mar

}

TargetMap.forEach((market2, revenueImpact2) -> {
    System.out.println("Market: " + market2);
    System.out.println("Total Marginal Revenue Around Target: " + revenueImpact2);
    System.out.println("---------------------------");
});

Map<String, Double> TargetMap1 = new HashMap<>();


// Populate the maps with marginal revenue for each market
for (SolutionOrder solutionOrder : allNegotiatedSolutions) {
    String SolutionOffer = solutionOrder.getSolutionOffer().getName(); // Assuming there's a method to get the market name
    double actual = solutionOrder.getActualPrice();
    double revenueImpact = solutionOrder.calculateRevenueImpact();
    double target = solutionOrder.getSolutionOffer().getTargetPrice();

    // Determine whether the actual price is above or below expected target
    if (actual > target) {
        // Above target
        TargetMap1.putIfAbsent(SolutionOffer, 0.0);
        

        // Adjust the target value based on a percentage of the actual value
        double adjustmentPercentage = 0.10; // 10% adjustment (you can modify this)
        double adjustment = actual * adjustmentPercentage;
        double newTarget = actual + adjustment;
        if(newTarget > solutionOrder.getSolutionOffer().getCeilingPrice())
        {
            newTarget = solutionOrder.getSolutionOffer().getCeilingPrice();
        }
        TargetMap1.put(SolutionOffer, newTarget);
        solutionOrder.getSolutionOffer().setTargetPrice(newTarget);
        String offer = solutionOrder.getSolutionOffer().getName();
        
      
    } else if (actual < target) {
        // Below target
        TargetMap1.putIfAbsent(SolutionOffer, 0.0);
      

        // Adjust the target value based on a percentage of the actual value
        double adjustmentPercentage = 0.10; // 10% adjustment (you can modify this)
        double adjustment = actual * adjustmentPercentage;
        double newTarget = actual - adjustment;
        if(newTarget < solutionOrder.getSolutionOffer().getFloorPrice())
        {
            newTarget = solutionOrder.getSolutionOffer().getFloorPrice();
        }
        solutionOrder.getSolutionOffer().setTargetPrice(newTarget);
          TargetMap1.put(SolutionOffer, newTarget);
       
          
    }
}

// Print values in aboveTargetMap
System.out.println("---Target Adjustments---");
TargetMap1.forEach((key, value) -> {
    System.out.println(key + ": " + value);
});


        return business;

    }

}