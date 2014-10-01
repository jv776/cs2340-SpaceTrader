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
 * @author Kevin Gibby, Alex, John
 */
public class CargoHold {
    private final int capacity;
    private HashMap<CargoItem, Integer> cargo;
    
    public CargoHold(int maxCapacity) {
        capacity = maxCapacity;
        cargo = new HashMap<>();
    }
    
    public void addItem(CargoItem item) {
        if (cargo.size() < capacity) {
            boolean isNew = !cargo.keySet().contains(item);
            if (isNew) {
                cargo.put(item, 1);
            } else {
                cargo.put(item, cargo.get(item) + 1);
            }
        }
    }
    
    public void addItemQuantity(CargoItem item, int amount) {
        boolean isNew = !cargo.keySet().contains(item);
        if (isNew) {
            cargo.put(item, amount);
        } else {
            cargo.put(item, cargo.get(item) + amount);
        }
    }
    
    public boolean removeItem(CargoItem item) {
        if (cargo.keySet().contains(item)) {
            if (cargo.get(item) == 1) {
                cargo.remove(item);
            } else {
                cargo.put(item, cargo.get(item) - 1);
            }
            return true;
        } else {
            return false;
        }
    }
    
    public TradeGood[] getTradeGoods() {
        TradeGood[] goods = new TradeGood[10];
        int count = 0;
        for (CargoItem item : cargo.keySet()) {
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
        System.out.println(cargo);
        System.out.println(cargo.keySet());
        System.out.println(item);
        if (cargo.keySet().contains(item)) {
            return cargo.get(item);            
        } else {
            return 0;
        }
    }
    
    public boolean hasSpace() {
        int size = 0;
        for (CargoItem item : cargo.keySet()) {
            size += getQuantity(item);
        }
        
        return size < capacity;
    }
}
