/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import models.SolarSystem;

/**
 *
 * @author Alex
 */
public class UniverseMapController extends GameController implements Initializable {

    
    public Canvas canvas;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,600,400);
        for (SolarSystem s : gameData.getUniverse().solarSystems) {
            Image image = new Image("/controllers/asdf.jpg");
            
            gc.drawImage(image, s.getX(), s.getY());
            //gc.setFill(Color.BLUE);
            //gc.fillOval(s.getX(), s.getY(), 5, 5);
        }
            
    }

}
