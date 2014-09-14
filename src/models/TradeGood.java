/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Highest-level representation of a good that can be bought and sold in
 * marketplaces. Specific types of goods will provide concrete implementations
 * of this class.
 * 
 * @author John Varela
 */
abstract class TradeGood {
    protected final TechLevel minTechLevelBuy;
    protected final TechLevel minTechLevelSell;
    protected final TechLevel preferredTechLevel;
    protected final int basePrice;
    protected final int priceChangePerTechLevel;
    protected final int priceVariance;
    protected final int minTraderPrice;
    protected final int maxTraderPrice;
    //price event info goes here
    
    public TradeGood(TechLevel minTechBuy, TechLevel minTechSell,
            TechLevel preferred, int base, int priceChange, int variance,
            int lowTraderPrice, int highTraderPrice) {
        minTechLevelBuy = minTechBuy;
        minTechLevelSell = minTechSell;
        preferredTechLevel = preferred;
        basePrice = base;
        priceChangePerTechLevel = priceChange;
        priceVariance = variance;
        minTraderPrice = lowTraderPrice;
        maxTraderPrice = highTraderPrice;
    }
}
