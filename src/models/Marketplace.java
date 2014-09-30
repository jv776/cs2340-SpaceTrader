/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

/**
 * Model of a planet's marketplace.
 * 
 * @author John Varela
 */
public class Marketplace {
    private final Planet location;
    private HashMap<TradeGood, Integer> productSupply;
    private HashMap<TradeGood, Integer> prices;
    private HashMap<TradeGood, Integer> basket; //keeps track of quantities of goods marked for purchase
    
    public Marketplace(Planet marketLocation) {
        location = marketLocation;
        productSupply = generateSupplies();
        prices = generatePrices();
        basket = new HashMap<>();
        
        for (TradeGood good : TradeGood.values()) {
            basket.put(good, 0);
        }
    }
    
    private HashMap<TradeGood, Integer> generateSupplies() {
        HashMap<TradeGood, Integer> goods = new HashMap<>();
        
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
    
    private HashMap<TradeGood, Integer> generatePrices() {
        HashMap<TradeGood, Integer> priceMap = new HashMap<>();
        
        for (TradeGood good : TradeGood.values()) {
            Random r = new Random();
        
            int techLevelFactor = good.priceChangePerTechLevel *
                    (location.system.getTechLevel().toInt() -
                    good.minTechLevelBuy.toInt());
        
            int variance = 1 + r.nextInt(good.priceVariance) / 100;
        
            double scarcityFactor = 1;
            Optional<Resource> expensiveResource = good.expensiveConditions;
            
            if (expensiveResource.isPresent()) {
                scarcityFactor = (expensiveResource.get() == location.resource)
                        ? 0.75 : 1;
            }

            double abundanceFactor = 1;
            Optional<Resource> cheapResource = good.cheapConditions;
            
            if (cheapResource.isPresent()) {
                abundanceFactor = (cheapResource.get() == location.resource)
                        ? 0.75 : 1;
            }
            
            double eventFactor = 1;
            Optional<PriceEvent> currentEvent = location.getCurrentEvent();
            
            if (currentEvent.isPresent()) {
                eventFactor = (currentEvent.get() == good.priceIncreaseEvent)
                        ? 1.5 : 1;
            }
            
            priceMap.put(good, (int) ((good.basePrice + techLevelFactor)
                    * variance * scarcityFactor * abundanceFactor
                    * eventFactor));
        }
        
        return priceMap;
    }
    
    public void markForPurchase() {
        
    }
}
