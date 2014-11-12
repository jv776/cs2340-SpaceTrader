package models;

import java.io.Serializable;

/**
 * Model of a weapon that can be attached to a ship to deal damage
 *
 * @author Taylor
 */
public class Weapon implements Upgrade, Serializable {

    /**
     * Enum representing the types of weapons available.
     *
     * @author Taylor
     */
    public static enum Type {
        Pulse(5, 1000, TechLevel.EARLY_INDUSTRIAL, "Plasma repeating laser, only able to fire in single bursts."),
        Beam(10, 10000, TechLevel.INDUSTRIAL, "Plasma laser able to sustain fire for more than a few seconds."),
        Military(15, 10000, TechLevel.POST_INDUSTRIAL, "Quantum laser built with top of the line military technology."),
        Alien(30, 100000, TechLevel.HI_TECH, "The most advanced and powerful laser, more complex than you could ever comprehend.");

        /**
         * Damage dealt by the weapon.
         */
        private final int damage;

        /**
         * The minimum technology level at which the weapon can be bought.
         */
        private final TechLevel minTechLevel;

        /**
         * A description of the weapon to be shown in-game.
         */
        private final String description;

        /**
         * The price of the weapon.
         */
        private final int price;

        Type(int damage, int price, TechLevel minTechLevel, String description) {
            this.damage = damage;
            this.minTechLevel = minTechLevel;
            this.description = description;
            this.price = price;
        }

        /**
         * Damage dealt by the weapon.
         * @return the damage
         */
        public int getDamage() {
            return damage;
        }

        /**
         * The minimum technology level at which the weapon can be bought.
         * @return the minTechLevel
         */
        public TechLevel getMinTechLevel() {
            return minTechLevel;
        }

        /**
         * A description of the weapon to be shown in-game.
         * @return the description
         */
        public String getDescription() {
            return description;
        }

        /**
         * The price of the weapon.
         * @return the price
         */
        public int getPrice() {
            return price;
        }
    }

    static enum Quality {
        Standard(0, 1, 1, "Standard-issue with no modifications."),
        Overclocked(1, 10, 2, "Standard with software modification for a little extra kick."),
        Kitted(2, 100, 3, "Overclocked with some extra hardware modification for even more power."),
        Perfected(4, 1000, 4, "Tuned and modified to absolute perfection.");


        private final int damage;
        private final int rarity;
        private final String description;
        private final int price;

        Quality(int damage, int rarity, int price, String description) {
            this.damage = damage;
            this.rarity = rarity;
            this.description = description;
            this.price = price;
        }

        /**
         * @return the damage
         */
        public int getDamage() {
            return damage;
        }

        /**
         * @return the rarity
         */
        public int getRarity() {
            return rarity;
        }

        /**
         * @return the description
         */
        public String getDescription() {
            return description;
        }

        /**
         * @return the price
         */
        public int getPrice() {
            return price;
        }
    }

    private Type type;
    private Quality quality;
    private String name;
    private int damage;
    private int price;
    public static final double DAMAGE_MODIFIER = 1;

    /**
     * Create a new Weapon of a given type and level of quality.
     * 
     * @param type The type of the weapon.
     * @param quality The quality of the weapon.
     */
    public Weapon(Type type, Quality quality) {
        this.type = type;
        name = quality + " " + type + " Laser";
        damage = (int) (DAMAGE_MODIFIER * (type.getDamage() + quality.getDamage()));
        price = type.getPrice() * quality.getPrice();
    }

    /**
     * Create a new Weapon of a given type.
     * 
     * @param type The type of the weapon.
     */
    public Weapon(Type type) {
        this.type = type;
        double r = Math.random();
        if (r < .02) {
            quality = Quality.Perfected;
        } else if (r < .15) {
            quality = Quality.Kitted;
        } else if (r < .35) {
            quality = Quality.Overclocked;
        } else {
            quality = Quality.Standard;
        }

        name = quality + " " + type + " Laser";
        damage = (int) (DAMAGE_MODIFIER * (type.getDamage() + quality.getDamage()));
        price = type.getPrice() * quality.getPrice();
    }

    /**
     * @return The amount of damage dealt by the weapon.
     */
    public int getDamage() {
        return damage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return type.getDescription() + "\n\n" + quality.getDescription() + "\n\nDamage: " + damage;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getSlot() {
        return "weapon";
    }

    @Override
    public TechLevel getTechLevel() {
        return type.getMinTechLevel();
    }

}
