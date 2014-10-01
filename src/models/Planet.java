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
    final SolarSystem solarSystem;
    final String name;
    final Resource resource;
    
    private final int distance; //Representing radial distance from the sun in kmE6
    //private final int radius; //Radius of the planet in km
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

    public Planet(SolarSystem s, String name, int distance, int sunTemperature){
        Random rand = new Random();
        
        this.solarSystem = s;
        this.name = name;
        this.distance = distance;
        this.resource = randomResource();
        
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

    /**
     * Get the event (if any) currently happening on the planet.
     * 
     * @return The current planetary event, if any
     */
    Optional<PriceEvent> getCurrentEvent() {
        return currentEvent;
    }
    
    /**
     * Get a description of the current event on the planet.
     * 
     * @return The type of event on this planet, if any
     */
    public String currentEvent() {
        if (currentEvent.isPresent()) {
            return currentEvent.get().toString().toLowerCase();
        } else {
            return "No major event";
        }
    }
    
    /**
     * Get a string containing the type of government ruling the planet.
     * 
     * @return The type of government on the planet
     */
    public String governmentType() {
        return solarSystem.government.toString().toLowerCase().replace('_', ' ');
    }
    
    /**
     * Get a string containing the level of technology in the planet's
     * solar system.
     * 
     * @return The technology available on the planet
     */
    public String technologyLevel() {
        return solarSystem.tech.toString().toLowerCase().replace('_', ' ');
    }
    
    /**
     * Get a string containing the special resources on this planet.
     * 
     * @return Description of available resources
     */
    public String resourceType() {
        return resource.toString().toLowerCase().replace('_', ' ');
    }
    
    public Resource getResource() {
        return resource;
    }
    
    public SolarSystem getSolarSystem() {
        return solarSystem;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean supportsLife() {
        return supportsLife;
    }
    
    public Marketplace getMarket() {
        return market;
    }

    /*
     * Another ugly helper function for randomly choosing resources on each
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
}
