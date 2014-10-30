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
    transient protected int pilotSkill;
    transient protected int fighterSkill;
    transient protected int traderSkill;
    transient protected int engineerSkill;
    transient protected int investorSkill;

    public CrewMember(final int pilotSP, final int fighterSP, final int traderSP, 
            final int engineerSP, final int investorSP) {
        pilotSkill = pilotSP;
        fighterSkill = fighterSP;
        traderSkill = traderSP;
        engineerSkill = engineerSP;
        investorSkill = investorSP;
    }

    /**
     * @return The number of skill points in the pilot category
     */
    public int getPilotSkillPoints() {
        return pilotSkill;
    }

    /**
     * Set the number of skill points for the pilot category.
     * 
     * @param pilotSkill The new number of skill points in piloting
     */
    public void setPilotSkillPoints(final int pilotSkill) {
        this.pilotSkill = pilotSkill;
    }

    /**
     * @return The number of skill points in the fighter category
     */
    public int getFighterSkillPoints() {
        return fighterSkill;
    }

    /**
     * Set the number of skill points in the fighter category.
     * 
     * @param fighterSkill The new number of fighting skill points
     */
    public void setFighterSkillPoints(final int fighterSkill) {
        this.fighterSkill = fighterSkill;
    }

    /**
     * @return The number of skill points in the trader category
     */
    public int getTraderSkillPoints() {
        return traderSkill;
    }

    /**
     * Set the number of skill points in the trader category.
     * 
     * @param traderSkill The new number of fighting skill points
     */
    public void setTraderSkillPoints(final int traderSkill) {
        this.traderSkill = traderSkill;
    }

    /**
     * @return The number of skill points in the engineer category
     */
    public int getEngineerSkillPoints() {
        return engineerSkill;
    }

    /**
     * Set the number of skill points in the engineer category.
     * 
     * @param engineerSkill The new number of engineering skill points
     */
    public void setEngineerSkillPoints(final int engineerSkill) {
        this.engineerSkill = engineerSkill;
    }

    /**
     * @return The number of skill points in the investor category
     */
    public int getInvestorSkillPoints() {
        return investorSkill;
    }

    /**
     * Set the number of skill points in the investor category.
     * 
     * @param investorSkill The new number of fighting skill points
     */
    public void setInvestorSkillPoints(final int investorSkill) {
        this.investorSkill = investorSkill;
    }
}
