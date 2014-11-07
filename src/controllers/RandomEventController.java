package controllers;

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
 * Created by Taylor on 10/14/14.
 */
public abstract class RandomEventController implements Initializable {
    protected Encounterable encountered;
    public Pane pane;
    public Label otherName;
    public ProgressBar otherHealth;
    public ProgressBar playerHealth;
    public ProgressBar playerShields;
    public ProgressBar otherShields;
    public Button NEButton;
    public Button NWButton;
    public Button SEButton;
    public Button SWButton;
    public ImageView playerPic;
    public ImageView otherPic;
    public Rectangle bubbleBox;
    public Polygon bubbleArrow;
    public Label speech;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        configureEncountered();
        configureButtons();
        otherName.setText(encountered.getName());
        playerPic.setImage(new Image("/images/spaceship.gif"));
        otherPic.setImage(new Image("/images/unreachable.png"));
        speech.setText(encountered.getWelcomeText());
        playerHealth.setStyle("-fx-accent: red");
        otherHealth.setStyle("-fx-accent: red");
        updateHealth();
        System.out.println("Encountered " + encountered.getName());

    }

    abstract void configureButtons();
    abstract void configureEncountered();

    void flee(){
        if (GameController.getGameData().getPlayer().getPilotSkillPoints()*.1*Math.random() > .2){
            //print you escaped message
            fleeSuccessful();
        } else{
            fleeFailed();

        }
    }
    private void fleeSuccessful(){
        showBubble();
        speech.setText(encountered.getFleeSuccessfulText());
        NWButton.setText("Okay");
        NWButton.setOnMouseClicked((MouseEvent t) -> {
            exitEvent();
        });
        NEButton.setDisable(true);
        SEButton.setDisable(true);

    }

    private void fleeFailed(){
        showBubble();
        speech.setText(encountered.getFleeFailedText());
        encounteredAttack();
        updateHealth();

    }

     void updateHealth(){
        otherHealth.setProgress((double) encountered.getHullStrength() / encountered.getMaxHullStrength());
        playerHealth.setProgress(((double)GameController.getGameData().getPlayer().getHullStrength()
                / GameController.getGameData().getPlayer().getMaxHullStrength()));
        otherShields.setProgress((double) (encountered.getCurrentShields() <= 0 ? -1 : 
                (double) encountered.getCurrentShields()) / encountered.getMaxShields());
        playerShields.setProgress((double)(GameController.getGameData().getPlayer().getCurrentShields() <= 0 
                ? -1 : GameController.getGameData().getPlayer().getCurrentShields())
                / GameController.getGameData().getPlayer().getMaxShields());
    }

     void fight(){
        GameController.getControl().setScreen(Screens.NEW_RANDOM_EVENT);
    }

     void playerAttack(){
        encountered.takeDamage(GameController.getGameData().getPlayer().calculateAttack());
    }
     void encounteredAttack(){
         GameController.getGameData().getPlayer().takeDamage(encountered.calculateAttack());
    }

     void encounteredDeath(){
        showBubble();
         System.out.println("Ending Player Health: " + GameController.getGameData().getPlayer().getHullStrength());
         System.out.println("Ending "+ encountered.getName() +" Health: " + encountered.getHullStrength());
        speech.setText(encountered.getDeathText());
        NWButton.setText("Okay");
        NWButton.setOnMouseClicked((MouseEvent t) -> {

            exitEvent();
        });
        NEButton.setDisable(true);
        SEButton.setDisable(true);
        SWButton.setDisable(true);
    }
     void playerDeath(){
        showBubble();
         System.out.println("Ending Player Health: " + GameController.getGameData().getPlayer().getHullStrength());
         System.out.println("Ending "+ encountered.getName() +" Health: " + encountered.getHullStrength());
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

     void showBubble(){
        bubbleArrow.setOpacity(1);
        bubbleBox.setOpacity(1);
        speech.setOpacity(1);
    }

     void hideBubble(){
        bubbleArrow.setOpacity(0);
        bubbleBox.setOpacity(0);
        speech.setOpacity(0);
    }
     void exitEvent(){
         GameController.getControl().setScreen(Screens.SOLAR_SYSTEM_MAP);
    }
}
