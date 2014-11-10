package models;

import javafx.scene.image.Image;
import models.Gadget.GadgetType;
import models.Shield.ShieldType;
import models.Weapon.WeaponType;

/**
 * Class representing a space-pirate
 *
 * @author Taylor
 */
public class Pirate extends Encounterable{

    public Pirate(String name){
        super(2, 5, 3, 0, 0);
        this.name = "Bob" + Math.random();
        welcomeText= "Yarr, surrender yer Credits or we'll take 'em by force!";
        complyText= "Aye, there's a smart chap.";
        deathText = "Arrr! You win this time, but I'll be back!";
        fleeFailedText = "Ye have no escape, ye scallywag!";
        fleeSuccessfulText = "Aye, ye might 'ave escaped this time, but I'll find yea, ye scurvey dog!";
        winText = "Ye should 'ave given up while yea had a chance";
        
                
                ship.equipWeapon(new Weapon(WeaponType.MILITARY_LASER));
                ship.equipWeapon(new Weapon(WeaponType.MILITARY_LASER));
                ship.equipWeapon(new Weapon(WeaponType.MILITARY_LASER));
                
                ship.equipShield(new Shield(ShieldType.ENERGY_SHIELD));
                ship.equipShield(new Shield(ShieldType.ENERGY_SHIELD));
                ship.equipShield(new Shield(ShieldType.ENERGY_SHIELD));
                
                ship.equipGadget(new Gadget(GadgetType.REFLECTOR));
        ship.equipGadget(new Gadget(GadgetType.REFLECTOR));
        ship.equipGadget(new Gadget(GadgetType.REFLECTOR));
        ship.equipGadget(new Gadget(GadgetType.REFLECTOR));
        ship.equipGadget(new Gadget(GadgetType.REFLECTOR));
        
        
        ship.equipGadget(new Gadget(GadgetType.HOMING_SHOT));
        ship.equipGadget(new Gadget(GadgetType.HOMING_SHOT));
        ship.equipGadget(new Gadget(GadgetType.HOMING_SHOT));
        ship.equipGadget(new Gadget(GadgetType.HOMING_SHOT));
        ship.equipGadget(new Gadget(GadgetType.HOMING_SHOT));
        
        ship.equipGadget(new Gadget(GadgetType.REFLECTOR));
        ship.equipGadget(new Gadget(GadgetType.REFLECTOR));
        ship.equipGadget(new Gadget(GadgetType.REFLECTOR));
        ship.equipGadget(new Gadget(GadgetType.REFLECTOR));


        }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public Image getShipImage() {
        return new Image("/images/pirateship.png");
    }
    
    public void equipForDifficulty(int difficulty) {
        for (int i = 0; i < difficulty; i++) {
            ship.equipWeapon(new Weapon(Math.random() < .5 ? Weapon.WeaponType.PULSE_LASER : Weapon.WeaponType.BEAM_LASER));
            ship.equipShield(new Shield(Math.random() < .5 ? Shield.ShieldType.ENERGY_SHIELD : Shield.ShieldType.BLAST_SHIELD));
        }
        if (difficulty > 3) {
            ship.equipGadget(new Gadget(GadgetType.FIGHTER_INCREASE));
        }
        if (difficulty > 4) {
            ship.equipGadget(new Gadget(GadgetType.SCATTER_SHOT));
        }
    }
}
