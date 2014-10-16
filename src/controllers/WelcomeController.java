package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


/**
 * FXML Controller class
 *
 * @author Alex
 */
public class WelcomeController extends GameController implements Initializable {
    
    @FXML
    private Button newGameButton;
    
    @FXML
    private Button loadGameButton; //implement later
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    /**
     * Handles click of "New Game" button. It first resets the customization screen,
     * as canceling midway through allocating skill points has the chance of
     * remembering previous output when clicking new game again.
     */
    @FXML
    private void handleNewGame() {
        control.setScreen("Customization");
    }
}
