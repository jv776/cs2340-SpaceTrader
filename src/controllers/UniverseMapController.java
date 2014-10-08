/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.Planet;
import models.SolarSystem;

/**
 * Displays and manages the map of the universe.
 * 
 * @author Alex, John
 */
public class UniverseMapController extends GameController implements Initializable {
    
    public Canvas canvas;
    private boolean inSolarSystem;
    private Circle test;
    
    //temporary fix to make planet locations clickable and (temporarily) persistent
    private HashMap<Planet, Circle> planetSaver;
    
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
    
    /*
    private int kmE6ToPixels(int dist) {
        return (int) (dist / 2.5);
    }
    */
    
    private void drawSolarSystems() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,600,400);
        canvas.setEffect(new Glow(3.0));
        
        SolarSystem currentSystem = gameData.getSolarSystem();
        
        gc.setFill(Color.GREENYELLOW);
        gc.fillOval(currentSystem.getX(), currentSystem.getY(),
                3 + currentSystem.getSun().getRadius(),
                3 + currentSystem.getSun().getRadius());

        for (SolarSystem s : gameData.getUniverse().solarSystems) {
            if (s != currentSystem && distance(gameData.getSolarSystem(), s)
                        < gameData.getShip().getFuelAmount()) {
                gc.setFill(computeStarColor(s));
                gc.fillOval(s.getX(), s.getY(), s.getSun().getRadius(), 
                        s.getSun().getRadius());
            } else if (s != currentSystem) {
                gc.setFill(Color.GRAY);
                gc.fillOval(s.getX(), s.getY(), s.getSun().getRadius(), 
                        s.getSun().getRadius());
            }
        }
    }
    
    private void drawPlanets() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,600,400);
        canvas.setEffect(new Glow(3.0));
        
        SolarSystem currentSystem = gameData.getSolarSystem();
        
        int x = 300;
        int y = 200;

        gc.setFill(computeStarColor(currentSystem));
        gc.fillOval(x, y, currentSystem.getSun().getRadius() * 2 + 5,
                currentSystem.getSun().getRadius() * 2 + 5);
        
        Planet currentPlanet = gameData.getPlanet();
        
        if (currentPlanet.getSolarSystem() == currentSystem) {
            Circle c;
            
            if (planetSaver.containsKey(currentPlanet)) {
                c = planetSaver.get(currentPlanet);
            } else {
                double theta = Math.random() * 2 * Math.PI;
                int radius = currentPlanet.getDistance();
                
                c = new Circle(x + Math.cos(theta) * radius,
                        y + Math.sin(theta) * radius, 3
                        + Math.sqrt(currentPlanet.getRadius()), Color.DIMGRAY);
                
                planetSaver.put(currentPlanet, c);
            }
            
            gc.setFill(Color.GREENYELLOW);
            gc.fillOval(c.getCenterX() - c.getRadius(), c.getCenterY()
                        - c.getRadius(), c.getRadius() * 1.2, c.getRadius() * 1.2);
        }
        
        for (Planet p : currentSystem.planets) {
            if (p != gameData.getPlanet()) {
                Circle c;
                
                if (planetSaver.containsKey(p)) {
                    c = planetSaver.get(p);
                } else {
                    double theta = Math.random() * 2 * Math.PI;
                    int radius = p.getDistance();

                    //right now every planet looks like a wasteland
                    c = new Circle(x + Math.cos(theta) * radius,
                        y + Math.sin(theta) * radius, 3
                        + Math.sqrt(p.getRadius()), Color.DIMGRAY);

                    planetSaver.put(p, c);
                }
                
                gc.setFill(c.getFill());
                gc.fillOval(c.getCenterX() - c.getRadius(), c.getCenterY()
                        - c.getRadius(), c.getRadius(), c.getRadius());
            }
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inSolarSystem = false;
        planetSaver = new HashMap<>();

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
        if (inSolarSystem) {
            for (Planet p : gameData.getSolarSystem().planets) {
                if (planetSaver.get(p).contains(x, y)) {
                    gameData.setPlanet(p);
                    
                    control.setScreen("Market");
                }
            }
        } else {
            //very inefficient collision test (could be optimized)
            for (SolarSystem s : gameData.getUniverse().solarSystems) {
                int radius = s.getSun().getRadius();
                
                if ((Math.abs(s.getX() - x) <= radius)
                        && (Math.abs(s.getY() - y) <= radius)) {
                    double dist = distance(gameData.getSolarSystem(), s);
                    
                    if (dist < gameData.getShip().getFuelAmount()) {
                        gameData.getShip().expendFuel(dist);
                        System.out.println(s.name);
                        gameData.setSolarSystem(s);
                        inSolarSystem = true;
                        drawPlanets();
                        break;
                    }
                }
            }
        }
    }
}
