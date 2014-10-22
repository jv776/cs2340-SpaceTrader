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

    }

    @Override
    void configureEncountered() {
        encountered = new Police("Police");
    }




    private void surrender(){
        if(GameController.getGameData().getPlayer().hasIllegalGoods()){
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
        GameController.getGameData().getPlayer().die();
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
