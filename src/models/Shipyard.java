package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import models.Ship.Type;

public class Shipyard implements Serializable {
    private final Planet location;
    private HashMap<Type, Integer> ships;
    private ArrayList<Weapon.Type> weapons;
    private ArrayList<Shield.Type> shields;
    private ArrayList<Gadget.Type> gadgets;
    
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
            int localTechFactor = location.getSolarSystem().getTechLevel().ordinal();
            
            if (localTechFactor >= ship.minTechLevel.ordinal()) {
                goods.put(ship, ship.price);
            }
        }
        return goods;
    }
    
    private ArrayList<Weapon.Type> generateWeapons() {
        ArrayList<Weapon.Type> goods = new ArrayList<>();
        
        for (Weapon.Type weapon : Weapon.Type.values()) {
            int minTech = weapon.getMinTechLevel().ordinal();
            
            if (minTech <= location.getSolarSystem().getTechLevel().ordinal()) {
                goods.add(weapon);
            }
        }
        
        return goods;
    }
    
    private ArrayList<Shield.Type> generateShields() {
        ArrayList<Shield.Type> goods = new ArrayList<>();
        
        for (Shield.Type shield : Shield.Type.values()) {
            int minTech = shield.getMinTechLevel().ordinal();
            
            if (minTech <= location.getSolarSystem().getTechLevel().ordinal()) {
                goods.add(shield);
            }
        }
        
        return goods;
    }
    
    private ArrayList<Gadget.Type> generateGadgets() {
        ArrayList<Gadget.Type> goods = new ArrayList<>();
        
        for (Gadget.Type gadget : Gadget.Type.values()) {
            int minTech = gadget.getMinTechLevel().ordinal();
            
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
    
    public ArrayList<Weapon.Type> getWeapons() {
        return weapons;
    }
    
    public ArrayList<Shield.Type> getShields() {
        return shields;
    }
    
    public ArrayList<Gadget.Type> getGadgets() {
        return gadgets;
    }
}
