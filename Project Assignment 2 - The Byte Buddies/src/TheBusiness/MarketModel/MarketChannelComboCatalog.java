/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.MarketModel;

import java.util.ArrayList;
import static jdk.internal.net.http.common.Log.channel;

/**
 *
 * @author kal bugrara
 */
public class MarketChannelComboCatalog {
    
    ArrayList<MarketChannelAssignment> mcalist ;
    
   public MarketChannelComboCatalog() {
       
       mcalist = new ArrayList();
       
   }

    public ArrayList<MarketChannelAssignment> getMcalist() {
        return mcalist;
    }

    public void setMcalist(ArrayList<MarketChannelAssignment> mcalist) {
        this.mcalist = mcalist;
    }
   
   public MarketChannelAssignment newMarketChannelCombo(Market m, Channel c){
       MarketChannelAssignment mcc = new MarketChannelAssignment(m, c);
       mcalist.add(mcc);
       return mcc;
       
   }
   public MarketChannelAssignment finMarketChannelCombo(Market m, Channel c){
       
       for( MarketChannelAssignment mca: mcalist){
           if(mca.matches(m,c)) return mca;
       }
       return null;
       
   }
   
   public MarketChannelAssignment findMarketChannelCombo(String marketName, String channelName) {
    for (MarketChannelAssignment mca : mcalist) {
        if (mca.matches(marketName, channelName)) {
            return mca;
        }
    }
    return null;
}
   
   public MarketChannelAssignment newMarketChannelCombo(String mcaId, Market m, Channel c){
       MarketChannelAssignment mcc = new MarketChannelAssignment(mcaId, m, c);
       mcalist.add(mcc);
       return mcc;
   }
public MarketChannelAssignment findMarketChannelComboById(String id){
        for( MarketChannelAssignment marketChannelAssignment: mcalist ){
            if (marketChannelAssignment.getMcaId().equalsIgnoreCase(id)) return marketChannelAssignment;
        }
        return null; //not found
    }


} 
