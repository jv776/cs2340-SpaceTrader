package com.hitchhikers.spacetrader.models;

import java.io.Serializable;

/**
 * Model of a shield that protects a ship from damage.
 *
 * @author Taylor
 */
public class Shield extends Upgrade implements Serializable {
    public enum Type {
        Energy(25, 5, 1000, TechLevel.EARLY_INDUSTRIAL, "Shield that creates an absorptive energy field around the ship."),
        Reflective(150, 30, 10000, TechLevel.INDUSTRIAL, "Shield that creates a reflective energy field around the ship."),
        Overcharged(150, 40, 10000, TechLevel.POST_INDUSTRIAL, "Shield that creates a reflective energy field around the ship."),
        Alien(150, 50, 10000, TechLevel.HI_TECH, "Shield that creates a reflective energy field around the ship.");

        private final int strength;
        private final TechLevel minTechLevel;
        private final int price;
        private final String description;

        Type(int strength, int chargeRate, int price, TechLevel minTechLevel, String description) {
            this.strength = strength;
            this.minTechLevel = minTechLevel;
            this.price = price;
            this.description = description;
        }

        /**
         * @return the strength
         */
        public int getStrength() {
            return strength;
        }

        /**
         * @return the minTechLevel
         */
        public TechLevel getMinTechLevel() {
            return minTechLevel;
        }

        /**
         * @return the price
         */
        public int getPrice() {
            return price;
        }

        /**
         * @return the description
         */
        public String getDescription() {
            return description;
        }
    }
    
    private int strength;
    private Type type;
    private int price;
    private String name;

    public Shield(Type type) {
        this.type = type;
        strength = type.getStrength();
        price = type.getPrice();
        name = type + " Shield";
    }
    
    public Type getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public String getSlot() {
        return "weapon";
    }

    public String toString() {
        return type.getDescription() + "\n\nStrength: " + strength;
    }

    public TechLevel getTechLevel() {
        return type.getMinTechLevel();
    }

    public String getName() {
        return name;
    }
    
    @Override
    public void onEquip(Ship ship) {
        ship.equipShield(this);
    }

    @Override
    public void onUnequip(Ship ship) {
        ship.unequipShield(this);
    }
}
