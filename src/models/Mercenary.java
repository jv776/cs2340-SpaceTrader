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
public class Mercenary extends CrewMember {

    private String name;
    private String info;
    
    public Mercenary(String name, String skill, int power) {
        super(0, 0, 0, 0, 0);
        this.name = name;
        this.info = name + ": +" + power + " " + skill;
        switch (skill) {
            case "Pilot": setPilotSkillPoints(power); break;
            case "Fighter": setFighterSkillPoints(power); break;
            case "Engineer": setEngineerSkillPoints(power); break;
            case "Trader": setTraderSkillPoints(power); break;
            case "Investor": setInvestorSkillPoints(power); break;
        }
    }
    
    public String getInfo() {
        return info;
    }
}
