package controllers;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import models.Planet;
import models.Player;
import models.SolarSystem;
import models.Universe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import models.StockMarket;
import models.Bank;

/**
 * Customization FXML Controller class
 *
 * @author Alex
 */
public class CustomizationController implements Initializable {

    public final int SKILL_POINT_MAX = 15;
    
    @FXML
    private GridPane grid;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private Label pilotSkillPoints;
    
    @FXML 
    private Label fighterSkillPoints;
    
    @FXML
    private Label traderSkillPoints;
    
    @FXML
    private Label engineerSkillPoints;
    
    @FXML
    private Label investorSkillPoints;
    
    @FXML
    private Label skillPointsRemaining;
    
    @FXML
    private Label pilotLabel, fighterLabel, engineerLabel, traderLabel, investorLabel;
    
    @FXML 
    private Slider pilotSlider, fighterSlider, traderSlider, engineerSlider, investorSlider;
    
    Label[] labels;
    Slider[] sliders;
    
    Tooltip pilot, fighter, engineer, trader, investor;
    
    
    @FXML
    private Button continueButton;
    
    private int skillPoints;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        grid.setBackground(new Background(new BackgroundImage(
            new Image("/images/welcome.jpg"),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT
        )));
        skillPoints = SKILL_POINT_MAX;
        skillPointsRemaining.setText("" + skillPoints);
        continueButton.setDisable(true);
        nameField.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent event) -> {
            if (nameField.getText().length() >= 18) {
                event.consume();
            }
        });
        
        Slider[] fxsliders = {pilotSlider, fighterSlider, traderSlider, engineerSlider, investorSlider};
        Label[] fxlabels = {pilotSkillPoints, fighterSkillPoints, traderSkillPoints, engineerSkillPoints, investorSkillPoints};
        this.sliders = fxsliders;
        this.labels = fxlabels;
        
        for (int i = 0; i < sliders.length; i++) {
            Slider s = sliders[i];
            final int j = i;
            s.setMax(15);
            s.setMajorTickUnit(1.0);
            s.setMinorTickCount(0);
            s.setSnapToTicks(true);
            s.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                skillPoints -= (new_val.intValue() - old_val.intValue());
                if (skillPoints < 0) {
                    s.setValue(new_val.intValue() + skillPoints);
                    skillPoints = 0;
                    skillPointsRemaining.setText("" + skillPoints);
                } else {
                    labels[j].setText(new_val.intValue() + "");
                    skillPointsRemaining.setText("" + skillPoints);
                }
                
                if (skillPoints == 0 && !nameField.getText().isEmpty()) {
                    continueButton.setDisable(false);
                } else {
                    continueButton.setDisable(true);
                }
            });
        }
        
        pilot = new Tooltip("Increases ship speed in battle and makes fleeing and pursing much easier.");
        pilot.setMaxWidth(200);
        pilot.setWrapText(true);
        fighter = new Tooltip("Increases laser speed and damage in battle.");
        fighter.setMaxWidth(200);
        fighter.setWrapText(true);
        engineer = new Tooltip("Increases shield regeneration rate in battle.");
        engineer.setMaxWidth(200);
        engineer.setWrapText(true);
        trader = new Tooltip("Increases sell prices up to 10% of their original value and lowers buy prices the same amount.");
        trader.setMaxWidth(200);
        trader.setWrapText(true);
        investor = new Tooltip("Increases chances of success in the stock market.");
        investor.setMaxWidth(200);
        investor.setWrapText(true);
        
        pilotLabel.setOnMouseEntered((MouseEvent t) -> {
            double xCoord = grid.getScene().getWindow().getX();
            double yCoord = grid.getScene().getWindow().getY();
            pilot.show(grid, xCoord + pilotLabel.getLayoutX() + pilotLabel.getWidth() + 20, 
                    yCoord + pilotLabel.getLayoutY() + 40);
        });
        pilotLabel.setOnMouseExited((MouseEvent t) -> {
            pilot.hide();
        });
        
        fighterLabel.setOnMouseEntered((MouseEvent t) -> {
            double xCoord = grid.getScene().getWindow().getX();
            double yCoord = grid.getScene().getWindow().getY();
            fighter.show(grid, xCoord + fighterLabel.getLayoutX() + fighterLabel.getWidth() + 20, 
                    yCoord + fighterLabel.getLayoutY() + 40);
        });
        fighterLabel.setOnMouseExited((MouseEvent t) -> {
            fighter.hide();
        });
        
        traderLabel.setOnMouseEntered((MouseEvent t) -> {
            double xCoord = grid.getScene().getWindow().getX();
            double yCoord = grid.getScene().getWindow().getY();
            trader.show(grid, xCoord + traderLabel.getLayoutX() + + traderLabel.getWidth() + 20, 
                    yCoord + traderLabel.getLayoutY() + 40);
        });
        traderLabel.setOnMouseExited((MouseEvent t) -> {
            trader.hide();
        });
        
        engineerLabel.setOnMouseEntered((MouseEvent t) -> {
            double xCoord = grid.getScene().getWindow().getX();
            double yCoord = grid.getScene().getWindow().getY();
            engineer.show(grid, xCoord + engineerLabel.getLayoutX() + engineerLabel.getWidth() + 20, 
                    yCoord + engineerLabel.getLayoutY() + 40);
        });
        engineerLabel.setOnMouseExited((MouseEvent t) -> {
            engineer.hide();
        });
        
        investorLabel.setOnMouseEntered((MouseEvent t) -> {
            double xCoord = grid.getScene().getWindow().getX();
            double yCoord = grid.getScene().getWindow().getY();
            investor.show(grid, xCoord + investorLabel.getLayoutX() + investorLabel.getWidth() + 20, 
                    yCoord + investorLabel.getLayoutY() + 40);
        });
        investorLabel.setOnMouseExited((MouseEvent t) -> {
            investor.hide();
        });
        
    }   
    
    /**
     * Handles what occurs after player has allocated skill points and given
     * a name to their character.
     */
    @FXML
    private void handleContinue() {
        Universe universe = new Universe();
        
        Player player = new Player(nameField.getText(),
            Integer.parseInt(pilotSkillPoints.getText()),
            Integer.parseInt(fighterSkillPoints.getText()),
            Integer.parseInt(traderSkillPoints.getText()),
            Integer.parseInt(engineerSkillPoints.getText()),
            Integer.parseInt(investorSkillPoints.getText())
        );
        
        player.earn(1000); //start with 1000 credits
        
        SolarSystem system = universe.solarSystems[(int) (Math.random() *
                universe.solarSystems.length)];
        system.discover();
        Planet planet = system.getPlanets()[(int) (Math.random() * system.getPlanets().length)];
        
        Bank bank = new Bank();
        StockMarket stockMarket = new StockMarket(player.getInvestorSkillPoints());
        
        player.setCurrentSystem(system);
        player.setCurrentPlanet(planet);
        
        GameController.getGameData().setStockMarket(stockMarket);
        GameController.getGameData().setPlayer(player);
        GameController.getGameData().setUniverse(universe);
        GameController.getGameData().setSolarSystem(system);
        GameController.getGameData().setPlanet(planet);
        GameController.getGameData().setBank(bank);
        
        GameController.getControl().setScreen(Screens.UNIVERSE_MAP);
    }
    
    /**
     * Returns to the home screen upon clicking the "Cancel" button.
     */
    @FXML
    private void handleCancel() {
        GameController.getControl().setScreen(Screens.WELCOME);
    }
    
    /**
     * Checks for an update to the "Continue" button, which can only be
     * enabled when all skill points are allocated and a name is input.
     */
    public void handleNameInput() {
        if (skillPoints == 0 && !nameField.getText().isEmpty()) {
            continueButton.setDisable(false);
        } else {
            continueButton.setDisable(true);
        }
    }
    
    /**
     * Handles all attribute incrementing and decrementing while checking
     * to see if the "Continue" button should be enabled.
     * @param attributePoints the Label corresponding to the attribute 
     * that is being incremented or decremented
     * @param direction "increase" if incrementing and "decrease" if
     * decrementing
     */
    private void updateSkillPoints(Label attributePoints, String direction) {
        if (direction.equals("increase")) {
            if (skillPoints > 0) {
                attributePoints.setText(Integer.parseInt(attributePoints.getText()) + 1 + "");
                skillPointsRemaining.setText(--skillPoints + "");
            }
        } else if (direction.equals("decrease")) {
            if (Integer.parseInt(attributePoints.getText()) > 0) {
                attributePoints.setText(Integer.parseInt(attributePoints.getText()) - 1 + "");
                skillPointsRemaining.setText(++skillPoints + "");
            }
        }
        
        handleNameInput();
    }
}
