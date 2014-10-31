package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.Ship;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.CargoItem;
import models.Ship.Type;
import models.Shipyard;

/**
 * @author Roi Atalla
 */
public class ShipYardController implements Initializable {
    private Shipyard shipyard;
    private Type typeSelected;
    
    @FXML AnchorPane ship_yard_anchor;
    
    @FXML
    private Label money_label;
    
    @FXML
    private Label shipTypeLabel;
    
    @FXML
    private Label hullStrengthLabel;
    
    @FXML
    private Label cargoBaysLabel;
    
    @FXML
    private Label weaponSlotsLabel;
    
    @FXML
    private Label shieldSlotsLabel;
    
    @FXML
    private Label gadgetSlotsLabel;
    
    @FXML
    private Label crewLabel;
    
    @FXML
    private Label rangeLabel;
    
    @FXML
    private Button buy_ship;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        shipyard = new Shipyard(GameController.getGameData().getPlanet());
        money_label.setText(GameController.getGameData().getPlayer().getCredits() + "");
        createHeaders();
        createShips();
    }
    
    private void setLabels(Type ship) {
        shipTypeLabel.setText(ship.name());
        hullStrengthLabel.setText("" + ship.hullStrength);
        cargoBaysLabel.setText("" + ship.cargoCapacity);
        weaponSlotsLabel.setText("" + ship.weaponSlots);
        shieldSlotsLabel.setText("" + ship.shieldSlots);
        gadgetSlotsLabel.setText("" + ship.gadgetSlots);
        crewLabel.setText("" + ship.crewCapacity);
        rangeLabel.setText("" + ship.fuelCapacity);
    }
    
    private void createHeaders() {
        Label shipHeader = new Label("Ship");
        shipHeader.setLayoutX(50);
        shipHeader.setLayoutY(100);
        shipHeader.setMinSize(150, 30);
        shipHeader.setAlignment(Pos.CENTER);
        ship_yard_anchor.getChildren().add(shipHeader);
        
        Label price = new Label("Price");
        price.setLayoutX(250);
        price.setLayoutY(100);
        price.setMinSize(100, 30);
        price.setAlignment(Pos.CENTER);
        ship_yard_anchor.getChildren().add(price);
    }
    
    private void createShips() {
        HashMap<Type, Integer> ships = shipyard.getShips();
        int y = 100;
        
        for (Type ship : ships.keySet()) {
            Button shipName = new Button(ship.name());
            shipName.setLayoutX(50);
            shipName.setLayoutY(y += 31);
            shipName.setMinSize(150, 30);
            shipName.setOnMouseClicked((MouseEvent t) -> {
                setLabels(ship);
                typeSelected = ship;
                if (ships.get(ship) > GameController.getGameData().getPlayer().getCredits()
                        || shipyard.ownsShip(ship, GameController.getGameData().getPlayer())) {
                    buy_ship.setDisable(true);
                } else {
                    buy_ship.setDisable(false);
                }
            });
            ship_yard_anchor.getChildren().add(shipName);
            
            Label price = new Label(ships.get(ship).toString());
            price.setLayoutX(250);
            price.setLayoutY(y);
            price.setMinSize(100, 30);
            price.setStyle(
                    "-fx-background-color: #000000;" +
                    "-fx-border-color: #444444;" +
                    "-fx-border-radius: 5;" +
                    "-fx-background-radius: 5;");
            ship_yard_anchor.getChildren().add(price);
            
            if (shipyard.ownsShip(ship, GameController.getGameData().getPlayer())) {
                ImageView image = new ImageView(new Image("/images/star.png"));
                image.setLayoutX(28);
                image.setLayoutY(y + 5);
                Tooltip currentShip = new Tooltip("Current Ship");
                Tooltip.install(image, currentShip);
                ship_yard_anchor.getChildren().add(image);
            }
        }
    }
    
    @FXML
    private void returnToSpacePort() {
        GameController.getControl().setScreen(Screens.SPACE_PORT);
    }
    
    @FXML
    private void purchaseShip() {
        Ship currentShip = GameController.getGameData().getShip();
        GameController.getGameData().getPlayer().spend(typeSelected.price);
        Ship ship = new Ship(typeSelected, GameController.getGameData().getPlayer());
        
        HashMap<CargoItem, Integer> currentCargo = currentShip.getCargoHold().getCargo();
        for (CargoItem item : currentCargo.keySet()) {
            ship.getCargoHold().addItemQuantity(item, Math.min(ship.getType().cargoCapacity
                - ship.getCargoHold().getCargoQuantity(), currentCargo.get(item)));
        }
        
        GameController.getGameData().getPlayer().setShip(ship);
        
        returnToSpacePort();
    }
}
