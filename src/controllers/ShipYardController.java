package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
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
    private Button buyShipButton;

    @FXML
    private Label creditsLabel;

    @FXML
    private ListView<String> shipList;

//    @FXML
//    private ListView<String> upgradeList;
//
//    private ObservableList<String> upgradeNames;
//    private ArrayList<Upgrade> upgrades;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reloadShipList();
//        createUpgradeList();
//        reloadUpgradeList();
    }

    private void reloadShipList() {
        Ship ship = GameController.getGameData().getShip();
        setLabels(ship);

        ObservableList<String> shipNames = FXCollections.observableArrayList(Arrays.stream(Ship.Type.values())
                .filter((Ship.Type type) -> type != ship.getType() && GameController.getGameData().getSolarSystem().getTechLevel().ordinal() >= type.minTechLevel.ordinal())
                .map(s -> s.toString() + " - " + s.price + " credits").toArray(String[]::new));
        shipNames.add(0, "Your ship: " + ship.getType() + " - " + ship.getType().price + " credits");
        shipList.setItems(shipNames);
        shipList.getSelectionModel().clearSelection();

        creditsLabel.setText(String.valueOf(GameController.getGameData().getPlayer().getCredits()));
    }

//    private void createUpgradeList(){
//        Random rand = new Random();
//        upgrades = new ArrayList<Upgrade>();
//        for (int i=0; i<rand.nextInt(2)+1;i++){
//            upgrades.add(new Weapon(Arrays.stream(Weapon.Type.values())
//                    .filter((Weapon.Type type) ->  GameController.getGameData().getSolarSystem().getTechLevel().ordinal() == type.minTechLevel.ordinal()).findAny().get()));
//        }
//        for (int i=0; i<rand.nextInt(1)+1;i++){
//            upgrades.add(new Weapon(Arrays.stream(Weapon.Type.values())
//                    .filter((Weapon.Type type) ->  GameController.getGameData().getSolarSystem().getTechLevel().ordinal() == type.minTechLevel.ordinal()).findAny().get()));
//        }
//        for (int i=0; i<rand.nextInt(1)+1;i++){
//            upgrades.add(new Weapon(Arrays.stream(Weapon.Type.values())
//                    .filter((Weapon.Type type) ->  GameController.getGameData().getSolarSystem().getTechLevel().ordinal() == type.minTechLevel.ordinal()).findAny().get()));
//        }
//        String[] names = new String[upgrades.size()];
//        for(int i=0; i<upgrades.size();i++){
//            names[i] = upgrades.get(i).getName();
//        }
//        ObservableList<String> upgradeNames = FXCollections.observableArrayList(names);
//        System.out.println(upgradeNames);
//        upgradeList.setItems(upgradeNames);
//        shipList.getSelectionModel().clearSelection();
//        creditsLabel.setText(String.valueOf(GameController.getGameData().getPlayer().getCredits()));
//
//
////        upgradeNames = FXCollections.observableArrayList(Arrays.stream(upgrades.stream().map(s -> s.toString() + " - " + s.getPrice() + " credits").toArray(String[]::new)));
////        ObservableList<String> weaponNames = FXCollections.observableArrayList(Arrays.stream(Weapon.Type.values())
////                .filter((Weapon.Type type) -> GameController.getGameData().getSolarSystem().getTechLevel().ordinal() >= type.minTechLevel.ordinal())
////                .map(s -> s.toString() + " - " + s.price + " credits").toArray(String[]::new));
////        ObservableList<String> shieldNames = FXCollections.observableArrayList(Arrays.stream(Shield.Type.values())
////                .filter((Shield.Type type) -> GameController.getGameData().getSolarSystem().getTechLevel().ordinal() >= type.minTechLevel.ordinal())
////                .map(s -> s.toString() + " - " + s.price + " credits").toArray(String[]::new));
////        ObservableList<String> gadgetNames = FXCollections.observableArrayList(Arrays.stream(Gadget.Type.values())
////                .filter((Gadget.Type type) -> GameController.getGameData().getSolarSystem().getTechLevel().ordinal() >= type.minTechLevel.ordinal())
////                .map(s -> s.toString() + " - " + s.price + " credits").toArray(String[]::new));
////        upgradeNames.addAll(weaponNames);
////        upgradeNames.addAll(shieldNames);
////        upgradeNames.addAll(gadgetNames);
//
//    }
//    private void reloadUpgradeList() {
//        Ship ship = GameController.getGameData().getShip();
//        setLabels(ship);
//
//
//
//        upgradeList.setItems(upgradeNames);
//        upgradeList.getSelectionModel().clearSelection();
//
//        creditsLabel.setText(String.valueOf(GameController.getGameData().getPlayer().getCredits()));
//    }

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

        if(selectedItem == null)
            return;

        if(selectedItem.contains("Your ship:")) {
            setLabels(GameController.getGameData().getShip());
            buyShipButton.setVisible(false);
        } else {
            Ship.Type shipSelected = Ship.Type.valueOf(selectedItem.substring(0, selectedItem.indexOf('-') - 1));
            setLabels(shipSelected);

            int currentShipPrice = GameController.getGameData().getShip().getType().price;
            int selectedShipPrice = shipSelected.price;
            int credits = GameController.getGameData().getPlayer().getCredits();

            buyShipButton.setVisible(true);
            buyShipButton.setDisable(selectedShipPrice > credits + currentShipPrice);
        }
    }

    public void onBuyShipClicked() {
        String selectedItem = shipList.getSelectionModel().getSelectedItem();

        if(selectedItem == null)
            return;

        if(selectedItem.contains("Your ship:")) {
            throw new IllegalStateException("Wtf?!");
        } else {
            Ship.Type shipSelected = Ship.Type.valueOf(selectedItem.substring(0, selectedItem.indexOf('-') - 1));

            Player player = GameController.getGameData().getPlayer();

            int oldShipPrice = player.getShip().getType().price;
            int selectedShipPrice = shipSelected.price;
            int credits = player.getCredits();

            if(selectedShipPrice > credits + oldShipPrice)
                throw new IllegalStateException("Wtf?!");

            player.earn(oldShipPrice);
            player.spend(selectedShipPrice);
            
            GameController.getGameData().setShip(new Ship(shipSelected, GameController.getGameData().getPlayer()));

            buyShipButton.setVisible(false);
            
            reloadShipList();
        }
    }
    public void onBuyUpgrades(){
        GameController.getControl().setScreen(Screens.UPGRADE);
    }
}
