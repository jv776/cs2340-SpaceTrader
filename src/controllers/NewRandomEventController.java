package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import models.CrewMember;
import models.Dreadnought;
import models.Encounterable;
import models.Pirate;
import models.Police;
import models.Ship;
import models.Weapon;

/**
 *
 * @author Alex
 */
public class NewRandomEventController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private ScrollPane scroll;

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

    private int delay;

    int pilotSP;
    int fighterSP;

    private ArrayList<Shot> shots;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchor.setBackground(new Background(new BackgroundImage(
                new Image("/images/stars_1.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        )));
        scroll.setHmin(0.0);
        scroll.setHmax(1400.0);
        scroll.setVmin(0.0);
        scroll.setVmax(1100.0);
        scroll.setPannable(false);

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

        pilotSP = getPlayerShip().getOwner().getPilotSkillPoints();
        fighterSP = getPlayerShip().getOwner().getFighterSkillPoints();

        AnimationTimer gameLoop = getGameLoop();
        gameLoop.start();

        initializePlayer();
        
        initializeEnemy("Pirate", 1);
    }

    private AnimationTimer getGameLoop() {
        return new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (leftDown) {
                    ship.setRotate(ship.getRotate() - 5);
                    if (ship.getRotate() <= -180) {
                        ship.setRotate(180);
                    }
                }
                if (rightDown) {
                    ship.setRotate((ship.getRotate() + 5));
                    if (ship.getRotate() > 180) {
                        ship.setRotate(-175);
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
                if (space && delay % 2 == 0) {
                    shoot(getPlayerShip(), ship);
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
                            }
                        }
                        s.checkCollision(getPlayerShip(), ship, playerHealth, playerShield);
                        updateHealthAndShields(getPlayerShip(), ship, playerHealth, playerShield);
                        
                        s.move();
                    }
                }
                for (int i = 0; i < enemyShips.size(); i++) {
                    masterAI(enemies.get(i).getShip(), enemyShips.get(i));
                    shieldRegen(enemies.get(i).getShip());
                    updateHealthAndShields(enemies.get(i).getShip(), enemyShips.get(i),
                            enemyHealths.get(i), enemyShields.get(i));
                }
                for (int i = 0; i < particles.size(); i++) {
                    if (particles.get(i).spread()) {
                        i--;
                    }
                }
                shieldRegen(getPlayerShip());
                if (delay == 20) {
                    delay = 0;
                } else {
                    delay++;
                }
            }
        };
    }

    private void initializePlayer() {
        int eventCenterX = 1000;
        int eventCenterY = 750;

        ship = new ImageView(new Image("/images/spaceship.gif"));
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
                upDown = true;
            }
            if (k.getCode() == KeyCode.SPACE) {
                space = true;
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
        });

        scroll.setOnMouseEntered((MouseEvent t) -> {
            centerOnScreen(ship.getTranslateX() + ship.getImage().getWidth() * ship.getScaleX() / 2,
                    ship.getTranslateY() + ship.getImage().getHeight() * ship.getScaleY() / 2);
            scroll.setMouseTransparent(true);
            anchor.requestFocus();
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
        if (enemy.equals("Police")) {
            for (int i = 0; i < numEnemy; i++) {
                createEnemy(new Police("Bob"));
            }
        } else if (enemy.equals("Dreadnought")) {
            for (int i = 0; i < numEnemy; i++) {
                createEnemy(new Dreadnought("Bob"));
            }
        } else {
            for (int i = 0; i < numEnemy; i++) {
                createEnemy(new Pirate("Bob"));
            }
        }
    }

    private void createEnemy(Encounterable enemy) {
        ImageView enemyShip = new ImageView(enemy.getShipImage());
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
        image.setRotate(Math.toDegrees(Math.atan(deltaY / deltaX)) + (deltaX < 0 ? 180 : 0));
        //image.setTranslateX(image.getTranslateX() + ((enemyPilotSP / 3.0 + 2) * Math.cos(Math.toRadians(image.getRotate()))));
        //image.setTranslateY(image.getTranslateY() + ((enemyPilotSP / 3.0 + 2) * Math.sin(Math.toRadians(image.getRotate()))));
        if (delay == 20) {
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
        public boolean homing;
        

        Shot(CrewMember owner, double x, double y, double direction, double speed, int damage, boolean homing) {
            this.owner = owner;
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
            this.homing = homing;
            if (homing) {
                target = enemyShips.size() > 0 ? selectTarget() : null;
            }
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
            return closest;
        }
        
        public void move() {
            if (homing && target != null && target.getParent() != null) {
                double deltaY = line.getEndY() - (target.getTranslateY() + target.getImage().getHeight() / 2);
                double deltaX = line.getEndX() - (target.getTranslateX() + target.getImage().getWidth() / 2);
                double difference = Math.toDegrees(Math.atan2(deltaY, deltaX)) - direction;
                direction += difference > 0 ? Math.min(difference, 3) : Math.max(difference, -3);
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

                    //System.out.println(line.getTranslateX() + " " + line.getStartX() + " " + shipImage.getTranslateX());
                    ship.takeDamage(damage);
                    anchor.getChildren().remove(line);
                    shots.remove(this);
                    if (ship.getHullStrength() <= 0) {
                        anchor.getChildren().remove(shipImage);
                        anchor.getChildren().remove(health);
                        anchor.getChildren().remove(shield);
                        if (ship.getOwner() instanceof Encounterable) {
                            explode(shipImage);
                            createEnemy(new Pirate("BO"));
                            enemies.remove(ship.getOwner());
                            enemyHealths.remove(health);
                            enemyShields.remove(shield);
                            return enemyShips.remove(shipImage);
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
            double angle = shipImage.getRotate() + (ship.shouldScatter() ? Math.random() * 20 - 10 : 0);
            Shot s = new Shot(ship.getOwner(), shipImage.getTranslateX() + deltaX + shipImage.getImage().getWidth() / 2,
                    shipImage.getTranslateY() + deltaY + shipImage.getImage().getHeight() / 2, angle, 5
                    + ship.getOwner().getFighterSkillPoints() / 1.5, (int) (w.getPower() * (1 + ship.getOwner().getFighterSkillPoints() / 30.0)), true);
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
            this.shape = new Circle(x, y, Math.random() * 4 + 1, Color.rgb(255, (int)(Math.random() * 255), 0));
            
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
}
