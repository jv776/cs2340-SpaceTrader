/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitchhikers.spacetrader.models;

/**
 *
 * @author Alex
 */
public abstract class ShipUpgrade {
    protected boolean active;
    
    public ShipUpgrade() {
        active = false;
    }
    
    public void activate(Ship ship) {
        active = true;
        onEquip(ship);
    }
    
    public abstract void onEquip(Ship ship);
    
    public void deactivate(Ship ship) {
        active = false;
        onUnequip(ship);
    }
    
    public abstract void onUnequip(Ship ship);
    
    public boolean isActive() {
        return active;
    }
}
