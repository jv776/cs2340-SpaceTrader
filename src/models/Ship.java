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
public enum Ship {
    Flea (10, 0, 0, 0, 1, 20, TechLevel.EARLY_INDUSTRIAL, 1, 2000, 5, 2, 25, 1),
    Gnat (15, 1, 0, 1, 1, 14, TechLevel.INDUSTRIAL, 2, 10000, 50, 28, 100, 1),
    Firefly (20, 1, 1, 1, 1, 17, TechLevel.INDUSTRIAL, 3, 25000, 75, 20, 100,
            1),
    Mosquito (15, 2, 1, 1, 1, 13, TechLevel.INDUSTRIAL, 5, 30000, 100, 20, 100,
                1),
    BumbleBee (25, 1, 2, 2, 2, 15, TechLevel.INDUSTRIAL, 7, 60000, 125, 15, 100,
                1);
    
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
    private int bounty; //I'm not sure what this does
    private int occurrence; //Once again not sure what this affects
    private final int maxHullStrength;
    private int hullStrength;
    private final int repairCost;
    
    private Ship(int cargoHolds, int weaponSlots, int shieldSlots,
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
