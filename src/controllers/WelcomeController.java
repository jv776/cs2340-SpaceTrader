package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * FXML Controller class
 *
 * @author Alex
 */
public class WelcomeController extends GameController implements Initializable {

    public Button newGameButton;
    public Button loadGameButton; //implement later
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    /**
     * Handles click of "New Game" button. It first resets the customization screen,
     * as canceling midway through allocating skill points has the chance of
     * remembering previous output when clicking new game again.
     */
    public void handleNewGame() {
        control.setScreen("Customization");
    }
}
