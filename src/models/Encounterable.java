package models;

/**
 * Abstract class representing an entity that can be encountered during travel
 *
 * @author Taylor
 */
public abstract class Encounterable extends CrewMember {

    protected String name;
    protected String welcomeText;
    protected String deathText;

    protected String fleeFailedText;

    protected String fleeSuccessfulText;

    protected String winText;

    protected Ship ship;

    protected int bounty;

    public Encounterable() {
        super(0, 0, 0, 0, 0);
        ship = new Ship(Ship.Type.Gnat, this);
    }

    public abstract String getName();

    public int calculateAttack() {
        return ship.calculateAttack() * (5 + (fighterSkill / 2));
    }

    public boolean isDead() {
        return ship.isDead();
    }

    public void takeDamage(int damage) {
        ship.takeDamage(damage);
    }

    public int getHullStrength() {
        return ship.getHullStrength();
    }


    public int getMaxHullStrength() {
        return ship.getMaxHullStrength();
    }

    public String getWelcomeText() {
        return welcomeText;
    }

    public String getWinText() {
        return winText;
    }

    public String getDeathText() {
        return deathText;
    }

    public int getBounty() {
        return bounty;
    }

    public String getFleeSuccessfulText() {
        return fleeSuccessfulText;
    }

    public String getFleeFailedText() {
        return fleeFailedText;
    }

}
