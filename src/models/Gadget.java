package models;

import java.io.Serializable;

/**
 * Model of a misc upgrade that can be applied to a ship.
 *
 * @author Taylor
 */
public class Gadget implements Upgrade, Serializable {
    /**
     * The type of the gadget. Affects how each individual gadget will upgrade
     * a ship.
     */
    public static enum Type {
        /**
         * Adds extra cargo space.
         */
        CARGO(5, 0, 0, 0, false, 200, TechLevel.EARLY_INDUSTRIAL, "Provides 5 extra cargo slots for your ship."),

        /**
         * Improves navigation abilities.
         */
        NAVIGATION(0, 5, 0, 0, false, 1000, TechLevel.INDUSTRIAL, "Increases your piloting skill."),

        /**
         * Improves fighting ability.
         */
        TARGETING(0, 0, 5, 0, false, 1000, TechLevel.INDUSTRIAL, "Increases your combat skill."),

        /**
         * Automatically assists in repairing the ship.
         */
        AUTOREPAIR(0, 0, 0, 5, false, 1000, TechLevel.POST_INDUSTRIAL, "Increases your engineering skill."),

        /**
         * Hides your ship from most others.
         */
        CLOAKING(0, 0, 0, 0, true, 10000, TechLevel.HI_TECH, "Allows you to travel undetected through space.");

        private final TechLevel minTechLevel;
        private final int price;
        private final String description;
        private final int cargoSlotModifier;
        private final int pilotSkillModifier;
        private final int combatSkillModifier;
        private final int engineeringSkillModifier;
        private final boolean cloakingAbility;

        Type(int cargo, int pilot, int combat, int engineering, boolean cloak, int price, TechLevel minTechLevel, String description) {
            this.cargoSlotModifier = cargo;
            this.cloakingAbility = cloak;
            this.pilotSkillModifier = pilot;
            this.combatSkillModifier = combat;
            this.engineeringSkillModifier = engineering;
            this.minTechLevel = minTechLevel;
            this.price = price;
            this.description = description;
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

        /**
         * @return the cargoSlotModifier
         */
        public int getCargoSlotModifier() {
            return cargoSlotModifier;
        }

        /**
         * @return the pilotSkillModifier
         */
        public int getPilotSkillModifier() {
            return pilotSkillModifier;
        }

        /**
         * @return the combatSkillModifier
         */
        public int getCombatSkillModifier() {
            return combatSkillModifier;
        }

        /**
         * @return the engineeringSkillModifier
         */
        public int getEngineeringSkillModifier() {
            return engineeringSkillModifier;
        }

        /**
         * @return the cloakingAbility
         */
        public boolean isCloakingAbility() {
            return cloakingAbility;
        }
    }

    private final Type type;
    private final int price;
    private final String name;

    /**
     * Create a new Gadget of a given type.
     *
     * @param type The type of Gadget to create.
     */
    public Gadget(Type type) {
        this.type = type;
        this.price = type.getPrice();
        name = type + " Upgrade";
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getSlot() {
        return "gadget";
    }

    @Override
    public TechLevel getTechLevel() {
        return type.getMinTechLevel();
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * @return A description of the type of the gadget.
     */
    @Override
    public String toString() {
        return type.getDescription();
    }
}
