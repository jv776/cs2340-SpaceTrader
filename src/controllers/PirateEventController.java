package controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import models.Pirate;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Taylor on 10/14/14.
 */
public class PirateEventController extends GameController implements Initializable {
    public Pirate pirate;
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


    public void initialize(URL location, ResourceBundle resources){
        System.out.println("PirateEncounter");
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
        pirate = new Pirate("Pirate"); //May want to change name in the future
        otherName.setText(pirate.getName());
        updateHealth();
        playerPic.setImage(new Image("/images/current.png"));
        otherPic.setImage(new Image("/images/unreachable.png"));


    }

    public void attack(){
        playerAttack();
        pirateAttack();
        if(pirate.isDead()){
            exitEvent();
        }
        if(gameData.getPlayer().isDead()){
            exitEvent();
        }
        updateHealth();
    }

    private void updateHealth(){
        otherHealth.setProgress(((double) pirate.getHullStrength() / pirate.getMaxHullStrength()));
        playerHealth.setProgress(((double)gameData.getPlayer().getHullStrength()/gameData.getPlayer().getMaxHullStrength()));
    }

    private void playerAttack(){
        pirate.takeDamage(gameData.getPlayer().calculateAttack());
    }
    private void pirateAttack(){
        gameData.getPlayer().takeDamage(pirate.calculateAttack());
    }

    private void exitEvent(){
        control.setScreen("SolarSystemMap");
    }

    private void surrender(){
        gameData.getPlayer().spend(gameData.getPlayer().getCredits());
        exitEvent();
    }

    private void flee(){
        if (gameData.getPlayer().getPilotSkillPoints()*.1*Math.random() > .2){
            //print you escaped message
            exitEvent();
        } else{
            pirateAttack();
            updateHealth();
        }

    }



}
