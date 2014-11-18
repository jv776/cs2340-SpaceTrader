/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.EllipseBuilder;
import javafx.scene.text.Font;
import javafx.util.Duration;
import models.Planet;
import models.SolarSystem;

/**
 * Displays and manages the map of the Solar System.
 * 
 * @author Alex, Taylor
 */
public class NewSolarSystemController implements Initializable {

    @FXML private AnchorPane map_anchor;
    @FXML private ScrollPane scroll;
    @FXML private AnchorPane info_anchor;
    
    private boolean leftDown;
    private boolean rightDown;
    private boolean upDown;
    
    private ImageView ship;
    private AnimationTimer timer;
    private SolarSystem currentSystem;
    
    private ArrayList<Planet> planetModels;
    private ArrayList<Circle> planets;
    
    private boolean infoShown;
    
    private Planet shown;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        leftDown = false;
        rightDown = false;
        upDown = false;
        
        initializePlayer();
        timer = getGameLoop();
        planets = new ArrayList<>();
        planetModels = new ArrayList<>();
        
        scroll.setHmin(0.0);
        scroll.setHmax(1400.0);
        scroll.setVmin(0.0);
        scroll.setVmax(1100.0);
        scroll.setPannable(false);
        
        infoShown = false;
        
        Circle star = new Circle();
        
        currentSystem = GameController.getGameData().getSolarSystem();
        
        final int centerX = 1000;
        final int centerY = 750;
        final int scaleFactor = 3;
        star.setCenterX(centerX);
        star.setCenterY(centerY);
        star.setRadius(currentSystem.getSun().getRadius() * 4 * scaleFactor);
        
        Color color = currentSystem.getSun().computeColor();
        
        RadialGradient radGrad = new RadialGradient(0,
                0,
                centerX,
                centerY,
                currentSystem.getSun().getRadius() * 4 * scaleFactor,
                false,
                CycleMethod.NO_CYCLE,
                new Stop(0, color),
                new Stop(0.5, color),
                new Stop(1, Color.TRANSPARENT));
            
        star.setFill(radGrad);
        star.setMouseTransparent(true);
        
        Planet currentPlanet = GameController.getGameData().getPlanet();
        
        int maxDistance = currentSystem.getPlanets()[0].getDistance() * scaleFactor;
        double centerFactor = 1400.0 / maxDistance / 2;
        
        for (Planet p : currentSystem.getPlanets()) {
            Ellipse orbit = EllipseBuilder.create() //deprecated!
                    .centerX(centerX) //deprecated!
                    .centerY(centerY) //deprecated!
                    .radiusX(p.getDistance() * centerFactor * scaleFactor)
                    .radiusY(p.getDistance() * centerFactor * scaleFactor)
                    .strokeWidth(2)
                    .stroke(currentPlanet == p ? Color.GREEN : Color.DARKGRAY)
                    .fill(Color.TRANSPARENT)
                    .build();
            map_anchor.getChildren().add(orbit);
            
            Circle planet = new Circle();
            planet.setRadius(p.getRadius() * scaleFactor);
            
            LinearGradient linGrad = new LinearGradient(0, 1, 0, 0, true, 
                    CycleMethod.NO_CYCLE, new Stop(0, p.getColor()), 
                    new Stop(0.3, p.getColor()), new Stop(1, Color.BLACK));
            planet.setFill(linGrad);
            planet.setMouseTransparent(true);
            
//            Tooltip planetName = new Tooltip(
//                (currentPlanet == p ? "Current Location\n" : "")
//                + p.getName() 
//                + "\nResource: " + p.getResource());
            
            orbit.setOnMouseEntered((MouseEvent t) -> {
//                planet.setRadius(p.getRadius() + 1);
//                double xCoord = scroll.getScene().getWindow().getX();
//                double yCoord = map_anchor.getScene().getWindow().getY();
//                planetName.show(anchor, xCoord + x + p.getDistance() * scaleFactor + 30, 
//                    yCoord + y + 20);
                orbit.setStroke(currentPlanet == p ? Color.GREEN : Color.YELLOW);
                
            });
            
            orbit.setOnMouseExited((MouseEvent t) -> {
                //planet.setRadius(p.getRadius());
                //lanetName.hide();
                orbit.setStroke(currentPlanet == p ? Color.GREEN : Color.DARKGRAY);
            });
            
            orbit.setOnMouseClicked((MouseEvent t) -> {
                GameController.getGameData().setPlanet(p);
                GameController.getControl().setScreen(Screens.SPACE_PORT);
            });
            
            double orbitDuration = scaleFactor * p.getDistance() / 5 * (.9 + Math.random() / 5);
            
            PathTransition first = new PathTransition(Duration.seconds(orbitDuration), orbit);
            first.setCycleCount(Animation.INDEFINITE);
            first.setNode(planet);
            first.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            first.setInterpolator(Interpolator.LINEAR);
            first.playFrom(Duration.seconds(orbitDuration * Math.random()));

            planets.add(planet);
            planetModels.add(p);
            map_anchor.getChildren().add(planet);

        }
        map_anchor.getChildren().add(star);
        
