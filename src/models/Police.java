package models;

/**
 * Class representing an interstellar cop.
 *
 * @author Taylor
 */
public class Police extends Encounterable {

    /**
     * Create a new police officer.
     *
     * @param name The officer's name
     */
    public Police(String name) {
        this.name = name;
        ship = new Ship(Ship.Type.Gnat, this);
        welcomeText = "Suspect, prepare to receive civil judgement.";
        deathText = "Shazbot!";
        goodComplyText = "You are free to go.";
        badComplyText = "You have commited crimes against the Great Galactic Federation."
                + " Prepare to be punished.";
        fleeFailedText = "You will never escape from justice.";
        fleeSuccessfulText = "They got away!";
        winText = "Criminal disposed of, resuming patrol";
    }

    public String getName() {
        return name;
    }

    /**
     * Returns whether the police has been defeated
     *
     * @return whether the police has been defeated
     */
    public boolean isDead() {
        return ship.isDead();
    }

    /**
     * Deals damage to the Police
     *
     * @param damage How much damage to inflict.
     */
    public void takeDamage(int damage) {
        ship.takeDamage(damage);
    }

    /**
     * Returns the attack of the Police
     *
     * @return attack of the Police
     */
    public int calculateAttack() {
        return ship.calculateAttack();
    }

    public int getHullStrength() {
        return ship.getHullStrength();
    }

    public int getMaxHullStrength() {
        return ship.getMaxHullStrength();
    }

    @Override
    public void equipForDifficulty(int difficulty) {
        for (int i = 0; i < difficulty; i++) {
            ship.equipWeapon(new Weapon(Weapon.Type.Military));
            ship.equipShield(new Shield(Shield.Type.Overcharged));
        }
        if (difficulty > 2) {
            ship.equipGadget(new Gadget(Gadget.Type.PILOT_INCREASE));
            ship.equipGadget(new Gadget(Gadget.Type.ENGINEER_INCREASE));
            ship.equipGadget(new Gadget(Gadget.Type.FIGHTER_INCREASE));
        }
        if (difficulty > 4) {
            ship.equipGadget(new Gadget(Gadget.Type.REFLECTOR));
        }
    }
}
