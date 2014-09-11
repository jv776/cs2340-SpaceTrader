package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import main.SpaceTraderMain;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


/**
 * FXML Controller class
 *
 * @author Alex
 */
public class WelcomeController implements Initializable, ControlledScreen {

    public Button newGameButton;
    public Button loadGameButton; //implement later

    private ScreensController parent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        parent = screenParent;
    }

    /**
     * Handles click of "New Game" button. It first resets the customization screen,
     * as canceling midway through allocating skill points has the chance of
     * remembering previous output when clicking new game again.
     */
    public void handleNewGame() {
        parent.resetScreen(SpaceTraderMain.CUSTOMIZATION_SCREEN);
        parent.setScreen(SpaceTraderMain.CUSTOMIZATION_SCREEN);
    }
}
