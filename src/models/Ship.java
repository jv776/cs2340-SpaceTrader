/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author John Varela
 */
public class Ship implements Purchasable {
    private final ShipType type;
    private int currentFuel;
    private int hullStrength;
    private int bounty;
    private CargoHold cargoHold;
    //private Weapon[] weapons;
    //private Shield[] shields;
    //private Gadget[] gadgets;
    
    public Ship(ShipType model) {
        type = model;
        currentFuel = type.fuelCapacity;
        hullStrength = type.maxHullStrength;
        bounty = 0; //bounty on ship starts at 0 by default (?)
        cargoHold = new CargoHold();
        //weapons = new Weapon[type.weaponSlots];
        //shields = new Shield[type.shieldSlots];
        //gadgets = new Gadget[type.gadgetSlots];
    }
    
    public int computeCost(SolarSystem marketLocation) {
        return type.price;
    }
}
