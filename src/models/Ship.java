/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 * Model of a ship.
 *
 * @author John Varela
 */
public class Ship implements Serializable {
    public static enum Type {
        Flea(10, 0, 0, 0, 1, 20, TechLevel.EARLY_INDUSTRIAL, 1, 2000, 5, 2, 25, 1, 0),
        Gnat(15, 1, 0, 1, 1, 20, TechLevel.INDUSTRIAL, 2, 10000, 50, 28, 100, 1, 1),
        Firefly(20, 1, 1, 1, 1, 17, TechLevel.INDUSTRIAL, 3, 25000, 75, 20, 100, 1, 1),
        Mosquito(15, 2, 1, 1, 1, 13, TechLevel.INDUSTRIAL, 5, 30000, 100, 20, 100, 1, 1),
        BumbleBee(25, 1, 2, 2, 2, 15, TechLevel.INDUSTRIAL, 7, 60000, 125, 15, 100, 1, 2);

        public final int cargoCapacity;

        public final int weaponSlots;
        public final int shieldSlots;
        public final int gadgetSlots;

        public final int crewCapacity;
        public final int fuelCapacity;

        public final TechLevel minTechLevel;

        public final int fuelCost;
        public final int price;
        public final int bounty;
        public final int occurrence;

        public final int hullStrength;
        public final int repairCost;
        public final int size;

        Type(int cargoCapacity, int weaponSlots, int shieldSlots, int gadgetSlots, int crewCapacity, int fuelCapacity,
             TechLevel minTechLevel, int fuelCost, int price, int bounty, int occurrence, int hullStrength, int repairCost, int size) {
            this.cargoCapacity = cargoCapacity;

            this.weaponSlots = weaponSlots;
            this.shieldSlots = shieldSlots;
            this.gadgetSlots = gadgetSlots;

            this.crewCapacity = crewCapacity;
            this.fuelCapacity = fuelCapacity;

            this.minTechLevel = minTechLevel;

            this.fuelCost = fuelCost;
            this.price = price;
            this.bounty = bounty;
            this.occurrence = occurrence;

            this.hullStrength = hullStrength;
            this.repairCost = repairCost;
            this.size = size;
        }
    }

    private Type type;
    private CrewMember owner; //in case the owner is an NPC (i.e. pirates/police)
    private CargoHold cargoHold;

    private double fuelAmount;
    private int hullStrength;

    public Ship(Type type, CrewMember owner) {
        this.type = type;
        this.fuelAmount = type.fuelCapacity;
        this.hullStrength = type.hullStrength;
        this.cargoHold = new CargoHold(type.cargoCapacity);

        this.owner = owner;
    }

    /**
     * @return The type of the ship
     */
    public Type getType() {
        return type;
    }

    /**
     * @return The character who owns the ship
     */
    public CrewMember getOwner() {
        return owner;
    }

    /**
     * @return The ship's cargo hold
     */
    public CargoHold getCargoHold() {
        return cargoHold;
    }

    /**
     * @return The amount of fuel currently available in the ship
     */
    public double getFuelAmount() {
        return fuelAmount;
    }

    /**
     * @return The ship's current hull strength
     */
    public int getHullStrength() {
        return hullStrength;
    }
    public int getMaxHullStrength() {
        return type.hullStrength;
    }

    public boolean isDead(){
        return hullStrength <= 0;
    }

    /**
     * Expend the amount of fuel necessary to travel a given distance.
     *
     * @param distance The distance traveled
     */
    public void expendFuel(double distance) {
        fuelAmount -= distance;
    }
    
    public void addFuel(double amount) {
        if(fuelAmount + amount > type.fuelCapacity)
            fuelAmount = type.fuelCapacity;
        else
            fuelAmount += amount;
    }
    
    public void refuel() {
        fuelAmount = type.fuelCapacity;
    }
    
    public int getFuelCapacity() {
        return this.type.fuelCapacity;
    }
    
    public int getFuelCost() {
        return this.type.fuelCost;
    }

    public void takeDamage(int damage){
        hullStrength -= damage; //needs to work with shields
    }

    public int calculateAttack(){
        return (int)(Math.random()*15); //change to use weapons when they are implemented
    }

    public boolean hasIllegalGoods(){
        return cargoHold.hasIllegalGoods();
    }
}
