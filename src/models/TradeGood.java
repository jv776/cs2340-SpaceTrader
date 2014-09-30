/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Optional;
import java.util.Random;

/**
 * Enumeration of every possible type of trading good that can be purchased
 * in the game.
 * 
 * @author John Varela
 */
public enum TradeGood implements Purchasable, CargoItem {
    WATER      (TechLevel.PRE_AGRICULTURE, TechLevel.PRE_AGRICULTURE,
                TechLevel.MEDIEVAL, 30, 3, 4, 30, 50, PriceEvent.DROUGHT,
                Optional.of(Resource.LOTS_OF_WATER),
                Optional.of(Resource.DESERT)),
    
    FOOD       (TechLevel.AGRICULTURE, TechLevel.PRE_AGRICULTURE,
                TechLevel.AGRICULTURE, 100, 5, 5, 90, 160,
                PriceEvent.CROP_FAILURE, Optional.of(Resource.RICH_SOIL),
                Optional.of(Resource.POOR_SOIL)),
    
    FURS       (TechLevel.PRE_AGRICULTURE, TechLevel.PRE_AGRICULTURE,
                TechLevel.PRE_AGRICULTURE, 250, 10, 10, 230, 280,
                PriceEvent.COLD, Optional.of(Resource.RICH_FAUNA), 
                Optional.of(Resource.LIFELESS)),
    
    ORE        (TechLevel.MEDIEVAL, TechLevel.MEDIEVAL,
                TechLevel.RENAISSANCE, 350, 20, 10, 350, 420, PriceEvent.WAR,
                Optional.of(Resource.MINERAL_RICH),
                Optional.of(Resource.MINERAL_POOR)),
    
    GAMES      (TechLevel.RENAISSANCE, TechLevel.AGRICULTURE,
                TechLevel.POST_INDUSTRIAL, 250, -10, 5, 160, 270,
                PriceEvent.BOREDOM, Optional.of(Resource.ARTISTIC),
                Optional.empty()),
    
    FIREARMS   (TechLevel.RENAISSANCE, TechLevel.AGRICULTURE,
                TechLevel.INDUSTRIAL, 1250, -75, 100, 600, 1100, PriceEvent.WAR,
                Optional.of(Resource.WARLIKE), Optional.empty()),
    
    MEDICINE   (TechLevel.EARLY_INDUSTRIAL, TechLevel.AGRICULTURE,
                TechLevel.POST_INDUSTRIAL, 650, -20, 10, 400, 700,
                PriceEvent.PLAGUE, Optional.of(Resource.LOTS_OF_HERBS),
                Optional.empty()),
    
    MACHINES   (TechLevel.EARLY_INDUSTRIAL, TechLevel.RENAISSANCE,
                TechLevel.INDUSTRIAL, 900, -30, 5, 600, 800,
                PriceEvent.LACK_OF_WORKERS, Optional.empty(), Optional.empty()),
    
    NARCOTICS  (TechLevel.INDUSTRIAL, TechLevel.AGRICULTURE,
                TechLevel.INDUSTRIAL, 3500, -125, 150, 2000, 3000,
                PriceEvent.BOREDOM, Optional.of(Resource.WEIRD_MUSHROOMS),
                Optional.empty()),
    
    ROBOTS     (TechLevel.POST_INDUSTRIAL, TechLevel.EARLY_INDUSTRIAL,
                TechLevel.HI_TECH, 5000, -150, 100, 3500, 5000,
                PriceEvent.LACK_OF_WORKERS, Optional.empty(), Optional.empty());
    
    final TechLevel minTechLevelBuy;
    final TechLevel minTechLevelSell;
    final TechLevel preferredTechLevel;
    final int basePrice;
    final int priceChangePerTechLevel;
    final int priceVariance;
    final int minTraderPrice;
    final int maxTraderPrice;
    final PriceEvent priceIncreaseEvent;
    final Optional<Resource> cheapConditions;
    final Optional<Resource> expensiveConditions;
    
    private TradeGood(TechLevel minTechBuy, TechLevel minTechSell,
            TechLevel preferred, int base, int priceChange, int variance,
            int lowTraderPrice, int highTraderPrice, PriceEvent increaseEvent,
            Optional<Resource> cheapResource, Optional<Resource> expensiveResource) {
        minTechLevelBuy = minTechBuy;
        minTechLevelSell = minTechSell;
        preferredTechLevel = preferred;
        basePrice = base;
        priceChangePerTechLevel = priceChange;
        priceVariance = variance;
        minTraderPrice = lowTraderPrice;
        maxTraderPrice = highTraderPrice;
        priceIncreaseEvent = increaseEvent;
        cheapConditions = cheapResource;
        expensiveConditions = expensiveResource;
    }
    
    private static int techLevelToInt(TechLevel tl) {
        switch(tl) {
            case PRE_AGRICULTURE: return 0;
            case AGRICULTURE: return 1;
            case MEDIEVAL: return 2;
            case RENAISSANCE: return 3;
            case EARLY_INDUSTRIAL: return 4;
            case INDUSTRIAL: return 5;
            case POST_INDUSTRIAL: return 6;
            default: return 7; //HI_TECH
        }
    }
    
    /**
     * Calculate the cost of a good based on the base price of the good,
     * the technology available in the market where it is being sold,
     * the natural resources and conditions on the planet that might
     * affect its price, and whether or not any special events that might
     * temporarily drive up the price are occurring.
     * 
     * @param marketLocation The location where the good is being purchased
     * @return The total price for the good
     */
    @Override
    public int computeCost(SolarSystem marketLocation) {
        Random r = new Random();
        
        int techLevelFactor = priceChangePerTechLevel *
                (techLevelToInt(marketLocation.getTechLevel()) -
                techLevelToInt(minTechLevelBuy));
        
        int variance = 1 + r.nextInt(priceVariance) / 100;
        
        double scarcityFactor = 1;
        if (expensiveConditions.isPresent()) {
            scarcityFactor = 1.25; //25% more expensive
        }
        
        double abundanceFactor = 1;
        if (cheapConditions.isPresent()) {
            abundanceFactor = 0.75; //25% cheaper
        }
        
        double eventFactor = 1;
        //need way to test if a special event is occurring in a system/planet
        
        return (int) ((basePrice + techLevelFactor) * variance * scarcityFactor
                * abundanceFactor * eventFactor);
    }

    /**
     * @return This item's name, as specified by the enum value.
     */
    @Override
    public String getItemName() {
        return this.toString();
    }
}
