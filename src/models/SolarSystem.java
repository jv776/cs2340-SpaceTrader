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
public class SolarSystem {
    private String name;
    private int x;
    private int y;
    private String tech;
    private String resource;
    
    public SolarSystem(String name, int xLoc, int yLoc, String techLevel, String resource) {
        this.name = name;
        x = xLoc;
        y = yLoc;
        tech = techLevel;
        this.resource = resource;
    }
    
    public String toString() {
        return name + ": " + x + ", " + y + ", " + tech + ", " + resource;
    }
}
