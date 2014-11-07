/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.scene.image.Image;

/**
 *
 * @author Alex
 */
public class Dreadnought extends Encounterable{
    public Dreadnought(String name) {
        super(5, 5, 5, 0, 0);
        this.name = name;
        ship = new Ship(Ship.Type.Dragonfly, this);
        ship.equipWeapon(new Weapon(Weapon.WeaponType.DEATH_LASER));
        ship.equipWeapon(new Weapon(Weapon.WeaponType.MILITARY_LASER));
        ship.equipWeapon(new Weapon(Weapon.WeaponType.DEATH_LASER));
        
        ship.equipShield(new Shield(Shield.ShieldType.IMPERVIOUS_SHIELD));
        ship.equipShield(new Shield(Shield.ShieldType.IMPERVIOUS_SHIELD));
        
        ship.equipGadget(new Gadget(Gadget.GadgetType.PILOT_INCREASE));
        ship.equipGadget(new Gadget(Gadget.GadgetType.FIGHTER_INCREASE));
        ship.equipGadget(new Gadget(Gadget.GadgetType.ENGINEER_INCREASE));
        ship.equipGadget(new Gadget(Gadget.GadgetType.SCATTER_SHOT));
        
        welcomeText= "Prepare to die.";
        deathText = "Wha-what?!";
        fleeFailedText = "You will never escape.";
        fleeSuccessfulText = "We will find you. And we will kill you.";
        winText = "Target destroyed.";
    }

    public String getName() {
        return name;
    }

    @Override
    public Image getShipImage() {
        return new Image("/images/dreadnoughtship.png");
    }
}
