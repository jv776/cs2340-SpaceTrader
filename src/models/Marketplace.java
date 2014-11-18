/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

/**
 * Model of a planet's marketplace.
 *
 * @author John Varela
 */
public class Marketplace implements Serializable {
    private final Planet location;
    private HashMap<TradeGood, Integer> productSupply;
    private HashMap<TradeGood, Integer> prices;
    //private HashMap<TradeGood, Integer> basket; //keeps track of quantities of goods marked for purchase
    //private int transactionValue;

    /**
     * Create a new Marketplace on a given planet.
     *
     * @param marketLocation The planet where the market is located.
     */
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
            int localTechFactor = location.getSolarSystem().getTechLevel().ordinal();

            int minTechFactor = (localTechFactor < good.getMinTechLevelBuy().ordinal())
                    ? 0 : (localTechFactor - good.getMinTechLevelBuy().ordinal() + 1);

            int preferredTechFactor = Math.abs(localTechFactor
                    - good.getPreferredTechLevel().ordinal());

            double quantityFactor = Math.exp(-Math.pow(preferredTechFactor
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

            int techLevelFactor = good.getPriceChangePerTechLevel() *
                    (location.getSolarSystem().getTechLevel().ordinal() -
                            good.getMinTechLevelBuy().ordinal());

            int variance = r.nextInt(11) - 2;

            double scarcityFactor = 1;
            Optional<Resource> expensiveResource = good.getExpensiveConditions();

            if (expensiveResource.isPresent()) {
                scarcityFactor = (expensiveResource.get() == location.getResource())
                        ? 1.5 : 1;
            }

            double abundanceFactor = 1;
            Optional<Resource> cheapResource = good.getCheapConditions();

            if (cheapResource.isPresent()) {
                abundanceFactor = (cheapResource.get() == location.getResource())
                        ? 0.5 : 1;
            }

            double eventFactor = 1;
            PriceEvent currentEvent = location.getCurrentEvent();


            eventFactor = (currentEvent == good.getPriceIncreaseEvent()) ? 1.5 : 1;


            priceMap.put(good, (int) ((good.getBasePrice() + techLevelFactor + variance)
                    * scarcityFactor * abundanceFactor
                    * eventFactor));
        }

        return priceMap;
    }

    /**
     * Determine whether or not the good can be bought at a good price.
     *
     * @param good The good requested.
     * @return True if the buy is good or false if it is not.
     */
    public boolean isGoodBuy(TradeGood good) {
        int techLevelFactor = good.getPriceChangePerTechLevel() *
                (location.getSolarSystem().getTechLevel().ordinal() -
                        good.getMinTechLevelBuy().ordinal());
        return getPrice(good) < (good.getBasePrice() + techLevelFactor) * .9;
    }

    /**
     * Determine whether or not a good can be sold at a good price.
     *
     * @param good The good being sold.
     * @return True if the sell is good or false if it is not.
     */
    public boolean isGoodSell(TradeGood good) {
        int techLevelFactor = good.getPriceChangePerTechLevel() *
                (location.getSolarSystem().getTechLevel().ordinal() -
                        good.getMinTechLevelBuy().ordinal());
        return getSalePrice(good) > (good.getBasePrice() + techLevelFactor);
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
    public int getSalePrice(TradeGood good, int traderSkill) {
        return (int) (0.9 * prices.get(good) * (1 + 2.0 * traderSkill / 3 / 100.0));
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
     */
    public void buyGood(TradeGood good) {
        productSupply.put(good, getSupply(good) - 1);
    }

    /**
     * Sell a good at the market.
     *
     * @param good The good being sold.
     */
    public void sellGood(TradeGood good) {
        productSupply.put(good, getSupply(good) + 1);
    }

    public void discountPrices(int traderFactor) {
        double discount = 1 - ((traderFactor * 2.0 / 3) / 100.0);
        for (TradeGood good : prices.keySet()) {
            prices.put(good, (int)(prices.get(good) * discount));
        }
    }
}
