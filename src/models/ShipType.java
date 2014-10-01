/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Enumeration of every type of ship usable by the player, police, and
 * pirates. Each ship type holds basic information about the properties
 * of that type of ship.
 * 
 * @author John Varela
 */
enum ShipType {
    Flea (10, 0, 0, 0, 1, 20, TechLevel.EARLY_INDUSTRIAL, 1, 2000, 2, 25, 1),
    Gnat (15, 1, 0, 1, 1, 14, TechLevel.INDUSTRIAL, 2, 10000, 28, 100, 1),
    Firefly (20, 1, 1, 1, 1, 17, TechLevel.INDUSTRIAL, 3, 25000, 20, 100,
            1),
    Mosquito (15, 2, 1, 1, 1, 13, TechLevel.INDUSTRIAL, 5, 30000, 20, 100,
                1),
    BumbleBee (25, 1, 2, 2, 2, 15, TechLevel.INDUSTRIAL, 7, 60000, 15, 100,
                1);
    
    final int cargoCapacity;
    final int crewCapacity;
    final int fuelCapacity;
    final int weaponSlots;
    final int shieldSlots;
    final int gadgetSlots;
    final TechLevel minTechLevelToBuy;
    final int fuelCost;
    final int price;
    final int occurrence; //Not sure what this affects in gameplay
    final int maxHullStrength;
    final int repairCost;
    
    private ShipType(int cargoHolds, int numWeaponSlots, int numShieldSlots,
            int numGadgetSlots, int crewMembers, int fuelTankSize,
            TechLevel minLevel, int fuelTankCost, int shipPrice,
            int shipOccurrence, int shipHullStrength, int shipRepairCost) {
        cargoCapacity = cargoHolds;
        crewCapacity = crewMembers;
        fuelCapacity = fuelTankSize;
        weaponSlots = numWeaponSlots;
        shieldSlots = numShieldSlots;
        gadgetSlots = numGadgetSlots;
        minTechLevelToBuy = minLevel;
        fuelCost = fuelTankCost;
        price = shipPrice;
        occurrence = shipOccurrence;
        maxHullStrength = shipHullStrength;
        repairCost = shipRepairCost;
    }
}
