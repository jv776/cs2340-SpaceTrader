package models;

import javafx.scene.image.Image;

/**
 * Class representing an interstellar cop
 *
 * @author Taylor, Alex
 */
public class Police extends Encounterable{


    public Police(String name) {
        super(5, 5, 5, 0, 0);
        this.name = name;
        ship.equipWeapon(new Weapon(Weapon.WeaponType.MILITARY_LASER));
        ship.equipWeapon(new Weapon(Weapon.WeaponType.MILITARY_LASER));
        ship.equipShield(new Shield(Shield.ShieldType.REFLECTIVE_SHIELD));
        ship.equipShield(new Shield(Shield.ShieldType.REFLECTIVE_SHIELD));
        welcomeText= "Suspect, prepare to receive civil judgement.";
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
        return new Image("/images/policeship.jpg");
    }
}