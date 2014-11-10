package models;

import javafx.scene.image.Image;

/**
 * Class representing a traveling Trader
 *
 * @author Taylor
 */
public class Trader extends Encounterable {
    protected String name;
    private Planet origin;

    protected String escapeText;

    public Trader(String name, Planet origin){
        this.name = name;
        this.origin = origin;
        escapeText = "Is that all, stranger?";
        welcomeText = "What're ya buy'in?";
    }
    public String getName(){
        return name;
    }

    public Planet getOrigin(){
        return origin;
    }

    @Override
    public Image getShipImage() {
        return new Image("/images/spaceship.gif");
    }

    @Override
    public void equipForDifficulty(int difficulty) {
        for (int i = 0; i < difficulty; i++) {
            ship.equipWeapon(new Weapon(Weapon.WeaponType.BEAM_LASER));
            ship.equipShield(new Shield(Shield.ShieldType.BLAST_SHIELD));
        }
        if (difficulty > 2) {
            ship.equipGadget(new Gadget(Gadget.GadgetType.ENGINEER_INCREASE));
        }
        if (difficulty > 3) {
            ship.equipGadget(new Gadget(Gadget.GadgetType.FIGHTER_INCREASE));
        } 
    }
}
