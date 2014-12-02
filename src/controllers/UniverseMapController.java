/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.GameData;
import models.SolarSystem;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.util.Duration;
import models.Player;

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

        ImageView ship = new ImageView(gameData.getPlayer().getShip().getImage());
        
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
                ship.setScaleX(0.4);
                ship.setScaleY(0.4);
                ship.setOpacity(0.8);
                ship.setRotate(-90);
                ship.setMouseTransparent(true);
                ship.setTranslateX(s.getX() - ship.getImage().getWidth() / 2);
                ship.setTranslateY(s.getY() - ship.getImage().getHeight() / 2);
                
                ship.setOnMouseClicked((MouseEvent t) -> {
                    GameController.getControl().setScreen(Screens.SOLAR_SYSTEM_MAP);
                });

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
                    RotateTransition rotate = new RotateTransition(Duration.millis(500), ship);
                    rotate.setFromAngle(ship.getRotate());
                    if (s.getX() >= currentSystem.getX()) {
                        rotate.setToAngle(Math.atan((s.getY() - currentSystem.getY() * 1.0) / 
                                (s.getX() - currentSystem.getX() * 1.0)) * 180.0 / Math.PI);
                    } else {
                        rotate.setToAngle(Math.atan((s.getY() - currentSystem.getY() * 1.0) / 
                                (s.getX() - currentSystem.getX() * 1.0)) * 180.0 / Math.PI - 180);
                    }

                    MoveTo move = new MoveTo(currentSystem.getX(), currentSystem.getY());
                    LineTo line = new LineTo();
                    line.setX(s.getX());
                    line.setY(s.getY());
                    PathTransition path = new PathTransition();
                    
                    Path flight = new Path();
                    flight.getElements().add(move);
                    flight.getElements().add(line);
                    
                    path.setPath(flight);
                    path.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                    path.setNode(ship);
                    path.setDuration(Duration.seconds(1.5));
                    path.setOnFinished((ActionEvent event) -> {
                        gameData.getShip().expendFuel(dist);
                        gameData.setSolarSystem(s);

                        s.discover();
                        
                        Player p = GameController.getGameData().getPlayer();
                        
                        int creditsAfterPayment = p.getCredits() - p.getDailyCost();
                        System.out.println(creditsAfterPayment);
                        if (creditsAfterPayment < 0) {
                            p.spend(p.getCredits());
                            p.setBounty(p.getBounty() - creditsAfterPayment);
                            String message = "Bounty increased by " + (-creditsAfterPayment) 
                                    + " Credits because daily costs could not be paid.";
                            while (p.getShip().getCrewSize() > 0) {
                                message += "\n" + p.getShip().getCrew().get(0).getName() 
                                        + " has left the ship.";
                                p.getShip().removeCrew(p.getShip().getCrew().get(0));
                            }
                            showUpdate(message);
                        } else {
                            p.spend(p.getDailyCost());
                            GameController.getControl().setScreen(Screens.NEW_RANDOM_EVENT);
                        }
                        
                        
                        /*if(policeEvent < rPolice) {
                            GameController.getControl().setScreen(Screens.POLICE_EVENT);
                        } else if(pirateEvent < rPirate) {
                            GameController.getControl().setScreen(Screens.PIRATE_EVENT);
                        } else if(tradeEvent < rTrader) {
                            GameController.getControl().setScreen(Screens.TRADE_EVENT);
                        } else {
                            GameController.getControl().setScreen(Screens.SOLAR_SYSTEM_MAP);
                        }*/
                    });
                    rotate.setOnFinished((ActionEvent event) -> {
                        path.play();
                    });
                    rotate.play();
                    
                } else if (s == currentSystem) {
                    GameController.getControl().setScreen(Screens.SOLAR_SYSTEM_MAP);
                }
            });
            
            universe_anchor.getChildren().add(circle);
            universe_anchor.getChildren().add(star);
            
        }
        universe_anchor.getChildren().add(ship);
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
    
    @FXML
    private void shipStatus() {
        GameController.getControl().setScreen(Screens.SHIP_STATUS);
    }
    
    private void showUpdate(String message) {
        Label label = new Label(message);
        label.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        label.setWrapText(true);
        label.setMinSize(600, 400);
        label.setMaxSize(600, 400);
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font(15));
        label.setOnMouseClicked((MouseEvent t) -> {
            GameController.getControl().setScreen(Screens.NEW_RANDOM_EVENT);
        });
        universe_anchor.getChildren().add(label);
        
    }
}
