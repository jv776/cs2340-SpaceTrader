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
public class Weapon extends ShipUpgrade {

    public enum WeaponType {
        PULSE_LASER(5, TechLevel.INDUSTRIAL, 8000),
        BEAM_LASER(8, TechLevel.POST_INDUSTRIAL, 20000),
        MILITARY_LASER(12, TechLevel.HI_TECH, 40000),
        DEATH_LASER(150, TechLevel.HI_TECH, 60000);
        
        public final int power;
        public final TechLevel minTechLevel;
        public final int price;
        
        WeaponType(int power, TechLevel minTechLevel, int price) {
            this.power = power;
            this.minTechLevel = minTechLevel;
            this.price = price;
        }
        
        @Override
        public String toString() {
            switch (this.ordinal()) {
                case 0: return "Pulse Laser";
                case 1: return "Beam Laser";
                case 2: return "Military Laser";
                default: return "Death Laser";
            }
        }
    }
        
    private WeaponType type;
    
    public Weapon(WeaponType type) {
        this.type = type;
    }
    
    public int getPower() {
        return type.power;
    }
    
    public WeaponType getType() {
        return type;
    }
    
    public TechLevel getMinTechLevel() {
        return type.minTechLevel;
    }
    
    public int getPrice() {
        return type.price;
    }
    
    @Override
    public String toString() {
        return type.toString();
    }
    
    @Override
    public void onEquip(Ship ship) {
        ship.equipWeapon(this);
    }

    @Override
    public void onUnequip(Ship ship) {
        ship.unequipWeapon(this);
    }
}
