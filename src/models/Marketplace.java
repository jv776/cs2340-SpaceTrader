/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.HashMap;
import java.util.Random;

/**
 * Model of a planet's marketplace.
 * 
 * @author John Varela
 */
public class Marketplace {
    private final Planet location;
    public HashMap<TradeGood, Integer> productSupply;
    private HashMap<TradeGood, Integer> basket; //keeps track of quantities of goods marked for purchase
    
    public Marketplace(Planet marketLocation) {
        location = marketLocation;
        productSupply = generateSupplies();
        basket = new HashMap<>();
        
        for (TradeGood good : TradeGood.values()) {
            basket.put(good, 0);
        }
    }
    
    private HashMap<TradeGood, Integer> generateSupplies() {
        HashMap<TradeGood, Integer> goods = new HashMap<>();
        Random r = new Random();
        
        for (TradeGood good : TradeGood.values()) {
            int localTechFactor = location.system.getTechLevel().toInt();
            
            int minTechFactor = (localTechFactor < good.minTechLevelBuy.toInt())
                    ? 0 : (localTechFactor - good.minTechLevelBuy.toInt() + 1);
            
            int preferredTechFactor = Math.abs(localTechFactor
                    - good.preferredTechLevel.toInt());
            
            double quantityFactor =  Math.exp(-Math.pow(preferredTechFactor
                    / (Math.random() + 1), 0.5));
            
            goods.put(good, (int) (100 * quantityFactor * minTechFactor));
        }
        
        return goods;
    }
    
    public void markForPurchase() {
        
    }
}
