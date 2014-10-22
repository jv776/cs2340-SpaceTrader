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
import models.Pirate;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Taylor on 10/14/14.
 */
public abstract class RandomEventController extends GameController implements Initializable {
    protected Encounterable encountered;
    public Pane pane;
    public Label otherName;
    public ProgressBar otherHealth;
    public ProgressBar playerHealth;
    public Button NEButton;
    public Button NWButton;
    public Button SEButton;
    public Button SWButton;
    public ImageView playerPic;
    public ImageView otherPic;
    public Rectangle bubbleBox;
    public Polygon bubbleArrow;
    public Label speech;


//    public static void tryEvent(){
//        tryEvent(1);
//    }
//
//    public static void tryEvent(double probModifier){
//        startEvent();
//        if (Math.random()*probModifier >.50){
//            startEvent();
//            tryEvent(probModifier/3);
//        }
//    }
//
//    public static void startEvent(){
//        double eventType = Math.random();
//        //not the best structure for this
//        RandomEventController event;
//        if(eventType<.33){
//            //control.setScreen("PoliceEvent");
//            //event.startEvent();
//        }else if(eventType<.66){
//            event = new PoliceEventController();
//            //event.startEvent();
//        }else {
//            event = new PirateEventController();
//            //event.startEvent();
//        }
//    }



    public void initialize(URL location, ResourceBundle resources){
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
//    abstract void configureTexts();

    void flee(){
        if (gameData.getPlayer().getPilotSkillPoints()*.1*Math.random() > .2){
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
        otherHealth.setProgress(((double) encountered.getHullStrength() / encountered.getMaxHullStrength()));
        playerHealth.setProgress(((double)gameData.getPlayer().getHullStrength()/gameData.getPlayer().getMaxHullStrength()));
    }

     void attack(){
        hideBubble();
        playerAttack();
        encounteredAttack();
        if(encountered.isDead()){
            encounteredDeath();
        }
        if(gameData.getPlayer().isDead()){
            playerDeath();
        }
        updateHealth();
    }

     void playerAttack(){
        encountered.takeDamage(gameData.getPlayer().calculateAttack());
    }
     void encounteredAttack(){
        gameData.getPlayer().takeDamage(encountered.calculateAttack());
    }

     void encounteredDeath(){
        showBubble();
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
        speech.setText(encountered.getWinText());
        NWButton.setText("Use Escape Pod");
        NWButton.setOnMouseClicked((MouseEvent t) -> {
            gameData.getPlayer().die();
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
        control.setScreen("SolarSystemMap");
    }



}
