/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * @author John Varela
 */
public class Ship {
    /**
     * @return The ship's cargo holds
     */
    
    final ShipType type;
    private int fuelAmount;
    private int hullStrength;
    private int cargoCapacity;
    private CargoHold cargoHold;
    
    public Ship(ShipType model) {
        type = model;
        fuelAmount = type.fuelCapacity;
        hullStrength = type.maxHullStrength;
        //bounty = 0; //bounty on ship starts at 0 by default (?)
        cargoHold = new CargoHold(type.cargoCapacity);
        //weapons = new Weapon[type.weaponSlots];
        //shields = new Shield[type.shieldSlots];
        //gadgets = new Gadget[type.gadgetSlots];
    }
    
    /**
     * @return The amount of fuel currently in the ship
     */
    public int getFuelAmount() {
        return fuelAmount;
    }
    
    public CargoHold getCargoHold() {
        return cargoHold;
    }
}
