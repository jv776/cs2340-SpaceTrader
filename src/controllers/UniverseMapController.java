/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.GameData;
import models.SolarSystem;
import models.TradeGood;

import java.net.URL;
import java.util.ResourceBundle;

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

        for (SolarSystem s : gameData.getUniverse().solarSystems) {
            ImageView image = new ImageView();

            image.setImage(new Image("/images/star.png"));

            ColorAdjust color = new ColorAdjust();

            Circle circle = new Circle();
            circle.setCenterX(s.getX() + image.getImage().getWidth() / 2.0);
            circle.setCenterY(s.getY() + image.getImage().getHeight() / 2.0);
            circle.setRadius(s.getSun().getRadius() + 1);
            circle.setFill(Color.YELLOW);
            circle.setVisible(false);

            image.setScaleX((double) s.getSun().getRadius() * 2.0 / image.getImage().getWidth());
            image.setScaleY((double) s.getSun().getRadius() * 2.0 / image.getImage().getHeight());
            image.setX(s.getX());
            image.setY(s.getY());


            if (s != currentSystem && distance(gameData.getSolarSystem(), s)
                    < gameData.getShip().getFuelAmount()) {
                if (s.isDiscovered()) {
                    color.setBrightness(-0.15);
                } else {
                    color.setBrightness(0.25);
                    color.setHue(0.5);
                }
                image.setOnMouseEntered((MouseEvent t) -> {
                    circle.setVisible(true);
                });
                image.setOnMouseExited((MouseEvent t) -> {
                    circle.setVisible(false);
                });
            } else if (s != currentSystem) {
                if (s.isDiscovered()) {
                    color.setSaturation(-0.5);
                    color.setBrightness(-0.75);
                } else {
                    color.setHue(0.5);
                    color.setSaturation(-0.5);
                    color.setBrightness(-0.75);
                }
            } else {
                color.setBrightness(1);
                color.setSaturation(-1);
                image.setOnMouseEntered((MouseEvent t) -> {
                    circle.setVisible(true);
                });
                image.setOnMouseExited((MouseEvent t) -> {
                    circle.setVisible(false);
                });
            }
            image.setEffect(color);
            double dist = distance(gameData.getSolarSystem(), s);

            image.setOnMouseClicked((MouseEvent t) -> {
                if (dist < gameData.getShip().getFuelAmount()) {
                    gameData.getShip().expendFuel(dist);
                    System.out.println(s.getName());
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

                    if (policeEvent < rPolice) {
                        GameController.getControl().setScreen(Screens.POLICE_EVENT);
                    } else if (pirateEvent < rPirate) {
                        GameController.getControl().setScreen(Screens.PIRATE_EVENT);
                    } else if (tradeEvent < rTrader) {
                        GameController.getControl().setScreen(Screens.TRADE_EVENT);
                    } else {
                        GameController.getControl().setScreen(Screens.SOLAR_SYSTEM_MAP);
                    }
                }
            });

            Tooltip systemInfo = new Tooltip(
                    String.format("%s\nTech Level: %s\nDistance: %.2f light-years",
                            s.isDiscovered() ? s.getName() + " System" : "????????",
                            s.isDiscovered() ? s.getTechLevel() : "????????",
                            dist));
            Tooltip.install(image, systemInfo);
            universe_anchor.getChildren().add(circle);
            universe_anchor.getChildren().add(image);
        }
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
