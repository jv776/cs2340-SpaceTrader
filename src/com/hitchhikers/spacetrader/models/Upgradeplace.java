package com.hitchhikers.spacetrader.models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

/**
 * The upgrade marketplace, specific to each planet.
 *
 * @author Taylor
 */
public class Upgradeplace implements Serializable {
    private final SolarSystem location;
    private final Weapon weaponUpgrade;
    private final Shield shieldUpgrade;
    private final Gadget gadgetUpgrade;

    /**
     * Create a new location where upgrades can be bought.
     * 
     * @param marketLocation The market where upgrades are purchased.
     */
    public Upgradeplace(SolarSystem marketLocation) {
        location = marketLocation;
        weaponUpgrade = generateWeapon();
        shieldUpgrade = generateShield();
        gadgetUpgrade = generateGadget();

    }

    private Weapon generateWeapon() {
        Random rand = new Random();
        if (location.getTechLevel().ordinal() >= TechLevel.EARLY_INDUSTRIAL.ordinal()) {
            return (new Weapon(Arrays.stream(Weapon.Type.values())
                    .filter((Weapon.Type type) -> location.getTechLevel().ordinal() == type.getMinTechLevel().ordinal()).findAny().get()));

        } else {
            return null;
        }
    }

    private Shield generateShield() {
        Random rand = new Random();
        if (location.getTechLevel().ordinal() >= TechLevel.EARLY_INDUSTRIAL.ordinal()) {
            return (new Shield(Arrays.stream(Shield.Type.values())
                    .filter((Shield.Type type) -> (location.getTechLevel().ordinal() == type.getMinTechLevel().ordinal())).findAny().get()));

        } else {
            return null;
        }
    }

    private Gadget generateGadget() {
        if (location.getTechLevel().ordinal() >= TechLevel.EARLY_INDUSTRIAL.ordinal()) {
            return (new Gadget(Arrays.stream(Gadget.Type.values())
                    .filter((Gadget.Type type) -> location.getTechLevel().ordinal() == type.getMinTechLevel().ordinal()).findAny().get()));

        } else {
            return null;
        }
    }

    /**
     * @return The gadget available for purchase.
     */
    public Gadget getGadgetUpgrade() {
        return gadgetUpgrade;
    }

    /**
     * @return The weapon upgrade available for purchase.
     */
    public Weapon getWeaponUpgrade() {
        return weaponUpgrade;
    }

    /**
     * @return The shield upgrade available for purchase.
     */
    public Shield getShieldUpgrade() {
        return shieldUpgrade;
    }


}
