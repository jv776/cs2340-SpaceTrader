package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.ProgressBar;
import models.Player;

/**
 * @author Roi Atalla
 */
public class SpacePortController implements Initializable {
    @FXML
    private Button btn_Market;
    
    @FXML
    private Button button_Refuel, button_Repair;
    
    @FXML
    private Button btn_ShipYard;

    @FXML
    private Button btn_Map;
    
    @FXML
    private Button btn_News;
    
    @FXML
    private Button btn_StockMarket;
    
    @FXML 
    private ProgressBar health_bar, fuel_bar;
    
    @FXML
    private Label spacePortLabel;
    
    @FXML private Label solar_system;
    @FXML private Label planet;
    @FXML private Label tech_level;
    @FXML private Label resource;
    
    private Player player;
    
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
    
    @FXML
    void handleRefuelButton() {
        player.spend((int) (Math.ceil(player.getShip().getFuelCapacity() 
                - player.getShip().getFuelAmount()) * player.getShip().getFuelCost()));
        player.getShip().refuel();
        
        fuel_bar.setProgress(1.0);
        button_Refuel.setDisable(true);
    }
    
    @FXML
    void handleRepairButton() {
        player.spend((int)((player.getShip().getMaxHullStrength() 
                - player.getShip().getHullStrength()) * player.getShip().getRepairCost()));
        player.getShip().repair();
        
        health_bar.setProgress(1.0);
        button_Repair.setDisable(true);
    }
    
    @FXML
    private void shipCustomization() {
        GameController.getControl().setScreen(Screens.SHIP_CUSTOMIZATION);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        player = GameController.getGameData().getPlayer();
        spacePortLabel.setText("Space Port: " + GameController.getGameData().getPlanet().getName());
        btn_Map.setText("Return to " + GameController.getGameData().getSolarSystem().getName());
        
        if (GameController.getGameData().getSolarSystem().getTechLevel().ordinal() < 4) {
            btn_ShipYard.setDisable(true);
        }
        
        if (GameController.getGameData().getShip().getFuelCapacity() 
                == GameController.getGameData().getShip().getFuelAmount()) {
            button_Refuel.setDisable(true);
        }
        
        if (GameController.getGameData().getShip().getHullStrength()
                == GameController.getGameData().getShip().getMaxHullStrength()) {
            button_Repair.setDisable(true);
        }
        
        solar_system.setText(GameController.getGameData().getSolarSystem().getName());
        planet.setText(GameController.getGameData().getPlanet().getName());
        tech_level.setText(GameController.getGameData().getSolarSystem()
            .getTechLevel().toString());
        resource.setText(GameController.getGameData().getPlanet()
            .getResource().toString());
        fuel_bar.setProgress(GameController.getGameData().getShip().getFuelAmount() 
            / (double)GameController.getGameData().getShip().getFuelCapacity());
        health_bar.setProgress(GameController.getGameData().getShip().getHullStrength()
            / (double)GameController.getGameData().getShip().getMaxHullStrength());
    }
}
