/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheBusiness;

/**
 *
 * @author abhilashkumargorle
 */
/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/

 
import TheBusiness.Business.Business;
import TheBusiness.CustomerManagement.CustomerDirectory;
import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.MarketModel.ChannelCatalog;
import TheBusiness.MarketModel.Market;
import TheBusiness.MarketModel.MarketCatalog;
import TheBusiness.MarketModel.MarketChannelComboCatalog;

import TheBusiness.Personnel.PersonDirectory;
import TheBusiness.ProductManagement.ProductCatalog;
import TheBusiness.SalesManagement.SalesPersonDirectory;
import TheBusiness.SalesManagement.SalesPersonProfile;
import TheBusiness.SolutionManagement.SolutionOffer;
import TheBusiness.SolutionManagement.SolutionOfferCatalog;
import TheBusiness.SolutionOrders.MasterSolutionOrderList;
import TheBusiness.UserAccountManagement.UserAccount;
import TheBusiness.UserAccountManagement.UserAccountDirectory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
 
/**
*
* @author venka
*/
public class ReadDataFromFiles {
 
    Business business;
    ProductCatalog productCatalog;
    ChannelCatalog channelCatalog;
    MarketCatalog marketCatalog;
    CustomerDirectory customerDirectory;
    PersonDirectory personDirectory;
    MarketChannelComboCatalog marketChannelComboCatalog;
    SolutionOfferCatalog solutionOfferCatalog;
    SalesPersonDirectory salesPersonDirectory;
    MasterSolutionOrderList masterSolutionOrderList;
    public ReadDataFromFiles(Business business) {
        this.business = business;
        this.productCatalog = business.getSupplierDirectory().newSupplier("").getProductCatalog();
        this.channelCatalog = business.getChannelCatalog();
        this.marketCatalog = business.getMarketCatalog();
        this.customerDirectory = business.getCustomerDirectory();
        this.personDirectory = business.getPersonDirectory();
        this.marketChannelComboCatalog = business.getMarketChannelComboCatalog();
        this.solutionOfferCatalog = business.getSolutionOfferCatalog();
        this.salesPersonDirectory = business.getSalesPersonDirectory();
        this.masterSolutionOrderList = business.getMasterSolutionOrderList();
    }
    public void readData() {
        String prefixFilePath = "/Users/abhilashkumargorle/Documents/assignment-4-thebytebuddies/Assignment4/src/resources/dataFiles/";
        // Reading products related information
        System.out.println("Processing the products data");
        processDataFromFile(prefixFilePath + "products.csv", "products");
        System.out.println("Processing the available channels data");
        processDataFromFile(prefixFilePath + "channels.csv", "channels");
        System.out.println("Processing the available markets data");
        processDataFromFile(prefixFilePath + "markets.csv", "markets");
        System.out.println("Processing customer profiles data");
        processDataFromFile(prefixFilePath + "customer.csv", "customerProfiles");
        System.out.println("Processing sales personnel profiles data");
        processDataFromFile(prefixFilePath + "sales_perons.csv", "salesPersonnelProfiles");
        System.out.println("Processing market channel assignments data");
        processDataFromFile(prefixFilePath + "market_channel_assignment.csv", "marketChannel");
        System.out.println("Processing solution offers data");
        processDataFromFile(prefixFilePath + "solution_offers.csv", "solutionOffers");
        System.out.println("Processing solution orders data");
        processDataFromFile(prefixFilePath + "solution_orders.csv", "solutionOrders");
    }
    private void processDataFromFile(String filePath, String type) {
        Scanner sc = new Scanner(System.in);
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            int i=0;
            while ((line = br.readLine()) != null) {
               String[] data = line.split(",");
               if (type.equalsIgnoreCase("products")) {
                  productCatalog.newProduct(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), 
                       Integer.parseInt(data[4])); 
               } else if (type.equalsIgnoreCase("channels")) {
                  channelCatalog.newChannel(data[0]); 
               } else if (type.equalsIgnoreCase("markets")) {
                  Market market = marketCatalog.newMarket(data[0]);
                  for(String channel: data[1].split(";")) {
                      market.addValidChannel(channelCatalog.findChannel(channel));
                  }
               } else if (type.equalsIgnoreCase("customerProfiles")) {
                   customerDirectory.newCustomerProfile(personDirectory.newPerson(data[0]), marketCatalog.findMarket(data[1]));
                   
               } else if (type.equalsIgnoreCase("salesPersonnelProfiles")) {
                   SalesPersonProfile sp = salesPersonDirectory.newSalesPersonProfile(personDirectory.newPerson(data[0]));
                   String name = sp.getPerson().getPersonId();
                   UserAccountDirectory uadirectory = business.getUserAccountDirectory();
                    UserAccount ua1 = uadirectory.newUserAccount(sp,name,name);
               } else if (type.equalsIgnoreCase("marketChannel")) {
                   marketChannelComboCatalog.newMarketChannelCombo(data[0],
                           marketCatalog.findMarket(data[1]), channelCatalog.findChannel(data[2]));
               } else if (type.equalsIgnoreCase("solutionOffers")) {
                   SolutionOffer solutionOffer = solutionOfferCatalog.newSolutionOffer(
                                                marketChannelComboCatalog.findMarketChannelComboById(data[1]));
                   solutionOffer.setName(data[0]);
                   solutionOffer.setFloorPrice(Integer.parseInt(data[3]));
                   solutionOffer.setTargetPrice(Integer.parseInt(data[4]));
                   solutionOffer.setCeilingPrice(Integer.parseInt(data[5]));
                   for(String pId: data[2].split(";")) {
                       solutionOffer.addProduct(productCatalog.findProductByID(pId));
                   }
               } else if (type.equalsIgnoreCase("solutionOrders")) {
                   masterSolutionOrderList.newSolutionOrder(data[0],solutionOfferCatalog.findSolutionOffer(data[1]) , 
                           marketChannelComboCatalog.findMarketChannelComboById(data[4]),
                           
                         Double.parseDouble(data[5]), Integer.parseInt(data[6]),customerDirectory.findCustomer(data[2]) , 
                        
                         salesPersonDirectory.findSalesPerson(data[3]));
               }
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}