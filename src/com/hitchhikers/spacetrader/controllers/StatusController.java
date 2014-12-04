package com.hitchhikers.spacetrader.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import com.hitchhikers.spacetrader.models.CargoItem;
import com.hitchhikers.spacetrader.models.Gadget;
import com.hitchhikers.spacetrader.models.Mercenary;
import com.hitchhikers.spacetrader.models.Player;
import com.hitchhikers.spacetrader.models.Shield;
import com.hitchhikers.spacetrader.models.Ship;
import com.hitchhikers.spacetrader.models.Weapon;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class StatusController implements Initializable {
    
    private Player player;
    private Ship ship;
    
    @FXML
    private AnchorPane anchor;

    @FXML
    private VBox cargo_anchor, weapons_anchor, shields_anchor,
            gadgets_anchor, crew_anchor;

    @FXML
    private Label cargo_quantity, weapons_quantity, shields_quantity, 
            gadgets_quantity, crew_quantity, name_label, worth_label,
            bounty_label, credits_label, loan_label;
    
    @FXML 
    private Label pilot_skill, fighter_skill, engineer_skill,
            trader_skill, investor_skill, ship_name;
    
    @FXML
    private ImageView ship_image;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        player = GameController.getGameData().getPlayer();
        ship = player.getShip();
        
        name_label.setText(player.getName());
        worth_label.setText("" + player.getTotalCredits());
        bounty_label.setText("" + player.getBounty());
        credits_label.setText("" + player.getCredits());
        if (player.getBank().hasOpenLoan()) {
            loan_label.setText("" + player.getBank().getLoan().getPaidOff() 
                + " / " + player.getBank().getLoan().getTotal());
        } else {
            loan_label.setText("No current loan");
        }
        
        ship_image.setImage(player.getShip().getImage());
        
        for (CargoItem item : ship.getCargoHold().getCargoItems()) {
            for (int i = 0; i < ship.getCargoHold().getQuantity(item); i++) {
                Label good = new Label(item.getItemName());
                cargo_anchor.getChildren().add(good);
            }
        }
        
        for (Weapon weapon : ship.getEquippedWeapons()) {
            Label good = new Label(weapon.getName());
            weapons_anchor.getChildren().add(good);
        }
        
        for (Shield shield : ship.getEquippedShields()) {
            Label good = new Label(shield.getName());
            shields_anchor.getChildren().add(good);
        }
        
        for (Gadget gadget : ship.getEquippedGadgets()) {
            Label good = new Label(gadget.getName());
            gadgets_anchor.getChildren().add(good);
        }
        
        for (Mercenary merc : ship.getCrew()) {
            Label good = new Label(merc.getInfo());
            crew_anchor.getChildren().add(good);
        }
        
        pilot_skill.setText("" + player.getPilotSkillPoints());
        fighter_skill.setText("" + player.getFighterSkillPoints());
        engineer_skill.setText("" + player.getEngineerSkillPoints());
        trader_skill.setText("" + player.getTraderSkillPoints());
        investor_skill.setText("" + player.getInvestorSkillPoints());
        
        ship_name.setText(ship.getType().toString());
        ship_image = new ImageView(player.getShip().getImage());
        
        cargo_quantity.setText(ship.getCargoHold().getQuantity() + " / " + ship.getCargoHold().getCapacity());
        weapons_quantity.setText(ship.getNumWeapons() + " / " + ship.getWeaponsCapacity());
        shields_quantity.setText(ship.getNumShields() + " / " + ship.getShieldsCapacity());
        gadgets_quantity.setText(ship.getNumGadgets() + " / " + ship.getGadgetsCapacity());
        crew_quantity.setText(ship.getCrewSize() + " / " + ship.getMaxCrew());
    }
    
    @FXML
    private void returnToMap() {
        GameController.getControl().setScreen(Screens.UNIVERSE_MAP);
    }
    
}
