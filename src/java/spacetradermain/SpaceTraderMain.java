package java.spacetradermain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.controllers.ScreensController;
import java.util.HashMap;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for FXProject. Initializes various screens
 * and starts the game.
 * @author Alex
 */
public class SpaceTraderMain extends Application {
    
    public static final HashMap<String, String> SCREENS = new HashMap<>();
    public static final String WELCOME_SCREEN = "Welcome"; 
    public static final String WELCOME_SCREEN_FXML = "/main/java/views/Welcome.fxml"; 
    public static final String CUSTOMIZATION_SCREEN = "Customization"; 
    public static final String CUSTOMIZATION_SCREEN_FXML = "/main/java/views/Customization.fxml"; 

     @Override 
     public void start(Stage primaryStage) { 
        
        SCREENS.put(WELCOME_SCREEN, WELCOME_SCREEN_FXML);
        SCREENS.put(CUSTOMIZATION_SCREEN, CUSTOMIZATION_SCREEN_FXML);
       
        ScreensController mainContainer = new ScreensController(); 
        
        for (String key : SCREENS.keySet()) {
            mainContainer.loadScreen(key, SCREENS.get(key));
        }      
        
        mainContainer.setScreen(SpaceTraderMain.WELCOME_SCREEN); 

        Group root = new Group(); 
        root.getChildren().addAll(mainContainer); 
        Scene scene = new Scene(root); 
        primaryStage.setScene(scene); 
        primaryStage.show(); 
     } 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
