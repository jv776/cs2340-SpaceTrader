package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.Ship;

import java.net.URL;
import java.util.Arrays;
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

    @FXML
    private ListView<String> shipList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Ship ship = GameController.getGameData().getShip();
        setLabels(ship);

        ObservableList<String> shipNames = FXCollections.observableArrayList(Arrays.stream(Ship.Type.values())
                .filter((Ship.Type type) -> type != ship.getType() && GameController.getGameData().getSolarSystem().getTechLevel().ordinal() >= type.minTechLevel.ordinal())
                .map(Ship.Type::toString).toArray(String[]::new));
        shipNames.add(0, "Your ship: " + ship.getType());
        shipList.setItems(shipNames);
    }

    private void setLabels(Ship ship) {
        shipTypeLabel.setText(ship.getType().toString());
        hullStrengthLabel.setText(ship.getHullStrength() + "/" + ship.getMaxHullStrength());
        cargoBaysLabel.setText(ship.getCargoHold().getQuantity() + "/" + ship.getCargoHold().getCapacity());
        //weaponSlotsLabel.setText
        //shieldSlotsLabel.setText
        //gadgetSlotsLabel.setText
        //crewLabel.setText
        rangeLabel.setText((int) Math.round(ship.getFuelAmount()) + "/" + ship.getFuelCapacity());
    }

    private void setLabels(Ship.Type shipType) {
        shipTypeLabel.setText(shipType.toString());
        hullStrengthLabel.setText(String.valueOf(shipType.hullStrength));
        cargoBaysLabel.setText(String.valueOf(shipType.cargoCapacity));
        weaponSlotsLabel.setText(String.valueOf(shipType.weaponSlots));
        shieldSlotsLabel.setText(String.valueOf(shipType.shieldSlots));
        gadgetSlotsLabel.setText(String.valueOf(shipType.gadgetSlots));
        crewLabel.setText(String.valueOf(shipType.crewCapacity));
        rangeLabel.setText(String.valueOf(shipType.fuelCapacity));
    }

    public void onBackToSpacePort() {
        GameController.getControl().setScreen(Screens.SPACE_PORT);
    }

    public void onShipListSelectionChange() {
        String selectedItem = shipList.getSelectionModel().getSelectedItem();

        if(selectedItem.contains("Your ship:")) {
            setLabels(GameController.getGameData().getShip());
        } else {
            setLabels(Ship.Type.valueOf(selectedItem));
        }
    }
}
