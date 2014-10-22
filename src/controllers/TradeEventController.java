package controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import models.Pirate;
import models.Planet;
import models.SolarSystem;
import models.Trader;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * TradeEvent FXML Controller class
 *
 * @author Taylor
 */
public class TradeEventController extends RandomEventController implements Initializable {
//    public Trader trader;
//    public Pane pane;
//    public Label otherName;
//    public Button NEButton;
//    public Button NWButton;
//    public Button SEButton;
//    public Button SWButton;
//    public ImageView playerPic;
//    public ImageView otherPic;
//    public Rectangle bubbleBox;
//    public Polygon bubbleArrow;
//    public Label speech;
//
//    public void initialize(URL location, ResourceBundle resources){
//        super.initialize(location, resources);
//
//    }

    @Override
    void configureButtons() {
        NWButton.setText("Trade");
        NWButton.setOnMouseClicked((MouseEvent t) -> {
            trade();
        });

        NEButton.setText("Ignore");
        NEButton.setOnMouseClicked((MouseEvent t) -> {
            exitEvent();
        });

        SEButton.setDisable(true);
        SWButton.setDisable(true);
    }

    @Override
    void configureEncountered() {
        encountered = new Trader("The Trader", gameData.getUniverse().getRandomPlanet());
    }
//
//    public void initialize(URL location, ResourceBundle resources){
//        System.out.println("TraderEncounter");
//        NWButton.setText("Trade");
//        NWButton.setOnMouseClicked((MouseEvent t) -> {
//            trade();
//        });
//
//        NEButton.setText("Ignore");
//        NEButton.setOnMouseClicked((MouseEvent t) -> {
//            ignore();
//        });
//
//        SEButton.setDisable(true);
//        SWButton.setDisable(true);
//        trader = new Trader("The Trader", gameData.getUniverse().getRandomPlanet()); //May want to change name in the future
//        otherName.setText(trader.getName());
//        playerPic.setImage(new Image("/images/current.png"));
//        otherPic.setImage(new Image("/images/unreachable.png"));
//
//
//        speech.setText("What're ya buy'in?");
//    }

    private void trade(){// THIS PROBABLY NEEDS TO BE CHANGED, VERY DIRTY HACK
        Planet temp = gameData.getPlayer().getCurrentPlanet();
        gameData.getPlayer().setCurrentPlanet(((Trader)(encountered)).getOrigin());
        control.setScreen("Market");
        gameData.getPlayer().setCurrentPlanet(temp);
    }

//    private void ignore(){
//        control.setScreen("SolarSystemMap");
//
//    }


}
