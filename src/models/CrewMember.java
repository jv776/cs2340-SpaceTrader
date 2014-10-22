/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.io.Serializable;

/**
 * Abstract class to store information and methods common to all characters
 * capable of running a ship in some capacity (players, mercenaries, etc.).
 *
 * @author John
 */
public abstract class CrewMember implements Serializable {
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

    /**
     * @return The number of skill points in the pilot category
     */
    public int getPilotSkillPoints() {
        return pilotSkillPoints;
    }

    /**
     * Set the number of skill points for the pilot category.
     * 
     * @param pilotSkillPoints The new number of skill points in piloting
     */
    public void setPilotSkillPoints(int pilotSkillPoints) {
        this.pilotSkillPoints = pilotSkillPoints;
    }

    /**
     * @return The number of skill points in the fighter category
     */
    public int getFighterSkillPoints() {
        return fighterSkillPoints;
    }

    /**
     * Set the number of skill points in the fighter category.
     * 
     * @param fighterSkillPoints The new number of fighting skill points
     */
    public void setFighterSkillPoints(int fighterSkillPoints) {
        this.fighterSkillPoints = fighterSkillPoints;
    }

    /**
     * @return The number of skill points in the trader category
     */
    public int getTraderSkillPoints() {
        return traderSkillPoints;
    }

    /**
     * Set the number of skill points in the trader category.
     * 
     * @param traderSkillPoints The new number of fighting skill points
     */
    public void setTraderSkillPoints(int traderSkillPoints) {
        this.traderSkillPoints = traderSkillPoints;
    }

    /**
     * @return The number of skill points in the engineer category
     */
    public int getEngineerSkillPoints() {
        return engineerSkillPoints;
    }

    /**
     * Set the number of skill points in the engineer category.
     * 
     * @param engineerSkillPoints The new number of engineering skill points
     */
    public void setEngineerSkillPoints(int engineerSkillPoints) {
        this.engineerSkillPoints = engineerSkillPoints;
    }

    /**
     * @return The number of skill points in the investor category
     */
    public int getInvestorSkillPoints() {
        return investorSkillPoints;
    }

    /**
     * Set the number of skill points in the investor category.
     * 
     * @param investorSkillPoints The new number of fighting skill points
     */
    public void setInvestorSkillPoints(int investorSkillPoints) {
        this.investorSkillPoints = investorSkillPoints;
    }
}
