/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private long then;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        then = 0;
        s = gameData.getSolarSystem();
        returnButton.setText("Return to Universe");
        anchor.setBackground(new Background(
                new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        anchor.setOnKeyReleased((KeyEvent ke) -> {
            KeyCode code = ke.getCode();
            moveScreen(code);
        });
        for (Planet p : s.getPlanets()) {
            //Image image = new Image("/controllers/asdf.jpg");
            //gc.drawImage(image, s.getX(), s.getY());
//            gc.setFill(Color.BLUE);
//            gc.fillOval(295 + p.getX(), 195 + p.getY(), 10, 10);
//            gc.setFill(Color.YELLOW);
//            gc.fillOval(290, 190, 20, 20);
            Image pic = new Image("/images/star.png");
            ImageView image = new ImageView(pic);
            image.setLayoutX(p.getX() / 2.0 + 295);
            image.setLayoutY(p.getY() / 2.0 + 195);
            
            Label label = new Label(p.getName() + "\nResource: " + p.getResource());
            label.setLayoutX(295 + p.getX() / 2.0 - label.getWidth());
            label.setLayoutY(195 + p.getY() / 2.0 - 40);
            label.setTextFill(Color.WHITE);
            label.setVisible(false);
            
            image.setOnMouseClicked((MouseEvent t) -> {
                gameData.setPlanet(p);
                control.setScreen("Market");
            });
            image.setOnMouseEntered((MouseEvent t) -> {
                label.setVisible(true);
                label.toFront();
                label.setLayoutX(p.getX() / 2.0 + 290 - label.getWidth() / 2.0 + 20 / 2.0);
                if (label.getLayoutX() + label.getWidth() > 600) {
                    label.setLayoutX(label.getLayoutX()
                        - (label.getLayoutX() + label.getWidth() - 600));
                } else if (label.getLayoutX() < 0) {
                    label.setLayoutX(0);
                }
                label.setLayoutY(p.getY() / 2.0 + 190
                        + (p.getY() / 2.0 + 190 >= 200 ? - 10 -label.getHeight() : 10 + 20));
            });
            image.setOnMouseExited((MouseEvent t) -> {
                label.setVisible(false);
            });
            anchor.getChildren().addAll(image, label);
            
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    p.calcLocation(now / 1000000.0 - then);
                    then = now;
                    image.setLayoutX(p.getX() / 2.0 + 295);
                    image.setLayoutY(p.getY() / 2.0 + 195);
                }
            };
            timer.start();
        }
    }

    public void returnToUniverse() {
        gameData.setSolarSystem(null);
        control.setScreen("UniverseMap");
    }
    
    private void moveScreen(KeyCode code) {
        if (code == code.DOWN) {
            
        } 
    }
}
