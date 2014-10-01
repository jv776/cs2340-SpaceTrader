/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 *
 * @author Alex
 */
public class GameData {
    private Player player;
    private Universe universe;
    private SolarSystem solarSystem;
    private Planet planet;
    private CargoHold cargo;
    private Ship ship;
    
    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player p) {
        player = p;
    }
    
    public Universe getUniverse() {
        return universe;
    }
    
    public void setUniverse(Universe u) {
        universe = u;
    }
    
    public SolarSystem getSolarSystem() {
        return solarSystem;
    }
    
    public void setSolarSystem(SolarSystem s){
        solarSystem = s;
    }
    
    public Planet getPlanet() {
        return planet;
    }
    
    public void setPlanet(Planet p){
        planet = p;
    }
    
    public CargoHold getCargoHold() {
        return cargo;
    }
    
    public void setCargoHold(CargoHold ch){
        cargo = ch;
    }
    
    public Ship getShip() {
        return ship;
    }
    
    public void setShip(Ship s){
        ship = s;
    }
}
