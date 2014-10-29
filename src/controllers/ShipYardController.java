package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.Ship;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Roi Atalla
 */
public class ShipYardController implements Initializable {
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
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Ship ship = GameController.getGameData().getShip();
        setLabels(ship);
    }
    
    private void setLabels(Ship ship) {
        shipTypeLabel.setText(ship.getType().toString());
        hullStrengthLabel.setText(ship.getHullStrength() + "/" + ship.getMaxHullStrength());
        cargoBaysLabel.setText(ship.getCargoHold().getQuantity() + "/" + ship.getCargoHold().getCapacity());
        //weaponSlotsLabel.setText
        //shieldSlotsLabel.setText
        //gadgetSlotsLabel.setText
        //crewLabel.setText
        rangeLabel.setText((int)Math.round(ship.getFuelAmount()) + "/" + ship.getFuelCapacity());
    }
}
