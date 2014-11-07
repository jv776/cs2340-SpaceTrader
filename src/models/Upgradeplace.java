package models;

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
                    .filter((Weapon.Type type) -> location.getTechLevel().ordinal() == type.minTechLevel.ordinal()).findAny().get()));

        } else {
            return null;
        }
    }

    private Shield generateShield() {
        Random rand = new Random();
        if (location.getTechLevel().ordinal() >= TechLevel.EARLY_INDUSTRIAL.ordinal()) {
            return (new Shield(Arrays.stream(Shield.Type.values())
                    .filter((Shield.Type type) -> (location.getTechLevel().ordinal() == type.minTechLevel.ordinal())).findAny().get()));

        } else {
            return null;
        }
    }

    private Gadget generateGadget() {
        if (location.getTechLevel().ordinal() >= TechLevel.EARLY_INDUSTRIAL.ordinal()) {
            return (new Gadget(Arrays.stream(Gadget.Type.values())
                    .filter((Gadget.Type type) -> location.getTechLevel().ordinal() == type.minTechLevel.ordinal()).findAny().get()));

        } else {
            return null;
        }
    }

    public Gadget getGadgetUpgrade() {
        return gadgetUpgrade;
    }

    public Weapon getWeaponUpgrade() {
        return weaponUpgrade;
    }

    public Shield getShieldUpgrade() {
        return shieldUpgrade;
    }


}
