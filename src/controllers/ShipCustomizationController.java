/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Gadget;
import models.Shield;
import models.Ship;
import models.Weapon;

/**
 *
 * @author Alex
 */
public class ShipCustomizationController implements Initializable {

    @FXML private AnchorPane weapons_anchor;
    @FXML private AnchorPane shields_anchor;
    @FXML private AnchorPane gadgets_anchor;
    @FXML private AnchorPane anchor;
    
    private ArrayList<Node> weaponControls;
    private ArrayList<Node> shieldControls;
    private ArrayList<Node> gadgetControls;
    
    private ArrayList<Label> weaponSlots;
    private ArrayList<Label> shieldSlots;
    private ArrayList<Label> gadgetSlots;
    
    private int weaponIndex;
    private int shieldIndex;
    private int gadgetIndex;
    
    private Ship ship;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ship = GameController.getGameData().getShip();
        
        weaponSlots = new ArrayList<>();
        shieldSlots = new ArrayList<>();
        gadgetSlots = new ArrayList<>();
        
        int y = 250;
        for(int i = 0; i < ship.getType().weaponSlots; i++) {
            Label weaponSlot = new Label();
            weaponSlot.setLayoutX(6);
            weaponSlot.setLayoutY(y);
            weaponSlot.setMinSize(192, 30);
            weaponSlot.setAlignment(Pos.CENTER);
            weaponSlot.setStyle(
                    "-fx-background-color: #000000;" +
                    "-fx-border-color: #444444;" +
                    "-fx-border-radius: 5;" +
                    "-fx-background-radius: 5;");
            anchor.getChildren().add(weaponSlot);
            weaponSlots.add(weaponSlot);
            
            y += 31;
        }
        
        for (Weapon w : ship.getWeapons()) {
            if (w.isActive()) {
                weaponSlots.get(weaponIndex).setText(w.getName());
                weaponIndex++;
            }
        }
        
        weaponControls = getWeaponButtons();
        weapons_anchor.getChildren().addAll(weaponControls);
        
        y = 250;
        for(int i = 0; i < ship.getType().shieldSlots; i++) {
            Label shieldSlot = new Label();
            shieldSlot.setLayoutX(204);
            shieldSlot.setLayoutY(y);
            shieldSlot.setMinSize(192, 30);
            shieldSlot.setAlignment(Pos.CENTER);
            shieldSlot.setStyle(
                    "-fx-background-color: #000000;" +
                    "-fx-border-color: #444444;" +
                    "-fx-border-radius: 5;" +
                    "-fx-background-radius: 5;");
            anchor.getChildren().add(shieldSlot);
            shieldSlots.add(shieldSlot);
            
            y += 31;
        }
        
        for (Shield s : ship.getShields()) {
            if (s.isActive()) {
                shieldSlots.get(shieldIndex).setText(s.getName());
                shieldIndex++;
            }
        }
        shieldControls = getShieldButtons();
        shields_anchor.getChildren().addAll(shieldControls);
        
