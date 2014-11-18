/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * @author Alex
 */
public abstract class ShipUpgrade {
    /**
     * Keeps track of whether or not the upgrade is actually in use.
     */
    protected boolean active;

    /**
     * Create a new ship upgrade and deactivate it by default.
     */
    public ShipUpgrade() {
        active = false;
    }

    /**
     * Activate the upgrade in a particular ship.
     *
     * @param ship The ship to apply the upgrade to
     */
    public void activate(Ship ship) {
        active = true;
        onEquip(ship);
    }

    /**
     * Initialize the upgrade and ship so that the upgrade is properly applied.
     *
     * @param ship The ship being upgraded.
     */
    public abstract void onEquip(Ship ship);

    /**
     * Deactivate an upgrade on a ship.
     *
     * @param ship The ship which is being downgraded.
     */
    public void deactivate(Ship ship) {
        active = false;
        onUnequip(ship);
    }

    /**
     * Return the upgrade and the ship to which it was applied to their
     * state before the upgrade.
     *
     * @param ship The ship being downgraded.
     */
    public abstract void onUnequip(Ship ship);

    /**
     * @return True if the upgrade is in use on a ship, false otherwise.
     */
    public boolean isActive() {
        return active;
    }
}
