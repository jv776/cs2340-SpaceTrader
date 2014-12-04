/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controllers.GameController;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 * Model of a ship.
 *
 * @author John Varela
 */
public class Ship implements Serializable {
    public enum Type {
        /**
         * The cheapest and most basic type of ship.
         */
        Flea(10, 0, 0, 0, 1, 20, TechLevel.EARLY_INDUSTRIAL, 1, 2000, 100, 1, 0,
                new Image("/images/flea.png")),
        /**
         * A cheap ship. The player begins the game with a Gnat.
         */
        Gnat(15, 1, 0, 1, 1, 20, TechLevel.INDUSTRIAL, 2, 10000, 300, 1, 1,
                new Image("/images/gnat.gif")),
        /**
         * A moderately priced ship with decent features.
         */
        Firefly(20, 1, 1, 1, 1, 17, TechLevel.INDUSTRIAL, 3, 25000, 500, 1, 1,
                new Image("/images/firefly.png")),
        /**
         * An expensive and high-tech ship.
         */
        Mosquito(15, 2, 1, 1, 1, 13, TechLevel.INDUSTRIAL, 5, 30000, 700, 1, 1,
                new Image("/images/mosquito.gif")),
        /**
         * The second-largest and second-most expensive ship available.
         */
        BumbleBee(25, 1, 2, 2, 2, 15, TechLevel.INDUSTRIAL, 7, 60000, 900, 1, 2,
                new Image("/images/bumblebee.jpg")),
        /**
         * The largest and most expensive ship available.
         */
        Dragonfly(50, 4, 4, 4, 4, 25, TechLevel.POST_INDUSTRIAL, 10, 200000, 1200, 1, 2,
                new Image("/images/dragonfly.png"));

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

        /**
         * The Image that portrays the ship.
         */
        public final Image image;
        
        


        Type(int cargoCapacity, int weaponSlots, int shieldSlots,
             int gadgetSlots, int crewCapacity, int fuelCapacity,
             TechLevel minTechLevel, int fuelCost, int price,
             int hullStrength, int repairCost, int size,
             Image image) {

            this.cargoCapacity = cargoCapacity;

            this.weaponSlots = weaponSlots;
            this.shieldSlots = shieldSlots;
            this.gadgetSlots = gadgetSlots;

            this.crewCapacity = crewCapacity;
            this.fuelCapacity = fuelCapacity;

            this.minTechLevel = minTechLevel;

            this.fuelCost = fuelCost;
            this.price = price;

            this.hullStrength = hullStrength;
            this.repairCost = repairCost;
            this.size = size;

            this.image = image;
        }
    }

    private Type type;
    private CrewMember owner; //in case the owner is an NPC (i.e. pirates/police)
    private ArrayList<Mercenary> crew;
    private CargoHold cargoHold;

    private int numWeapons;
    private int numShields;
    private int numGadgets;

    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;
    private ArrayList<Gadget> gadgets;

    private ArrayList<Weapon> equippedWeapons;
    private ArrayList<Shield> equippedShields;
    private ArrayList<Gadget> equippedGadgets;


    private double fuelAmount;
    private int hullStrength;
    private int maxShields;
    private double currentShields;

    private double scatter;
    private double homing;
    private boolean piercing;
    private double reflectChance;

