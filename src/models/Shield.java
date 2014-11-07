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
public class Shield extends ShipUpgrade {
    public enum ShieldType {
        ENERGY_SHIELD(50, TechLevel.INDUSTRIAL, 10000),
        BLAST_SHIELD(100, TechLevel.POST_INDUSTRIAL, 25000),
        REFLECTIVE_SHIELD(200, TechLevel.HI_TECH, 45000),
        IMPERVIOUS_SHIELD(10000, TechLevel.HI_TECH, 60000);

        public final int protection;
        public final TechLevel minTechLevel;
        public final int price;

        ShieldType(int protection, TechLevel minTechLevel, int price) {
            this.protection = protection;
            this.minTechLevel = minTechLevel;
            this.price = price;
        } 
        
        @Override
        public String toString() {
            switch (this.ordinal()) {
                case 0: return "Energy Shield";
                case 1: return "Blast Shield";
                case 2: return "Reflective Shield";
                default: return "Impervious Shield";
            }
        }
    }
    
    private ShieldType type;
    
    public Shield(ShieldType type) {
        this.type = type;
    }
        
    public int getProtection() {
        return type.protection;
    }
    
    public TechLevel getMinTechLevel() {
        return type.minTechLevel;
    }
    
    public int getPrice() {
        return type.price;
    }
    
    public ShieldType getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return type.toString();
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
