package models;

import java.util.ArrayList;
import javafx.scene.image.Image;
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
        ship.equipWeapon(new Weapon(WeaponType.PULSE_LASER));
        ship.equipWeapon(new Weapon(WeaponType.PULSE_LASER));
        ship.equipShield(new Shield(ShieldType.ENERGY_SHIELD));
        this.name = "Bob" + Math.random();
        welcomeText= "Yarr, surrender yer Credits or we'll take 'em by force!";
        deathText = "Arrr! You win this time, but I'll be back!";
        fleeFailedText = "Ye have no escape, ye scallywag!";
        fleeSuccessfulText = "Aye, ye might 'ave escaped this time, but I'll find yea, ye scurvey dog!";
        winText = "Ye should 'ave given up while yea had a chance";
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public Image getShipImage() {
        return new Image("/images/pirateship.png");
    }
}
