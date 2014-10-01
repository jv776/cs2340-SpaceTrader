package controllers;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import models.Planet;
import models.Player;
import models.SolarSystem;
import models.Universe;

/**
 * Customization FXML Controller class
 *
 * @author Alex
 */
public class CustomizationController extends GameController implements Initializable {

    public final int SKILL_POINT_MAX = 15;
    
    public TextField nameField;
    public Label pilotSkillPoints;
    public Label fighterSkillPoints;
    public Label traderSkillPoints;
    public Label engineerSkillPoints;
    public Label investorSkillPoints;
    public Label skillPointsRemaining;
    public Button continueButton;
    private int skillPoints;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        skillPoints = SKILL_POINT_MAX;
        skillPointsRemaining.setText("" + skillPoints);
        continueButton.setDisable(true);
        nameField.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent event) -> {
            if (nameField.getText().length() >= 30) {
                event.consume();
            }
        });
    }    
    
    /**
     * Increments "Pilot" label counter if any skill points are left.
     */
    public void pilotIncrease() {
        updateSkillPoints(pilotSkillPoints, "increase");
    }
    /**
     * Decrements "Pilot" label counter if any skill points are allocated there.
     */
    public void pilotDecrease() {
        updateSkillPoints(pilotSkillPoints, "decrease");
    }
    /**
     * Increments "Fighter" label counter if any skill points are left.
     */
    public void fighterIncrease() {
        updateSkillPoints(fighterSkillPoints, "increase");
    }
    /**
     * Decrements "Fighter" label counter if any skill points are allocated there.
     */
    public void fighterDecrease() {
        updateSkillPoints(fighterSkillPoints, "decrease");
    }
    /**
     * Increments "Trader" label counter if any skill points are left.
     */
    public void traderIncrease() {
        updateSkillPoints(traderSkillPoints, "increase");
    }
    
    /**
     * Decrements "Trader" label counter if any skill points are allocated there.
     */
    public void traderDecrease() {
        updateSkillPoints(traderSkillPoints, "decrease");
    }
    
    /**
     * Increments "Engineer" label counter if any skill points are left.
     */
    public void engineerIncrease() {
        updateSkillPoints(engineerSkillPoints, "increase");
    }
    
    /**
     * Decrements "Engineer" label counter if any skill points are allocated there.
     */
    public void engineerDecrease() {
        updateSkillPoints(engineerSkillPoints, "decrease");
    }
    
    /**
     * Increments "Investor" label counter if any skill points are left.
     */
    public void investorIncrease() {
        updateSkillPoints(investorSkillPoints, "increase");
    }
    
    /**
     * Decrements "Investor" label counter if any skill points are allocated there.
     */
    public void investorDecrease() {
        updateSkillPoints(investorSkillPoints, "decrease");
    }
    
    /**
     * Handles what occurs after player has allocated skill points and given
     * a name to their character.
     */
    public void handleContinue() {
        Universe universe = new Universe();
        
        Player player = new Player(nameField.getText(),
            Integer.parseInt(pilotSkillPoints.getText()),
            Integer.parseInt(fighterSkillPoints.getText()),
            Integer.parseInt(traderSkillPoints.getText()),
            Integer.parseInt(engineerSkillPoints.getText()),
            Integer.parseInt(investorSkillPoints.getText())
        );
        
        gameData.setPlayer(player);
        gameData.setUniverse(universe);
        
        for (SolarSystem s : universe.solarSystems) {
            for (Planet p : s.planets) {
                if (p.supportsLife()) {
                    gameData.setSolarSystem(s);
                    gameData.setPlanet(p);
                    break;
                }
            }
            if (gameData.getPlanet() != null) {
                break;
            }
        }
        
        control.setScreen("Market");
    }
    
    /**
     * Returns to the home screen upon clicking the "Cancel" button.
     */
    public void handleCancel() {
        control.setScreen("Welcome");
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
