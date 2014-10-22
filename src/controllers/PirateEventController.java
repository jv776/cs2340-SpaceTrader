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

import java.net.URL;
import java.util.ResourceBundle;

/**
 * PirateEvent FXML Controller class
 *
 * @author Taylor
 */
public class PirateEventController extends RandomEventController implements Initializable {

    void configureButtons(){
        NWButton.setText("Attack");
        NWButton.setOnMouseClicked((MouseEvent t) -> {
            attack();
        });

        NEButton.setText("Surrender");
        NEButton.setOnMouseClicked((MouseEvent t) -> {
            surrender();
        });

        SEButton.setText("Flee");
        SEButton.setOnMouseClicked((MouseEvent t) -> {
            flee();
        });
        SWButton.setDisable(true);
    }
    void configureEncountered(){
        encountered = new Pirate("Pirate");
    }



    public void initialize(URL location, ResourceBundle resources){
        super.initialize(location, resources);
    }
//    public void initialize(URL location, ResourceBundle resources){
//        System.out.println("PirateEncounter");
//        NWButton.setText("Attack");
//        NWButton.setOnMouseClicked((MouseEvent t) -> {
//            attack();
//        });
//
//        NEButton.setText("Surrender");
//        NEButton.setOnMouseClicked((MouseEvent t) -> {
//            surrender();
//        });
//
//        SEButton.setText("Flee");
//        SEButton.setOnMouseClicked((MouseEvent t) -> {
//            flee();
//        });
//        SWButton.setDisable(true);
//        ; //May want to change name in the future
//        otherName.setText(pirate.getName());
//        updateHealth();
//        playerPic.setImage(new Image("/images/current.png"));
//        otherPic.setImage(new Image("/images/unreachable.png"));
//
//        speech.setText("Yarr, surrender yer Credits or we'll take 'em by force!");
//
//
//    }
//
//
//    public void playerDeath(){
//        showBubble();
//        speech.setText("Ye should 'ave given up while yea had a chance!");
//        NWButton.setText("Use Escape Pod");
//        NWButton.setOnMouseClicked((MouseEvent t) -> {
//            gameData.getPlayer().die();
//            exitEvent();
//        });
//        NEButton.setDisable(true);
//        SEButton.setDisable(true);
//    }


    private void surrender(){
        showBubble();
        speech.setText("Ah'har, I'll be take'n them credits now!");
        NWButton.setText("Okay");
        NWButton.setOnMouseClicked((MouseEvent t) -> {
            exitEvent();
        });
        NEButton.setDisable(true);
        SEButton.setDisable(true);
        gameData.getPlayer().spend(gameData.getPlayer().getCredits()/2);
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
//    private void fleeSuccessful(){
//        showBubble();
//        speech.setText("Aye, ye might 'ave escaped this time, but I'll find yea, ye scurvey dog!");
//        NWButton.setText("Okay");
//        NWButton.setOnMouseClicked((MouseEvent t) -> {
//            exitEvent();
//        });
//        NEButton.setDisable(true);
//        SEButton.setDisable(true);
//
//    }
//    private void fleeFailed(){
//        showBubble();
//        speech.setText("Ye have no escape, ye scallywag!");
//        pirateAttack();
//        updateHealth();
//
//    }
//    private void playerWins(){
//        showBubble();
//        speech.setText("Arrr! You win this time, " + gameData.getPlayer().getName() + ", but I'll be back!");
//        NWButton.setText("Okay");
//        NWButton.setOnMouseClicked((MouseEvent t) -> {
//            exitEvent();
//        });
//        NEButton.setDisable(true);
//        SEButton.setDisable(true);
//    }
    @Override
    void playerDeath(){
        super.playerDeath();
        gameData.getPlayer().spend(gameData.getPlayer().getCredits()/2);
    }


}
