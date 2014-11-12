/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.io.Serializable;

/**
 * Models various solar-system-wide aspects of a solar
 * system and contains solar bodies such as
 * planets and stars
 *
 * @author Alex, Taylor
 */
public class SolarSystem implements Serializable {
    private final String name;
    private final int x;
    private final int y;
    private final TechLevel tech;
    private final PoliticalSystem government;
    private final Star sun;
    private final Planet[] planets;
    private boolean isDiscovered;

    /**
     * Create a new solar system.
     *
     * @param name The name of the system.
     * @param xLoc The x-coordinate of the system's location on the universe
     * map.
     * @param yLoc The y-coordinate of the system's location.
     * @param techLevel The level of technology in the solar system.
     * @param governmentType The type of government in the solar system.
     */
    public SolarSystem(String name, int xLoc, int yLoc, TechLevel techLevel,
                       PoliticalSystem governmentType) {
        this.name = name;
        x = xLoc;
        y = yLoc;
        tech = techLevel;
        government = governmentType;
        sun = new Star(""); //add name
        planets = generatePlanets();
        isDiscovered = false;
    }

    private Planet[] generatePlanets() {
        Planet [] planetArray = new Planet[(int)(Math.random() * 4.0 + 4.0)];
        int dist = sun.getRadius() + 40;

        for (int i = 0; i < planetArray.length; i++){
            dist += 2 + (i + 0.25) * 6.5 * (0.76 + 0.24 * (Math.random() + 1));
            planetArray[planetArray.length - i - 1] = new Planet(this, name + " " + i, dist, sun.getTemperature()); //add name
        }

        return planetArray;
    }

    @Override
    public String toString() {
        String temp = "System name: " + getName() + "\n";
        temp += "Sun:\n" + getSun() + "\nTech Level:\t" + getTech() + "\nGovernment:\t"
                + getGovernment() + "\n\nPlanets:\n";

        for (Planet p : getPlanets()) {
            temp += p + "\n~~~~~\n";
        }

        return temp;
    }

    /**
     * @return The tech level of the solar system
     */
    public TechLevel getTechLevel() {
        return getTech();
    }

    /**
     * @return The x coordinate of the solar system's location in the universe
     */
    public int getX() {
        return x;
    }

    /**
     * @return The y coordinate of the solar system's location in the universe
     */
    public int getY() {
        return y;
    }

    /**
     * @return The name of the solar system
     */
    public String getName() {
        return name;
    }

    /**
     * @return The star in the solar system
     */
    public Star getSun() {
        return sun;
    }

    /**
     * @return A randomly chosen planet in the solar system.
     */
    public Planet getRandomPlanet() {
        return planets[(int)(Math.random() * planets.length)];
    }

    /**
     * @return Whether or not the solar system has been discovered.
     */
    public boolean isDiscovered() {
        return isDiscovered;
    }

    /**
     * Store that a solar system has been discovered by the player.
     */
    public void discover() {
        isDiscovered = true;
    }

    /**
     * @return the tech
     */
    public TechLevel getTech() {
        return tech;
    }

    /**
     * @return the government
     */
    public PoliticalSystem getGovernment() {
        return government;
    }

    /**
     * @return the planets
     */
    public Planet[] getPlanets() {
        return planets;
    }
}

