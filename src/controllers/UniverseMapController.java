/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import models.GameData;
import models.SolarSystem;

/**
 * Displays and manages the map of the universe.
 *
 * @author Alex, John
 */
public class UniverseMapController implements Initializable {
    public AnchorPane anchor;
    private boolean inSolarSystem;

    private void drawSolarSystems() {
        GameData gameData = GameController.getGameData();
        
        SolarSystem currentSystem = gameData.getSolarSystem();

        for (SolarSystem s : gameData.getUniverse().solarSystems) {
            ImageView image = new ImageView();
            if (s != currentSystem && distance(gameData.getSolarSystem(), s)
                    < gameData.getShip().getFuelAmount()) {
                image.setImage(new Image("/images/star.png"));
                image.setOnMouseEntered((MouseEvent t) -> {
                    ColorAdjust color = new ColorAdjust();
                    color.setBrightness(1);
                    image.setEffect(color);
                });
                image.setOnMouseExited((MouseEvent t) -> {
                    ColorAdjust color = new ColorAdjust();
                    color.setBrightness(0);
                    image.setEffect(color);
                });
            } else if (s != currentSystem) {
                image.setImage(new Image("/images/unreachable.png"));
            } else {
                image.setImage(new Image("/images/current.png"));
            }
            image.setX(s.getX());
            image.setY(s.getY());
            image.setScaleX((double) s.getSun().getRadius() * 2.0 / image.getImage().getWidth());
            image.setScaleY((double) s.getSun().getRadius() * 2.0 / image.getImage().getHeight());
            double dist = distance(gameData.getSolarSystem(), s);
            image.setOnMouseClicked((MouseEvent t) -> {
                if (dist < gameData.getShip().getFuelAmount()) {
                        gameData.getShip().expendFuel(dist);
                        System.out.println(s.name);
                        gameData.setSolarSystem(s);
                        GameController.getControl().setScreen("SolarSystemMap");
                }
            });

            Tooltip systemInfo = new Tooltip(s.getName() + " System" + "\nDistance: "
                    + new DecimalFormat("0.00").format(dist) +" light-years");
            Tooltip.install(image, systemInfo);
            anchor.getChildren().add(image);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchor.setBackground(new Background(
                new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
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
}
