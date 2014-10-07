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
public abstract class CrewMember {
    protected int pilotSkillPoints;
    protected int fighterSkillPoints;
    protected int traderSkillPoints;
    protected int engineerSkillPoints;
    protected int investorSkillPoints;

    public CrewMember(int pilotSP, int fighterSP, int traderSP, int engineerSP,
            int investorSP) {
        pilotSkillPoints = pilotSP;
        fighterSkillPoints = fighterSP;
        traderSkillPoints = traderSP;
        engineerSkillPoints = engineerSP;
        investorSkillPoints = investorSP;
    }

    public int getPilotSkillPoints() {
        return pilotSkillPoints;
    }

    public void setPilotSkillPoints(int pilotSkillPoints) {
        this.pilotSkillPoints = pilotSkillPoints;
    }

    public int getFighterSkillPoints() {
        return fighterSkillPoints;
    }

    public void setFighterSkillPoints(int fighterSkillPoints) {
        this.fighterSkillPoints = fighterSkillPoints;
    }

    public int getTraderSkillPoints() {
        return traderSkillPoints;
    }

    public void setTraderSkillPoints(int traderSkillPoints) {
        this.traderSkillPoints = traderSkillPoints;
    }

    public int getEngineerSkillPoints() {
        return engineerSkillPoints;
    }

    public void setEngineerSkillPoints(int engineerSkillPoints) {
        this.engineerSkillPoints = engineerSkillPoints;
    }

    public int getInvestorSkillPoints() {
        return investorSkillPoints;
    }

    public void setInvestorSkillPoints(int investorSkillPoints) {
        this.investorSkillPoints = investorSkillPoints;
    }
}
