package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * @author Roi Atalla
 */
public class SpacePortController extends GameController implements Initializable {
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
    
    public void onMarketClicked() {
        control.setScreen(Screens.MARKET.getName());
    }
    
    public void onShipYardClicked() {
        System.out.println("SHIP YARD CLICKED WOOOOOO!");
    }
    
    public void onMapClicked() {
        control.setScreen(Screens.SOLAR_SYSTEM_MAP.getName());
    }
    
    public void onNewsClicked() {
        System.out.println("NEWS CLICKED WOOOOO!");
    }
    
    public void onStockMarketClicked() {
        System.out.println("STOCK MARKET CLICKED WOOOO!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        spacePortLabel.setText("Space Port: " + gameData.getPlanet().getName());
        btn_Map.setText("Return to " + gameData.getSolarSystem().getName());
    }
}
