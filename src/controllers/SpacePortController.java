package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author Roi Atalla
 */
public class SpacePortController extends GameController {
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
    
    public void onMarketClicked() {
        control.setScreen(Screens.MARKET.getName());
    }
    
    public void onShipYardClicked() {
        System.out.println("SHIP YARD CLICKED WOOOOOO!");
    }
    
    public void onMapClicked() {
        control.setScreen(Screens.UNIVERSE_MAP.getName());
    }
    
    public void onNewsClicked() {
        System.out.println("NEWS CLICKED WOOOOO!");
    }
    
    public void onStockMarketClicked() {
        System.out.println("STOCK MARKET CLICKED WOOOO!");
    }
}
