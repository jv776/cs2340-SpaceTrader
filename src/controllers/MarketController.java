/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import models.Marketplace;
import models.Player;
import models.TradeGood;

/**
 * FXML Controller class
 *
 * @author Alex, John
 */
public class MarketController extends GameController implements Initializable {
    
    @FXML
    private Label buyNarcoticsValueLabel;

    @FXML
    private Button sellFursButton;

    @FXML
    private Label buyNarcoticsQuantityLabel;

    @FXML
    private Button buyFirearmsButton;

    @FXML
    private Label buyMedicineValueLabel;

    @FXML
    private GridPane sellPane;

    @FXML
    private Button sellWaterButton;

    @FXML
    private Button buyFursButton;

    @FXML
    private Label sellNarcoticsValueLabel;

    @FXML
    private Button sellFirearmsButton;

    @FXML
    private Label sellWaterQuantityLabel;

    @FXML
    private Button sellNarcoticsButton;

    @FXML
    private GridPane buyPane;

    @FXML
    private Button sellGamesButton;

    @FXML
    private Label sellOreValueLabel;

    @FXML
    private Label buyGamesQuantityLabel;

    @FXML
    private Label valueLabel1;

    @FXML
    private Label sellGamesValueLabel;

    @FXML
    private Label buyMachinesValueLabel;

    @FXML
    private Label buyFoodQuantityLabel;

    @FXML
    private Label sellFirearmsQuantityLabel;

    @FXML
    private Label buyFirearmsQuantityLabel;

    @FXML
    private Button buyWaterButton;

    @FXML
    private Label marketNameLabel;

    @FXML
    private Label valueLabel;

    @FXML
    private Label buyWaterQuantityLabel;

    @FXML
    private Label buyFirearmsValueLabel;

    @FXML
    private Button sellButton;

    @FXML
    private Label buyOreQuantityLabel;

    @FXML
    private Label buyMedicineQuantityLabel;

    @FXML
    private Button buyGamesButton;

    @FXML
    private Label sellMedicineValueLabel;

    @FXML
    private Label buyWaterValueLabel;

    @FXML
    private Button sellRobotsButton;

    @FXML
    private Label moneyLabel;

    @FXML
    private Label sellMedicineQuantityLabel;

    @FXML
    private Label sellRobotsQuantityLabel;

    @FXML
    private Label buyGamesValueLabel;

    @FXML
    private Label buyFursQuantityLabel;

    @FXML
    private Label sellGamesQuantityLabel;

    @FXML
    private Label sellFirearmsValueLabel;

    @FXML
    private Label sellNarcoticsQuantityLabel;

    @FXML
    private Label buyFursValueLabel;

    @FXML
    private Label buyRobotsValueLabel;

    @FXML
    private Label sellFursQuantityLabel;

    @FXML
    private Label buyRobotsQuantityLabel;

    @FXML
    private Label sellMachinesQuantityLabel;

    @FXML
    private Button buyFoodButton;

    @FXML
    private Label sellMachinesValueLabel;

    @FXML
    private Button buyRobotsButton;

    @FXML
    private Label buyOreValueLabel;

    @FXML
    private Label sellOreQuantityLabel;

    @FXML
    private Button buyMedicineButton;

    @FXML
    private Label buyFoodValueLabel;

    @FXML
    private Button sellMedicineButton;

    @FXML
    private Label sellRobotsValueLabel;

    @FXML
    private Button buyMachinesButton;

    @FXML
    private Button sellOreButton;

    @FXML
    private Label sellFursValueLabel;

    @FXML
    private Button buyButton;

    @FXML
    private Label sellFoodQuantityLabel;

    @FXML
    private Button buyOreButton;

    @FXML
    private Label sellWaterValueLabel;

    @FXML
    private Label sellFoodValueLabel;

    @FXML
    private Label buyMachinesQuantityLabel;

    @FXML
    private Button sellFoodButton;

    @FXML
    private Button buyNarcoticsButton;

    @FXML
    private Button sellMachinesButton;
    
    @FXML
    private Button returnButton;

