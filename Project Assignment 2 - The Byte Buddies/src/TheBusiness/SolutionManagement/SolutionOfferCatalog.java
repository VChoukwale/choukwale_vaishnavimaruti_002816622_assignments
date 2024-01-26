/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.SolutionManagement;

import TheBusiness.MarketModel.Market;
import TheBusiness.MarketModel.MarketChannelAssignment;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author kal bugrara
 */
public class SolutionOfferCatalog {

    ArrayList<SolutionOffer> solutionoffers;
    //mvchaitanya: added the  following
    Comparator<SolutionOffer> solutionOfferComparator = Comparator.comparingInt(SolutionOffer::getNoOfSalesAboveTarget)
                                                                  .thenComparing(SolutionOffer::getTotalPricePerformance)
                                                                  .reversed();
    Map<Market, List<SolutionOffer>> solutionsByMarket;

    public SolutionOfferCatalog() {
        solutionoffers = new ArrayList();
        solutionsByMarket = new LinkedHashMap<>();
    }

    public ArrayList<SolutionOffer> getSolutionoffers() {
        return solutionoffers;
    }

    public void setSolutionoffers(ArrayList<SolutionOffer> solutionoffers) {
        this.solutionoffers = solutionoffers;
    }

    public Comparator<SolutionOffer> getSolutionOfferComparator() {
        return solutionOfferComparator;
    }

    public void setSolutionOfferComparator(Comparator<SolutionOffer> solutionOfferComparator) {
        this.solutionOfferComparator = solutionOfferComparator;
    }
    
    public SolutionOffer newSolutionOffer(MarketChannelAssignment mca){
        
        SolutionOffer so = new SolutionOffer(mca);
        solutionoffers.add(so);
        //mvchaitanya: added the  following
        Market market = so.marketchannelcomb.getMarket();
        if (!solutionsByMarket.containsKey(market)) {
            solutionsByMarket.put(market, new ArrayList<>());
        }
        solutionsByMarket.get(market).add(so);
        
        return so;
    }
    
    public SolutionOffer findSolutionOffer(String name){
        for(SolutionOffer solutionOffer: solutionoffers ){
            if (solutionOffer.getName().equalsIgnoreCase(name)) return solutionOffer; 
        }
        return null; //not found
    }
    
    

    //return all solution offers that match m/c combination
    public ArrayList<SolutionOffer> findSolutionsForMarketChannelCombo(MarketChannelAssignment mcc) {
        ArrayList<SolutionOffer> foundsolutions = new ArrayList();

        for (SolutionOffer so : solutionoffers) {

            if (so.isSolutionOfferMatchMarketChannel(mcc) == true) {
                foundsolutions.add(so);
            }
            //find all solution offers available in the market/channel combin
        }
        return foundsolutions;

    }
    
    public SolutionOffer findSolutionOfferByName(String name) {
    for (SolutionOffer solutionOffer : solutionoffers) {
        if (solutionOffer.getName().equals(name)) {
            return solutionOffer;
        }
    }
    return null; // Return null if no SolutionOffer with the specified name is found
}

    
    //mvchaitanya: added the following
    public void sortSolutionsBrokenDownByMarket() {
       for (Market market: solutionsByMarket.keySet()) {
           Collections.sort(solutionsByMarket.get(market), solutionOfferComparator);
       }
    }

    //mvchaitanya: added the following
    public Map<Market, List<SolutionOffer>> getSolutionsByMarket() {
        sortSolutionsBrokenDownByMarket();
        return solutionsByMarket;
    }

}
