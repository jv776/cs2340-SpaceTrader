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
    private final String name;
    private final int x;
    private final int y;
    private final TechLevel tech;
    private final Resource resource;
    
    public SolarSystem(String name, int xLoc, int yLoc, TechLevel techLevel, Resource resource) {
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
