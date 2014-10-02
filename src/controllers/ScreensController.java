package controllers;

import java.io.IOException;
import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import main.SpaceTraderMain;
import models.Player;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * The main view handler.
 *
 * @author Alex
 */
public class ScreensController extends StackPane {
    private HashMap<String, Node> screens = new HashMap<>();
    private Player player;

    /**
     * Adds the screen to the HashMap of existing screens.
     *
     * @param name   the name of the screen
     * @param screen the loaded screen obtained from a resource
     */
    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    /**
     * Loads a screen from a resource and adds it to the HashMap of existing
     * screens.
     *
     * @param name     the name of the screen
     * @param resource the resource path of the associated View
     * @return
     */
    public boolean loadScreen(String name, String resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = myLoader.load();
            ControlledScreen myScreenController = myLoader.getController();
            myScreenController.setScreenParent(this);
            addScreen(name, loadScreen);
            return true;
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Sets the screen to the one with the specified name and removes the
     * old one from the pane. This increases performance, as simply stacking
     * screens could get heavy. There is also a fade-to-white transition
     * from screen to screen. We can change this later.
     *
     * @param name the name of the screen
     * @return true if screen is set, false if name is not recognized
     */
    public boolean setScreen(final String name) {
        if(screens.get(name) != null) { //screen loaded 
            final DoubleProperty opacity = opacityProperty();

            //Is there is more than one screen 
            if(!getChildren().isEmpty()) {
                Timeline fade = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                    new KeyFrame(new Duration(1000),
                        (ActionEvent t) -> {
                            getChildren().remove(0); //remove displayed screen
                            getChildren().add(0, screens.get(name)); //add new screen
                            Timeline fadeIn = new Timeline(
                                new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
                            fadeIn.play();
                        }, new KeyValue(opacity, 0.0)));
                fade.play();
            }
            else {
                //no one else been displayed, then just show 
                setOpacity(0.0);
                getChildren().add(screens.get(name));
                Timeline fadeIn = new Timeline(
                    new KeyFrame(Duration.ZERO,
                        new KeyValue(opacity, 0.0)),
                    new KeyFrame(new Duration(2500),
                        new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;
        }
        else {
            System.out.println("screen hasn't been loaded!\n");
            return false;
        }
    }

    /**
     * Removes a screen from the ones in the HashMap.
     *
     * @param name the name of the screen
     * @return true if removed, false if not found
     */
    public boolean unloadScreen(String name) {
        if(screens.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Resets the screen and loads it into the HashMap again. This is to
     * prevent the screen from simply "pausing" and resuming when
     * reloaded.
     *
     * @param name the name of the screen
     * @return true if reset, false if not found
     */
    public boolean resetScreen(String name) {
        Node screen = screens.get(name);
        if(screens.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        }
        else {
            loadScreen(name, SpaceTraderMain.SCREENS.get(name));
            return true;
        }
    }

    public void setPlayer(Player p) {
        player = p;
    }

    public Player getPlayer() {
        return player;
    }
}
