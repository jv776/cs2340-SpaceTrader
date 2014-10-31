package models;

import java.io.Serializable;
import java.util.HashMap;
import models.Ship.Type;

public class Shipyard implements Serializable {
    private final Planet location;
    private HashMap<Type, Integer> ships;
    
    public Shipyard(Planet location) {
        this.location = location;
        ships = generateSupplies();
    }
    
    //Determine the number of goods available on a planet
    private HashMap<Type, Integer> generateSupplies() {
        HashMap<Type, Integer> goods = new HashMap<>();
        
        for (Type ship : Ship.Type.values()) {
            int localTechFactor = location.solarSystem.getTechLevel().ordinal();
            
            if (localTechFactor >= ship.minTechLevel.ordinal()) {
                goods.put(ship, ship.price);
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
    public int getPrice(Type ship) {
        return ships.get(ship);
    }
    
    public boolean ownsShip(Type ship, Player p) {
        return p.getShip().getType().equals(ship);
    }
    
    public HashMap<Type, Integer> getShips() {
        return ships;
    }
}
