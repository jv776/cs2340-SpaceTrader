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
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import models.SolarSystem;

/**
 *
 * @author Alex
 */
public class UniverseMapController extends GameController implements Initializable {
    
    public Canvas canvas;
    
    //linear interpolation
    private double lerp(double x, double x0, double y0, double x1, double y1) {
        return y0 + (y1 - y0) * (x - x0) / (x1 - x0);
    }
    
    private Color computeStarColor(SolarSystem s) {
        double temp = s.getSun().getTemperature();
        
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
        
        return new Color(r/255, g/255, b/255, 1.0);
    }
    
    private void drawSolarSystems() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,600,400);
        canvas.setEffect(new Glow(3.0));
        
        SolarSystem currentSystem = gameData.getSolarSystem();
        
        gc.setFill(Color.GREENYELLOW);
        gc.fillOval(currentSystem.getX(), currentSystem.getY(), 8, 8);
        
        for (SolarSystem s : gameData.getUniverse().solarSystems) {
            if (s != currentSystem && distance(gameData.getSolarSystem(), s)
                        < gameData.getShip().getFuelAmount()) {
                gc.setFill(computeStarColor(s));
                gc.fillOval(s.getX(), s.getY(), 5, 5);
            } else if (s != currentSystem) {
                gc.setFill(Color.GRAY);
                gc.fillOval(s.getX(), s.getY(), 5, 5);
            }
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                handleClick(e.getX(), e.getY());
            }
        });
        
        drawSolarSystems();
    }

    //convert a distance in pixels to a distance in light years
    private double pixelsToLightYears(double px) {
        return 0.2 * px;
    }
    
    //calculate distance in light years between two solar systems
    private double distance(SolarSystem s1, SolarSystem s2) {
        double dx = s1.getX() - s2.getX();
        double dy = s1.getY() - s2.getY();
        
        return pixelsToLightYears(Math.sqrt(dx*dx + dy*dy));
    }
    
    private void handleClick(double x, double y) {
        //very inefficient collision test (could be optimized)
        for (SolarSystem s : gameData.getUniverse().solarSystems) {
            if ((Math.abs(s.getX() - x) <= 5) && (Math.abs(s.getY() - y) <= 5)) {
                System.out.println("You clicked on: " + s.getName());
                
                if (distance(gameData.getSolarSystem(), s)
                        < gameData.getShip().getFuelAmount()) {
                    gameData.setPlanet(s.planets[0]);
                    drawSolarSystems();
                    break;
                } else {
                    System.out.println("Too far away");
                }
            }
        }
    }
}
