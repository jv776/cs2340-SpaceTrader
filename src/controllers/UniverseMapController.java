/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.GameData;
import models.SolarSystem;
import models.TradeGood;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

/**
 * Displays and manages the map of the universe.
 *
 * @author Alex, John
 */
public class UniverseMapController implements Initializable {
    @FXML
    private AnchorPane universe_anchor;

    private void drawSolarSystems() {
        GameData gameData = GameController.getGameData();

        SolarSystem currentSystem = gameData.getSolarSystem();

        //ImageView ship = new ImageView(new Image("/images/spaceship.jpg"));
        
        for(SolarSystem s : gameData.getUniverse().solarSystems) {
            double dist = distance(gameData.getSolarSystem(), s);
            
            Circle star = new Circle();
            star.setCenterX(s.getX());
            star.setCenterY(s.getY());
            star.setRadius(s.getSun().getRadius());
            Color color = s.getSun().computeColor();
            
            RadialGradient radGrad = new RadialGradient(0,
                0,
                s.getX(),
                s.getY(),
                s.getSun().getRadius(),
                false,
                CycleMethod.NO_CYCLE,
                new Stop(0, color),
                new Stop(0.5, color),
                new Stop(1, Color.BLACK));
            
            star.setFill(radGrad);
            
            ColorAdjust adjust = new ColorAdjust();

            Circle circle = new Circle();
            circle.setCenterX(s.getX());
            circle.setCenterY(s.getY());
            circle.setRadius(s.getSun().getRadius() + 1);
            circle.setFill(Color.YELLOW);
            circle.setVisible(false);

            Tooltip systemInfo = new Tooltip(
                    String.format("%s\nTech Level: %s\nDistance: %.2f light-years\nStatus: %s",
                        s.isDiscovered() ? s.getName() + " System" : "????????",
                        s.isDiscovered() ? s.getTechLevel() : "????????",
                        dist,
                        s.isDiscovered() ? "Discovered" : "Undiscovered"));
            systemInfo.setAutoHide(false);

            if(s != currentSystem && distance(gameData.getSolarSystem(), s)
                    < gameData.getShip().getFuelAmount()) {
                if(s.isDiscovered()) {
                    adjust.setSaturation(.2);
                } else {
                    adjust.setSaturation(-.75);
                }
            } else if(s != currentSystem) {
                if(s.isDiscovered()) {
                    adjust.setBrightness(-.75);
                } else {
                    adjust.setBrightness(-.75);
                    adjust.setSaturation(-.9);
                }
            } else {
                /*ship.setScaleX(0.3);
                ship.setScaleY(0.3);
                ship.setLayoutX(s.getX() - ship.getImage().getWidth() / 2);
                ship.setLayoutY(s.getY() - ship.getImage().getHeight() / 2);
                
                ship.setOnMouseClicked((MouseEvent t) -> {
                    GameController.getControl().setScreen(Screens.SOLAR_SYSTEM_MAP);
                });*/

                adjust.setBrightness(0.25);
                star.setOnMouseEntered((MouseEvent t) -> {
                    circle.setVisible(true);
                });
                star.setOnMouseExited((MouseEvent t) -> {
                    circle.setVisible(false);
                });
            }
            
            star.setOnMouseEntered((MouseEvent t) -> {
                if(distance(gameData.getSolarSystem(), s) 
                        < gameData.getShip().getFuelAmount()) {
                    circle.setVisible(true);
                }
                if (s.isDiscovered() || distance(gameData.getSolarSystem(), s) 
                        < gameData.getShip().getFuelAmount()) {
                    double xCoord = universe_anchor.getScene().getWindow().getX();
                    double yCoord = universe_anchor.getScene().getWindow().getY();
                    systemInfo.show(universe_anchor, xCoord + s.getX() + 30, 
                            yCoord + s.getY() + 40);
                }
            });
            star.setOnMouseExited((MouseEvent t) -> {
                circle.setVisible(false);
                systemInfo.hide();
            });
            
            star.setEffect(adjust);
            circle.setEffect(adjust);

            star.setOnMouseClicked((MouseEvent t) -> {
                if(dist < gameData.getShip().getFuelAmount() && s != currentSystem) {
                    
                    /*boolean moveComplete = false;
                    
                    double targetX = s.getX() - ship.getImage().getWidth() / 2;
                    double xDistance = targetX - ship.getLayoutX();
                    
                    double targetY = s.getY() - ship.getImage().getHeight() / 2;
                    double yDistance = targetY - ship.getLayoutY();
                    
                    double xStep = xDistance / 100.0;
                    double yStep = yDistance / 100.0;
                    
                    double xLoc = ship.getLayoutX();
                    double yLoc = ship.getLayoutY();

                    if (Math.abs(xLoc - targetX) < 1 && Math.abs(yLoc - targetY) < 1) {
                        moveComplete = true;
                    } else {
                        ship.setLayoutX(xLoc + xStep);
                        ship.setLayoutY(yLoc + yStep);
                    }*/
                    
                    gameData.getShip().expendFuel(dist);
                    gameData.setSolarSystem(s);
                    
                    s.discover();
                    double policeEvent = Math.random();
                    double pirateEvent = Math.random();
                    double tradeEvent = Math.random();
                    
                    double rPolice = 0.0;
                    rPolice += gameData.getCargoHold().getQuantity(TradeGood.NARCOTICS) * 0.03;
                    rPolice += gameData.getCargoHold().getQuantity(TradeGood.FIREARMS) * 0.01;
                    
                    double rPirate = 0.0;
                    rPirate += Math.min(gameData.getCargoHold().getCargoQuantity() * .005
                            + gameData.getPlayer().getCredits() * .00001, .5);
                    
                    double rTrader = 0.0;
                    rTrader += gameData.getCargoHold().getCargoQuantity() * .003;
                    
                    if(policeEvent < rPolice) {
                        GameController.getControl().setScreen(Screens.POLICE_EVENT);
                    } else if(pirateEvent < rPirate) {
                        GameController.getControl().setScreen(Screens.PIRATE_EVENT);
                    } else if(tradeEvent < rTrader) {
                        GameController.getControl().setScreen(Screens.TRADE_EVENT);
                    } else {
                        GameController.getControl().setScreen(Screens.SOLAR_SYSTEM_MAP);
                    }
                } else if (s == currentSystem) {
                    GameController.getControl().setScreen(Screens.SOLAR_SYSTEM_MAP);
                }
            });
            
            universe_anchor.getChildren().add(circle);
            universe_anchor.getChildren().add(star);
            
        }
        //universe_anchor.getChildren().add(ship);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawSolarSystems();
        universe_anchor.setBackground(new Background(new BackgroundImage(
                new Image("/images/universe_map.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        )));
    }

    //convert a distance in pixels to a distance in light years
    private double pixelsToLightYears(double px) {
        return 0.2 * px;
    }

    //calculate distance in light years between two solar systems
    private double distance(SolarSystem s1, SolarSystem s2) {
        double dx = s1.getX() - s2.getX();
        double dy = s1.getY() - s2.getY();

        return pixelsToLightYears(Math.sqrt(dx * dx + dy * dy));
    }
}
