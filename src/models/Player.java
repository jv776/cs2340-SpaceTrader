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
        ship = new Ship(Ship.Type.Gnat, this);
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
    
    public Ship getShip() {
        return ship;
    }
    
    public Planet getCurrentPlanet() {
        return currentPlanet;
    }
    
    public int getCredits() {
        return credits;
    }
    
    public String shipType() {
        return ship.getType().toString().toLowerCase();
    }
    
    public void spend(int amount) {
        credits -= amount;
    }
    
    public void earn(int amount) {
        credits += amount;
    }
}
