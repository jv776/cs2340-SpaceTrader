package models;

import controllers.GameController;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class representing an interstellar cop
 *
 * @author Taylor
 */
public class Police extends Encounterable{
//    private String name;
//    private Ship ship;
//    private int bounty;
//    protected String welcomeText;
//    protected String deathText;
//    protected String fleeFailedText;
//    protected String fleeSuccessfulText;
//    protected String winText;

    public Police(String name) {
//        super(0, 0, 0, 0, 0);
        this.name = name;
        ship = new Ship(Ship.Type.Gnat, this);
        welcomeText= "Suspect, prepare to receive civil judgement.";
        deathText = "Shazbot!";
        fleeFailedText = "You will never escape from justice.";
        fleeSuccessfulText = "They got away!";
        winText = "Criminal disposed of, resuming patrol";
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
}