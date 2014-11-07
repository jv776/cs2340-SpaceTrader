package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import models.Gadget.GadgetType;
import models.Shield.ShieldType;
import models.Ship.Type;
import models.Weapon.WeaponType;

public class Shipyard implements Serializable {
    private final Planet location;
    private HashMap<Type, Integer> ships;
    private ArrayList<WeaponType> weapons;
    private ArrayList<ShieldType> shields;
    private ArrayList<GadgetType> gadgets;
    
    public Shipyard(Planet location) {
        this.location = location;
        ships = generateShips();
        weapons = generateWeapons();
        shields = generateShields();
        gadgets = generateGadgets();
    }
    
    //Determine the number of goods available on a planet
    private HashMap<Type, Integer> generateShips() {
        HashMap<Type, Integer> goods = new HashMap<>();
        
        for (Type ship : Ship.Type.values()) {
            int localTechFactor = location.solarSystem.getTechLevel().ordinal();
            
            if (localTechFactor >= ship.minTechLevel.ordinal()) {
                goods.put(ship, ship.price);
            }
        }
        return goods;
    }
    
    private ArrayList<WeaponType> generateWeapons() {
        ArrayList<WeaponType> goods = new ArrayList<>();
        
        for (WeaponType weapon : WeaponType.values()) {
            int minTech = weapon.minTechLevel.ordinal();
            
            if (minTech <= location.getSolarSystem().getTechLevel().ordinal()) {
                goods.add(weapon);
            }
        }
        
        return goods;
    }
    
    private ArrayList<ShieldType> generateShields() {
        ArrayList<ShieldType> goods = new ArrayList<>();
        
        for (ShieldType shield : ShieldType.values()) {
            int minTech = shield.minTechLevel.ordinal();
            
            if (minTech <= location.getSolarSystem().getTechLevel().ordinal()) {
                goods.add(shield);
            }
        }
        
        return goods;
    }
    
    private ArrayList<GadgetType> generateGadgets() {
        ArrayList<GadgetType> goods = new ArrayList<>();
        
        for (GadgetType gadget : GadgetType.values()) {
            int minTech = gadget.minTechLevel.ordinal();
            
            if (minTech <= location.getSolarSystem().getTechLevel().ordinal()) {
                goods.add(gadget);
            }
        }
        
        return goods;
    }
    
    /**
     * Find the price of a specific good.
     * 
     * @param ship The good being checked for its price
     * @return The price of the specified trade good
     */
    public int getShipPrice(Type ship) {
        return ships.get(ship);
    }
    
    public boolean ownsShip(Type ship, Player p) {
        return p.getShip().getType().equals(ship);
    }
    
    public HashMap<Type, Integer> getShips() {
        return ships;
    }
    
    public ArrayList<WeaponType> getWeapons() {
        return weapons;
    }
    
    public ArrayList<ShieldType> getShields() {
        return shields;
    }
    
    public ArrayList<GadgetType> getGadgets() {
        return gadgets;
    }
}
