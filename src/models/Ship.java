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
abstract class Ship {
    private final int cargoCapacity;
    //private Weapon[] weapons;
    //private Shield[] shields;
    //private Gadget[] gadgets;
    private final int crewCapacity;
    private final int fuelCapacity;
    private int currentFuel;
    private final TechLevel minTechLevelToBuy;
    private final int fuelCost;
    private final int price;
    private int bounty; //I'm not sure what this does...
    private final int occurrence; //Once again not sure what this affects
    private final int maxHullStrength;
    private int hullStrength;
    private final int repairCost;
    
    public Ship(int cargoHolds, int weaponSlots, int shieldSlots,
            int gadgetSlots, int crewMembers, int fuelTankSize,
            TechLevel minLevel, int fuelTankCost, int shipPrice, int shipBounty,
            int shipOccurrence, int shipHullStrength, int shipRepairCost) {
        cargoCapacity = cargoHolds;
        //weapons = new Weapon[weaponSlots];
        //shields = new Shield[shieldSlots];
        //gadgets = new Gadget[gadgetSlots];
        crewCapacity = crewMembers;
        fuelCapacity = fuelTankSize;
        minTechLevelToBuy = minLevel;
        fuelCost = fuelTankCost;
        price = shipPrice;
        bounty = shipBounty;
        occurrence = shipOccurrence;
        maxHullStrength = shipHullStrength;
        hullStrength = maxHullStrength;
        repairCost = shipRepairCost;
    }
}
