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
 * Created by limbic on 11/5/14.
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

    private int selectedItem;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createUpgrades();
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
        }
        if(sup!=null){
            shieldButton.setText(sup.getName());
            shieldButton.setDisable(false);
        }
        if(gup!=null){
            gadgetButton.setText(gup.getName());
            gadgetButton.setDisable(false);
        }
        updateSlots();
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

    public void onBuyButtonClicked(){
        Ship ship = GameController.getGameData().getShip();
        Upgradeplace upgrades = GameController.getGameData().getPlanet().getUpgrade();
        if(selectedItem == 0){
            ship.addWeapon(upgrades.getWeaponUpgrade());
        } else if (selectedItem==1){
            ship.addShield(upgrades.getShieldUpgrade());
        }else if (selectedItem==2){
            ship.addGadget(upgrades.getGadgetUpgrade());
        }
        selectedItem = -1;
        createUpgrades();

    }
    public void onReturnButtonClicked(){
        GameController.getControl().setScreen(Screens.SHIP_YARD);
    }


}
