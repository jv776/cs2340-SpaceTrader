package models;

import javafx.scene.image.Image;
import models.Gadget.GadgetType;
import models.Shield.ShieldType;
import models.Weapon.WeaponType;

/**
 * Class representing an interstellar cop
 *
 * @author Taylor, Alex
 */
public class Police extends Encounterable{


    public Police(String name) {
        super(5, 5, 5, 0, 0);
        this.name = name;
        welcomeText= "Suspect, prepare to receive civil judgement.";
        complyText="You have committed crimes against Skyrim and her people. What say you in your defense?";
        deathText = "Shazbot!";
        fleeFailedText = "You will never escape from justice.";
        fleeSuccessfulText = "They got away!";
        winText = "Criminal disposed of, resuming patrol";
        
    }

    public String getName() {
        return name;
    }

    @Override
    public Image getShipImage() {
        return new Image("/images/policeship.gif");
    }
    
    public void equipForDifficulty(int difficulty) {
        for (int i = 0; i < difficulty; i++) {
            ship.equipWeapon(new Weapon(WeaponType.MILITARY_LASER));
            ship.equipShield(new Shield(ShieldType.REFLECTIVE_SHIELD));
        }
        if (difficulty > 2) {
            ship.equipGadget(new Gadget(GadgetType.PILOT_INCREASE));
            ship.equipGadget(new Gadget(GadgetType.ENGINEER_INCREASE));
            ship.equipGadget(new Gadget(GadgetType.FIGHTER_INCREASE));
        }
        if (difficulty > 4) {
            ship.equipGadget(new Gadget(GadgetType.REFLECTOR));
        }
    }
}