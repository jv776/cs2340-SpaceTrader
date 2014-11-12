package models;

import java.io.Serializable;

/**
 * Model of a shield that protects a ship from damage.
 *
 * @author Taylor
 */
public class Shield implements Serializable {
    /**
     * The types of shields available for use as upgrades.
     */
    public static enum Type {
        /**
         * A basic energy shield.
         */
        Energy(25, 5, 1000, TechLevel.EARLY_INDUSTRIAL, "Shield that creates an absorptive energy field around the ship."),

        /**
         * A moderately powerful reflective shield.
         */
        Reflective(150, 30, 10000, TechLevel.INDUSTRIAL, "Shield that creates a reflective energy field around the ship."),

        /**
         * A very powerful reflective shield.
         */
        Overcharged(150, 40, 10000, TechLevel.POST_INDUSTRIAL, "Shield that creates a reflective energy field around the ship."),

        /**
         * An extremely strong shield made with alien technology.
         */
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

    /**
     * Create a new shield of a given type.
     *
     * @param type The type of the new shield.
     */
    public Shield(Type type) {
        this.type = type;
        strength = type.getStrength();
        price = type.getPrice();
        name = type + " Shield";
    }

    /**
     * @return The price of the shield.
     */
    public int getPrice() {
        return price;
    }

    /**
     * @return The type of slot used by the shield.
     */
    public String getSlot() {
        return "weapon";
    }

    /**
     * @return A string describing the shield.
     */
    @Override
    public String toString() {
        return type.getDescription() + "\n\nStrength: " + strength;
    }

    /**
     * @return The minimum tech level required to buy the shield.
     */
    public TechLevel getTechLevel() {
        return type.getMinTechLevel();
    }

    /**
     * @return The name of the type of shield.
     */
    public String getName() {
        return name;
    }

}