    private Button[] buyButtons;
    private Button[] sellButtons;
    private Label[] buyQuantities;
    private Label[] sellQuantities;
    private Label[] buyValues;
    private Label[] sellValues;
    
    private Player player;
    private Marketplace market;
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        player = gameData.getPlayer();
        market = player.getCurrentPlanet().getMarket();
        
        returnButton.setText("Return to " + gameData.getSolarSystem().getName());
        
        Label[] buyQuantities = {
            buyWaterQuantityLabel, buyFoodQuantityLabel, buyFursQuantityLabel,
            buyOreQuantityLabel, buyGamesQuantityLabel,
            buyFirearmsQuantityLabel, buyMedicineQuantityLabel,
            buyMachinesQuantityLabel, buyNarcoticsQuantityLabel,
            buyRobotsQuantityLabel
        };
        this.buyQuantities = buyQuantities;
        
        for (int i = 0; i < buyQuantities.length; i++) {
            int quantity = player.getShip().getCargoHold().getQuantity(TradeGood.values()[i]);
            buyQuantities[i].setText(Integer.toString(quantity));
        }
        
        Label[] buyValues = {
            buyWaterValueLabel, buyFoodValueLabel, buyFursValueLabel,
            buyOreValueLabel, buyGamesValueLabel, buyFirearmsValueLabel,
            buyMedicineValueLabel, buyMachinesValueLabel,
            buyNarcoticsValueLabel, buyRobotsValueLabel
        };
        this.buyValues = buyValues;
        
        for (int i = 0; i < buyValues.length; i++) {
            int price = market.getPrice(TradeGood.values()[i]);
            buyValues[i].setText(Integer.toString(price));
        }
    
        Label[] sellQuantities = {
            sellWaterQuantityLabel, sellFoodQuantityLabel,
            sellFursQuantityLabel, sellOreQuantityLabel, sellGamesQuantityLabel,
            sellFirearmsQuantityLabel, sellMedicineQuantityLabel,
            sellMachinesQuantityLabel, sellNarcoticsQuantityLabel,
            sellRobotsQuantityLabel
        };
        this.sellQuantities = sellQuantities;
    
        for (int i = 0; i < sellQuantities.length; i++) {
            int quantity = player.getShip().getCargoHold().getQuantity(TradeGood.values()[i]);
            sellQuantities[i].setText(Integer.toString(quantity));
        }
        
        Label[] sellValues = {
            sellWaterValueLabel, sellFoodValueLabel, sellFursValueLabel,
            sellOreValueLabel, sellGamesValueLabel, sellFirearmsValueLabel,
            sellMedicineValueLabel, sellMachinesValueLabel,
            sellNarcoticsValueLabel, sellRobotsValueLabel
        };
        this.sellValues = sellValues;
        
        for (int i = 0; i < sellValues.length; i++) {
            int price = market.getSalePrice(TradeGood.values()[i]);
            sellValues[i].setText(Integer.toString(price));
        }
        
        Button[] buyButtons = {
            buyWaterButton, buyFoodButton, buyFursButton, buyOreButton,
            buyGamesButton, buyFirearmsButton, buyMedicineButton,
            buyMachinesButton, buyNarcoticsButton, buyRobotsButton
        };
        this.buyButtons = buyButtons;
        
        for (int i = 0; i < buyButtons.length; i++) {
            int supply = market.getSupply(TradeGood.values()[i]);
            int price = market.getPrice(TradeGood.values()[i]);
            buyButtons[i].setDisable(supply <= 0 || price > player.getCredits()
                    || !player.getShip().getCargoHold().hasSpace());
        }
        
        Button[] sellButtons = {
            sellWaterButton, sellFoodButton, sellFursButton, sellOreButton,
            sellGamesButton, sellFirearmsButton, sellMedicineButton,
            sellMachinesButton, sellNarcoticsButton, sellRobotsButton
        };
        this.sellButtons = sellButtons;
    
        for (int i = 0; i < sellButtons.length; i++) {
            int quantity = player.getShip().getCargoHold().getQuantity(TradeGood.values()[i]);
            sellButtons[i].setDisable(quantity <= 0);
        }
        
