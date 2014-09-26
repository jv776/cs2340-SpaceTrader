/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 * @author Alex
 */
public class Player extends CrewMember {
    private String name;

    public Player(String name, int pilotSP, int fighterSP, int traderSP, int engineerSP, int investorSP) {
        super(pilotSP, fighterSP, traderSP, engineerSP, investorSP);
        this.name = name;
    }

    @Override
    public String toString() {
        return name + ": Pilot: " + pilotSkillPoints + ", Fighter: "
                + fighterSkillPoints + ", Trader: " + traderSkillPoints
                + ", Engineer: " + engineerSkillPoints + ", Investor: " + investorSkillPoints;
    }
}
