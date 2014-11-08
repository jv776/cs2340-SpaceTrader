package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import models.Encounterable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * An abstract representation of an encounter in space with another entity
 *
 * @author Taylor
 */
public abstract class RandomEventController implements Initializable {
    /**
     * The person encountered by the player (could be police, a pirate, or
     * another trader).
     */
    protected Encounterable encountered;

    /**
     * All of these objects are UI elements needed for the FXML to work.
     * They are protected so that each specific random event's controller can
     * still access them.
     */

    @FXML
    protected Pane pane;

    @FXML
    protected Label otherName;

    @FXML
    protected ProgressBar otherHealth;

    @FXML
    protected ProgressBar playerHealth;

    @FXML
    protected Button NEButton;

    @FXML
    protected Button NWButton;

    @FXML
    protected Button SEButton;

    @FXML
    protected Button SWButton;

    @FXML
    protected ImageView playerPic;

    @FXML
    protected ImageView otherPic;

    @FXML
    protected Rectangle bubbleBox;

    @FXML
    protected Polygon bubbleArrow;

    @FXML
    protected Label speech;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureEncountered();
        configureButtons();
        otherName.setText(encountered.getName());
        playerPic.setImage(new Image("/images/current.png"));
        otherPic.setImage(new Image("/images/unreachable.png"));
        speech.setText(encountered.getWelcomeText());
        updateHealth();
        System.out.println("Encountered " + encountered.getName());

    }

    abstract void configureButtons();

    abstract void configureEncountered();

    protected void flee() {
        if (GameController.getGameData().getPlayer().getPilotSkillPoints() * .1 * Math.random() > .2) {
            //print you escaped message
            fleeSuccessful();
        } else {
            fleeFailed();

        }
    }

    protected void fleeSuccessful() {
        showBubble();
        speech.setText(encountered.getFleeSuccessfulText());
        NWButton.setText("Okay");
        NWButton.setOnMouseClicked((MouseEvent t) -> {
            exitEvent();
        });
        NEButton.setDisable(true);
        SEButton.setDisable(true);

    }

    protected void fleeFailed() {
        showBubble();
        speech.setText(encountered.getFleeFailedText());
        encounteredAttack();
        updateHealth();

    }

    protected void updateHealth() {
        otherHealth.setProgress(((double) encountered.getHullStrength() / encountered.getMaxHullStrength()));
        playerHealth.setProgress(((double) GameController.getGameData().getPlayer().getHullStrength() / GameController.getGameData().getPlayer().getMaxHullStrength()));
    }

    protected void attack() {
        hideBubble();
        playerAttack();
        encounteredAttack();
        if (encountered.isDead()) {
            encounteredDeath();
        }
        if (GameController.getGameData().getPlayer().isDead()) {
            playerDeath();
        }
        updateHealth();
    }

    protected void playerAttack() {
        encountered.takeDamage(GameController.getGameData().getPlayer().calculateAttack());
    }

    protected void encounteredAttack() {
        GameController.getGameData().getPlayer().takeDamage(encountered.calculateAttack());
    }

    protected void encounteredDeath() {
        showBubble();
        System.out.println("Ending Player Health: " + GameController.getGameData().getPlayer().getHullStrength());
        System.out.println("Ending " + encountered.getName() + " Health: " + encountered.getHullStrength());
        speech.setText(encountered.getDeathText());
        NWButton.setText("Okay");
        NWButton.setOnMouseClicked((MouseEvent t) -> {

            exitEvent();
        });
        NEButton.setDisable(true);
        SEButton.setDisable(true);
        SWButton.setDisable(true);
    }

    protected void playerDeath() {
        showBubble();
        System.out.println("Ending Player Health: " + GameController.getGameData().getPlayer().getHullStrength());
        System.out.println("Ending " + encountered.getName() + " Health: " + encountered.getHullStrength());
        speech.setText(encountered.getWinText());
        NWButton.setText("Use Escape Pod");
        NWButton.setOnMouseClicked((MouseEvent t) -> {
            GameController.getGameData().getPlayer().die();
            exitEvent();
        });
        NEButton.setDisable(true);
        SEButton.setDisable(true);
        SWButton.setDisable(true);
    }

    protected void showBubble() {
        bubbleArrow.setOpacity(1);
        bubbleBox.setOpacity(1);
        speech.setOpacity(1);
    }

    protected void hideBubble() {
        bubbleArrow.setOpacity(0);
        bubbleBox.setOpacity(0);
        speech.setOpacity(0);
    }

    protected void exitEvent() {
        GameController.getControl().setScreen(Screens.SOLAR_SYSTEM_MAP);
    }


}
