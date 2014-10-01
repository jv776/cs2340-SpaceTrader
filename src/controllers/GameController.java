/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import models.GameData;

/**
 *
 * @author Alex
 */
public class GameController extends StackPane {
    
    protected GameData gameData;
    protected GameController control;
    
    public GameController() {
        gameData = GameData.DATA;
    }
    
    public void initData(GameData data, GameController controller) {
        gameData = data;
        control = controller;
    }
    
    public boolean setScreen(String screenName) {
        try { 
            
            String resource = "/views/" + screenName + ".fxml";
            System.out.println(resource);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            
            Class myClass = Class.forName("controllers." + screenName + "Controller");
            Class[] types = {};
            Constructor constructor = myClass.getConstructor(types);
            Object[] parameters = {};
            GameController controller = (GameController) constructor.newInstance(parameters);
            controller.initData(gameData, this);
            
            loader.setController((Initializable)controller);
            
            Parent loadScreen = (Parent) loader.load();
            
            final DoubleProperty opacity = opacityProperty(); 

            //Is there is more than one screen 
            if(!getChildren().isEmpty()){ 
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
                    new KeyFrame(Duration.ZERO, 
                    new KeyValue(opacity, 0.0)), 
                    new KeyFrame(new Duration(2500), 
                    new KeyValue(opacity, 1.0))); 
                fadeIn.play(); 
            } 
            return true; 
        } catch (Exception e) {
            e.printStackTrace();
            return false; 
        }
        
        
    }
}
