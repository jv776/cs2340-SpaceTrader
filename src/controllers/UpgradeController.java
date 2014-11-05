package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Background;
import models.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class for buying upgrades for ship
 *
 * @author Taylor
 */
public class UpgradeController implements Initializable {

    @FXML
    private Button weaponButton;
    @FXML
    private Button shieldButton;
    @FXML
    private Button gadgetButton;
    @FXML
    private Label infoLabel;
    @FXML
    private Label weaponSlotLabel;
    @FXML
    private Label shieldSlotLabel;
    @FXML
    private Label gadgetSlotLabel;
    @FXML
    private Label playerCreditsLabel;
    @FXML
    private Label weaponCostLabel;
    @FXML
    private Label shieldCostLabel;
    @FXML
    private Label gadgetCostLabel;

    private int selectedItem;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createUpgrades();
        selectedItem = -1;
//        createUpgradeList();
//        reloadUpgradeList();
    }
    private void createUpgrades(){
        Weapon wup = GameController.getGameData().getPlanet().getUpgrade().getWeaponUpgrade();
        Shield sup = GameController.getGameData().getPlanet().getUpgrade().getShieldUpgrade();
        Gadget gup = GameController.getGameData().getPlanet().getUpgrade().getGadgetUpgrade();
        Ship ship = GameController.getGameData().getShip();
        weaponButton.setDisable(true);
        shieldButton.setDisable(true);
        gadgetButton.setDisable(true);
        if(wup!=null) {
            weaponButton.setText(wup.getName());
            weaponButton.setDisable(false);
            weaponCostLabel.setText("Cost: "+wup.getPrice());
        }
        if(sup!=null){
            shieldButton.setText(sup.getName());
            shieldButton.setDisable(false);
            shieldCostLabel.setText("Cost: "+sup.getPrice());
        }
        if(gup!=null){
            gadgetButton.setText(gup.getName());
            gadgetButton.setDisable(false);
            gadgetCostLabel.setText("Cost: "+gup.getPrice());
        }
        updateSlots();
        updatePrices();
    }


    public void onWeaponButtonClicked(){
//        weaponButton.setEffect(new Glow());
        infoLabel.setText(GameController.getGameData().getPlanet().getUpgrade().getWeaponUpgrade().toString());
        selectedItem = 0;
    }
    public void onShieldButtonClicked(){
//        shieldButton.setEffect(new Glow());
        infoLabel.setText(GameController.getGameData().getPlanet().getUpgrade().getShieldUpgrade().toString());
        selectedItem = 1;
    }
    public void onGadgetButtonClicked(){
//        gadgetButton.setEffect(new Glow());
        infoLabel.setText(GameController.getGameData().getPlanet().getUpgrade().getGadgetUpgrade().toString());
        selectedItem = 2;
    }

    private void updateSlots(){

        Ship ship = GameController.getGameData().getShip();
        if(ship.getWeapons().size()>=ship.getType().weaponSlots){
            weaponButton.setDisable(true);
        }
        if(ship.getShields().size()>=ship.getType().shieldSlots){
            shieldButton.setDisable(true);
        }
        if(ship.getGadgets().size()>=ship.getType().gadgetSlots){
            gadgetButton.setDisable(true);
        }

        weaponSlotLabel.setText(ship.getWeapons().size()+"/"+ship.getType().weaponSlots);
        shieldSlotLabel.setText(ship.getShields().size()+"/"+ship.getType().shieldSlots);
        gadgetSlotLabel.setText(ship.getGadgets().size()+"/"+ship.getType().gadgetSlots);

    }

    private void updatePrices(){
        Weapon wup = GameController.getGameData().getPlanet().getUpgrade().getWeaponUpgrade();
        Shield sup = GameController.getGameData().getPlanet().getUpgrade().getShieldUpgrade();
        Gadget gup = GameController.getGameData().getPlanet().getUpgrade().getGadgetUpgrade();
        if(wup!=null && GameController.getGameData().getPlayer().getCredits() <wup.getPrice()){
            weaponButton.setDisable(true);
        }
        if(sup!=null && GameController.getGameData().getPlayer().getCredits() <sup.getPrice()){
            shieldButton.setDisable(true);
        }
        if(gup!=null && GameController.getGameData().getPlayer().getCredits() <gup.getPrice()){
            gadgetButton.setDisable(true);
        }
        playerCreditsLabel.setText("Credits: "+GameController.getGameData().getPlayer().getCredits());

    }
    public void onBuyButtonClicked(){
        Ship ship = GameController.getGameData().getShip();
        Upgradeplace upgrades = GameController.getGameData().getPlanet().getUpgrade();
        if(selectedItem == 0){
            ship.addWeapon(upgrades.getWeaponUpgrade());
            GameController.getGameData().getPlayer().spend(upgrades.getWeaponUpgrade().getPrice());
        } else if (selectedItem==1){
            ship.addShield(upgrades.getShieldUpgrade());
            GameController.getGameData().getPlayer().spend(upgrades.getShieldUpgrade().getPrice());
        }else if (selectedItem==2){
            ship.addGadget(upgrades.getGadgetUpgrade());
            GameController.getGameData().getPlayer().spend(upgrades.getGadgetUpgrade().getPrice());
        }
        selectedItem = -1;

        createUpgrades();

    }
    public void onReturnButtonClicked(){
        GameController.getControl().setScreen(Screens.SHIP_YARD);
    }


}
