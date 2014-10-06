/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 * Class representing the player's character.
 * 
 * @author Alex, John
 */
public class Player extends CrewMember {
    public final String name;
    private Ship ship;
    //private SolarSystem currentSystem;
    private Planet currentPlanet;
    private int credits;
    
    public Player (String playerName, int pilotSP, int fighterSP, int traderSP,
            int engineerSP, int investorSP) {
        super(pilotSP, fighterSP, traderSP, engineerSP, investorSP);
        name = playerName;
        
        //starting goods/equipment, can be changed
        ship = new Ship(ShipType.Gnat);
        credits = 10000;
        
        
        SolarSystem currentSystem = Universe.solarSystems[(int) (Math.random()
                * Universe.solarSystems.length)];
        currentPlanet = currentSystem.planets[(int) (Math.random()
                * currentSystem.planets.length)];
    }
    
    @Override
    public String toString() {
        return name + ": Pilot: " + pilotSkillPoints + ", Fighter: "
                + fighterSkillPoints + ", Trader: " + traderSkillPoints 
                + ", Engineer: " + engineerSkillPoints + ", Investor: "
                + investorSkillPoints + "\nCurrently in the "
                + currentPlanet.solarSystem.name + " system on planet "
                + currentPlanet.name;
    }
    
    /**
     * @return The player's ship
     */
    public Ship getShip() {
        return ship;
    }
    
    /**
     * @return The planet on which the player is currently located
     */
    public Planet getCurrentPlanet() {
        return currentPlanet;
    }
    
    /**
     * @return The number of credits currently possessed by the player
     */
    public int getCredits() {
        return credits;
    }
    
    /**
     * Set the location of the player to a different planet.
     * 
     * @param planet The player's new location
     */
    public void setCurrentPlanet(Planet planet) {
        currentPlanet = planet;
    }
    
    /**
     * @return A string representation of the type of ship owned by the player
     */
    public String shipType() {
        return ship.type.toString().toLowerCase();
    }
    
    /**
     * Spend a certain amount of money.
     * 
     * @param amount How much money to spend
     */
    public void spend(int amount) {
        credits -= amount;
    }
    
    /**
     * Earn money.
     * 
     * @param amount The amount the player has earned
     */
    public void earn(int amount) {
        credits += amount;
    }
}
