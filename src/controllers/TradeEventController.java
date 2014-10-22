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
import models.Planet;
import models.SolarSystem;
import models.Trader;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * TradeEvent FXML Controller class
 *
 * @author Taylor
 */
public class TradeEventController extends RandomEventController implements Initializable {


    @Override
    void configureButtons() {
        NWButton.setText("Trade");
        NWButton.setOnMouseClicked((MouseEvent t) -> {
            trade();
        });

        NEButton.setText("Ignore");
        NEButton.setOnMouseClicked((MouseEvent t) -> {
            exitEvent();
        });

        SEButton.setDisable(true);
        SWButton.setDisable(true);
    }

    @Override
    void configureEncountered() {
        encountered = new Trader("The Trader", gameData.getUniverse().getRandomPlanet());
    }


    private void trade(){// THIS PROBABLY NEEDS TO BE CHANGED, VERY DIRTY HACK
        Planet temp = gameData.getPlayer().getCurrentPlanet();
        gameData.getPlayer().setCurrentPlanet(((Trader)(encountered)).getOrigin());
        control.setScreen("Market");
        gameData.getPlayer().setCurrentPlanet(temp);
    }




}
