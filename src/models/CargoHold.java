/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Wrapper class for cargo items.
 *
 * @author Kevin Gibby, Alex, John
 */
public class CargoHold implements Serializable {
    private final int capacity;
    private HashMap<CargoItem, Integer> cargo;

    /**
     * Create a new cargo hold.
     * 
     * @param maxCapacity The capacity of the cargo hold.
     */
    public CargoHold(int maxCapacity) {
        capacity = maxCapacity;
        cargo = new HashMap<>();
    }


    /**
     * Add a new item to the cargo hold.
     *
     * @param item The item to add to the cargo hold
     */
    public void addItem(CargoItem item) {
        if (getQuantity() < capacity) {
            boolean isNew = !cargo.keySet().contains(item);
            if (isNew) {
                cargo.put(item, 1);
            } else {
                cargo.put(item, cargo.get(item) + 1);
            }
        }
    }

    public int getQuantity() {
        int size = 0;
        for (CargoItem item : cargo.keySet()) {
            size += getQuantity(item);
        }
        return size;
    }
    
    public int getCapacity() {
        return capacity;
    }

    /**
     * Add many of the same item to the cargo hold at once
     *
     * @param item The item to be added
     * @param amount The quantity of the item to be added
     */
    public void addItemQuantity(CargoItem item, int amount) {
        boolean isNew = !cargo.keySet().contains(item);
        if (isNew) {
            cargo.put(item, amount);
        } else {
            cargo.put(item, cargo.get(item) + amount);
        }
    }


    /**
     * Remove an item from the cargo hold.
     *
     * @param item The item to be removed
     * @return whether or not the item was successfully removed
     */
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


    /**
     * Get an array of every trade good contained in a cargo hold.
     *
     * @return The trade goods currently held in a cargo hold
     */
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

    /**
     * Get the quantity of a particular item currently held in cargo.
     *
     * @param item The item being queried
     * @return The quantity of the item currently held
     */
    public int getQuantity(CargoItem item) {
        if (cargo.keySet().contains(item)) {
            return cargo.get(item);
        } else {
            return 0;
        }
    }

    /**
     * @return Whether or not the cargo hold has space for more items
     */
    public boolean hasSpace() {
        return getQuantity() < capacity;
    }
    
    public boolean hasIllegalGoods(){
        for (TradeGood g:getTradeGoods()){
            if (g.getItemName().equals("Narcotics") || g.getItemName().equals("Firearms")){
                return true;
            }
        }
        return false;
    }
    
    public int getCargoQuantity() {
        return cargo.size();
    }
}
