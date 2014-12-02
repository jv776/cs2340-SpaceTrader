package models;

/**
 * Interface for any upgrade that can be applied to a ship
 */
public abstract class Upgrade {
    
    protected boolean active;
    
    public Upgrade() {
        active = false;
    }

    public void activate(Ship ship) {
        active = true;
        onEquip(ship);
    }
    
    public abstract void onEquip(Ship ship);
    
    public void deactivate(Ship ship) {
        active = false;
        onUnequip(ship);
    }
    
    public abstract void onUnequip(Ship ship);
    
    public boolean isActive() {
        return active;
    }
}
