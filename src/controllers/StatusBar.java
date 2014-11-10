/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import models.GameData;

/**
 *
 * @author Alex
 */
public class StatusBar extends HBox {
    @FXML private Label name_tab;
    @FXML private Label location_tab;
    @FXML private Label cargo_tab;
    @FXML private Label credits_tab;
    @FXML private Label fuel_tab;
    
    private GameData gameData;
    
    public StatusBar() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
            "/views/StatusBar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
        gameData = GameController.getGameData();
    }
    
    public void updateName() {
        name_tab.setText(gameData.getPlayer().getName());
    }
    
    public void updateLocation() {
        location_tab.setText(gameData.getPlanet() != null ? gameData.getPlanet().getName()
                : gameData.getSolarSystem().getName());
    }
    
    public void updateCargo() {
        cargo_tab.setText("Cargo: " + gameData.getShip().getCargoHold().getCargoQuantity() + "/"
            + gameData.getShip().getCargoHold().getCapacity());
    }
    
    public void updateCredits() {
        credits_tab.setText("Credits: " + gameData.getPlayer().getCredits());
    }
    
    public void updateFuel() {
        double fuelAmount = gameData.getShip().getFuelAmount();
        int fuelCapacity = gameData.getShip().getFuelCapacity();
        fuel_tab.setText("Fuel: " + Math.round(fuelAmount * 100) / 100.0 + "/" + fuelCapacity);
    }
}
