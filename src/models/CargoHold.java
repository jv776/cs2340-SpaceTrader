/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.HashMap;

/**
 * Wrapper class for cargo items.
 * 
 * @author Kevin Gibby
 */
public class CargoHold extends HashMap<CargoItem, Integer> {
    
    public void addItem(CargoItem item) {
        boolean isNew = !keySet().contains(item);
        if (isNew) {
            put(item, 1);
        } else {
            put(item, get(item) + 1);
        }
    }
    
    public void addItemQuantity(CargoItem item, int amount) {
        boolean isNew = !keySet().contains(item);
        if (isNew) {
            put(item, amount);
        } else {
            put(item, get(item) + amount);
        }
    }
    
    public boolean removeItem(CargoItem item) {
        if (keySet().contains(item)) {
            if (get(item) == 1) {
                remove(item);
            } else {
                put(item, get(item) - 1);
            }
            return true;
        } else {
            return false;
        }
    }
    
    public TradeGood[] getTradeGoods() {
        TradeGood[] goods = new TradeGood[10];
        int count = 0;
        for (CargoItem item : keySet()) {
            if (item instanceof TradeGood) {
                goods[count] = (TradeGood)item;
                count++;
            }
        }
        TradeGood[] finalGoods = new TradeGood[count];
        for (int i = 0; i < count; i++) {
            finalGoods[i] = goods[i];
        }
        return finalGoods;
    }
    
    public int getQuantity(CargoItem item) {
        if (keySet().contains(item)) {
            return get(item);            
        } else {
            return 0;
        }
    }
}