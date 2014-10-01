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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import models.CargoHold;
import models.Market;
import models.TradeGood;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class MarketController extends GameController implements Initializable {

    public GridPane buyPane;
    public GridPane sellPane;
    
    public Button buyButton, sellButton;
    
    public Label marketNameLabel;
    public Label moneyLabel;

    public Button buyGood1, buyGood2, buyGood3, buyGood4, buyGood5,
            buyGood6, buyGood7, buyGood8, buyGood9, buyGood10,
            sellGood1, sellGood2, sellGood3, sellGood4, sellGood5,
            sellGood6, sellGood7, sellGood8, sellGood9, sellGood10;

    public Label buyValue1, buyValue2, buyValue3, buyValue4, buyValue5,
            buyValue6, buyValue7, buyValue8, buyValue9, buyValue10,
            sellValue1, sellValue2, sellValue3, sellValue4, sellValue5,
            sellValue6, sellValue7, sellValue8, sellValue9, sellValue10;
    
    public Label buyQuantity1, buyQuantity2, buyQuantity3, buyQuantity4, buyQuantity5,
            buyQuantity6, buyQuantity7, buyQuantity8, buyQuantity9, buyQuantity10,
            sellQuantity1, sellQuantity2, sellQuantity3, sellQuantity4, sellQuantity5,
            sellQuantity6, sellQuantity7, sellQuantity8, sellQuantity9, sellQuantity10;
    
    private Button[] buyButtons;
    private Button[] sellButtons;
    private Label[] buyValues;
    private Label[] buyQuantities;
    private Label[] sellValues;
    private Label[] sellQuantities;
    
    private CargoHold cargo;
    
    private ArrayList<TradeGood> buyList;
    private ArrayList<TradeGood> sellList;
    
    private Market market;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buyList = new ArrayList();
        sellList = new ArrayList();
        cargo = gameData.getCargoHold();
        Button[] buyButtons = {
            buyGood1, buyGood2, buyGood3, buyGood4, buyGood5,
            buyGood6, buyGood7, buyGood8, buyGood9, buyGood10
        };
        this.buyButtons = buyButtons;
           
        Button[] sellButtons = {
            sellGood1, sellGood2, sellGood3, sellGood4, sellGood5,
            sellGood6, sellGood7, sellGood8, sellGood9, sellGood10
        };
        this.sellButtons = sellButtons;
    
        Label[] buyQuantities = {
            buyQuantity1, buyQuantity2, buyQuantity3, buyQuantity4, buyQuantity5,
            buyQuantity6, buyQuantity7, buyQuantity8, buyQuantity9, buyQuantity10
        };
        this.buyQuantities = buyQuantities;
            
        Label[] buyValues = {
            buyValue1, buyValue2, buyValue3, buyValue4, buyValue5,
            buyValue6, buyValue7, buyValue8, buyValue9, buyValue10
        };
        this.buyValues = buyValues;
    
        Label[] sellQuantities = {
            sellQuantity1, sellQuantity2, sellQuantity3, sellQuantity4, sellQuantity5,
            sellQuantity6, sellQuantity7, sellQuantity8, sellQuantity9, sellQuantity10
        };
        this.sellQuantities = sellQuantities;
    
        Label[] sellValues = {
            sellValue1, sellValue2, sellValue3, sellValue4, sellValue5,
            sellValue6, sellValue7, sellValue8, sellValue9, sellValue10
        };
        this.sellValues = sellValues;
        
        marketNameLabel.setText("Market: " + gameData.getPlanet().getName());
        moneyLabel.setText("" + gameData.getPlayer().getMoney());
        sellPane.setVisible(false);
        buyButton.setDisable(true);
        market = new Market(gameData.getPlanet());
        configureBuyPane();
        updateSellPane();
    }
    
    public void handleBuyButton() {
        buyPane.setVisible(true);
        sellPane.setVisible(false);
        sellButton.setDisable(false);
        buyButton.setDisable(true);
    }
    
    public void handleSellButton() {
        buyPane.setVisible(false);
        sellPane.setVisible(true);
        buyButton.setDisable(false);
        sellButton.setDisable(true);
    }
    
    public void buyGood1() { buyGood(buyList.get(0), buyValue1, buyQuantity1); }
    public void buyGood2() { buyGood(buyList.get(1), buyValue2, buyQuantity2); }
    public void buyGood3() { buyGood(buyList.get(2), buyValue3, buyQuantity3); }
    public void buyGood4() { buyGood(buyList.get(3), buyValue4, buyQuantity4); }
    public void buyGood5() { buyGood(buyList.get(4), buyValue5, buyQuantity5); }
    public void buyGood6() { buyGood(buyList.get(5), buyValue6, buyQuantity6); }
    public void buyGood7() { buyGood(buyList.get(6), buyValue7, buyQuantity7); }
    public void buyGood8() { buyGood(buyList.get(7), buyValue8, buyQuantity8); }
    public void buyGood9() { buyGood(buyList.get(8), buyValue9, buyQuantity9); }
    public void buyGood10() { buyGood(buyList.get(9), buyValue10, buyQuantity10); }
    
    public void sellGood1() { sellGood(sellList.get(0), sellValue1, sellQuantity1); }
    public void sellGood2() { sellGood(sellList.get(1), sellValue2, sellQuantity2); }
    public void sellGood3() { sellGood(sellList.get(2), sellValue3, sellQuantity3); }
    public void sellGood4() { sellGood(sellList.get(3), sellValue4, sellQuantity4); }
    public void sellGood5() { sellGood(sellList.get(4), sellValue5, sellQuantity5); }
    public void sellGood6() { sellGood(sellList.get(5), sellValue6, sellQuantity6); }
    public void sellGood7() { sellGood(sellList.get(6), sellValue7, sellQuantity7); }
    public void sellGood8() { sellGood(sellList.get(7), sellValue8, sellQuantity8); }
    public void sellGood9() { sellGood(sellList.get(8), sellValue9, sellQuantity9); }
    public void sellGood10() { sellGood(sellList.get(9), sellValue10, sellQuantity10); }
    
    private void configureBuyPane() {
        TradeGood[] buyGoods = market.getBuyableGoods();
        
        int i = 0;
        while (i < buyGoods.length) {
            buyButtons[i].setText(buyGoods[i].getItemName());
            buyList.add(buyGoods[i]);
            i++;
        }
        while (i < buyButtons.length) {
            buyButtons[i].setDisable(true);
            i++;
        }
        
        int j = 0;
        while (j < buyList.size()) {
            buyQuantities[j].setText("" + 
                    (cargo.get(buyList.get(j)) == null ? 0 : cargo.get(buyList.get(j))));
            j++;
        }
        while (j < buyQuantities.length) {
            buyQuantities[j].setDisable(true);
            j++;
        }

        
        int k = 0;
        while(k < buyList.size()) {
            buyValues[k].setText("" + buyList.get(k).computeCost(gameData.getPlanet()));
            k++;
        }
        while (k < buyValues.length) {
            buyValues[k].setDisable(true);
            k++;
        }
    }
    
    private void updateSellPane() {
        sellList = new ArrayList();
        TradeGood[] sellableGoods = market.getSellableGoods();
        ArrayList<TradeGood> sellGoods = new ArrayList(Arrays.asList(sellableGoods));
        
        int j = 0;
        int count = 0;
        TradeGood[] playerGoods = cargo.getTradeGoods();
        while (j < playerGoods.length) {
            if (sellGoods.contains(playerGoods[j])) {
                sellButtons[count].setText(playerGoods[j].getItemName());
                sellButtons[count].setDisable(false);
                sellList.add(playerGoods[j]);
                count++;
            }
            j++;
        }
        while (count < sellButtons.length) {
            sellButtons[count].setDisable(true);
            sellButtons[count].setText("");
            count++;
        }
        
        int k = 0;
        while (k < sellList.size()) {
            sellQuantities[k].setText("" + cargo.get(sellList.get(k)));
            sellQuantities[k].setDisable(false);
            k++;
        }
        while (k < sellQuantities.length) {
            sellQuantities[k].setDisable(true);
            sellQuantities[k].setText("");
            k++;
        }
        
        int m = 0;
        while (m < sellList.size()) {
            sellValues[m].setText("" 
                    + sellList.get(m).computeCost(gameData.getPlanet()) * 2 / 3);
            sellValues[m].setDisable(false);
            m++;
        }
        
        while (m < sellValues.length) {
            sellValues[m].setDisable(true);
            sellValues[m].setText("");
            m++;
        }
    }
    
    private void buyGood(TradeGood good, Label cost, Label quantity) {
        if (gameData.getPlayer().getMoney() >= Integer.parseInt(cost.getText())) {
            gameData.getPlayer().spend(Integer.parseInt(cost.getText()));
            cargo.addItem(good);
            quantity.setText("" + cargo.getQuantity(good));
            updateSellPane();
            updateMoney();
        } else {
            
        }
    }
    
    private void sellGood(TradeGood good, Label cost, Label quantity) {
        gameData.getPlayer().earn(Integer.parseInt(cost.getText()));
        cargo.removeItem(good);
        if (cargo.getQuantity(good) != 0) {
            quantity.setText("" + cargo.getQuantity(good));
        } else {
            updateSellPane();
        }
        updateMoney();
    }
    
    private void updateMoney() {
        moneyLabel.setText("" + gameData.getPlayer().getMoney());
    }
    
    class CargoItem {
        private TradeGood good;
        private int quantity;
        
        public CargoItem(TradeGood tg, int q) {
            good = tg;
            quantity = q;
        }
        
        public TradeGood getTradeGood() {
            return good;
        }
        
        public int getQuantity() {
            return quantity;
        }
    }
}
