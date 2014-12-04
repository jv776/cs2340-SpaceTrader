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
    public final String name;
    private LinkedList<Double> values;
    private double volatility;
    
    private static String randomString(int length) {
        String s = "";
        
        for (int i = 0; i < length; i++) {
            int codePoint = 65 + (int)(Math.random() * (90 - 65));
            s += (char)codePoint;
        }
        
        return s;
    }
    
    public StockCorporation() {
        name = randomString(3);
        values = new LinkedList<>();
        volatility = 10 + Math.random() * 100;
        
        values.addFirst(Math.random() * 1000);
        
        for (int i = 1; i < 30; i++) {
            values.addFirst(Math.max(values.peekFirst() + Math.random() * volatility - (volatility / 2), 5));
        }
    }
    
    public void updateStock(int weight) {
        double randomChange = Math.random() * (volatility + weight / 2.0) - (volatility / 2);
        values.addFirst(Math.max(values.peekFirst() + randomChange, 5));
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
    
    public String getName() {
        return name;
    }

    public LinkedList<Double> getValues() {
        return values;
    }
}
