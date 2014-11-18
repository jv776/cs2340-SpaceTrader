package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.Ship;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import models.CargoItem;
import models.Gadget;
import models.Shield;
import models.Shipyard;
import models.Weapon;

/**
 * @author Roi Atalla
 */
public class ShipYardController implements Initializable {
    private Shipyard shipyard;
    private Ship.Type typeSelected;
    
    @FXML AnchorPane ship_yard_anchor;
    
    @FXML
    private Label money_label;
    
    @FXML
    private Label shipTypeLabel, shipTypeLabel2;
    
    @FXML
    private Label hullStrengthLabel, hullStrengthLabel2;
    
    @FXML
    private Label cargoBaysLabel, cargoBaysLabel2;
    
    @FXML
    private Label weaponSlotsLabel, weaponSlotsLabel2;
    
    @FXML
    private Label shieldSlotsLabel, shieldSlotsLabel2;
    
    @FXML
    private Label gadgetSlotsLabel, gadgetSlotsLabel2;
    
    @FXML
    private Label crewLabel, crewLabel2;
    
    @FXML
    private Label rangeLabel, rangeLabel2;
    
    @FXML
    private Button buy_ship;
    
    @FXML
    private Button shipsButton;
    
    @FXML 
    private Button weaponsButton;
    
    @FXML
    private Button gadgetsButton;
    
    @FXML
    private Button shieldsButton;
    
    private ArrayList<Node> shipControls;
    
    private ArrayList<Node> weaponControls;
    
    private ArrayList<Node> shieldControls;
    
    private ArrayList<Node> gadgetControls;
    
    private Button[] menuButtons;
    
    private ImageView image;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Node[] controls = {shipTypeLabel, hullStrengthLabel, cargoBaysLabel,
            weaponSlotsLabel, shieldSlotsLabel, gadgetSlotsLabel, crewLabel,
            rangeLabel, buy_ship, shipTypeLabel2, hullStrengthLabel2, cargoBaysLabel2,
            weaponSlotsLabel2, shieldSlotsLabel2, gadgetSlotsLabel2, crewLabel2,
            rangeLabel2
        };
        
        shipyard = new Shipyard(GameController.getGameData().getPlanet());
        
        this.shipControls = new ArrayList<>(Arrays.asList(controls));
        shipControls.addAll(createHeaders("Ship"));
        createShips();
        
        this.weaponControls = createWeapons();
        weaponControls.addAll(createHeaders("Weapon"));
        
        this.shieldControls = createShields();
        shieldControls.addAll(createHeaders("Shield"));
        
        this.gadgetControls = createGadgets();
        gadgetControls.addAll(createHeaders("Gadget"));
        
        for (Node c : shipControls) {
            c.setOpacity(0.0);
            c.setMouseTransparent(true);
        }
        
        for (Node c : weaponControls) {
            c.setOpacity(0.0);
            c.setMouseTransparent(true);
        }
        
        for (Node c : shieldControls) {
            c.setOpacity(0.0);
            c.setMouseTransparent(true);
        }
        
        for (Node c : gadgetControls) {
            c.setOpacity(0.0);
            c.setMouseTransparent(true);
        }
        
