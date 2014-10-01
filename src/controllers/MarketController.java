/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import models.Player;
import models.Planet;

/**
 * FXML Controller class
 *
 * @author Alex, John
 */
public class MarketController implements Initializable, ControlledScreen {

    private ScreensController parent;
    private Player player;
    
    @FXML
    private Label systemGovernmentLabel;

    @FXML
    private Label systemNameLabel;

    @FXML
    private Label planetNameLabel;

    @FXML
    private Label shipTypeLabel;

    @FXML
    private Label planetEventLabel;

    @FXML
    private Label planetResourcesLabel;

    @FXML
    private Label systemTechLevelLabel;

    @FXML
    private Label playerNameLabel;

    @FXML
    private Label creditsLabel;

    @FXML
    private ComboBox<?> waterQuantityBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        parent = screenParent;
        player = parent.getPlayer();
        
        if (player != null) {
            Planet p = player.getCurrentPlanet();
            
            systemGovernmentLabel.setText(p.governmentType());
            systemNameLabel.setText(p.system.name);
            planetNameLabel.setText(p.name);
            shipTypeLabel.setText(player.shipType());
            planetEventLabel.setText(p.currentEvent());
            planetResourcesLabel.setText(p.resourceType());
            systemTechLevelLabel.setText(p.technologyLevel());
            playerNameLabel.setText(player.name);
            creditsLabel.setText(Integer.toString(player.getCredits()));
            //waterQuantityBox.
        }
    }

}
