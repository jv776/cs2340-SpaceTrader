/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.Player;

//remove - test code
import models.TradeGood;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class MarketController implements Initializable, ControlledScreen {

    private ScreensController parent;
    private Player player;
    public Label playerInfoLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        parent = screenParent;
        player = parent.getPlayer();
        if (player != null) {
            playerInfoLabel.setText(player.toString());
        }
    }
    
}
