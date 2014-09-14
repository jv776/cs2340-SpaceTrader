/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 * Abstract class to store information and methods common to all characters
 * capable of running a ship in some capacity (players, mercenaries, etc.).
 * 
 * @author John
 */
abstract class CrewMember {
    protected int pilotSkillPoints;
    protected int fighterSkillPoints;
    protected int traderSkillPoints;
    protected int engineerSkillPoints;
    protected int investorSkillPoints;
    
    public CrewMember(int pilotSP, int fighterSP, int traderSP,
            int engineerSP, int investorSP) {
        pilotSkillPoints = pilotSP;
        fighterSkillPoints = fighterSP;
        traderSkillPoints = traderSP;
        engineerSkillPoints = engineerSP;
        investorSkillPoints = investorSP;
    }
    
    //methods common to players, mercenaries, pirates, etc. can go here
}
