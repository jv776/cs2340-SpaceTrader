/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hitchhikers.spacetrader.models;

import java.io.Serializable;

/**
 * Abstract class to store information and methods common to all characters
 * capable of running a ship in some capacity (players, mercenaries, etc.).
 *
 * @author John
 */
public abstract class CrewMember implements Serializable {
    /**
     * The CrewMember's skill as a pilot.
     */
    protected int pilotSkill;

    /**
     * The CrewMember's skill as a fighter.
     */
    protected int fighterSkill;

    /**
     * The CrewMember's skill as a trader.
     */
    protected int traderSkill;

    /**
     * The CrewMember's skill as an engineer.
     */
    protected int engineerSkill;

    /**
     * The CrewMember's skill as an investor.
     */
    protected int investorSkill;

    /**
     * Create a new CrewMember object with given skills.
     *
     * @param pilotSP The CrewMember's skill as a pilot.
     * @param fighterSP The CrewMember's skill as a fighter.
     * @param traderSP The CrewMember's skill as a trader.
     * @param engineerSP The CrewMember's skill as an engineer.
     * @param investorSP The CrewMember's skill as an investor.
     */
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
