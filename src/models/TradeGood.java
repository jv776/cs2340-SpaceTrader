/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Optional;

/**
 * Enumeration of every possible type of trading good that can be purchased
 * in the game.
 *
 * @author John Varela
 */
public enum TradeGood implements CargoItem {
    WATER(TechLevel.PRE_AGRICULTURE, TechLevel.PRE_AGRICULTURE,
            TechLevel.MEDIEVAL, 30, 3, 4, 30, 50, PriceEvent.DROUGHT,
            Optional.of(Resource.LOTS_OF_WATER),
            Optional.of(Resource.DESERT)),

    FOOD(TechLevel.AGRICULTURE, TechLevel.PRE_AGRICULTURE,
            TechLevel.AGRICULTURE, 100, 5, 5, 90, 160,
            PriceEvent.CROP_FAILURE, Optional.of(Resource.RICH_SOIL),
            Optional.of(Resource.POOR_SOIL)),

    FURS(TechLevel.PRE_AGRICULTURE, TechLevel.PRE_AGRICULTURE,
            TechLevel.PRE_AGRICULTURE, 250, 10, 10, 230, 280,
            PriceEvent.COLD, Optional.of(Resource.RICH_FAUNA),
            Optional.of(Resource.LIFELESS)),

    ORE(TechLevel.MEDIEVAL, TechLevel.MEDIEVAL,
            TechLevel.RENAISSANCE, 350, 20, 10, 350, 420, PriceEvent.WAR,
            Optional.of(Resource.MINERAL_RICH),
            Optional.of(Resource.MINERAL_POOR)),

    GAMES(TechLevel.RENAISSANCE, TechLevel.AGRICULTURE,
            TechLevel.POST_INDUSTRIAL, 250, -10, 5, 160, 270,
            PriceEvent.BOREDOM, Optional.of(Resource.ARTISTIC),
            Optional.<Resource>empty()),

    FIREARMS(TechLevel.RENAISSANCE, TechLevel.AGRICULTURE,
            TechLevel.INDUSTRIAL, 1250, -75, 100, 600, 1100, PriceEvent.WAR,
            Optional.of(Resource.WARLIKE), Optional.<Resource>empty()),

    MEDICINE(TechLevel.EARLY_INDUSTRIAL, TechLevel.AGRICULTURE,
            TechLevel.POST_INDUSTRIAL, 650, -20, 10, 400, 700,
            PriceEvent.PLAGUE, Optional.of(Resource.LOTS_OF_HERBS),
            Optional.<Resource>empty()),

    MACHINES(TechLevel.EARLY_INDUSTRIAL, TechLevel.RENAISSANCE,
            TechLevel.INDUSTRIAL, 900, -30, 5, 600, 800,
            PriceEvent.LACK_OF_WORKERS, Optional.<Resource>empty(), Optional.<Resource>empty()),

    NARCOTICS(TechLevel.INDUSTRIAL, TechLevel.AGRICULTURE,
            TechLevel.INDUSTRIAL, 3500, -125, 150, 2000, 3000,
            PriceEvent.BOREDOM, Optional.of(Resource.WEIRD_MUSHROOMS),
            Optional.<Resource>empty()),

    ROBOTS(TechLevel.POST_INDUSTRIAL, TechLevel.EARLY_INDUSTRIAL,
            TechLevel.HI_TECH, 5000, -150, 100, 3500, 5000,
            PriceEvent.LACK_OF_WORKERS, Optional.<Resource>empty(), Optional.<Resource>empty());

    private final TechLevel minTechLevelBuy;
    private final TechLevel minTechLevelSell;
    private final TechLevel preferredTechLevel;
    private final int basePrice;
    private final int priceChangePerTechLevel;
    private final int priceVariance;
    private final int minTraderPrice;
    private final int maxTraderPrice;
    private final PriceEvent priceIncreaseEvent;
    private final Optional<Resource> cheapConditions;
    private final Optional<Resource> expensiveConditions;

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

    /**
     * @return This item's name, as specified by the enum value.
     */
    @Override
    public String getItemName() {
        String s = this.toString();
        char c = s.charAt(0);
        return Character.toUpperCase(c) + s.substring(1).toLowerCase();
    }

    /**
     * @return the minTechLevelBuy
     */
    public TechLevel getMinTechLevelBuy() {
        return minTechLevelBuy;
    }

    /**
     * @return the minTechLevelSell
     */
    public TechLevel getMinTechLevelSell() {
        return minTechLevelSell;
    }

    /**
     * @return the preferredTechLevel
     */
    public TechLevel getPreferredTechLevel() {
        return preferredTechLevel;
    }

    /**
     * @return the basePrice
     */
    public int getBasePrice() {
        return basePrice;
    }

    /**
     * @return the priceChangePerTechLevel
     */
    public int getPriceChangePerTechLevel() {
        return priceChangePerTechLevel;
    }

    /**
     * @return the priceVariance
     */
    public int getPriceVariance() {
        return priceVariance;
    }

    /**
     * @return the minTraderPrice
     */
    public int getMinTraderPrice() {
        return minTraderPrice;
    }

    /**
     * @return the maxTraderPrice
     */
    public int getMaxTraderPrice() {
        return maxTraderPrice;
    }

    /**
     * @return the priceIncreaseEvent
     */
    public PriceEvent getPriceIncreaseEvent() {
        return priceIncreaseEvent;
    }

    /**
     * @return the cheapConditions
     */
    public Optional<Resource> getCheapConditions() {
        return cheapConditions;
    }

    /**
     * @return the expensiveConditions
     */
    public Optional<Resource> getExpensiveConditions() {
        return expensiveConditions;
    }
}
