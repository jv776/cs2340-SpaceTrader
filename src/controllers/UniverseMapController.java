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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import models.SolarSystem;

/**
 *
 * @author Alex
 */
public class UniverseMapController extends GameController implements Initializable {

    
    public AnchorPane anchor;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchor.setBackground(new Background(
                new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        for (SolarSystem s : gameData.getUniverse().solarSystems) {
//            //Image image = new Image("/controllers/asdf.jpg");
//            //gc.drawImage(image, s.getX(), s.getY());
//            gc.setFill(Color.BLUE);
//            gc.fillOval(s.getX(), s.getY(), 5, 5);
            Image pic = new Image("/images/star.png");
            ImageView image = new ImageView(pic);
            image.setScaleX(s.getSize() / 20.0);
            image.setScaleY(s.getSize() / 20.0);
            
            Label label = new Label(s.getName() 
                    + "\nTechLevel: " + s.getTechLevel());
            label.setBorder(new Border(new BorderStroke(
                    Color.LIGHTGRAY,
                    BorderStrokeStyle.SOLID,
                    CornerRadii.EMPTY,
                    BorderWidths.DEFAULT,
                    Insets.EMPTY
            )));
            label.setBackground(new Background(
                    new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            
            
            double offset = label.getWidth();
            label.setVisible(false);
            label.setTextFill(Color.BLACK);
            
            image.setLayoutX(s.getX());
            image.setLayoutY(s.getY());
            image.setId(s.getName());
            
            image.setOnMouseClicked((MouseEvent t) -> {
                gameData.setSolarSystem(s);
                control.setScreen("SolarSystemMap");
            });
            image.setOnMouseEntered((MouseEvent t) -> {
                label.setVisible(true);
                label.toFront();
                label.setLayoutX(s.getX() - label.getWidth() / 2.0 + s.getSize() / 2.0);
                if (label.getLayoutX() + label.getWidth() > 600) {
                    label.setLayoutX(label.getLayoutX()
                        - (label.getLayoutX() + label.getWidth() - 600));
                } else if (label.getLayoutX() < 0) {
                    label.setLayoutX(0);
                }
                label.setLayoutY(s.getY() 
                        + (s.getY() >= 200 ? - 10 -label.getHeight() : 10 + s.getSize()));
            });
            image.setOnMouseExited((MouseEvent t) -> {
                label.setVisible(false);
            });
            anchor.getChildren().addAll(image, label);
        }
    }
}
