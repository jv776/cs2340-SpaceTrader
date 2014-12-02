package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.scene.AmbientLight;
import javafx.scene.PointLight;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import models.Planet;
import models.Player;

/**
 * @author Alex Poole
 */
public class NewSpacePortController implements Initializable {
    
    @FXML 
    private AnchorPane anchor;
    
    @FXML
    private Button btnMarket;

    @FXML
    private Button button_Refuel, button_Repair;

    @FXML
    private Button btn_ShipYard; 

    @FXML
    private Button btn_Map;

    @FXML
    private Button btnNews;

    @FXML
    private Button btn_StockMarket;

    @FXML
    private ProgressBar health_bar, fuel_bar;

    @FXML
    private Label spacePortLabel;
    
    @FXML
    private Label repairCost, refuelCost;

    @FXML private Label solar_system;
    @FXML private Label planet_label;
    @FXML private Label tech_level;
    @FXML private Label resource;

    private Player player;
    private Planet planet;

    public void onMarketClicked() {
        GameController.getControl().setScreen(Screens.MARKET);
    }

    @FXML
    private void onShipYardClicked() {
        GameController.getControl().setScreen(Screens.SHIP_YARD);
    }

    @FXML
    private void onMapClicked() {
        GameController.getControl().setScreen(Screens.SOLAR_SYSTEM_MAP);
    }

    @FXML
    private void onNewsClicked() {
        System.out.println("NEWS CLICKED WOOOOO!");
    }

    @FXML
    private void onStockMarketClicked() {
        System.out.println("STOCK MARKET CLICKED WOOOO!");
    }

    @FXML
    void handleRefuelButton() {
        player.spend((int) (Math.ceil(player.getShip().getFuelCapacity()
                - player.getShip().getFuelAmount()) * player.getShip().getFuelCost()));
        player.getShip().refuel();

        fuel_bar.setProgress(1.0);
        button_Refuel.setDisable(true);
        refuelCost.setVisible(false);
    }

    @FXML
    void handleRepairButton() {
        int cost = (int)((player.getShip().getMaxHullStrength()
                - player.getShip().getHullStrength()) * player.getShip().getRepairCost());
        player.spend(cost);
        player.getShip().repair();

        health_bar.setProgress(1.0);
        button_Repair.setDisable(true);
        repairCost.setVisible(false);
    }

    @FXML
    private void shipCustomization() {
        GameController.getControl().setScreen(Screens.SHIP_CUSTOMIZATION);
    }
    
    /*@FXML
    private void tavern() {
        GameController.getControl().setScreen(Screens.TAVERN);
    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        player = GameController.getGameData().getPlayer();
        planet = GameController.getGameData().getPlanet();
        
        if (GameController.getGameData().getSolarSystem().getTechLevel().ordinal() < 4) {
            btn_ShipYard.setDisable(true);
        }

        if (GameController.getGameData().getShip().getFuelCapacity()
                == GameController.getGameData().getShip().getFuelAmount()) {
            button_Refuel.setDisable(true);
            refuelCost.setVisible(false);
        }

        if (GameController.getGameData().getShip().getHullStrength()
                == GameController.getGameData().getShip().getMaxHullStrength()) {
            button_Repair.setDisable(true);
            repairCost.setVisible(false);
        }
        
        int repcost = (int)((player.getShip().getMaxHullStrength()
                - player.getShip().getHullStrength()) * player.getShip().getRepairCost());
        repairCost.setText(repcost + " Credits");
        
        int refcost = (int) (Math.ceil(player.getShip().getFuelCapacity()
                - player.getShip().getFuelAmount()) * player.getShip().getFuelCost());
        refuelCost.setText(refcost + " Credits");

        btn_Map.setText("Return to " + GameController.getGameData().getSolarSystem().getName());
        solar_system.setText(GameController.getGameData().getSolarSystem().getName());
        planet_label.setText(GameController.getGameData().getPlanet().getName());
        tech_level.setText(GameController.getGameData().getSolarSystem()
            .getTechLevel().toString());
        resource.setText(GameController.getGameData().getPlanet()
            .getResource().toString());
        fuel_bar.setProgress(GameController.getGameData().getShip().getFuelAmount()
            / (double)GameController.getGameData().getShip().getFuelCapacity());
        health_bar.setProgress(GameController.getGameData().getShip().getHullStrength()
            / (double)GameController.getGameData().getShip().getMaxHullStrength());
        
        Sphere sphere = new Sphere(planet.getRadius() * 10.0, 500);
        
        sphere.setTranslateX(300.0);
        sphere.setTranslateY(200.0);

        PhongMaterial earthMaterial = new PhongMaterial();
        earthMaterial.setDiffuseMap(planet.getDiffuseMap());
        earthMaterial.setBumpMap(planet.getBumpMap());
        
        sphere.setMaterial(earthMaterial);
        
        Rotate ry = new Rotate();
        ry.setAxis(Rotate.Y_AXIS);
        ry.setPivotZ(1000);
        
        Rotate nry = new Rotate();
        nry.setAxis(Rotate.Y_AXIS);
        sphere.getTransforms().add(nry);
        
        PointLight light = new PointLight();
        light.setTranslateX(300);
        light.setTranslateY(200);
        light.setTranslateZ(-1000);
        light.getTransforms().add(ry);
        
        AmbientLight light2 = new AmbientLight(Color.LIGHTGRAY);
        
        anchor.getChildren().add(sphere);
        anchor.getChildren().addAll(light, light2);
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                ry.setAngle(ry.getAngle() + 0.5);
                nry.setAngle(nry.getAngle() - 0.5);
            }
        };
        timer.start();
        
        
    }
}
