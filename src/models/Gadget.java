package models;

import java.io.Serializable;

/**
 * Model of a misc upgrade that can be applied to a ship.
 *
 * @author Taylor
 */
<<<<<<< HEAD
public class Gadget extends Upgrade implements Serializable {
    public static enum Type {
         CARGO_INCREASE("Extra Cargo Bays", TechLevel.EARLY_INDUSTRIAL, 6000,
            "Increases the maximum amount of cargo you can carry by 5.",
            new Effect() {
                @Override
                public void onEquip(Ship ship) {
                    ship.getCargoHold().increaseCapacity(5);
                }

                @Override
                public void onUnequip(Ship ship) {
                    ship.getCargoHold().decreaseCapacity(5);
                }
        }),

        PILOT_INCREASE("Navigation System", TechLevel.INDUSTRIAL, 15000,
            "Increases the player's pilot skill by 5.",
            new Effect() {
                @Override
                public void onEquip(Ship ship) {
                    ship.getOwner().setPilotSkillPoints(ship.getOwner().getPilotSkillPoints() + 5);
                }

                @Override
                public void onUnequip(Ship ship) {
                   ship.getOwner().setPilotSkillPoints(ship.getOwner().getPilotSkillPoints() - 5);
                }
        }),

        ENGINEER_INCREASE("Auto-Repair System", TechLevel.INDUSTRIAL, 17000,
            "Increases the player's engineer skill by 5.",
            new Effect() {
                @Override
                public void onEquip(Ship ship) {
                    ship.getOwner().setEngineerSkillPoints(ship.getOwner().getEngineerSkillPoints() + 5);
                }

                @Override
                public void onUnequip(Ship ship) {
                    ship.getOwner().setEngineerSkillPoints(ship.getOwner().getEngineerSkillPoints() - 5);
                }
        }),

        FIGHTER_INCREASE("Targeting System", TechLevel.POST_INDUSTRIAL, 20000,
            "Increases the player's fighter skill by 5.",
            new Effect() {
                @Override
                public void onEquip(Ship ship) {
                    ship.getOwner().setFighterSkillPoints(ship.getOwner().getFighterSkillPoints() + 5);

                }

                @Override
                public void onUnequip(Ship ship) {
                    ship.getOwner().setFighterSkillPoints(ship.getOwner().getFighterSkillPoints() - 5);
                }
        }),

        CLOAKING_DEVICE("Cloaking Device", TechLevel.HI_TECH, 30000,
            "Allows player to travel unseen by some ships.",
            new Effect() {
                @Override
                public void onEquip(Ship ship) {
                    ship.activateCloaking();
                }

                @Override
                public void onUnequip(Ship ship) {
                    ship.deactivateCloaking();
                }
        }),

        SCATTER_SHOT("Scatter Shot", TechLevel.HI_TECH, 100000,
            "Adds a scatter effect to all laser shots.",
            new Effect() {
                @Override
                public void onEquip(Ship ship) {
                    ship.addScatter();
                }

                @Override
                public void onUnequip(Ship ship) {
                    ship.removeScatter();
                }
        }),

        PIERCING_SHOT("Piercing Shot", TechLevel.HI_TECH, 100000,
            "Lasers travel through enemies, and are not consumed on impact.",
            new Effect() {
                @Override
                public void onEquip(Ship ship) {
                    ship.activatePiercing();
                }

                @Override
                public void onUnequip(Ship ship) {
                    ship.deactivatePiercing();
                }
        }),

        HOMING_SHOT("Homing Shot", TechLevel.HI_TECH, 200000,
            "Adds a homing effect to all laser shots. Target nearest enemy.",
            new Effect() {
                @Override
                public void onEquip(Ship ship) {
                    ship.addHoming();
                }

                @Override
                public void onUnequip(Ship ship) {
                    ship.removeHoming();
                }
        }),

        REFLECTOR("Reflector", TechLevel.HI_TECH, 200000,
            "Adds a 20% chance to reflect enemy fire.",
            new Effect() {
                @Override
                public void onEquip(Ship ship) {
                    ship.addReflector();
                }

                @Override
                public void onUnequip(Ship ship) {
                    ship.removeReflector();
                }
        });

        private final String name;
        private final TechLevel minTechLevel;
        private final int price;
        private Effect effect;
        private final String description;

        Type(String name, TechLevel minTechLevel, int price, String info, Effect effect) {
            this.name = name;
            this.minTechLevel = minTechLevel;
            this.price = price;
            this.description = info;
            this.effect = effect;
        }

        public String getName() {
            return name;
        }

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

    private final Type type;
    private final int price;
    private final String name;

    public Gadget(Type type) {
        this.type = type;
        this.price = type.getPrice();
        name = type.name;
    }

    public int getPrice() {
        return price;
    }

    public TechLevel getTechLevel() {
        return type.getMinTechLevel();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return type.getDescription();
    }

    @Override
    public void onEquip(Ship ship) {
        type.effect.onEquip(ship);
    }

    @Override
    public void onUnequip(Ship ship) {
        type.effect.onUnequip(ship);
    }

    static abstract class Effect {
        public abstract void onEquip(Ship ship);
        public abstract void onUnequip(Ship ship);
    }
}
