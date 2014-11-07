package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Roi Atalla
 */
public class SpacePortController implements Initializable {
    @FXML
    private Button btnMarket;

    @FXML
    private Button btnShipYard;

    @FXML
    private Button btnMap;

    @FXML
    private Button btnNews;

    @FXML
    private Button btnStockMarket;

    @FXML
    private Label spacePortLabel;

    public void onMarketClicked() {
        GameController.getControl().setScreen(Screens.MARKET);
    }

    public void onShipYardClicked() {
        GameController.getControl().setScreen(Screens.SHIP_YARD);
    }

    public void onMapClicked() {
        GameController.getControl().setScreen(Screens.SOLAR_SYSTEM_MAP);
    }

    public void onNewsClicked() {
        System.out.println("NEWS CLICKED WOOOOO!");
    }

    public void onStockMarketClicked() {
        System.out.println("STOCK MARKET CLICKED WOOOO!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        spacePortLabel.setText("Space Port: " + GameController.getGameData().getPlanet().getName());
        btnMap.setText("Return to " + GameController.getGameData().getSolarSystem().getName());
    }
}
