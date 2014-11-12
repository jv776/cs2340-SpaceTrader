/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.EllipseBuilder;
import models.Planet;
import models.SolarSystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.fxml.FXML;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.util.Duration;

/**
 * Displays and manages the map of the Solar System.
 * 
 * @author Alex, Taylor
 */
public class SolarSystemMapController implements Initializable {

    @FXML
    private AnchorPane anchor;

    @FXML
    private Button returnButton;
    
    @FXML
    private Label locationLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        anchor.setBackground(new Background(new BackgroundImage(
                new Image("/images/solar_system_map.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        )));
        
        SolarSystem currentSystem = GameController.getGameData().getSolarSystem();
        
        returnButton.setText("Return to Universe");
        locationLabel.setTextFill(Color.WHITE);
        locationLabel.setText(currentSystem.getName() + ": " +
                currentSystem.getTechLevel());

        double x = 300;
        double y = 200;

        Circle star = new Circle();

        star.setCenterX(x);
        star.setCenterY(y);
        star.setRadius(currentSystem.getSun().getRadius() * 4);
        
        Color color = currentSystem.getSun().computeColor();
        
        RadialGradient radGrad = new RadialGradient(0,
                0,
                300,
                200,
                currentSystem.getSun().getRadius() * 4,
                false,
                CycleMethod.NO_CYCLE,
                new Stop(0, color),
                new Stop(0.5, color),
                new Stop(1, Color.TRANSPARENT));
            
        star.setFill(radGrad);
        star.setMouseTransparent(true);
        
        Planet currentPlanet = GameController.getGameData().getPlanet();
        
        int maxDistance = currentSystem.getPlanets()[0].getDistance();
        double scaleFactor = 375.0 / maxDistance / 2;
        
        for (Planet p : currentSystem.getPlanets()) {
            Ellipse orbit = EllipseBuilder.create() //deprecated!
                    .centerX(x) //deprecated!
                    .centerY(y) //deprecated!
                    .radiusX(p.getDistance() * scaleFactor)
                    .radiusY(p.getDistance() * scaleFactor)
                    .strokeWidth(3)
                    .stroke(currentPlanet == p ? Color.GREEN : Color.DARKGRAY)
                    .fill(Color.TRANSPARENT)
                    .build();
            anchor.getChildren().add(orbit);
            
            Circle planet = new Circle();
            planet.setRadius(p.getRadius());
            
            LinearGradient linGrad = new LinearGradient(0, 1, 0, 0, true, 
                    CycleMethod.NO_CYCLE, new Stop(0, p.getColor()), 
                    new Stop(0.3, p.getColor()), new Stop(1, Color.BLACK));
            planet.setFill(linGrad);
            planet.setMouseTransparent(true);
            
            Tooltip planetName = new Tooltip(
                (currentPlanet == p ? "Current Location\n" : "")
                + p.getName() 
                + "\nResource: " + p.getResource());
            
            orbit.setOnMouseEntered((MouseEvent t) -> {
                planet.setRadius(p.getRadius() + 1);
                double xCoord = anchor.getScene().getWindow().getX();
                double yCoord = anchor.getScene().getWindow().getY();
                planetName.show(anchor, xCoord + x + p.getDistance() * scaleFactor + 30, 
                    yCoord + y + 20);
                orbit.setStroke(currentPlanet == p ? Color.GREEN : Color.YELLOW);
                
            });
            
            orbit.setOnMouseExited((MouseEvent t) -> {
                planet.setRadius(p.getRadius());
                planetName.hide();
                orbit.setStroke(currentPlanet == p ? Color.GREEN : Color.DARKGRAY);
            });
            
            orbit.setOnMouseClicked((MouseEvent t) -> {
                GameController.getGameData().setPlanet(p);
                GameController.getControl().setScreen(Screens.SPACE_PORT);
            });
            
            double orbitDuration = p.getDistance() / 5 * (.9 + Math.random() / 5);
            
            PathTransition first = new PathTransition(Duration.seconds(orbitDuration), orbit);
            first.setCycleCount(Animation.INDEFINITE);
            first.setNode(planet);
            first.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
            first.setInterpolator(Interpolator.LINEAR);
            first.playFrom(Duration.seconds(orbitDuration * Math.random()));

            anchor.getChildren().add(planet);

        }
        anchor.getChildren().add(star);
    }

    /**
     * Go back to the map of the universe.
     */
    public void returnToUniverse() {
        GameController.getControl().setScreen(Screens.UNIVERSE_MAP);
    }
}
