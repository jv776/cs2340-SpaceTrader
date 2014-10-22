/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.io.Serializable;

/**
 * Allows easy access to basic information about the status of the
 * player and the universe in which the current game is occurring.
 *
 * @author Alex, John
 */
public class GameData implements Serializable {
    private Player player;
    private Universe universe;

    /**
     * @return The in-game representation of the current player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Change the current player to a new one (possibly when switching between
     * save files or starting a new game)
     *
     * @param p The new player
     */
    public void setPlayer(Player p) {
        player = p;
    }

    /**
     * @return The current in-game universe
     */
    public Universe getUniverse() {
        return universe;
    }

    /**
     * Specify a particular Universe object in which the game should take
     * place. Used at the beginning of a new game.
     *
     * @param u The new universe
     */
    public void setUniverse(Universe u) {
        universe = u;
    }

    /**
     * @return The solar system in which the player is located
     */
    public SolarSystem getSolarSystem() {
//        return player.getCurrentPlanet().getSolarSystem();
        return player.getCurrentSystem();
    }


    /**
     * Specify the solar system in which the player should be located.
     *
     * @param s The solar system where the player will be located
     */
    public void setSolarSystem(SolarSystem s){
        player.setCurrentSystem(s);
    }

    /**
     * @return The planet the player is currently on
     */
    public Planet getPlanet() {
        return player.getCurrentPlanet();
    }

    /**
     * Set the location of the player to be a new planet.
     *
     * @param p The planet the player will be moved to
     */
    public void setPlanet(Planet p){
        player.setCurrentPlanet(p);
    }

    /**
     * @return The player's cargo hold
     */
    public CargoHold getCargoHold() {
        return player.getShip().getCargoHold();
    }

    /*
    public void setCargoHold(CargoHold ch){
        cargo = ch;
    }
    */

    /**
     * @return The player's ship
     */
    public Ship getShip() {
        return player.getShip();
    }

    /**
     * Give the player a new ship (i.e. after buying a new ship).
     *
     * @param s The player's new ship
     */
    public void setShip(Ship s){
        //will need code for buying ships to implement properly
    }
}
