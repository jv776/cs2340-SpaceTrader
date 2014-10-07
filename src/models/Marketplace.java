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
    //private HashMap<TradeGood, Integer> basket; //keeps track of quantities of goods marked for purchase
    //private int transactionValue;
    
    public Marketplace(Planet marketLocation) {
        location = marketLocation;
        productSupply = generateSupplies();
        prices = generatePrices();
        //basket = new HashMap<>();
        //transactionValue = 0;
        
        /*
        for (TradeGood good : TradeGood.values()) {
            basket.put(good, 0);
        }
        */
    }
    
    //Determine the number of goods available on a planet
    private HashMap<TradeGood, Integer> generateSupplies() {
        HashMap<TradeGood, Integer> goods = new HashMap<>();
        
        for (TradeGood good : TradeGood.values()) {
            int localTechFactor = location.solarSystem.getTechLevel().toInt();
            
            int minTechFactor = (localTechFactor < good.minTechLevelBuy.toInt())
                    ? 0 : (localTechFactor - good.minTechLevelBuy.toInt() + 1);
            
            int preferredTechFactor = Math.abs(localTechFactor
                    - good.preferredTechLevel.toInt());
            
            double quantityFactor =  Math.exp(-Math.pow(preferredTechFactor
                    / (Math.random() + 1), 0.5));
            
            goods.put(good, (int) (100 * quantityFactor) * minTechFactor);
        }
        
        return goods;
    }
    
    //Determine the price of each good
    private HashMap<TradeGood, Integer> generatePrices() {
        HashMap<TradeGood, Integer> priceMap = new HashMap<>();
        
        for (TradeGood good : TradeGood.values()) {
            Random r = new Random();
        
            int techLevelFactor = good.priceChangePerTechLevel *
                    (location.solarSystem.getTechLevel().toInt() -
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
    
    /**
     * Find the price of a specific good.
     * 
     * @param good The good being checked for its price
     * @return The price of the specified trade good
     */
    public int getPrice(TradeGood good) {
        return prices.get(good);
    }
    
    /**
     * Find the sale price of a specific good.
     * 
     * @param good The good being checked for its sale price
     * @return The price for this good when sold in this market
     */
    public int getSalePrice(TradeGood good) {
        return (int) (0.7 * prices.get(good));
    }
    
    /**
     * Find the supply of particular trade good.
     * 
     * @param good The good being checked for its supply
     * @return The quantity of the good available in this market
     */
    public int getSupply(TradeGood good) {
        return productSupply.get(good);
    }
    
    /**
     * Buy a good from the market.
     * 
     * @param good The good being purchased.
     * @return Whether or not the purchase was successful
     */
    public boolean buyGood(TradeGood good) {
        Player p = GameData.DATA.getPlayer();
        
        if (p.getCredits() >= getPrice(good) && getSupply(good) > 0
                && p.getShip().getCargoHold().hasSpace()) {
            p.spend(getPrice(good));
            productSupply.put(good, getSupply(good) - 1);
            p.getShip().getCargoHold().addItem(good);
            return true;
        } else {
            // do something here?
            return false;
        }
    }
    
    /**
     * Sell a good at the market.
     * 
     * @param good The good being sold.
     * @return Whether or not the sale was successful
     */
    public boolean sellGood(TradeGood good) {
        Player p = GameData.DATA.getPlayer();
        
        if (p.getShip().getCargoHold().getQuantity(good) > 0) {
            p.earn(getSalePrice(good));
            productSupply.put(good, getSupply(good) + 1);
            p.getShip().getCargoHold().removeItem(good);
            return true;
        } else {
            // do something here?
            return false;
        }
    }
    
    /*
    public void addPurchase(TradeGood good) {
        //basket.put(good, basket.get(good));
    }
    
    public void addSale(TradeGood good) {
        
    }
    */
}
