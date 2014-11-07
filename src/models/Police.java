package models;

/**
 * Class representing an interstellar cop.
 *
 * @author Taylor
 */
public class Police extends Encounterable {


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

    public boolean isDead() {
        return ship.isDead();
    }

    public void takeDamage(int damage) {
        ship.takeDamage(damage);
    }

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
