/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.LinkedList;

/**
 * Represents a corporation on the stock market.
 *
 * @author John
 */
public class StockCorporation {
    private final String name;
    private LinkedList<Double> values;
    
    public StockCorporation() {
        name = "test";
        values = new LinkedList<>();
        
        values.addFirst(Math.random() * 1000);
        
        for (int i = 1; i < values.size(); i++) {
            values.addFirst(values.peekFirst() + Math.random() * 10 - 5);
        }
    }
    
    public void updateStock() {
        values.addFirst(values.peekFirst() + Math.random() * 10 - 5);
        values.removeLast();
    }
    
    public double currentValue() {
        return values.peekFirst();
    }
    
    public double previousValue() {
        double temp = values.removeFirst();
        double result = values.peekFirst();
        values.addFirst(temp);
        return result;
    }
    
    public double percentChange() {
        double now = currentValue();
        double prev = previousValue();
        
        return 100 * (now - prev) / prev;
    }
}