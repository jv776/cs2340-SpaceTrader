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
public class Player extends CrewMember {
    private String name;
    private int money;
    
    public Player (String name, int pilotSP, int fighterSP, int traderSP, int engineerSP, int investorSP) {
        super(pilotSP, fighterSP, traderSP, engineerSP, investorSP);
        this.name = name;
        money = 1930823048;
    }
    
    @Override
    public String toString() {
        return name + ": Pilot: " + pilotSkillPoints + ", Fighter: "
                + fighterSkillPoints + ", Trader: " + traderSkillPoints 
                + ", Engineer: " + engineerSkillPoints + ", Investor: " + investorSkillPoints;
    }
    
    public void spend(int amount) {
        money -= amount;
    }
    
    public void earn(int amount) {
        money += amount;
    }
    
    public int getMoney() {
        return money;
    }
}
