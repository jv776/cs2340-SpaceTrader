/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;
import models.GameData;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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

    /**
     * Create a new GameController instance.
     */
    public GameController() {
        gameData = new GameData();
        control = this;
        this.setBackground(new Background(new BackgroundImage(
                new Image("/images/welcome.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        )));
    }

    /**
     * Switches between views/controllers.
     *
     * @param screen The enum of the screen
     * @return true, if the screen is successfully loaded, false otherwise
     */
    public boolean setScreen(Screens screen) {
        String screenName = screen.getName();

        try {
            String resource = "/views/" + screenName + ".fxml";
            Class myClass = Class.forName("controllers." + screenName + "Controller");
            Class[] types = {};
            Constructor constructor = myClass.getConstructor(types);
            Object[] parameters = {};

            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            loader.setController((Initializable) constructor.newInstance(parameters));

            Parent loadScreen = (Parent) loader.load();

            //Is there is more than one screen 
            if (!getChildren().isEmpty()) {
                final DoubleProperty opacity = getChildren().get(0).opacityProperty();
                Timeline fadeOut = new Timeline(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(1000), (ActionEvent t) -> {
                            getChildren().remove(0);
                            getChildren().add(0, loadScreen);
                            getChildren().get(0).setOpacity(0);
                            final DoubleProperty opacity2 = getChildren().get(0).opacityProperty();
                            Timeline fadeIn = new Timeline(
                                    new KeyFrame(Duration.ZERO,
                                            new KeyValue(opacity2, 0.0)),
                                    new KeyFrame(new Duration(1000),
                                            new KeyValue(opacity2, 1.0)));
                            fadeIn.play();
                        },
                                new KeyValue(opacity, 0.0)));
                fadeOut.play();
            } else {
                //no one else been displayed, then just show 
                final DoubleProperty opacity = opacityProperty();
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
