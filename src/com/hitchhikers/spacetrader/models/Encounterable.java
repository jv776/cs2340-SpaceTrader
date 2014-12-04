package com.hitchhikers.spacetrader.models;

/**
 * Abstract class representing an entity that can be encountered during travel.
 *
 * @author Taylor
 */
public abstract class Encounterable extends CrewMember {
    /**
     * The name of the person encountered.
     */
    protected String name;

    /**
     * The text displayed at the beginning of an encounter.
     */
    protected String welcomeText;

    /**
     * The text displayed when the person encountered is killed.
     */
    protected String deathText;
    
    /**
     * The text displayed when a player complies with the entity's wishes and
     * the entity responds positively.
     */
    protected String goodComplyText;
    
    /**
     * The text displayed when a player complies with the entity's wishes and
     * the entity responds negatively.
     */
    protected String badComplyText;

    /**
     * The text displayed when the player unsuccessfully flees an encounter.
     */
    protected String fleeFailedText;

    /**
     * The text displayed when the player successfully flees an encounter.
     */
    protected String fleeSuccessfulText;

    /**
     * The text displayed when the player wins an encounter.
     */
    protected String winText;

    /**
     * The ship owned by the person encountered.
     */
    protected Ship ship;

    /**
     * The bounty on the person encountered.
     */
    protected int bounty;

    /**
     * Creates a new person that can be encountered when traveling through a
     * solar system.
     */
    public Encounterable() {
        super(0, 0, 0, 0, 0);
        ship = new Ship(Ship.Type.Gnat, this);
    }
    
    /**
     * Creates a new person that can be encountered while traveling through
     * a solar system with the specified skill point distribution.
     * @param pilotSP Pilot skill points of the Encounterable.
     * @param fighterSP Fighter skill points of the Encounterable.
     * @param traderSP Trader skill points of the Encounterable.
     * @param engineerSP Engineer skill points of the Encounterable.
     * @param investorSP Investor skill points of the Encounterable.
     */
    public Encounterable(final int pilotSP, final int fighterSP, final int traderSP, 
            final int engineerSP, final int investorSP) {
        super(pilotSP, fighterSP, traderSP, engineerSP, investorSP);
        ship = new Ship(Ship.Type.Firefly, this);
    }

    /**
     * @return The name of the person encountered.
     */
    public abstract String getName();

    /**
     * Computes the attack power of the Encounterable based on their fighting
     * skill.
     *
     * @return The Encounterable's attack power.
     */
    public int calculateAttack() {
        return ship.calculateAttack() * (5 + (fighterSkill / 2));
    }

    /**
     * @return True, if the Encounterable's ship is destroyed, otherwise false.
     */
    public boolean isDead() {
        return ship.isDead();
    }

    /**
     * Deals a given amount of damage to the Encounterable's ship.
     *
     * @param damage How much damage to inflict.
     */
    public void takeDamage(int damage) {
        ship.takeDamage(damage);
    }

    /**
     * @return The strength of the hull of the Encounterable's ship.
     */
    public int getHullStrength() {
        return ship.getHullStrength();
    }

    /**
     * @return The maximum possible strength of the hull of the Encounterable's
     * ship.
     */
    public int getMaxHullStrength() {
        return ship.getMaxHullStrength();
    }
    
    /**
     * @return The maximum of shields this Encounterable can have.
     */
    public int getMaxShields() {
        return ship.getMaxShields();
    }
    
    /**
     * @return The current amount of shields this Encounterable has.
     */
    public int getCurrentShields() {
        return ship.getCurrentShields();
    }

    /**
     * @return The text displayed upon when an Encounterable is met.
     */
    public String getWelcomeText() {
        return welcomeText;
    }

    /**
     * @return The text displayed after a successful event involving an
     * Encounterable.
     */
    public String getWinText() {
        return winText;
    }

    /**
     * @return The text displayed when the Encounterable is killed in combat.
     */
    public String getDeathText() {
        return deathText;
    }
    
    /**
     * Returns the text an entity says when you comply.
     * @return the comply text
     */
    public String getGoodComplyText() {
        return goodComplyText;
    }
    
    public String getBadComplyText() {
        return badComplyText;
    }

    /**
     * @return The value of the bounty upon an Encounterable.
     */
    public int getBounty() {
        return bounty;
    }
    
    /**
     * @return The ship that the Encounterable is using.
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * @return The text displayed when the player successfully flees an
     * Encounterable.
     */
    public String getFleeSuccessfulText() {
        return fleeSuccessfulText;
    }

    /**
     * @return The text displayed when the player unsuccessfully attempts
     * to flee from an Encounterable.
     */
    public String getFleeFailedText() {
        return fleeFailedText;
    }
    
    public abstract void equipForDifficulty(int difficulty);

}
