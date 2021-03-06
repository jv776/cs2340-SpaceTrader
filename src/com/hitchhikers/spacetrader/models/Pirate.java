package com.hitchhikers.spacetrader.models;

/**
 * Class representing a space-pirate.
 *
 * @author Taylor
 */
public class Pirate extends Encounterable {

    /**
     * Create a new Pirate.
     *
     * @param name The pirate's name.
     */
    public Pirate(String name) {
//        super(0,0,0,0,0);
        this.name = name;
        welcomeText = "Yarr, surrender yer Credits or we'll take 'em by force!";
        deathText = "Arrr! You win this time, but I'll be back!";
        goodComplyText = "Aye, there's a smart chap.";
        badComplyText = "'Ey, let's jus' kill 'em anyway.";
        fleeFailedText = "Ye have no escape, ye scallywag!";
        fleeSuccessfulText = "Aye, ye might 'ave escaped this time, but I'll find yea, ye scurvey dog!";
        winText = "Ye should 'ave given up while yea had a chance";
    }

    public String getName() {
        return name;
    }

    public boolean isDead() {
        return ship.isDead();
    }

    public void takeDamage(int damage) {
        ship.takeDamage(damage);
    }

    public int calculateAttack() {
        return ship.calculateAttack();
    }

    public int getHullStrength() {
        return ship.getHullStrength();
    }

    public int getMaxHullStrength() {
        return ship.getMaxHullStrength();
    }

    @Override
    public void equipForDifficulty(int difficulty) {
        for (int i = 0; i < difficulty; i++) {
            ship.equipWeapon(new Weapon(Math.random() < .5 ? Weapon.Type.Pulse : Weapon.Type.Beam));
            ship.equipShield(new Shield(Math.random() < .5 ? Shield.Type.Energy : Shield.Type.Reflective));
        }
        if (difficulty > 3) {
            ship.equipGadget(new Gadget(Gadget.Type.FIGHTER_INCREASE));
        }
        if (difficulty > 4) {
            ship.equipGadget(new Gadget(Gadget.Type.SCATTER_SHOT));
        }
    }
}
