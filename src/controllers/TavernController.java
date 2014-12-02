package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import models.Mercenary;
import models.Player;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class TavernController implements Initializable {

    private Player player;
    private int dailyCost;
    private int power;
    private Button selectedSkill;
    
    @FXML
    private Button pilot_button, fighter_button, engineer_button,
            trader_button, investor_button, hire_button;
    
    @FXML
    private Slider power_slider;
    
    @FXML
    private Label credits_label, no_space_label, stat_name_label, stats_label;
    
    @FXML
    private TextField name_field;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        player = GameController.getGameData().getPlayer();
        dailyCost = 0;
        selectedSkill = pilot_button;
        pilot_button.requestFocus();
        
        checkForHire();
        
        Button[] buttons = {pilot_button, fighter_button, engineer_button,
            trader_button, investor_button};
        for (Button b : buttons) {
            b.setOnMouseClicked((MouseEvent t) -> {
                selectedSkill = b;
                generateCostAndStat();
            });
        }
        
        name_field.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent event) -> {
            if (name_field.getText().length() >= 18) {
                event.consume();
            }
        });
        
        power_slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            generateCostAndStat();
        });
        
        hire_button.setOnMouseClicked((MouseEvent t) -> {
            Mercenary merc = new Mercenary(name_field.getText(), selectedSkill.getText(), power, dailyCost);
            player.getShip().addCrew(merc);
            checkForHire();
        });
    }
    
    private void generateCostAndStat() {
        String skill = selectedSkill.getText();
        power = (int) power_slider.getValue();
        dailyCost = power * 50;
        credits_label.setText(dailyCost + " Credits");
        stat_name_label.setText(skill + ": ");
        switch (skill) {
            case "Fighter": stats_label.setText("" + (player.getFighterSkillPoints() + power)); break;
            case "Pilot": stats_label.setText("" + (player.getPilotSkillPoints() + power)); break;
            case "Engineer": stats_label.setText("" + (player.getEngineerSkillPoints() + power)); break;
            case "Trader": stats_label.setText("" + (player.getTraderSkillPoints() + power)); break;
            default: stats_label.setText("" + (player.getInvestorSkillPoints() + power)); break;
        }
        checkForHire();
    }
    
    private void checkForHire() {
        if (!(player.getShip().getCrewSize() < player.getShip().getMaxCrew())) {
            hire_button.setDisable(true);
            no_space_label.setVisible(true);
        } else {
            no_space_label.setVisible(false);
            if (!name_field.getText().isEmpty()) {
                hire_button.setDisable(false);
            } else {
                hire_button.setDisable(true);
            }
        }
    }
    
    @FXML
    void returnToSpacePort() {
        GameController.getControl().setScreen(Screens.NEW_SPACE_PORT);
    }
}
