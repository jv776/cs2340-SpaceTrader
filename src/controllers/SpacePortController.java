package controllers;

import java.net.URL;
import java.util.ResourceBundle;
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
    
    @FXML private Label solar_system;
    @FXML private Label planet;
    @FXML private Label tech_level;
    @FXML private Label resource;
    
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
        btn_Map.setText("Return to " + GameController.getGameData().getSolarSystem().getName());
        
        if (GameController.getGameData().getSolarSystem().getTechLevel().ordinal() < 4) {
            btn_ShipYard.setDisable(true);
        }
        
        solar_system.setText(GameController.getGameData().getSolarSystem().getName());
        planet.setText(GameController.getGameData().getPlanet().getName());
        tech_level.setText(GameController.getGameData().getSolarSystem()
            .getTechLevel().toString());
        resource.setText(GameController.getGameData().getPlanet()
            .getResource().toString());
    }
}
