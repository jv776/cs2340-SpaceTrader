/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hitchhikers.spacetrader.models;

import java.io.Serializable;

/**
 * Models various solar-system-wide aspects of a solar system and contains solar bodies such as
 * planets and stars
 *
 * @author Alex, Taylor
 */
public class SolarSystem implements Serializable {
    private final String name;
    private final int x;
    private final int y;
    private final double crime;
    private final double law;
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
        crime = government.baseCrime + government.crimeVar*Math.random();
        law = government.baseLaw+ government.lawVar*Math.random();
        sun = new Star(""); //add name
        planets = generatePlanets();
        isDiscovered = false;
    }

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
        Planet [] planetArray = new Planet[(int)(Math.random() * 4.0 + 4.0)];
        int dist = sun.getRadius() + 40;

        for (int i = 0; i < planetArray.length; i++){
            dist += 2 + (i + 0.25) * 6.5 * (0.76 + (0.24 * (Math.random() + 1)));
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

    private String getLevel(double factor){
        if(factor ==0){
            return "absent";
        } else if(factor <.2){
            return "very low";
        } else if(factor <.4){
            return "low";
        } else if(factor <.6){
            return "normal";
        } else if(factor <.8){
            return "high";
        } else if(factor <1){
            return "very high";
        } else if(factor == 1){
            return "total";
        } else {
            return "Unknown";
        }
    }

    public String getCrimeLevel(){
        return getLevel(crime);
    }
    public String getLawLevel(){
        return getLevel(law);
    }

    public double getCrime(){
        return crime;
    }
    public double getLaw(){
        return law;
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

