package com.hitchhikers.spacetrader.main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.hitchhikers.spacetrader.Music;
import com.hitchhikers.spacetrader.Utils;
import com.hitchhikers.spacetrader.controllers.GameController;
import com.hitchhikers.spacetrader.controllers.Screens;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Main class for FXProject. Initializes various screens
 * and starts the game.
 *
 * @author Alex
 */
public class SpaceTraderMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        Music.init();
        
        GameController mainContainer = new GameController();

        mainContainer.setScreen(Screens.WELCOME);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Utils.getResource("css/spacetrader.css").toString());
        primaryStage.setScene(scene);
        primaryStage.show();
        
        primaryStage.setOnCloseRequest((WindowEvent we) -> {
            Music.stopAll();
            System.exit(0);
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
