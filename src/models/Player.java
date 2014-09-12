/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 *
 * @author Alex
 */
public class Player {
    private String name;
    private int pilotSkillPoints;
    private int fighterSkillPoints;
    private int traderSkillPoints;
    private int engineerSkillPoints;
    private int investorSkillPoints;
    
    public Player (String name, int pilotSP, int fighterSP, int traderSP, int engineerSP, int investorSP) {
        this.name = name;
        pilotSkillPoints = pilotSP;
        fighterSkillPoints = fighterSP;
        traderSkillPoints = traderSP;
        engineerSkillPoints = engineerSP;
        investorSkillPoints = investorSP;
    }
    
    @Override
    public String toString() {
        return name + ": Pilot: " + pilotSkillPoints + ", Fighter: "
                + fighterSkillPoints + ", Trader: " + traderSkillPoints 
                + ", Engineer: " + engineerSkillPoints + ", Investor: " + investorSkillPoints;
    }
}
