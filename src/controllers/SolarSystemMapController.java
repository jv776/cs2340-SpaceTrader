/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import models.Planet;
import models.SolarSystem;

/**
 *
 * @author Alex
 */
public class SolarSystemMapController extends GameController implements Initializable {

    private SolarSystem s;
    public AnchorPane anchor;
    public Button returnButton;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        returnButton.setText("Return to Universe");
        anchor.setBackground(new Background(
                new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        SolarSystem currentSystem = gameData.getSolarSystem();
        
        double x = 300 - currentSystem.getSun().getRadius() - 2.5;
        double y = 200 - currentSystem.getSun().getRadius() - 2.5;
        double[] hsv = computeStarColor(currentSystem);
        
        ImageView image = new ImageView("/images/star.png");
        ColorAdjust color = new ColorAdjust();
        color.setHue(hsv[0] / 180 - 1);
        color.setSaturation(hsv[1] * 2 - 1);
        color.setBrightness(hsv[2] - 1);
        System.out.println("H: " + (hsv[0] / 180 - 1) + " S: " + (hsv[1] * 2 - 1) + " V: " + (hsv[2] - 1));
        image.setEffect(color);
        
        image.setX(x);
        image.setY(y);
        image.setScaleX((currentSystem.getSun().getRadius() * 2 + 5) / image.getImage().getWidth());
        image.setScaleY((currentSystem.getSun().getRadius() * 2 + 5) / image.getImage().getHeight());
        
        anchor.getChildren().add(image);
        
        Planet currentPlanet = gameData.getPlanet();
        
        for (Planet p : currentSystem.planets) {
            ImageView planet = new ImageView("/images/star.png");
            if (p == currentPlanet) {
                planet.setEffect(new ColorAdjust(0, 0, 1, 0));
            } 
            planet.setX(x + Math.cos(Math.random() * 2 * Math.PI) * currentPlanet.getDistance());
            planet.setY(y + Math.sin(Math.random() * 2 * Math.PI) * currentPlanet.getDistance());
            planet.setScaleX(10.0 / planet.getImage().getWidth());
            planet.setScaleY(10.0 / planet.getImage().getWidth());  
            planet.setOnMouseClicked((MouseEvent t) -> {
                gameData.setPlanet(p);
                control.setScreen("Market");
            });
            anchor.getChildren().add(planet);
        }
    }
    
    

    public void returnToUniverse() {
        control.setScreen("UniverseMap");
    }
    
    private double lerp(double x, double x0, double y0, double x1, double y1) {
        return y0 + (y1 - y0) * (x - x0) / (x1 - x0);
    }
    
    private double[] computeStarColor(SolarSystem s) {
        double temp = s.getSun().getTemperature();
        System.out.println(s.getSun().getTemperature());
        double r, g, b;
        
        //star coloration seems too pale
        if (0.0 <= temp && temp < 3.7) {
            r = 255;
            g = lerp(temp, 0, 220, 3.7, 190);
            b = lerp(temp, 0, 100, 3.7, 120);
        } else if (temp < 5.2) {
            r = 255;
            g = lerp(temp, 3.7, 190, 5.2, 185);
            b = lerp(temp, 3.7, 120, 5.2, 150);
        } else if (temp < 6) {
            r = lerp(temp, 5.2, 255, 6, 250);
            g = lerp(temp, 5.2, 185, 6, 230);
            b = lerp(temp, 5.2, 150, 6, 200);
        } else if (temp < 7.5) {
            r = lerp(temp, 6, 250, 7.5, 200);
            g = lerp(temp, 6, 230, 7.5, 200);
            b = lerp(temp, 6, 200, 7.5, 255);
        } else if (temp < 10) {
            r = lerp(temp, 7.5, 200, 10, 160);
            g = lerp(temp, 7.5, 200, 10, 180);
            b = 255;
        } else if (temp < 20) {
            r = lerp(temp, 10, 160, 20, 135);
            g = lerp(temp, 10, 180, 20, 160);
            b = 255;
        } else if (temp < 30) {
            r = lerp(temp, 20, 135, 30, 120);
            g = lerp(temp, 20, 160, 30, 135);
            b = 255;
        } else {
            r = 120;
            g = 130;
            b = 255;
        }
        System.out.println("R: " + r + " G: " + g + " B: " + b);
        return rgbtohsv(r, g, b);
    }
    
    private double[] rgbtohsv( double r, double g, double b) {
	r /= 255;
        g /= 255;
        b /= 255;
        double h, s, v;
        double min = Math.min(Math.min(r, g), b);
	double max = Math.max(Math.max(r, g), b);
	v = max;				
	double delta = max - min;
	if (max != 0) {
            s = delta / max;		
        } else {
            s = 0;
            h = -1;
            return new double[] {h, s, v};
	}
	if (r == max) {
            h = (g - b) / delta;
        } else if (g == max) {
            h = 2 + (b - r) / delta;	
        } else {
            h = 4 + (r - g) / delta;	
            h *= 60;				
        }
	if (h < 0) {
            h += 360;
        }
        System.out.println("H: " + h + " S: " + s + " V: " + v);
        return new double[] {h, s, v};
    }
}
