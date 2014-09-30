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
    private final String name;
    private Ship ship;
    private SolarSystem currentSystem;
    private Planet currentPlanet;
    
    public Player (String playerName, int pilotSP, int fighterSP, int traderSP,
            int engineerSP, int investorSP) {
        super(pilotSP, fighterSP, traderSP, engineerSP, investorSP);
        name = playerName;
        ship = new Ship(ShipType.Gnat);
        currentSystem = Universe.solarSystems[(int) (Math.random()
                * Universe.solarSystems.length)];
        currentPlanet = currentSystem.planets[(int) (Math.random()
                * currentSystem.planets.length)];
        
        //test code - please remove
        System.out.println(currentPlanet.system.tech);
        for (TradeGood t : currentPlanet.market.productSupply.keySet()) {
            System.out.println(t + ":\t" + currentPlanet.market.productSupply.get(t));
        }
    }
    
    @Override
    public String toString() {
        return name + ": Pilot: " + pilotSkillPoints + ", Fighter: "
                + fighterSkillPoints + ", Trader: " + traderSkillPoints 
                + ", Engineer: " + engineerSkillPoints + ", Investor: "
                + investorSkillPoints + "\nCurrently in the "
                + currentSystem.name + " system on planet " + currentPlanet.name;
    }
    
    public Planet getCurrentPlanet() {
        return currentPlanet;
    }
}