        Button[] buttons = {shipsButton, weaponsButton, shieldsButton, gadgetsButton};
        menuButtons = buttons;
        money_label.setText(GameController.getGameData().getPlayer().getCredits() + "");
    }
    
    private void setLabels(Ship.Type ship) {
        shipTypeLabel.setText(ship.name());
        hullStrengthLabel.setText("" + ship.hullStrength);
        cargoBaysLabel.setText("" + ship.cargoCapacity);
        weaponSlotsLabel.setText("" + ship.weaponSlots);
        shieldSlotsLabel.setText("" + ship.shieldSlots);
        gadgetSlotsLabel.setText("" + ship.gadgetSlots);
        crewLabel.setText("" + ship.crewCapacity);
        rangeLabel.setText("" + ship.fuelCapacity);
    }
    
    private ArrayList<Node> createHeaders(String nameHeader) {
        ArrayList<Node> controls = new  ArrayList<>();
        Label header = new Label(nameHeader);
        header.setLayoutX(50);
        header.setLayoutY(100);
        header.setMinSize(150, 30);
        header.setAlignment(Pos.CENTER);
        
        ship_yard_anchor.getChildren().add(header);
        controls.add(header);
        
        Label price = new Label("Price");
        price.setLayoutX(250);
        price.setLayoutY(100);
        price.setMinSize(100, 30);
        price.setAlignment(Pos.CENTER);
        
        ship_yard_anchor.getChildren().add(price);
        controls.add(price);
        
        if (nameHeader.equals("Weapon")) {
            Label power = new Label("Power");
            power.setLayoutX(400);
            power.setLayoutY(100);
            power.setMinSize(100, 30);
            power.setAlignment(Pos.CENTER);

            ship_yard_anchor.getChildren().add(power);
            controls.add(power);
        } else if (nameHeader.equals("Shield")) {
            Label protection = new Label("Protection");
            protection.setLayoutX(400);
            protection.setLayoutY(100);
            protection.setMinSize(100, 30);
            protection.setAlignment(Pos.CENTER);

            ship_yard_anchor.getChildren().add(protection);
            controls.add(protection);
        }
        return controls;
    }
    
    private void createShips() {
        HashMap<Ship.Type, Integer> ships = shipyard.getShips();
        int y = 100;
        
        Button back = new Button("Back");
        back.setLayoutX(10);
        back.setLayoutY(350);
        back.setMinSize(60, 40);
        back.setOnMouseClicked((MouseEvent event) -> {
            backToMenu(shipControls);
        });
        ship_yard_anchor.getChildren().add(back);
        shipControls.add(back);
        
        for (Ship.Type ship : ships.keySet()) {
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
            shipControls.add(shipName);
            
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
            shipControls.add(price);
            
            if (shipyard.ownsShip(ship, GameController.getGameData().getPlayer())) {
                image = new ImageView(new Image("/images/star.png"));
                image.setLayoutX(28);
                image.setLayoutY(y + 5);
                Tooltip currentShip = new Tooltip("Current Ship");
                Tooltip.install(image, currentShip);
                ship_yard_anchor.getChildren().add(image);
                shipControls.add(image);
            }
        }
    }
    
    private ArrayList<Node> createWeapons() {
        ArrayList<Node> controls = new ArrayList<>();
        ArrayList<Weapon.Type> weapons = shipyard.getWeapons();
        int y = 100;
        
        Button back = new Button("Back");
        back.setLayoutX(10);
        back.setLayoutY(350);
        back.setMinSize(60, 40);
        back.setOnMouseClicked((MouseEvent event) -> {
            backToMenu(weaponControls);
        });
        ship_yard_anchor.getChildren().add(back);
        controls.add(back);
        
        Button purchaseWeapon = new Button("Purchase Weapon");
        purchaseWeapon.setLayoutX(200);
        purchaseWeapon.setLayoutY(330);
        purchaseWeapon.setMinSize(200, 50);
        purchaseWeapon.setDisable(true);
        
        ship_yard_anchor.getChildren().add(purchaseWeapon);
        controls.add(purchaseWeapon);
        
        for (Weapon.Type weapon : weapons) {
            Button weaponName = new Button(weapon.toString() + " Laser");
            weaponName.setLayoutX(50);
            weaponName.setLayoutY(y += 31);
            weaponName.setMinSize(150, 30);
            weaponName.setOnMouseClicked((MouseEvent t) -> {
                if (weapon.getPrice() > GameController.getGameData().getPlayer().getCredits()) {
                    purchaseWeapon.setDisable(true);
                    purchaseWeapon.setOpacity(0.5);
                } else {
                    purchaseWeapon.setOnMouseClicked((MouseEvent event) -> {
                        purchaseWeapon(weapon);
                        if (weapon.getPrice() > GameController.getGameData().getPlayer().getCredits()) {
                            purchaseWeapon.setDisable(true);
                            purchaseWeapon.setOpacity(0.5);
                        }
                    });
                    purchaseWeapon.setDisable(false);
                }
            });
            
            ship_yard_anchor.getChildren().add(weaponName);
            controls.add(weaponName);
            
            Label price = new Label(weapon.getPrice() + "");
            price.setLayoutX(250);
            price.setLayoutY(y);
            price.setMinSize(100, 30);
            price.setStyle(
                    "-fx-background-color: #000000;" +
                    "-fx-border-color: #444444;" +
                    "-fx-border-radius: 5;" +
                    "-fx-background-radius: 5;");
            ship_yard_anchor.getChildren().add(price);
            controls.add(price);
            
            Label power = new Label(weapon.getDamage() + "");
            power.setLayoutX(400);
            power.setLayoutY(y);
            power.setMinSize(100, 30);
            power.setStyle(
                    "-fx-background-color: #000000;" +
                    "-fx-border-color: #444444;" +
                    "-fx-border-radius: 5;" +
                    "-fx-background-radius: 5;");
            ship_yard_anchor.getChildren().add(power);
            controls.add(power);
        }
        return controls;
    }
    
    private ArrayList<Node> createShields() {
        ArrayList<Node> controls = new ArrayList<>();
        ArrayList<Shield.Type> shields = shipyard.getShields();
        int y = 100;
        
        Button back = new Button("Back");
        back.setLayoutX(10);
        back.setLayoutY(350);
        back.setMinSize(60, 40);
        back.setOnMouseClicked((MouseEvent event) -> {
            backToMenu(shieldControls);
        });
        ship_yard_anchor.getChildren().add(back);
        controls.add(back);
        
        Button purchaseShields = new Button("Purchase Shield");
        purchaseShields.setLayoutX(200);
        purchaseShields.setLayoutY(330);
        purchaseShields.setMinSize(200, 50);
        purchaseShields.setDisable(true);
        ship_yard_anchor.getChildren().add(purchaseShields);
        controls.add(purchaseShields);
        
        for (Shield.Type shield : shields) {
            Button shieldName = new Button(shield.toString() +" Shield");
            shieldName.setLayoutX(50);
            shieldName.setLayoutY(y += 31);
            shieldName.setMinSize(150, 30);
            shieldName.setOnMouseClicked((MouseEvent t) -> {
                if (shield.getPrice() > GameController.getGameData().getPlayer().getCredits()) {
                    purchaseShields.setDisable(true);
                    purchaseShields.setOpacity(0.5);
                } else {
                    purchaseShields.setOnMouseClicked((MouseEvent event) -> {
                        purchaseShield(shield);
                        if (shield.getPrice() > GameController.getGameData().getPlayer().getCredits()) {
                            purchaseShields.setDisable(true);
                            purchaseShields.setOpacity(0.5);
                        }
                    });
                    purchaseShields.setDisable(false);
                }
            });
            
            ship_yard_anchor.getChildren().add(shieldName);
            controls.add(shieldName);
            
            Label price = new Label(shield.getPrice() + "");
            price.setLayoutX(250);
            price.setLayoutY(y);
            price.setMinSize(100, 30);
            price.setStyle(
                    "-fx-background-color: #000000;" +
                    "-fx-border-color: #444444;" +
                    "-fx-border-radius: 5;" +
                    "-fx-background-radius: 5;");
            ship_yard_anchor.getChildren().add(price);
            controls.add(price);
            
            Label protection = new Label(shield.getStrength() + "");
            protection.setLayoutX(400);
            protection.setLayoutY(y);
            protection.setMinSize(100, 30);
            protection.setStyle(
                    "-fx-background-color: #000000;" +
                    "-fx-border-color: #444444;" +
                    "-fx-border-radius: 5;" +
                    "-fx-background-radius: 5;");
            ship_yard_anchor.getChildren().add(protection);
            controls.add(protection);
            
        }
        return controls;
    }
    
    private ArrayList<Node> createGadgets() {
        ArrayList<Node> controls = new ArrayList<>();
        ArrayList<Gadget.Type> gadgets = shipyard.getGadgets();
        int y = 100;
        
        Button back = new Button("Back");
        back.setLayoutX(10);
        back.setLayoutY(350);
        back.setMinSize(60, 40);
        back.setOnMouseClicked((MouseEvent event) -> {
            backToMenu(gadgetControls);
        });
        ship_yard_anchor.getChildren().add(back);
        controls.add(back);
        
        Button purchaseGadgets = new Button("Purchase Gadget");
        purchaseGadgets.setLayoutX(200);
        purchaseGadgets.setLayoutY(340);
        purchaseGadgets.setMinSize(200, 50);
        purchaseGadgets.setDisable(true);
        ship_yard_anchor.getChildren().add(purchaseGadgets);
        controls.add(purchaseGadgets);
        
        Label info = new Label();
        info.setAlignment(Pos.CENTER);
        info.setLayoutX(50);
        info.setLayoutY(300);
        info.setMinSize(500, 30);
        info.setStyle(
                "-fx-background-color: #000000;" +
                "-fx-border-color: #444444;" +
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5;");
        ship_yard_anchor.getChildren().add(info);
        controls.add(info);
        
        for (Gadget.Type gadget : gadgets) {
            Button gadgetName = new Button(gadget.getName());
            gadgetName.setLayoutX(50);
            gadgetName.setLayoutY(y += 31);
            gadgetName.setMinSize(150, 30);
            gadgetName.setOnMouseClicked((MouseEvent t) -> {
                info.setText(gadget.getDescription());
                if (gadget.getPrice() > GameController.getGameData().getPlayer().getCredits()) {
                    purchaseGadgets.setDisable(true);
                    purchaseGadgets.setOpacity(0.5);
                } else {
                    purchaseGadgets.setOnMouseClicked((MouseEvent event) -> {
                        purchaseGadget(gadget);
                        if (gadget.getPrice() > GameController.getGameData().getPlayer().getCredits()) {
                            purchaseGadgets.setDisable(true);
                            purchaseGadgets.setOpacity(0.5);
                        }
                    });
                    purchaseGadgets.setDisable(false);
                }
            });
            
            ship_yard_anchor.getChildren().add(gadgetName);
            controls.add(gadgetName);
            
            Label price = new Label(gadget.getPrice() + "");
            price.setLayoutX(250);
            price.setLayoutY(y);
            price.setMinSize(100, 30);
            price.setStyle(
                    "-fx-background-color: #000000;" +
                    "-fx-border-color: #444444;" +
                    "-fx-border-radius: 5;" +
                    "-fx-background-radius: 5;");
            ship_yard_anchor.getChildren().add(price);
            controls.add(price);
        }
        return controls;
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
        
        
        money_label.setText(GameController.getGameData().getPlayer().getCredits() + "");
    }
    
    private void purchaseWeapon(Weapon.Type weapon) {
        GameController.getGameData().getPlayer().spend(weapon.getPrice());
        GameController.getGameData().getShip().addWeapon(new Weapon(weapon));
        money_label.setText(GameController.getGameData().getPlayer().getCredits() + "");
    }
    
    private void purchaseShield(Shield.Type shield) {
        GameController.getGameData().getPlayer().spend(shield.getPrice());
        GameController.getGameData().getShip().addShield(new Shield(shield));
        money_label.setText(GameController.getGameData().getPlayer().getCredits() + "");
    }
    
    private void purchaseGadget(Gadget.Type gadget) {
        GameController.getGameData().getPlayer().spend(gadget.getPrice());
        GameController.getGameData().getShip().addGadget(new Gadget(gadget));
        money_label.setText(GameController.getGameData().getPlayer().getCredits() + "");
    }
    
    @FXML
    private void returnToSpacePort() {
        GameController.getControl().setScreen(Screens.SPACE_PORT);
    }
    
    private void backToMenu(ArrayList<Node> toHide) {
        for (Node c : toHide) {
            fadeOut(c);
        }
        for (Button b : menuButtons) {
            fadeIn(b);
        }
    }
    
    @FXML
    private void shipsMode() {
        for (Button b : menuButtons) {
            fadeOut(b);
        }
        for (Node c : shipControls) {
            fadeIn(c);
        }
    }
    
    @FXML
    private void weaponsMode() {
        for (Button b : menuButtons) {
            fadeOut(b);
        }
        for (Node c : weaponControls) {
            fadeIn(c);
        }
    }
    
    @FXML
    private void shieldsMode() {
        for (Button b : menuButtons) {
            fadeOut(b);
        }
        for (Node c : shieldControls) {
            fadeIn(c);
        }
    }
    
    @FXML
    private void gadgetsMode() {
        for (Button b : menuButtons) {
            fadeOut(b);
        }
        for (Node c : gadgetControls) {
            fadeIn(c);
        }
    }
    
    private void fadeIn(Node node) {
        node.setOpacity(0);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), node);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        
        node.setMouseTransparent(false);
        
        fadeIn.play();
    }
    
    private void fadeOut(Node node) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), node);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        
        node.setMouseTransparent(true);
        
        fadeOut.play();
    }
    
}
