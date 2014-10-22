/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import models.GameData;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javafx.fxml.Initializable;

/**
 * Main controller for the game, maintains information about game state
 * and manages transitions between screens.
 * 
 * @author Alex, John
 */
public class GameController extends StackPane implements Serializable {
    
    private static GameData gameData;
    private static GameController control;
    private static File saveFile;
    
    public GameController() {
        gameData = new GameData();
        control = this; //
    }
    
    /**
     * Switches between views/controllers.
     * 
     * @param screenName The name of the FXML view
     * @return true, if the screen is successfully loaded, false otherwise
     */
    public boolean setScreen(String screenName) {
        try {
            String resource = "/views/" + screenName + ".fxml";
            Class myClass = Class.forName("controllers." + screenName + "Controller");
            Class[] types = {};
            Constructor constructor = myClass.getConstructor(types);
            Object[] parameters = {};
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            loader.setController((Initializable)constructor.newInstance(parameters));
            
            Parent loadScreen = (Parent) loader.load();
            final DoubleProperty opacity = opacityProperty(); 

            //Is there is more than one screen 
            if(!getChildren().isEmpty()) {
                Timeline fade = new Timeline( 
                    new KeyFrame(Duration.ZERO, 
                    new KeyValue(opacity,1.0)), 
                    new KeyFrame(new Duration(1000), (ActionEvent t) -> {
                        getChildren().remove(0);
                        //add new screen
                        getChildren().add(0, loadScreen);
                        Timeline fadeIn = new Timeline(
                                new KeyFrame(Duration.ZERO,
                                        new KeyValue(opacity, 0.0)),
                                new KeyFrame(new Duration(800),
                                        new KeyValue(opacity, 1.0)));
                        fadeIn.play();
                }, new KeyValue(opacity, 0.0))); 
                fade.play(); 
            } else {
                //no one else been displayed, then just show 
                setOpacity(0.0);
                getChildren().add(loadScreen);
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(2500), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;
        } catch (ClassNotFoundException | NoSuchMethodException |
                SecurityException | InstantiationException |
                IllegalAccessException | IllegalArgumentException |
                InvocationTargetException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Save the state of the current game.
     */
    public static void saveGameData() {
        try {
            saveFile = new File("saves/" + gameData.getPlayer().name + ".ser");
            FileOutputStream fos = new FileOutputStream(saveFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(gameData);
        } catch (FileNotFoundException e) {
            //file not found
            
            System.out.println("SAVE FAILED: file not found");
            //e.printStackTrace();
        } catch (IOException e) {
            //IO error occurred while writing save file
            
            System.out.println("SAVE FAILED: IO error while saving");
            //e.printStackTrace();
        }
    }
    
    /**
     * Load the game data from a given file.
     * 
     * @param f The file from which to load the new data
     */
    public static void loadGameData(File f) {
        saveFile = f;

        try {
            FileInputStream fis = new FileInputStream(saveFile);
            ObjectInputStream ois = new ObjectInputStream(fis);

            gameData = (GameData) ois.readObject();
        } catch (FileNotFoundException e) {
            //the selected file wasn't found
            System.out.println("LOADING FAILED: file not found");
            //e.printStackTrace();
        } catch (IOException e) {
            //IO error while loading file
            System.out.println("LOADING FAILED: IO error while loading");
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("LOADING FAILED: Class not found");
            //e.printStackTrace();
        }
    }
    
    /**
     * @return Game data for the currently active game
     */
    public static GameData getGameData() {
        return gameData;
    }
    
    /**
     * @return The current active GameController instance
     */
    public static GameController getControl() {
        return control;
    }
}
