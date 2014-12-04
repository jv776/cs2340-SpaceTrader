/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Stores a "portfolio" of the player's investments.
 *
 * @author John
 */
public class StockPortfolio implements Serializable {
    private HashMap<StockCorporation, Integer> numShares;
    
    public StockPortfolio() {
        numShares = new HashMap<>();
    }
    
    public double buyStock(StockCorporation corp, int shares) {
        Integer currentShares = numShares.putIfAbsent(corp, shares);
        
        if (currentShares != null) {
            numShares.put(corp, currentShares + shares);
        }
        
        return corp.currentValue() * shares;
    }
    
    public double sellStock(StockCorporation corp, int shares) {
        if (numShares.containsKey(corp) && numShares.get(corp) >= shares) {
            numShares.put(corp, numShares.get(corp) - shares);
            return corp.currentValue() * shares;
        }
        
        return 0;
    }
    
    public int getShares(StockCorporation corp) {
        return (numShares.get(corp) == null ? 0 : numShares.get(corp));
    }
}
