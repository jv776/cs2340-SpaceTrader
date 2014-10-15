package models;

/**
 * Created by Taylor on 10/14/14.
 */
public class Pirate extends CrewMember{
    private String name;
    private Ship ship;
    private int bounty;

    public Pirate(String name){
        super(0,0,0,0,0);
        this.name = name;
        ship = new Ship(Ship.Type.Gnat, this);
    }

    public String getName(){
        return name;
    }

    public boolean isDead(){
        return ship.isDead();
    }
    public void takeDamage(int damage){
        ship.takeDamage(damage);
    }
    public int calculateAttack(){
        return ship.calculateAttack();
    }
    public int getHullStrength(){
        return ship.getHullStrength();
    }

    public int getMaxHullStrength(){
        return ship.getMaxHullStrength();
    }
}
