package com.hitchhikers.spacetrader.controllers;

import com.hitchhikers.spacetrader.Utils;
import com.hitchhikers.spacetrader.models.*;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 *
 * @author Alex
 */
public class NewRandomEventController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private ScrollPane scroll;

    private AnimationTimer timer;

    private ImageView ship;
    private ArrayList<Encounterable> enemies;
    private ArrayList<ImageView> enemyShips;
    private ArrayList<Rectangle> enemyHealths;
    private ArrayList<Rectangle> enemyShields;
    private ArrayList<Particle> particles;

    private Rectangle playerHealth;
    private Rectangle playerShield;

    boolean leftDown;
    boolean rightDown;
    boolean upDown;
    boolean space;
    boolean shift;
    boolean enter;

    private int escapeCount;
    private int delay;
    
    private ProgressBar escapeBar;
    private Label escapeLabel;

    int pilotSP;
    int fighterSP;

    private ArrayList<Shot> shots;

    private Label endLabel;
    private Button endButton;
    private ImageView endImage;

    private boolean endGame;
    private boolean gamePaused;
    private Label pausedLabel;
    
    private int difficulty;
    private String name;
    private Encounterable enemy;
    
    private AudioClip explode;
    private AudioClip laser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Player player = GameController.getGameData().getPlayer();


        double policeChance = 0.05 + player.getShip().getCargoHold().getQuantity(TradeGood.NARCOTICS) * 0.015
                + player.getShip().getCargoHold().getQuantity(TradeGood.FIREARMS) * 0.005;

        double pirateChance = 0.02 + Math.min(player.getShip().getCargoHold().getCargoQuantity() * .001
                + player.getCredits() * .00001, .3);

        double traderChance = 0.02;

        double bountyHunterChance = player.getBounty() * .00001;

        name = "";

        if (Math.random() < 1.0) {//policeChance) {
            name = "Police";
            enemy = new Police("Bob");
        } else if (Math.random() < pirateChance) {
            name = "Pirate";
            enemy = new Pirate("Bob");
        } else if (Math.random() < traderChance) {
            name = "Trader";
            enemy = new Trader("Bob", GameController.getGameData().getUniverse().getRandomPlanet());
        } else if (Math.random() < bountyHunterChance) {
            name = "Dreadnought";
            enemy = new Dreadnought("Bob");
        } else {
            GameController.getControl().setScreen(Screens.SOLAR_SYSTEM_MAP);
        }

        difficulty = 1;
        int totalCredits = player.getTotalCredits();

        if (totalCredits > 500000) {
            difficulty = 5;
        } else if (totalCredits > 200000) {
            difficulty = 4;
        } else if (totalCredits > 50000) {
            difficulty = 3;
        } else if (totalCredits > 10000) {
            difficulty = 2;
        }

        anchor.setBackground(new Background(new BackgroundImage(
                new Image(Utils.getResourceAsStream("images/stars_1.jpg")),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        )));
        scroll.setHmin(0.0);
        scroll.setHmax(1400.0);
        scroll.setVmin(0.0);
        scroll.setVmax(1100.0);
        scroll.setPannable(true);

        timer = getGameLoop();

        explode = new AudioClip(getClass().getResource("/com/hitchhikers/spacetrader/sounds/explosion.mp3").toString());
        laser = new AudioClip(getClass().getResource("/com/hitchhikers/spacetrader/sounds/laser.mp3").toString());
        laser.setVolume(0.5); 
        
        pausedLabel = new Label("PAUSED");
        pausedLabel.setFont(Font.font(30));
        pausedLabel.setMinSize(600, 400);
        pausedLabel.setTranslateX(scroll.getHvalue());
        pausedLabel.setTranslateY(scroll.getVvalue());
        pausedLabel.setMouseTransparent(true);
        pausedLabel.setAlignment(Pos.CENTER);
        pausedLabel.setOnKeyPressed((KeyEvent t) -> {
            if (t.getCode() == KeyCode.ENTER) {
                unPauseGame();
            }
        });

        shots = new ArrayList<>();
        enemies = new ArrayList<>();
        enemyShips = new ArrayList<>();
        enemyHealths = new ArrayList<>();
        enemyShields = new ArrayList<>();
        particles = new ArrayList<>();

        leftDown = false;
        rightDown = false;
        upDown = false;
        space = false;
        shift = false;
        enter = false;
        
        escapeCount = 0;
        escapeBar = new ProgressBar();
        escapeBar.setProgress(0.0);
        escapeBar.setMinSize(200.0, 30.0);
        escapeBar.setVisible(false);
        escapeLabel = new Label("Flee: " + (int)(escapeCount / 100.0) + "%");
        escapeLabel.setText((int)(escapeCount / 100.0) + "%");
        escapeLabel.setMinSize(200.0, 30.0);
        escapeLabel.setVisible(false);
        escapeLabel.setAlignment(Pos.CENTER);
        
        anchor.getChildren().add(escapeBar);
        anchor.getChildren().add(escapeLabel);

        pilotSP = getPlayerShip().getOwner().getPilotSkillPoints();
        fighterSP = getPlayerShip().getOwner().getFighterSkillPoints();
        if (!name.equals("")) {
            initializePlayer();
            initializeEnemy(name, difficulty * 4 + player.getBounty() / 1000);

            for (Encounterable enc : enemies) {
                enc.equipForDifficulty(difficulty);
            }

            encounter(enemy);
        }

    }

    private void encounter(Encounterable enc) {
        Label title = new Label("You have encountered " + (name.equals("Police") ? "the Police!" : name + "s!"));
        title.setMinSize(600, 200);
        title.setMaxSize(600, 200);
        title.setWrapText(true);
        title.setFont(Font.font(40));
        title.setAlignment(Pos.CENTER);
        title.toFront();

        Button okayButton = new Button("Continue");
        okayButton.setFont(Font.font(15));
        okayButton.setMinSize(200, 50);
        okayButton.setOnMouseClicked((MouseEvent t) -> {
            fadeOut(okayButton);
            FadeTransition fadeOut = new FadeTransition(Duration.millis(500), title);
            fadeOut.setOnFinished((ActionEvent e) -> {
                anchor.getChildren().remove(title);
                enemyText(enc);
            });
            fadeOut.play();
        });

        FadeTransition fade = new FadeTransition(Duration.millis(200), title);
        fade.setOnFinished((ActionEvent t) -> {
            centerOnScreen(ship.getTranslateX() + ship.getImage().getWidth() * ship.getScaleX() / 2,
                    ship.getTranslateY() + ship.getImage().getHeight() * ship.getScaleY() / 2);
            title.setTranslateX(scroll.getHvalue());
            title.setTranslateY(scroll.getVvalue());
            okayButton.setTranslateX(scroll.getHvalue() + 200);
            okayButton.setTranslateY(scroll.getVvalue() + 300);
        });
        fade.play();
        anchor.getChildren().add(title);
        anchor.getChildren().add(okayButton);
    }

    private void enemyText(Encounterable enc) {
        ImageView image = new ImageView(enc.getShip().getImage());
        image.setScaleX(100.0 / image.getImage().getWidth());
        image.setScaleY(100.0 / image.getImage().getHeight());
        image.setTranslateX(scroll.getHvalue() + (image.getImage().getWidth() * image.getScaleX() - image.getImage().getWidth()) / 2.0);
        image.setTranslateY(scroll.getVvalue() + 300 + (image.getImage().getHeight() * image.getScaleY() - image.getImage().getHeight()) / 2.0);

        Label speech = new Label(enc.getWelcomeText());
        speech.setWrapText(true);
        speech.setTranslateX(scroll.getHvalue() + 112.5);
        speech.setTranslateY(scroll.getVvalue() + 312.5);
        speech.setMinSize(475, 75);
        speech.setMaxSize(475, 75);
        speech.setFont(Font.font(15.0));
        speech.setAlignment(Pos.CENTER);
        speech.setId("encountered_text");
        speech.setOpacity(0.0);

        Button engage = new Button(enemy instanceof Trader ? "Steal" : "Fight");
        engage.setMinSize(150, 50);
        engage.setTranslateX(scroll.getHvalue() + 75);
        engage.setTranslateY(scroll.getVvalue() + 175);

        Button comply = new Button(enemy instanceof Trader ? "Trade" : "Comply");
        comply.setMinSize(150, 50);
        comply.setTranslateX(scroll.getHvalue() + 375);
        comply.setTranslateY(scroll.getVvalue() + 175);

        engage.setOnMouseClicked((MouseEvent t) -> {
            fadeOut(speech);
            fadeOut(engage);
            fadeOut(image);
            fadeOut(comply);
            if (GameController.getGameData().getPlayer().isFirstFight()) {
                anchor.setEffect(new ColorAdjust(0, 0, -0.5, 0));
                Tooltip controls = new Tooltip("-Use Left and Right buttons to rotate your ship."
                        + "\n-Use Up to move your ship in the direction you are facing."
                        + "\n-Use Space Bar to fire lasers if you have any equipped."
                        + "\n-Hold down Shift to escape from battle."
                        + "\n-Click outside box to start fight.");
                controls.setMinSize(600, 200);
                controls.setFont(Font.font(20));
                controls.setX(scroll.getHvalue() - 25);
                controls.setY(scroll.getVvalue() - 200);
               
                anchor.setOnMouseClicked((MouseEvent e) -> {
                    controls.hide();
                    GameController.getGameData().getPlayer().finishedFirstFight();
                    anchor.removeEventHandler(MouseEvent.MOUSE_CLICKED, anchor.getOnMouseClicked());
                    anchor.setEffect(new ColorAdjust(0, 0, 0, 0));
                    anchor.getChildren().remove(controls);
                    scroll.setMouseTransparent(true);
                    anchor.requestFocus();
                    timer.start();
                });
                
                controls.show(anchor.getScene().getWindow());
            } else {
                scroll.setMouseTransparent(true);
                anchor.requestFocus();
                timer.start();
            }
        });

        comply.setOnMouseClicked((MouseEvent t) -> {
            boolean fightAnyway = false;
            if (enc instanceof Pirate) {
                fightAnyway = pirateComply();
            } else if (enc instanceof Police) {
                fightAnyway = policeComply();
            } else if (enc instanceof Trader) {
                traderComply();
            } else {
                fightAnyway = dreadnoughtComply();
            }
            
            if (fightAnyway) {
                fadeOut(engage);
                fadeOut(comply);
                speech.setText(enc.getBadComplyText());
                Button cont = new Button("Continue");
                cont.setMinSize(200, 50);
                cont.setTranslateX(scroll.getHvalue() + 200);
                cont.setTranslateY(scroll.getVvalue() + 175);
                cont.setOnMouseClicked((MouseEvent e) -> {
                    fadeOut(cont);
                    fadeOut(speech);
                    fadeOut(image);
                    scroll.setMouseTransparent(true);
                    anchor.requestFocus();
                    timer.start();
                });
                fadeIn(cont);
            } else {
                fadeOut(engage);
                fadeOut(comply);
                speech.setText(enc.getGoodComplyText());
                Button cont = new Button("Continue");
                cont.setMinSize(200, 50);
                cont.setTranslateX(scroll.getHvalue() + 200);
                cont.setTranslateY(scroll.getVvalue() + 175);
                cont.setOnMouseClicked((MouseEvent e) -> {
                    GameController.getControl().setScreen(Screens.SOLAR_SYSTEM_MAP);
                });
                fadeIn(cont);
            }
        });

        anchor.getChildren().add(speech);

        fadeIn(image);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), speech);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setOnFinished((ActionEvent e) -> {
            fadeIn(engage);
            fadeIn(comply);
        });
        fadeIn.play();
    }

    private void playerWin(Encounterable enc) {
        endImage = new ImageView(enc.getShip().getImage());
        endImage.setScaleX(100.0 / endImage.getImage().getWidth());
        endImage.setScaleY(100.0 / endImage.getImage().getHeight());
        endImage.setTranslateX(scroll.getHvalue() + (endImage.getImage().getWidth() * endImage.getScaleX() - endImage.getImage().getWidth()) / 2.0);
        endImage.setTranslateY(scroll.getVvalue() + 300 + (endImage.getImage().getHeight() * endImage.getScaleY() - endImage.getImage().getHeight()) / 2.0);

        endLabel = new Label(enc.getDeathText());
        endLabel.setWrapText(true);
        endLabel.setTranslateX(scroll.getHvalue() + 112.5);
        endLabel.setTranslateY(scroll.getVvalue() + 312.5);
        endLabel.setMinSize(475, 75);
        endLabel.setMaxSize(475, 75);
        endLabel.setFont(Font.font(15.0));
        endLabel.setAlignment(Pos.CENTER);
        endLabel.setId("encountered_text");
        endLabel.setOpacity(0.0);

        endButton = new Button("Continue");
        endButton.setMinSize(200, 50);
        endButton.setTranslateX(scroll.getHvalue() + 200);
        endButton.setTranslateY(scroll.getVvalue() + 250);
        endButton.setOnMouseClicked((MouseEvent e) -> {
            GameController.getControl().setScreen(Screens.SOLAR_SYSTEM_MAP);
        });

        anchor.getChildren().add(endLabel);

        fadeIn(endImage);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), endLabel);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setOnFinished((ActionEvent e) -> {
            scroll.setMouseTransparent(false);
            fadeIn(endButton);
        });
        fadeIn.play();
    }

    private void playerLose(Encounterable enc) {
        endImage = new ImageView(enc.getShip().getImage());
        endImage.setScaleX(100.0 / endImage.getImage().getWidth());
        endImage.setScaleY(100.0 / endImage.getImage().getHeight());
        endImage.setTranslateX(scroll.getHvalue() + (endImage.getImage().getWidth() * endImage.getScaleX() - endImage.getImage().getWidth()) / 2.0);
        endImage.setTranslateY(scroll.getVvalue() + 300 + (endImage.getImage().getHeight() * endImage.getScaleY() - endImage.getImage().getHeight()) / 2.0);

        endLabel = new Label(enc.getWinText());
        endLabel.setWrapText(true);
        endLabel.setTranslateX(scroll.getHvalue() + 112.5);
        endLabel.setTranslateY(scroll.getVvalue() + 312.5);
        endLabel.setMinSize(475, 75);
        endLabel.setMaxSize(475, 75);
        endLabel.setFont(Font.font(15.0));
        endLabel.setAlignment(Pos.CENTER);
        endLabel.setId("encountered_text");
        endLabel.setOpacity(0.0);

        endButton = new Button("Continue");
        endButton.setMinSize(200, 50);
        endButton.setTranslateX(scroll.getHvalue() + 200);
        endButton.setTranslateY(scroll.getVvalue() + 250);
        endButton.setOnMouseClicked((MouseEvent e) -> {
            GameController.getControl().setScreen(Screens.SOLAR_SYSTEM_MAP);
        });

        anchor.getChildren().add(endLabel);

        fadeIn(endImage);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), endLabel);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setOnFinished((ActionEvent e) -> {
            scroll.setMouseTransparent(false);
            fadeIn(endButton);
        });
        fadeIn.play();
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
                    ship.setTranslateX(Math.max(-delta, Math.min((ship.getTranslateX() + ((2 + pilotSP / 3.0)
                            * Math.cos(Math.toRadians(ship.getRotate())))), 2000 - ship.getImage().getWidth() + delta)));
                    ship.setTranslateY(Math.max(-delta, Math.min((ship.getTranslateY() + ((2 + pilotSP / 3.0)
                            * Math.sin(Math.toRadians(ship.getRotate())))), 1500 - ship.getImage().getWidth() + delta)));
                    centerOnScreen(ship.getTranslateX() + ship.getImage().getWidth() * ship.getScaleX() / 2,
                            ship.getTranslateY() + ship.getImage().getHeight() * ship.getScaleY() / 2);
                }
                if (space && delay % 10 == 0) {
                    shoot(getPlayerShip(), ship);
                }
                
                if (shift) {
                    if (escapeCount == 0) {
                        escapeBar.setVisible(true);
                        escapeLabel.setVisible(true);
                    } else if (escapeCount <= 250) {
                        escapeBar.setProgress(escapeCount / 250.0);
                        escapeLabel.setText("Flee: " + (int)(escapeCount / 250.0 * 100.0) + "%");
                        if (escapeBar.getProgress() == 1.0) {
                            GameController.getControl().setScreen(Screens.SOLAR_SYSTEM_MAP);
                            timer.stop();
                        }
                    } 
                    escapeBar.setTranslateX(scroll.getHvalue() + 200);
                    escapeBar.setTranslateY(scroll.getVvalue() + 300);
                    escapeLabel.setTranslateX(scroll.getHvalue() + 200);
                    escapeLabel.setTranslateY(scroll.getVvalue() + 330);
                    escapeCount++;
                } else {
                    escapeBar.setVisible(false);
                    escapeLabel.setVisible(false);
                    escapeCount = 0;
                }
                
                if (enter) {
                    enter = false;
                    if (gamePaused) {
                        unPauseGame();
                        gamePaused = false;
                    } else {
                        pauseGame();
                        gamePaused = true;
                    }
                }

                for (int i = 0; i < shots.size(); i++) {
                    Shot s = shots.get(i);
                    if (s.x < 0 || s.x > 2000 || s.y < 0 || s.y > 1500) {
                        anchor.getChildren().remove(s.line);
                        shots.remove(s);
                        i--;
                    } else {
                        for (int j = 0; j < enemyShips.size(); j++) {
                            if (s.checkCollision(enemies.get(j).getShip(),
                                    enemyShips.get(j), enemyHealths.get(j), enemyShields.get(j))) {
                                j = -1;
                                if (i < shots.size()) {
                                    s = shots.get(i);
                                }
                                if (enemyShips.isEmpty()) {
                                    endGame = true;
                                    playerWin(enemy);
                                }
                            }
                        }
                        if (s.checkCollision(getPlayerShip(), ship, playerHealth, playerShield)) {;
                            endGame = true;
                            playerLose(enemy);
                        }

                        s.move();
                    }
                }
                for (int i = 0; i < enemyShips.size(); i++) {
                    if (!endGame) {
                        switch (difficulty) {
                            case 1:
                                noviceAI(enemies.get(i).getShip(), enemyShips.get(i));
                                break;
                            case 2:
                                apprenticeAI(enemies.get(i).getShip(), enemyShips.get(i));
                                break;
                            case 3:
                                adeptAI(enemies.get(i).getShip(), enemyShips.get(i));
                                break;
                            case 4:
                                expertAI(enemies.get(i).getShip(), enemyShips.get(i));
                                break;
                            default:
                                masterAI(enemies.get(i).getShip(), enemyShips.get(i));
                                break;
                        }

                        shieldRegen(enemies.get(i).getShip());
                        updateHealthAndShields(enemies.get(i).getShip(), enemyShips.get(i),
                                enemyHealths.get(i), enemyShields.get(i));
                    }
                }
                for (int i = 0; i < particles.size(); i++) {
                    if (particles.get(i).spread()) {
                        i--;
                    }
                }
                updateHealthAndShields(getPlayerShip(), ship, playerHealth, playerShield);
                shieldRegen(getPlayerShip());
                if (delay == 20) {
                    delay = 0;
                } else {
                    delay++;
                }

                if (endGame) {
                    endImage.setTranslateX(scroll.getHvalue() + (endImage.getImage().getWidth() * endImage.getScaleX()
                            - endImage.getImage().getWidth()) / 2.0);
                    endImage.setTranslateY(scroll.getVvalue() + 300 + (endImage.getImage().getHeight() * endImage.getScaleY()
                            - endImage.getImage().getHeight()) / 2.0);
                    endLabel.setTranslateX(scroll.getHvalue() + 112.5);
                    endLabel.setTranslateY(scroll.getVvalue() + 312.5);
                    endButton.setTranslateX(scroll.getHvalue() + 200);
                    endButton.setTranslateY(scroll.getVvalue() + 250);
                }
            }
        };
    }
    
    private void pauseGame() {
        timer.stop();
        for (Node n : anchor.getChildren()) {
            n.setEffect(new ColorAdjust(0, 0, -0.5, 0));
        }
        
        pausedLabel.setTranslateX(scroll.getHvalue());
        pausedLabel.setTranslateY(scroll.getVvalue());
        
        
        anchor.getChildren().add(pausedLabel);
        
        pausedLabel.requestFocus();
    }
    
    private void unPauseGame() {
        for (Node n : anchor.getChildren()) {
            n.setEffect(new ColorAdjust(0, 0, 0, 0));
        }
        anchor.getChildren().remove(pausedLabel);
        anchor.requestFocus();
        timer.start();
    }

    private void initializePlayer() {
        int eventCenterX = 1000;
        int eventCenterY = 750;

        ship = new ImageView(GameController.getGameData().getPlayer().getShip().getImage());
        ship.setScaleX(.5);
        ship.setScaleY(.5);
        ship.setTranslateX(eventCenterX - ship.getImage().getWidth() / 2);
        ship.setTranslateY(eventCenterY - ship.getImage().getHeight() / 2);

        anchor.setOnKeyPressed((KeyEvent k) -> {
            if (k.getCode() == KeyCode.LEFT) {
                leftDown = true;
            } else if (k.getCode() == KeyCode.RIGHT) {
                rightDown = true;
            }
            if (k.getCode() == KeyCode.UP) {
                upDown = !getPlayerShip().isDead();
            }
            if (k.getCode() == KeyCode.SPACE) {
                space = !getPlayerShip().isDead();
            }
            if (k.getCode() == KeyCode.SHIFT) {
                shift = !getPlayerShip().isDead();
            }
            if (k.getCode() == KeyCode.ENTER) {
                enter = !getPlayerShip().isDead();
            }
        });

        anchor.setOnKeyReleased((KeyEvent k) -> {
            if (k.getCode() == KeyCode.LEFT) {
                leftDown = false;
            } else if (k.getCode() == KeyCode.RIGHT) {
                rightDown = false;
            }
            if (k.getCode() == KeyCode.UP) {
                upDown = false;
            }
            if (k.getCode() == KeyCode.SPACE) {
                space = false;
            }
            if (k.getCode() == KeyCode.SHIFT) {
                shift = false;
            }
        });

        playerHealth = new Rectangle();
        playerHealth.setHeight(5);
        playerHealth.setFill(Color.RED);

        playerShield = new Rectangle();
        playerShield.setHeight(5);
        playerShield.setFill(Color.BLUE);

        updateHealthAndShields(getPlayerShip(), ship, playerHealth, playerShield);

        anchor.getChildren().add(ship);
        anchor.getChildren().add(playerHealth);
        anchor.getChildren().add(playerShield);
    }

    private void initializeEnemy(String enemy, int numEnemy) {
        switch (enemy) {
            case "Police":
                for (int i = 0; i < numEnemy; i++) {
                    createEnemy(new Police("Bob"));
                }
                break;
            case "Dreadnought":
                for (int i = 0; i < numEnemy; i++) {
                    createEnemy(new Dreadnought("Bob"));
                }
                break;
            case "Trader":
                for (int i = 0; i < 1; i++) {
                    createEnemy(new Trader("Bob", GameController.getGameData().getUniverse().getRandomPlanet()));
                }
            default:
                for (int i = 0; i < numEnemy; i++) {
                    createEnemy(new Pirate("Bob"));
                }
                break;
        }
    }

    private void createEnemy(Encounterable enemy) {
        ImageView enemyShip = new ImageView(enemy.getShip().getImage());
        enemyShip.setScaleX(0.5);
        enemyShip.setScaleY(0.5);

        enemyShip.setTranslateX(Math.random() * 2000);
        enemyShip.setTranslateY(Math.random() * 1500);

        Rectangle enemyHealth = new Rectangle();
        enemyHealth.setHeight(5);
        enemyHealth.setFill(Color.RED);
        enemyHealths.add(enemyHealth);

        Rectangle enemyShield = new Rectangle();
        enemyShield.setHeight(5);
        enemyShield.setFill(Color.BLUE);
        enemyShields.add(enemyShield);

        updateHealthAndShields(enemy.getShip(), enemyShip, enemyHealth, enemyShield);

        anchor.getChildren().add(enemyShip);
        anchor.getChildren().add(enemyHealth);
        anchor.getChildren().add(enemyShield);
        enemies.add(enemy);
        enemyShips.add(enemyShip);
    }

    private void masterAI(Ship enemy, ImageView image) {
        double deltaY = ship.getTranslateY() + ship.getImage().getHeight() / 2
                - (image.getTranslateY() + image.getImage().getHeight() / 2);
        double deltaX = ship.getTranslateX() + ship.getImage().getWidth() / 2
                - (image.getTranslateX() + image.getImage().getWidth() / 2);
        int enemyPilotSP = enemy.getOwner().getPilotSkillPoints();
        image.setRotate(Math.toDegrees(Math.atan2(deltaY, deltaX)));
        image.setTranslateX(image.getTranslateX() + ((enemyPilotSP / 3.0 + 2) * Math.cos(Math.toRadians(image.getRotate()))));
        image.setTranslateY(image.getTranslateY() + ((enemyPilotSP / 3.0 + 2) * Math.sin(Math.toRadians(image.getRotate()))));
        if (delay == 20) {
            shoot(enemy, image);
        }
    }

    private void noviceAI(Ship enemy, ImageView image) {
        double deltaY = ship.getTranslateY() + ship.getImage().getHeight() / 2
                - (image.getTranslateY() + image.getImage().getHeight() / 2);
        double deltaX = ship.getTranslateX() + ship.getImage().getWidth() / 2
                - (image.getTranslateX() + image.getImage().getWidth() / 2);
        double pilotFactor = enemy.getOwner().getPilotSkillPoints() / 3.0 + 2;
        double toEnemy = Math.toDegrees(Math.atan2(deltaY, deltaX));
        double difference = toEnemy - image.getRotate();
        if (difference <= -180) {
            difference += 360;
        } else if (difference > 180) {
            difference -= 360;
        }
        image.setRotate(image.getRotate() + (difference > 0 ? Math.min(difference, 1) : Math.max(difference, -1)));
        image.setTranslateX(image.getTranslateX() + (pilotFactor * Math.cos(Math.toRadians(image.getRotate()))));
        image.setTranslateY(image.getTranslateY() + (pilotFactor * Math.sin(Math.toRadians(image.getRotate()))));
        if (delay == 20 && deltaY * deltaY + deltaX * deltaX < 300 * 300 && Math.abs(difference) < 20) {
            shoot(enemy, image);
        }
    }

    private void apprenticeAI(Ship enemy, ImageView image) {
        double deltaY = ship.getTranslateY() + ship.getImage().getHeight() / 2
                - (image.getTranslateY() + image.getImage().getHeight() / 2);
        double deltaX = ship.getTranslateX() + ship.getImage().getWidth() / 2
                - (image.getTranslateX() + image.getImage().getWidth() / 2);
        double pilotFactor = enemy.getOwner().getPilotSkillPoints() / 3.0 + 2;
        double toEnemy = Math.toDegrees(Math.atan2(deltaY, deltaX));
        double difference = toEnemy - image.getRotate();
        if (difference <= -180) {
            difference += 360;
        } else if (difference > 180) {
            difference -= 360;
        }
        image.setRotate(image.getRotate() + (difference > 0 ? Math.min(difference, 3) : Math.max(difference, -3)));
        image.setTranslateX(image.getTranslateX() + (pilotFactor * Math.cos(Math.toRadians(image.getRotate()))));
        image.setTranslateY(image.getTranslateY() + (pilotFactor * Math.sin(Math.toRadians(image.getRotate()))));
        if (delay == 20 && deltaY * deltaY + deltaX * deltaX < 400 * 400 && Math.abs(difference) < 45) {
            shoot(enemy, image);
        }
    }

    private void adeptAI(Ship enemy, ImageView image) {
        double deltaY = ship.getTranslateY() + ship.getImage().getHeight() / 2
                - (image.getTranslateY() + image.getImage().getHeight() / 2);
        double deltaX = ship.getTranslateX() + ship.getImage().getWidth() / 2
                - (image.getTranslateX() + image.getImage().getWidth() / 2);
        double pilotFactor = enemy.getOwner().getPilotSkillPoints() / 3.0 + 2;
        double toEnemy = Math.toDegrees(Math.atan2(deltaY, deltaX));
        double difference = toEnemy - image.getRotate();
        if (difference <= -180) {
            difference += 360;
        } else if (difference > 180) {
            difference -= 360;
        }
        image.setRotate(image.getRotate() + (difference > 0 ? Math.min(difference, 5) : Math.max(difference, -5)));
        image.setTranslateX(image.getTranslateX() + (pilotFactor * Math.cos(Math.toRadians(image.getRotate()))));
        image.setTranslateY(image.getTranslateY() + (pilotFactor * Math.sin(Math.toRadians(image.getRotate()))));
        if (delay == 20 && deltaY * deltaY + deltaX * deltaX < 500 * 500 && Math.abs(difference) < 70) {
            shoot(enemy, image);
        }
    }

    private void expertAI(Ship enemy, ImageView image) {
        double deltaY = ship.getTranslateY() + ship.getImage().getHeight() / 2
                - (image.getTranslateY() + image.getImage().getHeight() / 2);
        double deltaX = ship.getTranslateX() + ship.getImage().getWidth() / 2
                - (image.getTranslateX() + image.getImage().getWidth() / 2);
        double pilotFactor = enemy.getOwner().getPilotSkillPoints() / 3.0 + 2;
        double toEnemy = Math.toDegrees(Math.atan2(deltaY, deltaX));
        double difference = toEnemy - image.getRotate();
        if (difference <= -180) {
            difference += 360;
        } else if (difference > 180) {
            difference -= 360;
        }
        image.setRotate(image.getRotate() + (difference > 0 ? Math.min(difference, 8) : Math.max(difference, -8)));
        image.setTranslateX(image.getTranslateX() + (pilotFactor * Math.cos(Math.toRadians(image.getRotate()))));
        image.setTranslateY(image.getTranslateY() + (pilotFactor * Math.sin(Math.toRadians(image.getRotate()))));
        if (delay == 20 && deltaY * deltaY + deltaX * deltaX < 600 * 600 && Math.abs(difference) < 90) {
            shoot(enemy, image);
        }
    }

    private void updateHealthAndShields(Ship enemy, ImageView image, Rectangle health, Rectangle shields) {
        double translate = image.getTranslateX();
        health.setTranslateX(translate);
        health.setTranslateY(image.getTranslateY() - 5);
        health.setWidth((double) enemy.getHullStrength() / enemy.getMaxHullStrength() * image.getImage().getWidth());

        shields.setTranslateX(translate);
        shields.setTranslateY(image.getTranslateY());
        shields.setWidth((double) enemy.getCurrentShields() / enemy.getMaxShields() * image.getImage().getWidth());
    }

    private void shieldRegen(Ship ship) {
        ship.shieldRegen();
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

    private class Shot {

        public CrewMember owner;
        public ImageView ownerImage;
        public ImageView target;
        public double startX;
        public double startY;
        public double x;
        public double y;
        public double direction;
        public double speed;
        public double length;
        public int damage;
        public Line line;
        public double homing;
        public boolean piercing;

        Shot(CrewMember owner, ImageView ownerImage, Ship ship, double x, double y, double direction, double speed, int damage) {
            this.owner = owner;
            this.ownerImage = ownerImage;
            this.x = x;
            this.y = y;
            this.startX = x;
            this.startY = y;
            this.damage = damage;
            this.direction = direction;
            this.length = 30;
            this.speed = speed;
            this.line = new Line(x, y, x + length * Math.cos(Math.toRadians(direction)),
                    y + length * Math.sin(Math.toRadians(direction)));
            this.homing = ship.getHoming();
            if (homing > 0) {
                target = enemyShips.size() > 0 ? selectTarget() : null;
            }
            this.piercing = ship.shouldPierce();
        }

        private ImageView selectTarget() {
            ImageView closest = null;
            double closestDistance = Integer.MAX_VALUE;
            for (int i = 0; i < enemyShips.size(); i++) {
                if (!owner.getClass().equals(enemies.get(i).getClass())) {
                    double viewDistance = Math.pow(line.getEndX() - (enemyShips.get(i).getTranslateX() + enemyShips.get(i).getImage().getWidth() / 2), 2)
                            + Math.pow(line.getEndY() - (enemyShips.get(i).getTranslateY() + enemyShips.get(i).getImage().getHeight() / 2), 2);
                    if (viewDistance < closestDistance) {
                        closest = enemyShips.get(i);
                        closestDistance = viewDistance;
                    }
                }
            }
            if (ship != null && owner != getPlayerShip().getOwner() && Math.pow(line.getEndX()
                    - (ship.getTranslateX() + ship.getImage().getWidth() / 2), 2)
                    + Math.pow(line.getEndY() - (ship.getTranslateY() + ship.getImage().getHeight() / 2), 2) < closestDistance) {
                closest = ship;
            }
            return closest;
        }

        public void move() {
            if (homing > 0 && target != null && target.getParent() != null) {
                double deltaY = line.getEndY() - (target.getTranslateY() + target.getImage().getHeight() / 2);
                double deltaX = line.getEndX() - (target.getTranslateX() + target.getImage().getWidth() / 2);
                double difference = ((Math.toDegrees(Math.atan2(deltaY, deltaX)) + 180)) - direction;
                if (difference <= -180) {
                    difference += 360;
                } else if (difference > 180) {
                    difference -= 360;
                }

                direction += difference > 0 ? Math.min(difference, homing) : Math.max(difference, -homing);
                if (direction < 0) {
                    direction += 360;
                } else if (direction >= 360) {
                    direction %= 360;
                }
            }
            double cos = Math.cos(Math.toRadians(direction));
            double sin = Math.sin(Math.toRadians(direction));
            x = x + speed * cos;
            y = y + speed * sin;
            line.setStartX(x);
            line.setStartY(y);
            line.setEndX(x + length * cos);
            line.setEndY(y + length * sin);
        }

        public boolean checkCollision(Ship ship, ImageView shipImage, Rectangle health, Rectangle shield) {
            if (ship.getOwner() != owner && !(ship.getOwner().getClass().equals(owner.getClass()))) {
                double width = shipImage.getImage().getWidth();
                double height = shipImage.getImage().getHeight();
                if (Math.pow(width * shipImage.getScaleX() / 2, 2)
                        > Math.pow(shipImage.getTranslateX() + width / 2 - line.getEndX(), 2)
                        + Math.pow(shipImage.getTranslateY() + height / 2 - line.getEndY(), 2)) {

                    ship.takeDamage(damage);
                    boolean reflected = false;
                    double reflect = ship.getReflectChance();
                    if (ship.getOwner() instanceof Player) {
                        escapeCount = 0;
                    }
                    if (reflect > 0) {
                        if (Math.random() < reflect) {
                            owner = ship.getOwner();
                            target = ownerImage;
                            ownerImage = shipImage;
                            direction += direction >= 180 ? -180 : 180;
                            reflected = true;
                        }
                    }
                    if (!piercing && !reflected) {
                        anchor.getChildren().remove(line);
                        shots.remove(this);
                    }
                    if (ship.getHullStrength() <= 0) {
                        anchor.getChildren().remove(shipImage);
                        anchor.getChildren().remove(health);
                        anchor.getChildren().remove(shield);
                        if (ship.getOwner() instanceof Encounterable) {
                            explode(shipImage);
                            enemies.remove(ship.getOwner());
                            enemyHealths.remove(health);
                            enemyShields.remove(shield);
                            return enemyShips.remove(shipImage);
                        } else if (!endGame) {
                            explode(shipImage);
                            explode(shipImage);
                            explode(shipImage);
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    private void explode(ImageView shipImage) {
        double centerX = shipImage.getTranslateX() + shipImage.getImage().getWidth() / 2.0;
        double centerY = shipImage.getTranslateY() + shipImage.getImage().getHeight() / 2.0;
        for (int i = 0; i < 75; i++) {
            Particle particle = new Particle(centerX, centerY);
        }
        explode.play();
    }

    private Ship getPlayerShip() {
        return GameController.getGameData().getShip();
    }

    private void shoot(Ship ship, ImageView shipImage) {
        ArrayList<Weapon> weapons = ship.getEquippedWeapons();
        for (int i = 0; i < weapons.size(); i++) {
            Weapon w = weapons.get(i);
            double r = (i + 1) / (double) (weapons.size() + 1);
            double deltaX = Math.cos(Math.toRadians(shipImage.getRotate() - 90)) * (0.5 - r) * shipImage.getImage().getWidth() * shipImage.getScaleX();
            double deltaY = Math.sin(Math.toRadians(shipImage.getRotate() - 90)) * (0.5 - r) * shipImage.getImage().getWidth() * shipImage.getScaleX();
            double scatter = ship.getScatter();;
            double angle = shipImage.getRotate() + (scatter > 0 ? Math.random() * scatter - scatter / 2 : 0);
            Shot s = new Shot(ship.getOwner(), shipImage, ship, shipImage.getTranslateX() + deltaX + shipImage.getImage().getWidth() / 2,
                    shipImage.getTranslateY() + deltaY + shipImage.getImage().getHeight() / 2, angle, 5
                    + ship.getOwner().getFighterSkillPoints() / 1.5, (int) (w.getDamage()* (1 + ship.getOwner().getFighterSkillPoints() / 30.0)));
            shots.add(s);
            anchor.getChildren().add(s.line);
            shipImage.toFront();
            switch (w.getType().ordinal()) {
                case 0:
                    s.line.setStroke(Color.RED);
                    break;
                case 1:
                    s.line.setStroke(Color.GREEN);
                    break;
                case 2:
                    s.line.setStroke(Color.BLUE);
                    break;
                case 3:
                    s.line.setStroke(Color.VIOLET);
                    break;
            }
        }
        if (weapons.size() > 0) {
            laser.play();
        }
    }

    private class Particle {

        double x;
        double y;
        double direction;
        double speed;
        public Shape shape;

        public Particle(double x, double y) {
            this.x = x;
            this.y = y;
            this.direction = Math.random() * 360;
            this.speed = Math.random() * 10 + 1;
            this.shape = new Circle(x, y, Math.random() * 4 + 1, Color.rgb(255, (int) (Math.random() * 255), 0));

            FadeTransition fade = new FadeTransition(Duration.seconds(1), shape);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setOnFinished((ActionEvent e) -> {
                anchor.getChildren().remove(shape);
                speed = 0;
            });
            fade.play();

            anchor.getChildren().add(shape);
            particles.add(this);
        }

        public boolean spread() {
            if (speed > 0) {
                shape.setTranslateX(shape.getTranslateX() + speed * Math.cos(Math.toRadians(direction)));
                shape.setTranslateY(shape.getTranslateY() + speed * Math.sin(Math.toRadians(direction)));
            } else {
                return particles.remove(this);
            }
            return false;
        }
    }

    private boolean hasPirates() {
        for (int i = 0; i < enemyShips.size(); i++) {
            if (enemies.get(i) instanceof Pirate) {
                return true;
            }
        }
        return false;
    }

    private boolean hasPolice() {
        for (int i = 0; i < enemyShips.size(); i++) {
            if (enemies.get(i) instanceof Police) {
                return true;
            }
        }
        return false;
    }

    private boolean hasDreadnoughts() {
        for (int i = 0; i < enemyShips.size(); i++) {
            if (enemies.get(i) instanceof Dreadnought) {
                return true;
            }
        }
        return false;
    }

    private void fadeIn(Node node) {
        node.setOpacity(0);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), node);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        node.setMouseTransparent(false);

        anchor.getChildren().add(node);

        fadeIn.play();

    }

    private void fadeOut(Node node) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), node);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        node.setMouseTransparent(true);

        anchor.getChildren().remove(node);

        fadeOut.play();
    }
    
    private boolean pirateComply() {
        if (Math.random() < 0.05) {
            return true;
        } else {
            GameController.getGameData().getPlayer().spend(3 * GameController.getGameData().getPlayer().getCredits() / 4);
            return false;
        }
    }
    
    private boolean policeComply() {
        return GameController.getGameData().getPlayer().hasIllegalGoods() 
                || GameController.getGameData().getPlayer().getBounty() > 5000;
    } 
    
    private boolean dreadnoughtComply() {
        if (GameController.getGameData().getPlayer().getCredits() 
                > 10 * GameController.getGameData().getPlayer().getBounty()) {
            GameController.getGameData().getPlayer().spend(10 * GameController.getGameData().getPlayer().getBounty());
            return false;
        }
        return true;
    }
    
    private boolean traderComply() {
        // Implement Trading :D
        return false;
    }
}