        y = 250;
        for(int i = 0; i < ship.getType().gadgetSlots; i++) {
            Label gadgetSlot = new Label();
            gadgetSlot.setLayoutX(402);
            gadgetSlot.setLayoutY(y);
            gadgetSlot.setMinSize(192, 30);
            gadgetSlot.setAlignment(Pos.CENTER);
            gadgetSlot.setStyle(
                    "-fx-background-color: #000000;" +
                    "-fx-border-color: #444444;" +
                    "-fx-border-radius: 5;" +
                    "-fx-background-radius: 5;");
            anchor.getChildren().add(gadgetSlot);
            gadgetSlots.add(gadgetSlot);
            
            y += 31;
        }
        for (Gadget g : ship.getGadgets()) {
            if (g.isActive()) {
                gadgetSlots.get(gadgetIndex).setText(g.getName());
                gadgetIndex++;
            }
        }
        gadgetControls = getGadgetButtons();
        gadgets_anchor.getChildren().addAll(gadgetControls);
    }
    
    private ArrayList<Node> getWeaponButtons() {
        ArrayList<Node> nodes = new ArrayList<>();
        ArrayList<Weapon> weapons = GameController.getGameData().getShip().getWeapons();
        
        if (weapons.size() > 0) {
            int y = 0;
            for (Weapon w : weapons) {
                Button button = new Button(w.getName());
                button.setLayoutX(30);
                button.setLayoutY(y);
                button.setMinSize(142, 30);
                button.setDisable(ship.getType().weaponSlots == weaponIndex && !w.isActive());
                
                CheckBox checkBox = new CheckBox();
                checkBox.setLayoutX(5);
                checkBox.setLayoutY(y);
                checkBox.setMinSize(30, 30);
                checkBox.setMouseTransparent(true);
                
                if (w.isActive()) {
                    button.setId("active");
                    checkBox.setSelected(true);
                } else {
                    button.setId("inactive");
                }
                button.setOnMouseClicked((MouseEvent t) -> {
                    if (w.isActive()) {
                        w.deactivate(ship);
                        checkBox.setSelected(false);
                        button.setId("inactive");
                        
                        for (int i = 0; i < weaponIndex; i++) {
                            if (weaponSlots.get(i).getText().equals(w.getName())) {
                                if (i + 1 == weaponIndex) {
                                    weaponSlots.get(i).setText("");
                                } else {
                                    for (i += 1; i < weaponIndex; i++) {
                                        String str = weaponSlots.get(i).getText();
                                        weaponSlots.get(i - 1).setText(str);
                                        if (i + 1 == weaponIndex) {
                                            weaponSlots.get(i).setText("");
                                        }
                                    }
                                }
                            }
                        }
                        weaponIndex--;
                        for (Node n : weaponControls) {
                            if (n instanceof Button) {
                                n.setDisable(false);
                            }
                        }
                    } else {
                        w.activate(ship);
                        button.setId("active");
                        checkBox.setSelected(true);
                        weaponSlots.get(weaponIndex).setText(w.getName());
                        weaponIndex++;
                        if (weaponIndex == ship.getType().weaponSlots) {
                            for (Node n : weaponControls) {
                                if (n instanceof Button) {
                                    n.setDisable(n.getId().equals("inactive"));
                                    System.out.println(n.getId());
                                }
                            }
                        }
                    }
                });
                
                y += 31;
                nodes.add(button);
                nodes.add(checkBox);
            }
        }
        return nodes;
    }
    
    private ArrayList<Node> getShieldButtons() {
        ArrayList<Node> nodes = new ArrayList<>();
        ArrayList<Shield> shields = GameController.getGameData().getShip().getShields();
        
        if (shields.size() > 0) {
            int y = 0;
            for (Shield s : shields) {
                Button button = new Button(s.getName());
                button.setLayoutX(30);
                button.setLayoutY(y);
                button.setMinSize(142, 30);
                button.setDisable(ship.getType().shieldSlots == shieldIndex && !s.isActive());
                
                CheckBox checkBox = new CheckBox();
                checkBox.setLayoutX(5);
                checkBox.setLayoutY(y);
                checkBox.setMinSize(30, 30);
                checkBox.setMouseTransparent(true);
                
                if (s.isActive()) {
                    button.setId("active");
                    checkBox.setSelected(true);
                } else {
                    button.setId("inactive");
                }
                button.setOnMouseClicked((MouseEvent t) -> {
                    if (s.isActive()) {
                        s.deactivate(ship);
                        checkBox.setSelected(false);
                        button.setId("inactive");
                        
                        for (int i = 0; i < shieldIndex; i++) {
                            if (shieldSlots.get(i).getText().equals(s.getName())) {
                                if (i + 1 == shieldIndex) {
                                    shieldSlots.get(i).setText("");
                                } else {
                                    for (i += 1; i < shieldIndex; i++) {
                                        String str = shieldSlots.get(i).getText();
                                        shieldSlots.get(i - 1).setText(str);
                                        if (i + 1 == shieldIndex) {
                                            shieldSlots.get(i).setText("");
                                        }
                                    }
                                }
                            }
                        }
                        shieldIndex--;
                        for (Node n : shieldControls) {
                            if (n instanceof Button) {
                                n.setDisable(false);
                            }
                        }
                    } else {
                        s.activate(ship);
                        button.setId("active");
                        checkBox.setSelected(true);
                        shieldSlots.get(shieldIndex).setText(s.getName());
                        shieldIndex++;
                        if (shieldIndex == ship.getType().shieldSlots) {
                            for (Node n : shieldControls) {
                                if (n instanceof Button) {
                                    n.setDisable(n.getId().equals("inactive"));
                                    System.out.println(n.getId());
                                }
                            }
                        }
                    }
                });
                
                y += 31;
                nodes.add(button);
                nodes.add(checkBox);
            }
        }
        return nodes;
    }
    
    private ArrayList<Node> getGadgetButtons() {
        ArrayList<Node> nodes = new ArrayList<>();
        ArrayList<Gadget> gadgets = GameController.getGameData().getShip().getGadgets();
        
        if (gadgets.size() > 0) {
            int y = 0;
            for (Gadget g : gadgets) {
                Button button = new Button(g.getName());
                button.setLayoutX(30);
                button.setLayoutY(y);
                button.setMinSize(142, 30);
                button.setDisable(ship.getType().gadgetSlots == gadgetIndex && !g.isActive());
                
                CheckBox checkBox = new CheckBox();
                checkBox.setLayoutX(5);
                checkBox.setLayoutY(y);
                checkBox.setMinSize(30, 30);
                checkBox.setMouseTransparent(true);
                
                if (g.isActive()) {
                    button.setId("active");
                    checkBox.setSelected(true);
                } else {
                    button.setId("inactive");
                }
                button.setOnMouseClicked((MouseEvent t) -> {
                    if (g.isActive()) {
                        g.deactivate(ship);
                        checkBox.setSelected(false);
                        button.setId("inactive");
                        
                        for (int i = 0; i < gadgetIndex; i++) {
                            if (gadgetSlots.get(i).getText().equals(g.getName())) {
                                if (i + 1 == gadgetIndex) {
                                    gadgetSlots.get(i).setText("");
                                } else {
                                    for (i += 1; i < gadgetIndex; i++) {
                                        String str = gadgetSlots.get(i).getText();
                                        gadgetSlots.get(i - 1).setText(str);
                                        if (i + 1 == gadgetIndex) {
                                            gadgetSlots.get(i).setText("");
                                        }
                                    }
                                }
                            }
                        }
                        gadgetIndex--;
                        for (Node n : gadgetControls) {
                            if (n instanceof Button) {
                                n.setDisable(false);
                            }
                        }
                    } else {
                        g.activate(ship);
                        button.setId("active");
                        checkBox.setSelected(true);
                        gadgetSlots.get(gadgetIndex).setText(g.getName());
                        gadgetIndex++;
                        if (gadgetIndex == ship.getType().gadgetSlots) {
                            for (Node n : gadgetControls) {
                                if (n instanceof Button) {
                                    n.setDisable(n.getId().equals("inactive"));
                                    System.out.println(n.getId());
                                }
                            }
                        }
                    }
                });
                
                y += 31;
                nodes.add(button);
                nodes.add(checkBox);
            }
        }
        return nodes;
    }
    
    @FXML
    private void returnToSpacePort() {
        GameController.getControl().setScreen(Screens.SPACE_PORT);
    }
}
