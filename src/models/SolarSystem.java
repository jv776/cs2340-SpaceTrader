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
    private final Star sun;
    private final Planet[] planets;
    
    public SolarSystem(String name, int xLoc, int yLoc) {
        this.name = name;
        x = xLoc;
        y = yLoc;
        sun = new Star(""); //add name
        planets = generatePlanets();
    }

    private Planet[] generatePlanets(){
        Planet [] planetArray = new Planet[7];
        int dist = 40;
        for (int i = 0; i<planetArray.length; i++){
            dist += (i+0.25)*50*(.76+(.24*Math.random()));
            planetArray[i] = new Planet("", dist, sun.getTemperature()); //add name
        }
        return planetArray;
    }
    
    public String toString() {
        String temp = name+  "\n";
        temp += "Sun:\n" + sun +"\n\nPlanets:\n";
        for(Planet p:planets){
            temp += p +"\n~~~~~\n";
        }
        return temp;
    }

}
