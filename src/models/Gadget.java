/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Alex
 */
public class Gadget extends ShipUpgrade {
    public enum GadgetType {
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
                    ship.getOwner().increasePilot(5);
                }

                @Override
                public void onUnequip(Ship ship) {
                   ship.getOwner().decreasePilot(5);
                }
        }),
        
        ENGINEER_INCREASE("Auto-Repair System", TechLevel.INDUSTRIAL, 17000,
            "Increases the player's engineer skill by 5.", 
            new Effect() {
                @Override
                public void onEquip(Ship ship) {
                    ship.getOwner().increaseEngineer(5);
                }

                @Override
                public void onUnequip(Ship ship) {
                    ship.getOwner().decreaseEngineer(5);
                }
        }),
        
        FIGHTER_INCREASE("Targeting System", TechLevel.POST_INDUSTRIAL, 20000,
            "Increases the player's fighter skill by 5.", 
            new Effect() {
                @Override
                public void onEquip(Ship ship) {
                    ship.getOwner().increaseFighter(5);
                }

                @Override
                public void onUnequip(Ship ship) {
                    ship.getOwner().decreaseFighter(5);
                }
        }),
        
        CLOAKING_DEVICE("Cloaking Device", TechLevel.HI_TECH, 70000,
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
        
        public final String name;
        public final TechLevel minTechLevel;
        public final int price;
        public final String info;
        private Effect effect;
        
        GadgetType(String name, TechLevel minTechLevel, int price, String info, Effect effect) {
            this.name = name;
            this.minTechLevel = minTechLevel;
            this.price = price;
            this.info = info;
            this.effect = effect;
        }
        
    }

    private GadgetType type;
    
    public Gadget(GadgetType type) {
        this.type = type;
    }
    
    public String toString() {
        return type.name;
    }
    
    public TechLevel getMinTechLevel() {
        return type.minTechLevel;
    }
    
    public int getPrice() {
        return type.price;
    }
    
    public String getInfo() {
        return type.info;
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
