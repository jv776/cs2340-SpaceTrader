/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitchhikers.spacetrader.models;

import java.io.Serializable;

/**
 * Class representing the stock market. For simplicity, there is only one stock
 * market that can be invested in from throughout the galaxy
 *
 * @author John
 */
public class StockMarket implements Serializable {
    private static final int NUM_CORPS = 10;
    private StockCorporation[] corporations;        
    private int weight;
    
    public StockMarket(int investor) {
        corporations = new StockCorporation[NUM_CORPS];
        
        weight = investor;
        
        for (int i = 0; i < NUM_CORPS; i++) {
            corporations[i] = new StockCorporation();
        }
    }
    
    public void updateStocks() {
        for (int i = 0; i < NUM_CORPS; i++) {
            corporations[i].updateStock(weight);
        }
    }
    
    public StockCorporation[] getCorporations() {
        return corporations;
    }
    
    public void setWeight(int newWeight) {
        weight = newWeight;
    }
    
    public StockCorporation getCorporation(int i) {
        return corporations[i];
    }
}