        marketNameLabel.setText("Market: " + player.getCurrentPlanet().getName()
                + ", " + player.getCurrentPlanet().technologyLevel());
        moneyLabel.setText("" + player.getCredits());
        sellPane.setVisible(false);
        buyButton.setDisable(true);
    }
    
    private void update() {
        for (int i = 0; i < buyQuantities.length; i++) {
            int quantity = player.getShip().getCargoHold().getQuantity(TradeGood.values()[i]);
            buyQuantities[i].setText(Integer.toString(quantity));
        }
        
        for (int i = 0; i < sellQuantities.length; i++) {
            int quantity = player.getShip().getCargoHold().getQuantity(TradeGood.values()[i]);
            sellQuantities[i].setText(Integer.toString(quantity));
        }
        
        for (int i = 0; i < buyButtons.length; i++) {
            int supply = market.getSupply(TradeGood.values()[i]);
            int price = market.getPrice(TradeGood.values()[i]);
            buyButtons[i].setDisable(supply <= 0 || price > player.getCredits()
                    || !player.getShip().getCargoHold().hasSpace());
        }
        
        for (int i = 0; i < sellButtons.length; i++) {
            int quantity = player.getShip().getCargoHold().getQuantity(TradeGood.values()[i]);
            sellButtons[i].setDisable(quantity <= 0);
        }
        
        moneyLabel.setText("" + player.getCredits());
    }
    
    @FXML
    void handleBuyButton() {
        sellPane.setVisible(false);
        buyButton.setDisable(true);
        buyPane.setVisible(true);
        sellButton.setDisable(false);
    }

    @FXML
    void handleSellButton() {
        buyPane.setVisible(false);
        sellButton.setDisable(true);
        sellPane.setVisible(true);
        buyButton.setDisable(false);
    }

    @FXML
    void buyFood() {
        market.buyGood(TradeGood.FOOD);
        update();
    }

    @FXML
    void buyWater() {
        market.buyGood(TradeGood.WATER);
        update();
    }

    @FXML
    void buyFurs() {
        market.buyGood(TradeGood.FURS);
        update();
    }

    @FXML
    void buyOre() {
        market.buyGood(TradeGood.ORE);
        update();
    }

    @FXML
    void buyGames() {
        market.buyGood(TradeGood.GAMES);
        update();
    }

    @FXML
    void buyFirearms() {
        market.buyGood(TradeGood.FIREARMS);
        update();
    }

    @FXML
    void buyMedicine() {
        market.buyGood(TradeGood.MEDICINE);
        update();
    }

    @FXML
    void buyMachines() {
        market.buyGood(TradeGood.MACHINES);
        update();
    }

    @FXML
    void buyNarcotics() {
        market.buyGood(TradeGood.NARCOTICS);
        update();
    }

    @FXML
    void buyRobots() {
        market.buyGood(TradeGood.ROBOTS);
        update();
    }

    @FXML
    void sellFood() {
        market.sellGood(TradeGood.FOOD);
        update();
    }

    @FXML
    void sellWater() {
        market.sellGood(TradeGood.WATER);
        update();
    }

    @FXML
    void sellFurs() {
        market.sellGood(TradeGood.FURS);
        update();
    }

    @FXML
    void sellOre() {
        market.sellGood(TradeGood.ORE);
        update();
    }

    @FXML
    void sellGames() {
        market.sellGood(TradeGood.GAMES);
        update();
    }

    @FXML
    void sellFirearms() {
        market.sellGood(TradeGood.FIREARMS);
        update();
    }

    @FXML
    void sellMedicine() {
        market.sellGood(TradeGood.MEDICINE);
        update();
    }

    @FXML
    void sellMachines() {
        market.sellGood(TradeGood.MACHINES);
        update();
    }

    @FXML
    void sellNarcotics() {
        market.sellGood(TradeGood.NARCOTICS);
        update();
    }

    @FXML
    void sellRobots() {
        market.sellGood(TradeGood.ROBOTS);
        update();
    }
    
    @FXML
    void returnToUniverse() {
        control.setScreen("SolarSystemMap");
    }
}
