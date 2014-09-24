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
    private final PoliticalSystem government;
    private final Star sun;
    private final Planet[] planets;
    
    public SolarSystem(String name, int xLoc, int yLoc, TechLevel techLevel,
            Resource specialResource, PoliticalSystem governmentType) {
        this.name = name;
        x = xLoc;
        y = yLoc;
        tech = techLevel;
        resource = specialResource;
        government = governmentType;
        sun = new Star(""); //add name
        planets = generatePlanets();
    }
    
    /*
    public SolarSystem(String name, int xLoc, int yLoc) {
        this.name = name;
        x = xLoc;
        y = yLoc;
        sun = new Star(""); //add name
        planets = generatePlanets();
    }
    */

    private Planet[] generatePlanets() {
        Planet [] planetArray = new Planet[7];
        int dist = 40;
        for (int i = 0; i < planetArray.length; i++){
            dist += (i + 0.25) * 50 * (0.76 + (0.24 * Math.random()));
            planetArray[i] = new Planet("", dist, sun.getTemperature()); //add name
        }
        return planetArray;
    }
    
    @Override
    public String toString() {
        String temp = "System name: " + name + "\n";
        temp += "Sun:\n" + sun + "\nResources:\t" + resource + "\nTech Level:\t" +
                tech + "\nGovernment:\t" + government + "\n\nPlanets:\n";
        
        for(Planet p:planets){
            temp += p +"\n~~~~~\n";
        }
        return temp;
    }
    
    TechLevel getTechLevel() {
        return tech;
    }
}
