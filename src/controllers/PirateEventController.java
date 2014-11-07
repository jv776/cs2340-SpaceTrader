package controllers;

import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import models.Pirate;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * PirateEvent FXML Controller class
 *
 * @author Taylor
 */
public class PirateEventController extends RandomEventController implements Initializable {

    void configureButtons() {
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

    void configureEncountered() {
        encountered = new Pirate("Pirate");
    }


    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }


    private void surrender() {
        showBubble();
        speech.setText("Ah'har, I'll be take'n them credits now!");
        NWButton.setText("Okay");
        NWButton.setOnMouseClicked((MouseEvent t) -> {
            exitEvent();
        });
        NEButton.setDisable(true);
        SEButton.setDisable(true);
        GameController.getGameData().getPlayer().spend(GameController.getGameData().getPlayer().getCredits() / 2);
    }

    @Override
    void playerDeath() {
        super.playerDeath();
        GameController.getGameData().getPlayer().spend(GameController.getGameData().getPlayer().getCredits() / 2);
    }


}
