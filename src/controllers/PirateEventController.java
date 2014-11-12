package controllers;

import javafx.fxml.Initializable;
import models.Pirate;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * PirateEvent FXML Controller class.
 *
 * @author Taylor
 */
public class PirateEventController extends RandomEventController implements Initializable {

    @Override
    protected void configureButtons() {
        NWButton.setText("Attack");
        NWButton.setOnMouseClicked(t -> attack());

        NEButton.setText("Surrender");
        NEButton.setOnMouseClicked(t -> surrender());

        SEButton.setText("Flee");
        SEButton.setOnMouseClicked(t -> flee());
        SWButton.setDisable(true);
    }

    @Override
    protected void configureEncountered() {
        encountered = new Pirate("Pirate");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }


    private void surrender() {
        showBubble();
        speech.setText("Ah'har, I'll be take'n them credits now!");
        NWButton.setText("Okay");
        NWButton.setOnMouseClicked(t -> exitEvent());
        NEButton.setDisable(true);
        SEButton.setDisable(true);
        GameController.getGameData().getPlayer().spend(GameController.getGameData().getPlayer().getCredits() / 2);
    }

    @Override
    protected void playerDeath() {
        super.playerDeath();
        GameController.getGameData().getPlayer().spend(GameController.getGameData().getPlayer().getCredits() / 2);
    }


}
