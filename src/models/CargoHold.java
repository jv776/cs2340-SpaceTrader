/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 * Wrapper class for cargo items.
 * 
 * @author Kevin Gibby
 */
public class CargoHold extends ArrayList<CargoItem> {
    private final int capacity;
    
    public CargoHold(int cargoCapacity) {
        super(cargoCapacity);
        capacity = cargoCapacity;
    }
    
    @Override
    public boolean add(CargoItem item) {
        if (this.size() < capacity) {
            return this.add(item);
        }
        
        return false;
    }
}
