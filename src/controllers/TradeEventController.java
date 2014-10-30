package controllers;

import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import models.Planet;
import models.Trader;

/**
 * TradeEvent FXML Controller class
 *
 * @author Taylor
 */
public class TradeEventController extends RandomEventController implements Initializable {


    @Override
    void configureButtons() {
        NWButton.setText("Trade");
        NWButton.setOnMouseClicked((MouseEvent t) -> trade());

        NEButton.setText("Ignore");
        NEButton.setOnMouseClicked((MouseEvent t) -> exitEvent());

        SEButton.setDisable(true);
        SWButton.setDisable(true);
    }

    @Override
    void configureEncountered() {
        encountered = new Trader("The Trader", GameController.getGameData().getUniverse().getRandomPlanet());
    }

    private void trade(){// THIS PROBABLY NEEDS TO BE CHANGED, VERY DIRTY HACK
        Planet temp = GameController.getGameData().getPlayer().getCurrentPlanet();
        GameController.getGameData().getPlayer().setCurrentPlanet(((Trader)(encountered)).getOrigin());
        GameController.getControl().setScreen(Screens.MARKET);
        GameController.getGameData().getPlayer().setCurrentPlanet(temp);
    }
}
