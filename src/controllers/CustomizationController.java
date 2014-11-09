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

/**
 * Customization FXML Controller class.
 *
 * @author Alex
 */
public class CustomizationController implements Initializable {

    private static final int SKILL_POINT_MAX = 15;

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
    private Button continueButton;

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
    @FXML
    private void pilotIncrease() {
        updateSkillPoints(pilotSkillPoints, Direction.INCREASE);
    }

    /**
     * Decrements "Pilot" label counter if any skill points are allocated there.
     */
    @FXML
    private void pilotDecrease() {
        updateSkillPoints(pilotSkillPoints, Direction.DECREASE);
    }

    /**
     * Increments "Fighter" label counter if any skill points are left.
     */
    @FXML
    private void fighterIncrease() {
        updateSkillPoints(fighterSkillPoints, Direction.INCREASE);
    }

    /**
     * Decrements "Fighter" label counter if any skill points are allocated there.
     */
    @FXML
    private void fighterDecrease() {
        updateSkillPoints(fighterSkillPoints, Direction.DECREASE);
    }

    /**
     * Increments "Trader" label counter if any skill points are left.
     */
    @FXML
    private void traderIncrease() {
        updateSkillPoints(traderSkillPoints, Direction.INCREASE);
    }

    /**
     * Decrements "Trader" label counter if any skill points are allocated there.
     */
    @FXML
    private void traderDecrease() {
        updateSkillPoints(traderSkillPoints, Direction.DECREASE);
    }

    /**
     * Increments "Engineer" label counter if any skill points are left.
     */
    @FXML
    private void engineerIncrease() {
        updateSkillPoints(engineerSkillPoints, Direction.INCREASE);
    }

    /**
     * Decrements "Engineer" label counter if any skill points are allocated there.
     */
    @FXML
    private void engineerDecrease() {
        updateSkillPoints(engineerSkillPoints, Direction.DECREASE);
    }

    /**
     * Increments "Investor" label counter if any skill points are left.
     */
    @FXML
    private void investorIncrease() {
        updateSkillPoints(investorSkillPoints, Direction.INCREASE);
    }

    /**
     * Decrements "Investor" label counter if any skill points are allocated there.
     */
    @FXML
    private void investorDecrease() {
        updateSkillPoints(investorSkillPoints, Direction.DECREASE);
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

        SolarSystem system = universe.solarSystems[(int) (Math.random() * universe.solarSystems.length)];
        system.discover();
        Planet planet = system.getPlanets()[(int) (Math.random() * system.getPlanets().length)];

        player.setCurrentSystem(system);
        player.setCurrentPlanet(planet);

        GameController.getGameData().setPlayer(player);
        GameController.getGameData().setUniverse(universe);
        GameController.getGameData().setSolarSystem(system);
        GameController.getGameData().setPlanet(planet);

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
     *
     * @param attributePoints the Label corresponding to the attribute
     *                        that is being incremented or decremented
     * @param direction       "increase" if incrementing and "decrease" if
     *                        decrementing
     */
    private void updateSkillPoints(Label attributePoints, Direction direction) {
        if (direction == Direction.INCREASE) {
            if (skillPoints > 0) {
                attributePoints.setText(Integer.parseInt(attributePoints.getText()) + 1 + "");
                skillPointsRemaining.setText(--skillPoints + "");
            }
        } else if (direction == Direction.DECREASE) {
            if (Integer.parseInt(attributePoints.getText()) > 0) {
                attributePoints.setText(Integer.parseInt(attributePoints.getText()) - 1 + "");
                skillPointsRemaining.setText(++skillPoints + "");
            }
        }

        handleNameInput();
    }
    
    private static enum Direction {
        INCREASE, DECREASE
    }
}