        timer.start();
        
    }
    
    private AnimationTimer getGameLoop() {
        return new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (leftDown) {
                    ship.setRotate(ship.getRotate() - 5);
                    if (ship.getRotate() < 0) {
                        ship.setRotate(355);
                    }
                }
                if (rightDown) {
                    ship.setRotate((ship.getRotate() + 5));
                    if (ship.getRotate() == 360) {
                        ship.setRotate(0);
                    }
                }
                if (upDown) {
                    double delta = ship.getImage().getWidth() * (1 - ship.getScaleX()) / 2;
                    ship.setTranslateX(Math.max(-delta, Math.min((ship.getTranslateX() + ((4)
                            * Math.cos(Math.toRadians(ship.getRotate())))), 2000 - ship.getImage().getWidth() + delta)));
                    ship.setTranslateY(Math.max(-delta, Math.min((ship.getTranslateY() + ((4)
                            * Math.sin(Math.toRadians(ship.getRotate())))), 1500 - ship.getImage().getWidth() + delta)));
                    centerOnScreen(ship.getTranslateX() + ship.getImage().getWidth() * ship.getScaleX() / 2,
                            ship.getTranslateY() + ship.getImage().getHeight() * ship.getScaleY() / 2);
                }
                
                
                Planet closest = getNearestDisplayable();
                if (closest != null) {
                    if (!infoShown) {
                        shown = closest;
                        Label name = new Label(closest.getName());
                        name.setMinSize(600, 100);
                        name.setFont(Font.font(30));
                        name.setAlignment(Pos.CENTER);
                        info_anchor.getChildren().add(name);
                        slideInfoUp();
                    }
                } else {
                    if (infoShown) {
                        slideInfoDown();
                    }
                }
                
            }
        };
    }
    
    private void initializePlayer() {
        int eventCenterX = 100;
        int eventCenterY = 750;

        ship = new ImageView(GameController.getGameData().getPlayer().getShip().getImage());
        ship.setScaleX(.3);
        ship.setScaleY(.3);
        ship.setTranslateX(eventCenterX - ship.getImage().getWidth() / 2);
        ship.setTranslateY(eventCenterY - ship.getImage().getHeight() / 2);

        map_anchor.setOnKeyPressed((KeyEvent k) -> {
            if (k.getCode() == KeyCode.LEFT) {
                leftDown = true;
            } else if (k.getCode() == KeyCode.RIGHT) {
                rightDown = true;
            }
            if (k.getCode() == KeyCode.UP) {
                upDown = true;
            }
        });

        map_anchor.setOnKeyReleased((KeyEvent k) -> {
            if (k.getCode() == KeyCode.LEFT) {
                leftDown = false;
            } else if (k.getCode() == KeyCode.RIGHT) {
                rightDown = false;
            }
            if (k.getCode() == KeyCode.UP) {
                upDown = false;
            }
        });
        
                
        FadeTransition fade = new FadeTransition(Duration.millis(200), ship);
        fade.setOnFinished((ActionEvent t) -> {
            centerOnScreen(ship.getTranslateX() + ship.getImage().getWidth() * ship.getScaleX() / 2,
                    ship.getTranslateY() + ship.getImage().getHeight() * ship.getScaleY() / 2);
            scroll.setMouseTransparent(true);
            map_anchor.requestFocus();
        });
        fade.play();
        
        map_anchor.getChildren().add(ship);
    }
    
    private void centerOnScreen(double x, double y) {
        if (x < 300) {
            scroll.setHvalue(0.0);
        } else if (x > 1700) {
            scroll.setHvalue(1400.0);
        } else {
            scroll.setHvalue(x - 300);
        }

        if (y < 200) {
            scroll.setVvalue(0.0);
        } else if (y > 1300) {
            scroll.setVvalue(1100.0);
        } else {
            scroll.setVvalue(y - 200);
        }
    }

    private Planet getNearestDisplayable() {
        Circle closest = null;
        double closestD = Double.MAX_VALUE;
        for (Circle p : planets) {
            double distance = Math.pow(p.getTranslateX() + p.getRadius() - (ship.getTranslateX() + ship.getImage().getWidth() / 2), 2)
                    + Math.pow(p.getTranslateY() + p.getRadius() - (ship.getTranslateY() + ship.getImage().getHeight() / 2), 2);
            if (distance < closestD && distance < Math.pow(100, 2)) {
                closestD = distance;
                System.out.println(closestD);
                closest = p;
            }
        }
        if (closest != null) {
            return planetModels.get(planets.indexOf(closest));
        } else {
            return null;
        }
    }
    
    private void slideInfoDown() {
        TranslateTransition translate = new TranslateTransition(Duration.millis(500), info_anchor);
        translate.setFromX(0);
        translate.setToX(0);
        translate.setFromY(info_anchor.getTranslateY());
        translate.setToY(100);
        translate.setOnFinished((ActionEvent t) -> {
            info_anchor.getChildren().clear();
        });
        translate.play();
        infoShown = false;
        
    }
    
    private void slideInfoUp() {
        TranslateTransition translate = new TranslateTransition(Duration.millis(500), info_anchor);
        translate.setFromX(0);
        translate.setToX(0);
        translate.setFromY(info_anchor.getTranslateY());
        translate.setToY(0);
        translate.play();
        infoShown = true;
    }
}