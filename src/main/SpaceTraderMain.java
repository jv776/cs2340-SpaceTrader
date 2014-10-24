package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controllers.GameController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for FXProject. Initializes various screens
 * and starts the game.
 * 
 * @author Alex
 */
public class SpaceTraderMain extends Application {
     @Override 
     public void start(Stage primaryStage) {
        GameController mainContainer = new GameController();
               
        mainContainer.setScreen("Welcome");

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/spacetrader.css");
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

