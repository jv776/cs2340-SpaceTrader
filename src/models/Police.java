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
}
