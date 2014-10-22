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
import models.Pirate;
import models.Police;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * PoliceEvent FXML Controller class
 *
 * @author Taylor
 */
public class PoliceEventController extends RandomEventController implements Initializable {
//    public Police police;
//    public Pane pane;
//    public Label otherName;
//    public ProgressBar otherHealth;
//    public ProgressBar playerHealth;
//    public Button NEButton;
//    public Button NWButton;
//    public Button SEButton;
//    public Button SWButton;
//    public ImageView playerPic;
//    public ImageView otherPic;
//    public Rectangle bubbleBox;
//    public Polygon bubbleArrow;
//    public Label speech;



//    public void initialize(URL location, ResourceBundle resources){
//        System.out.println("PoliceEncounter");
//        NWButton.setText("Attack");
//        NWButton.setOnMouseClicked((MouseEvent t) -> {
//            attack();
//        });
//
//        NEButton.setText("Comply");
//        NEButton.setOnMouseClicked((MouseEvent t) -> {
//            surrender();
//        });
//
//        SEButton.setText("Flee");
//        SEButton.setOnMouseClicked((MouseEvent t) -> {
//            flee();
//        });
//        SWButton.setDisable(true);
//        police = new Police("police"); //May want to change name in the future
//        otherName.setText(police.getName());
//        updateHealth();
//        playerPic.setImage(new Image("/images/current.png"));
//        otherPic.setImage(new Image("/images/reachable.png"));
//
//
//        speech.setText("Suspect, prepare to receive civil judgement.");
//
//    }

    @Override
    void configureButtons() {
        NWButton.setText("Attack");
        NWButton.setOnMouseClicked((MouseEvent t) -> {
            attack();
        });

        NEButton.setText("Comply");
        NEButton.setOnMouseClicked((MouseEvent t) -> {
            surrender();
        });

        SEButton.setText("Flee");
        SEButton.setOnMouseClicked((MouseEvent t) -> {
            flee();
        });
        SWButton.setDisable(true);
//        police = new Police("police"); //May want to change name in the future
//        otherName.setText(police.getName());
//        updateHealth();
//        playerPic.setImage(new Image("/images/current.png"));
//        otherPic.setImage(new Image("/images/reachable.png"));


//        speech.setText("Suspect, prepare to receive civil judgement.");
    }

    @Override
    void configureEncountered() {
        encountered = new Police("Police");
    }


//
//    private void updateHealth(){
//        otherHealth.setProgress(((double) police.getHullStrength() / police.getMaxHullStrength()));
//        playerHealth.setProgress(((double)gameData.getPlayer().getHullStrength()/gameData.getPlayer().getMaxHullStrength()));
//    }
//
//    private void playerAttack(){
//        police.takeDamage(gameData.getPlayer().calculateAttack());
//    }
//    private void policeAttack(){
//        gameData.getPlayer().takeDamage(police.calculateAttack());
//    }
//
//    private void exitEvent(){
//        control.setScreen("SolarSystemMap");
//    }

    private void surrender(){
        if(gameData.getPlayer().hasIllegalGoods()){
            punishment();
        }else {
            playerIsInnocent();
        }
    }
    public void playerIsInnocent(){
        showBubble();
        speech.setText("All right you can go.");
        NWButton.setText("Okay");
        NWButton.setOnMouseClicked((MouseEvent t) -> {
            exitEvent();
        });
        NEButton.setDisable(true);
        SEButton.setDisable(true);
    }


//    private void flee(){
//        if (gameData.getPlayer().getPilotSkillPoints()*.1*Math.random() > .2){
//            //print you escaped message
//            fleeSuccessful();
//        } else{
//            fleeFailed();
//
//        }
//    }
    private void fleeSuccessful(){
        showBubble();
        speech.setText("They got away!");
        NWButton.setText("Okay");
        NWButton.setOnMouseClicked((MouseEvent t) -> {
            exitEvent();
        });
        NEButton.setDisable(true);
        SEButton.setDisable(true);

    }
//    private void fleeFailed(){
//        showBubble();
//        speech.setText("You cannot run from justice.");
//        policeAttack();
//        updateHealth();
//
//    }
    private void punishment(){
        showBubble();
        speech.setText("You have broken the law, criminal scum. Prepare to die.");
        NEButton.setDisable(true);
        encounteredAttack();
    }
    public void playerDeath(){
        showBubble();
        speech.setText("Criminal disposed of, resuming patrol");
        NWButton.setText("Use Escape Pod");
        NWButton.setOnMouseClicked((MouseEvent t) -> {
            exitEvent();
        });
        NEButton.setDisable(true);
        SEButton.setDisable(true);
        gameData.getPlayer().die();
    }
    private void playerWins(){
        showBubble();
        speech.setText("This cannot be happening!");
        NWButton.setText("Okay");
        NWButton.setOnMouseClicked((MouseEvent t) -> {
            exitEvent();
        });
        NEButton.setDisable(true);
        SEButton.setDisable(true);
    }


}
