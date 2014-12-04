/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitchhikers.spacetrader.models;

/**
 *
 * @author Alex
 */
public class Dreadnought extends Encounterable{
    public Dreadnought(String name) {
        super(5, 5, 5, 0, 0);
        this.name = name;
        
        welcomeText= "Prepare to die.";
        deathText = "Wha-what?!";
        goodComplyText = "Run while you still can.";
        badComplyText = "The bounty on your head is too great not to destroy you.";
        fleeFailedText = "You will never escape.";
        fleeSuccessfulText = "We will find you. And we will kill you.";
        winText = "Target destroyed.";
    }

    public String getName() {
        return name;
    }

    @Override
    public void equipForDifficulty(int difficulty) {
        for (int i = 0; i < difficulty; i++) {
            ship.equipWeapon(new Weapon(Weapon.Type.Alien));
            ship.equipWeapon(new Weapon(Weapon.Type.Alien));
            ship.equipShield(new Shield(Shield.Type.Alien));
        }
        if (difficulty > 2) {
            ship.equipGadget(new Gadget(Gadget.Type.PILOT_INCREASE));
            ship.equipGadget(new Gadget(Gadget.Type.ENGINEER_INCREASE));
            ship.equipGadget(new Gadget(Gadget.Type.FIGHTER_INCREASE));
        }
        if (difficulty > 3) {
            ship.equipGadget(new Gadget(Gadget.Type.REFLECTOR));
            ship.equipGadget(new Gadget(Gadget.Type.HOMING_SHOT));
            ship.equipGadget(new Gadget(Gadget.Type.FIGHTER_INCREASE));
        } 
        if (difficulty > 4) {
            ship.equipGadget(new Gadget(Gadget.Type.REFLECTOR));
            ship.equipGadget(new Gadget(Gadget.Type.HOMING_SHOT));
            ship.equipGadget(new Gadget(Gadget.Type.ENGINEER_INCREASE));
        }
    }
}