    /**
     * Create a new ship of a given type and set its owner.
     *
     * @param type  The type of the new ship.
     * @param owner The owner of the new ship.
     */
    public Ship(Type type, CrewMember owner) {
        this.type = type;
        this.fuelAmount = type.fuelCapacity;
        this.hullStrength = type.hullStrength;
        this.cargoHold = new CargoHold(type.cargoCapacity);
        this.crew = new ArrayList<>();

        this.weapons = new ArrayList<>();
        this.equippedWeapons = new ArrayList<>();
        this.shields = new ArrayList<>();
        this.equippedShields = new ArrayList<>();
        this.gadgets = new ArrayList<>();
        this.equippedGadgets = new ArrayList<>();

        this.numWeapons = 0;
        this.numShields = 0;
        this.numGadgets = 0;

        this.maxShields = calculateMaxShields();
        this.currentShields = maxShields;

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
    
    public ArrayList<Mercenary> getCrew() {
        return crew;
    }

    public boolean addCrew(Mercenary merc) {
        if (crew.size() < type.crewCapacity) {
            crew.add(merc);
            GameController.getGameData().getStockMarket()
                    .setWeight(GameController.getGameData()
                            .getPlayer().getInvestorSkillPoints());
            //HACK
            return true;
        }
        return false;
    }
    
    public void removeCrew(Mercenary merc) {
        crew.remove(merc);
    }
    
    public int getCrewSize() {
        return crew.size();
    }
    
    public int getMaxCrew() {
        return type.crewCapacity;
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

    public int getMaxShields() {
        return maxShields;
    }

    public int getCurrentShields() {
        return (int)currentShields;
    }

    public int getRepairCost() {
        return type.repairCost;
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
        } else {
            fuelAmount += amount;
        }
    }

    /**
     * Restores ship fuel to maximum.
     */
    public void refuel() {
        fuelAmount = type.fuelCapacity;
    }

    public void repair() {
        hullStrength = type.hullStrength;
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

    public Image getImage() {
        return this.type.image;
    }

    /**
     * Damages ship
     *
     * @param damage amount of base damage to be detracted
     */
    public void takeDamage(int damage){
        if (currentShields < damage) {
            int overflow = damage - getCurrentShields();
            currentShields = 0;
            hullStrength -= damage;
        } else {
            currentShields -= damage;
        }
    }

    /**
     * Returns the Attack value of the ship.
     *
     * @return the attack of the ship
     */
    public int calculateAttack() {
        int attack = 0;
        for (Weapon w : equippedWeapons) {
            attack += w.getType().getDamage();
        }
        return attack;
    }

    private int calculateMaxShields() {
        int defense = 0;
        for (Shield s : equippedShields) {
            defense += s.getType().getStrength();
        }
        return defense;
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

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
        numWeapons++;
    }

    public void addShield(Shield shield) {
        shields.add(shield);
        numShields++;
    }

    public void addGadget(Gadget gadget) {
        gadgets.add(gadget);
        numGadgets++;
    }

    public void equipWeapon(Weapon weapon) {
        equippedWeapons.add(weapon);
    }

    public void unequipWeapon(Weapon weapon) {
        equippedWeapons.remove(weapon);
    }

    public void equipShield(Shield shield) {
        equippedShields.add(shield);
        maxShields = calculateMaxShields();
        currentShields = maxShields;
    }

    public void unequipShield(Shield shield) {
        equippedShields.remove(shield);
    }

    public void equipGadget(Gadget gadget) {
        gadget.onEquip(this);
        equippedGadgets.add(gadget);
    }

    public void unequipGadget(Gadget gadget) {
        gadget.onUnequip(this);
        equippedGadgets.remove(gadget);
    }

    public ArrayList<Weapon> getEquippedWeapons() {
        return equippedWeapons;
    }
    
    public ArrayList<Shield> getEquippedShields() {
        return equippedShields;
    }
    
    public ArrayList<Gadget> getEquippedGadgets() {
        return equippedGadgets;
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

    public void addScatter() {
        scatter += 10;
    }

    public void removeScatter() {
        scatter -= 10;
    }

    public double getScatter() {
        return scatter;
    }

    public void addHoming() {
        homing += 3;
    }

    public void removeHoming() {
        homing -= 3;
    }

    public double getHoming() {
        return homing;
    }

    public void activatePiercing() {
        piercing = true;
    }

    public void deactivatePiercing() {
        piercing = false;
    }

    public boolean shouldPierce() {
        return piercing;
    }

    public void addReflector() {
        reflectChance += .2;
    }

    public void removeReflector() {
        reflectChance -= .2;
    }

    public double getReflectChance() {
        return reflectChance;
    }

    public void shieldRegen() {
        currentShields += owner.getEngineerSkillPoints() / 10000.0 * maxShields;
        if (currentShields > maxShields) {
            currentShields = maxShields;
        }
    }

    public void activateCloaking() {

    }

    public void deactivateCloaking() {

    }
    
    public int getNumWeapons() {
        return equippedWeapons.size();
    }
    
    public int getWeaponsCapacity() {
        return type.weaponSlots;
    }
    
    public int getNumShields() {
        return equippedShields.size();
    }
    
    public int getShieldsCapacity() {
        return type.shieldSlots;
    }
    
    public int getNumGadgets() {
        return equippedGadgets.size();
    }
    
    public int getGadgetsCapacity() {
        return type.gadgetSlots;
    }
}
