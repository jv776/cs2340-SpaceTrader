/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Model of a ship.
 *
 * @author John Varela
 */
public class Ship implements Serializable {
    /**
     * The types of ships available throughout the universe.
     */
    public static enum Type {
        /**
         * The cheapest and most basic type of ship.
         */
        Flea(10, 0, 0, 0, 1, 20, TechLevel.EARLY_INDUSTRIAL, 1, 2000, 25, 1, 0),

        /**
         * A cheap ship. The player begins the game with a Gnat.
         */
        Gnat(15, 1, 0, 1, 1, 20, TechLevel.INDUSTRIAL, 2, 10000, 100, 1, 1),

        /**
         * A moderately priced ship with decent features.
         */
        Firefly(20, 1, 1, 1, 1, 17, TechLevel.INDUSTRIAL, 3, 25000, 100, 1, 1),

        /**
         * An expensive and high-tech ship.
         */
        Mosquito(15, 2, 1, 1, 1, 13, TechLevel.INDUSTRIAL, 5, 30000, 100, 1, 1),

        /**
         * The largest and most expensive ship available.
         */
        BumbleBee(25, 1, 2, 2, 2, 15, TechLevel.INDUSTRIAL, 7, 60000, 100, 1, 2);

        /**
         * The ship's maximum cargo capacity.
         */
        public final int cargoCapacity;

        /**
         * The number of weapons the ship can equip.
         */
        public final int weaponSlots;

        /**
         * The number of shields the ship can equip.
         */
        public final int shieldSlots;

        /**
         * The number of gadgets the ship can equip.
         */
        public final int gadgetSlots;

        /**
         * The maximum number of crew members the ship can accommodate.
         */
        public final int crewCapacity;

        /**
         * The maximum amount of fuel the ship can hold.
         */
        public final int fuelCapacity;

        /**
         * The minimum technology level at which the ship can be bought.
         */
        public final TechLevel minTechLevel;

        /**
         * The cost of fuel for the ship.
         */
        public final int fuelCost;

        /**
         * The price of the ship.
         */
        public final int price;

        //public final int bounty;
        //public final int occurrence;

        /**
         * The strength of the ship's hull.
         */
        public final int hullStrength;

        /**
         * The cost of repairing the ship.
         */
        public final int repairCost;

        /**
         * The size of the ship.
         */
        public final int size;

        Type(int cargoCapacity, int weaponSlots, int shieldSlots,
             int gadgetSlots, int crewCapacity, int fuelCapacity,
             TechLevel minTechLevel, int fuelCost, int price, int hullStrength,
             int repairCost, int size) {
            this.cargoCapacity = cargoCapacity;

            this.weaponSlots = weaponSlots;
            this.shieldSlots = shieldSlots;
            this.gadgetSlots = gadgetSlots;

            this.crewCapacity = crewCapacity;
            this.fuelCapacity = fuelCapacity;

            this.minTechLevel = minTechLevel;

            this.fuelCost = fuelCost;
            this.price = price;
            //this.bounty = bounty;
            //this.occurrence = occurrence;

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

    private ArrayList<Weapon> weapons;

    private ArrayList<Shield> shields;

    private ArrayList<Gadget> gadgets;

    /**
     * Create a new ship of a given type and set its owner.
     *
     * @param type The type of the new ship.
     * @param owner The owner of the new ship.
     */
    public Ship(Type type, CrewMember owner) {
        this.type = type;
        this.fuelAmount = type.fuelCapacity;
        this.hullStrength = type.hullStrength;
        this.cargoHold = new CargoHold(type.cargoCapacity);
        weapons = new ArrayList<>();
        shields = new ArrayList<>();
        gadgets = new ArrayList<>();

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

    /**
     * @return The maximum hull strength of the ship.
     */
    public int getMaxHullStrength() {
        return type.hullStrength;
    }

    /**
     * Returns whether ship is dead.
     *
     * @return ship is dead
     */
    public boolean isDead() {
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

    /**
     * Adds fuel to ship.
     *
     * @param amount amount of fuel to be added
     */
    public void addFuel(double amount) {
        if (fuelAmount + amount > type.fuelCapacity) {
            fuelAmount = type.fuelCapacity;
        }
        else {
            fuelAmount += amount;
        }
    }

    /**
     * Restores ship fuel to maximum.
     */
    public void refuel() {
        fuelAmount = type.fuelCapacity;
    }

    /**
     * Returns maximum amound of fuel ship can carry
     *
     * @return Maximum amount of fuel
     */
    public int getFuelCapacity() {
        return this.type.fuelCapacity;
    }

    /**
     * @return The cost of fuel for the ship.
     */
    public int getFuelCost() {
        return this.type.fuelCost;
    }

    /**
     * Damages ship
     *
     * @param damage amount of base damage to be detracted
     */
    public void takeDamage(int damage) {
        hullStrength -= damage; //needs to work with shields
    }

    /**
     * Returns the Attack value of the ship.
     *
     * @return the attack of the ship
     */
    public int calculateAttack() {
        int attack = 0;
        for (Weapon w : weapons) {
            attack += w.getDamage();
        }
        return attack; //change to use weapons when they are implemented
    }

    /**
     * Tells if the ship is carrying illegal goods
     *
     * @return ship has illegal goods
     */
    public boolean hasIllegalGoods() {
        return cargoHold.hasIllegalGoods();
    }

    /**
     * Returns weapons on ship
     *
     * @return List of weapons equipped
     */
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    /**
     * Adds a weapon to ship
     *
     * @param w weapon to be added
     */
    public void addWeapon(Weapon w) {
        weapons.add(w);
    }

    /**
     * Adds a shield to ship
     *
     * @param s shield to be added
     */
    public void addShield(Shield s) {
        shields.add(s);
    }

    /**
     * Adds a gadget to ship
     *
     * @param g gadget to be added
     */
    public void addGadget(Gadget g) {
        gadgets.add(g);
    }

    /**
     * Returns shields on ship
     *
     * @return List of shields equipped
     */
    public ArrayList<Shield> getShields() {
        return shields;
    }
    /**
     * Returns gadgets on ship
     *
     * @return List of gadgets equipped
     */
    public ArrayList<Gadget> getGadgets() {
        return gadgets;
    }
}
