package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class WelcomeController implements Initializable {

    public Button newGameButton;
    public Button loadGameButton;
    private FileChooser saveChooser;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        saveChooser = new FileChooser();
        saveChooser.setTitle("Load previous game");
        saveChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Save files",
                "*.ser"));
        saveChooser.setInitialDirectory(new File("saves/"));
    }

    /**
     * Handles click of "New Game" button. It first resets the customization screen,
     * as canceling midway through allocating skill points has the chance of
     * remembering previous output when clicking new game again.
     */
    public void handleNewGame() {
        GameController.getControl().setScreen("Customization");
    }
    
    public void handleLoadGame(Event e) {
        Node n = (Node) e.getTarget();
        Scene s = n.getScene();
        Window w = s.getWindow();
        
        File newSave = saveChooser.showOpenDialog(w);
        
        if (newSave != null) {
            GameController.loadGameData(newSave);
            GameController.getControl().setScreen("Market");
        }
    }
}
