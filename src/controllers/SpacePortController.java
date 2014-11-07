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
    private Button btn_Market;

    @FXML
    private Button btn_ShipYard;

    @FXML
    private Button btn_Map;

    @FXML
    private Button btn_News;

    @FXML
    private Button btn_StockMarket;

    @FXML
    private Label spacePortLabel;

    @FXML
    private void onMarketClicked() {
        GameController.getControl().setScreen(Screens.MARKET);
    }

    @FXML
    private void onShipYardClicked() {
        GameController.getControl().setScreen(Screens.SHIP_YARD);
    }

    @FXML
    private void onMapClicked() {
        GameController.getControl().setScreen(Screens.SOLAR_SYSTEM_MAP);
    }

    @FXML
    private void onNewsClicked() {
        System.out.println("NEWS CLICKED WOOOOO!");
    }

    @FXML
    private void onStockMarketClicked() {
        System.out.println("STOCK MARKET CLICKED WOOOO!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        spacePortLabel.setText("Space Port: " + GameController.getGameData().getPlanet().getName());
        btn_Map.setText("Return to " + GameController.getGameData().getSolarSystem().getName());
    }
}
