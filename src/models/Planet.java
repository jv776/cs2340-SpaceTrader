/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Optional;
import java.util.Random;

/**
 * Models various aspects of a Planet (currently its atmosphere, natural
 * resources, and trading market).
 * 
 * @author Alex, John
 */
public class Planet {
    final String name;
    final SolarSystem system;
    private final int distance; //Representing radial distance from the sun in kmE6
    //private final int radius; //Radius of the planet in km
    final Resource resource;
    private final boolean nitrogen; //N, O, C, and H dont really need to be instance variable
    private final boolean oxygen;
    private final boolean carbon;
    private final boolean hydrogen;
    private final boolean metals;
    private final int atmosphere; //percent sun radiation blocked
    private final boolean water; //Wheter the planet has water
    private final int temperature; //average temp in Kelvin

    private final boolean supportsLife; //Whether a planet can support native life

    private boolean colonized;
    
    private Marketplace market;
    private Optional<PriceEvent> currentEvent;

    public Planet(String name, SolarSystem location, int distance,
            int sunTemperature, Resource specialResource){
        Random rand = new Random();
        this.name = name;
        system = location;
        this.distance = distance;
        resource = specialResource;
        
        //need to adjust resource levels
        nitrogen = (Math.random() < 0.95);
        oxygen = (Math.random() < 0.85);
        carbon = (Math.random() < 0.95);
        hydrogen = (Math.random() < 0.95);
        metals = (Math.random() < 0.75);
        atmosphere = generateAtmosphere();
        temperature = generateTemperature(sunTemperature);
        water = generateWater();
        supportsLife = generateLife();
        
        currentEvent = Optional.empty();
        market = new Marketplace(this);
    }

    private int generateAtmosphere(){
        double atm = 0;
        
        if (nitrogen) {
            atm += 15;
        }
        
        if (carbon) {
            atm += 7;
        }
        
        if (oxygen) {
            atm += 5;
        }
        return (int)(atm - atm * (.50*(Math.random())));
    }

    private int generateTemperature(int sunTemp){ //units
        float temp = (float)Math.pow((sunTemp*1000/(float)Math.pow(distance,2)),.50);
        temp -= temp*(atmosphere/100f);
        return (int)(temp*1000);
    }

    private boolean generateWater(){
        return ((oxygen && hydrogen) && (temperature > 100 && temperature < 400));
    }

    private boolean generateLife(){
        if(nitrogen && carbon && water&&metals){
            return true;
        } else {
            return (Math.random() < 0.01);
        }
    }

    @Override
    public String toString(){
        return "Dist: " + distance + "kmE6 \tAtm: "+ atmosphere + "% \tTemp: " +
                (temperature - 273) + "C \tM: "+ metals + " \tN: "+ nitrogen +
                " \tC: "+ carbon +" \tO: "+ oxygen +" \tW: "+ water +" \tH: "+
                hydrogen + " \tLife: "+ supportsLife + " \tResources: "
                + resource;
    }

    Optional<PriceEvent> getCurrentEvent() {
        return currentEvent;
    } 
}
