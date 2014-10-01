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
    public final String name;
    private final int x;
    private final int y;
    final TechLevel tech;
    final PoliticalSystem government;
    private final Star sun;
    final Planet[] planets;
    
    public SolarSystem(String name, int xLoc, int yLoc, TechLevel techLevel,
            PoliticalSystem governmentType) {
        this.name = name;
        x = xLoc;
        y = yLoc;
        tech = techLevel;
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
    
    /*
     * Ugly helper function for randomly choosing resources on each
     * planet. Feel free to adjust probabilities or rewrite entirely.
     */
    private static Resource randomResource() {
        double r = Math.random();

        if (0.0 <= r && r < 0.3) {
            return Resource.NO_SPECIAL_RESOURCES; //30% chance
        } else if (0.3 <= r && r < 0.35) {
            return Resource.MINERAL_RICH; //5% chance
        } else if (0.35 <= r && r < 0.4) {
            return Resource.MINERAL_POOR; //5% chance
        } else if (0.4 <= r && r < 0.5) {
            return Resource.DESERT; //10% chance
        } else if (0.5 <= r && r < 0.55) {
            return Resource.LOTS_OF_WATER; //5% chance
        } else if (0.55 <= r && r < 0.6) {
            return Resource.RICH_SOIL; //5% chance
        } else if (0.6 <= r && r < 0.65) {
            return Resource.POOR_SOIL; //5% chance
        } else if (0.65 <= r && r < 0.7) {
            return Resource.RICH_FAUNA; //5% chance
        } else if (0.7 <= r && r < 0.85) {
            return Resource.LIFELESS; //15% chance
        } else if (0.85 <= r && r < 0.87) {
            return Resource.WEIRD_MUSHROOMS; //2% chance
        } else if (0.87 <= r && r < 0.9) {
            return Resource.LOTS_OF_HERBS; //3% chance
        } else if (0.9 <= r && r < 0.95) {
            return Resource.ARTISTIC; //5% chance
        } else {
            return Resource.WARLIKE; //5% chance
        }
    }

    private Planet[] generatePlanets() {
        Planet [] planetArray = new Planet[7];
        int dist = 40;
        
        for (int i = 0; i < planetArray.length; i++){
            dist += (i + 0.25) * 50 * (0.76 + (0.24 * Math.random()));
            planetArray[i] = new Planet("", this, dist, sun.getTemperature(),
                randomResource()); //add name
        }
        
        return planetArray;
    }
    
    @Override
    public String toString() {
        String temp = "System name: " + name + "\n";
        temp += "Sun:\n" + sun + "\nTech Level:\t" + tech + "\nGovernment:\t"
                + government + "\n\nPlanets:\n";
        
        for(Planet p:planets){
            temp += p +"\n~~~~~\n";
        }
        
        return temp;
    }
    
    TechLevel getTechLevel() {
        return tech;
    }
}
