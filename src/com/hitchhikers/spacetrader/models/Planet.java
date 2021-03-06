/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitchhikers.spacetrader.models;

import java.io.Serializable;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javafx.scene.paint.Color;

/**
 * Models various aspects of a Planet (currently its atmosphere, natural
 * resources, and trading market).
 *
 * @author Alex, John
 */
public class Planet implements Serializable {
    private final SolarSystem solarSystem;
    private final String name;
    private final Resource resource;

    private final int distance; //Representing radial distance from the sun in kmE6
    private final int radius; //Radius of the planet in km * 10^3
    private final boolean nitrogen; //N, O, C, and H dont really need to be instance variable
    private final boolean oxygen;
    private final boolean carbon;
    private final boolean hydrogen;
    private final boolean metals;
    private final int atmosphere; //percent sun radiation blocked
    private final boolean water; //Wheter the planet has water
    private final int temperature; //average temp in Kelvin

    private final boolean supportsLife; //Whether a planet can support native life

    private Marketplace market;
    private Upgradeplace upgrade;
    private PriceEvent currentEvent;
    private transient Color color;
    private transient Image diffuseImage;
    private transient Image bumpMap;

    /**
     * Create a new Planet and calculate it's resources and atmospheric
     * properties.
     *
     * @param system         The solar system in which the planet is located.
     * @param name           The name of the planet.
     * @param distance       The distance of the planet from it's sun.
     * @param sunTemperature The temperature of the sun on the new planet.
     */
    public Planet(SolarSystem system, String name, int distance, int sunTemperature) {
        this.solarSystem = system;
        this.name = name;
        this.distance = distance;
        this.resource = randomResource();
        radius = (int) (5 * Math.random() + 6);

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

        currentEvent = PriceEvent.NONE;
        market = new Marketplace(this);
        upgrade = new Upgradeplace(solarSystem);
        
        double prop = temperature / 2000.0;
        color = Color.hsb(240 - 240 * prop, 1, .5);
    }

    private int generateAtmosphere() {
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
        return (int) (atm - atm * (.50 * (Math.random())));
    }


    private int generateTemperature(int sunTemp) { //units
        float temp = (float) Math.pow((sunTemp * 1000 / (float) Math.pow(getDistance(), 2)), .50);
        temp -= temp * (getAtmosphere() / 100f);
        return (int) (temp * 1000);
    }

    private boolean generateWater() {
        return ((oxygen && hydrogen) && (temperature > 100 && temperature < 400));
    }


    private boolean generateLife() {
        if (nitrogen && carbon && water && metals) {
            return true;
        } else {
            return (Math.random() < 0.01);
        }
    }

    /**
     * @return A string providing information about the planet.
     */
    @Override
    public String toString() {
        return "Dist: " + getDistance() + "kmE6 \tAtm: " + getAtmosphere() + "% \tTemp: "
                + (getTemperature() - 273) + "C \tM: " + isMetals() + " \tN: " + isNitrogen()
                + " \tC: " + isCarbon() + " \tO: " + isOxygen() + " \tW: " + isWater() + " \tH: "
                + isHydrogen() + " \tLife: " + isSupportsLife() + " \tResources: "
                + getResource();
    }

    /**
     * Get the event (if any) currently happening on the planet.
     *
     * @return The current planetary event, if any
     */
    PriceEvent getCurrentEvent() {
        return currentEvent;
    }

    /**
     * Get a description of the current event on the planet.
     *
     * @return The type of event on this planet, if any
     */
    public String currentEvent() {
        return currentEvent.toString().toLowerCase();
    }

    /**
     * Get a string containing the type of government ruling the planet.
     *
     * @return The type of government on the planet
     */
    public String governmentType() {
        return getSolarSystem().getGovernment().toString().toLowerCase().replace('_', ' ');
    }

    /**
     * Get a string containing the level of technology in the planet's
     * solar system.
     *
     * @return The technology available on the planet
     */
    public String technologyLevel() {
        return getSolarSystem().getTech().toString().toLowerCase().replace('_', ' ');
    }

    /**
     * Get a string containing the special resources on this planet.
     *
     * @return Description of available resources
     */
    public String resourceType() {
        return getResource().toString().toLowerCase().replace('_', ' ');
    }

    /**
     * @return The most abundant resource on the planet
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * @return The solar system in which the planet is located
     */
    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    /**
     * @return The name of the planet
     */
    public String getName() {
        return name;
    }

    /**
     * @return The distance between the planet and its sun
     */
    public int getDistance() {
        return distance;
    }

    /**
     * @return The radius of the planet in thousands of kilometers
     */
    public int getRadius() {
        return radius;
    }

    /**
     * @return Whether or not the planet naturally supports life
     */
    public boolean supportsLife() {
        return isSupportsLife();
    }

    /**
     * @return The marketplace located on the planet
     */
    public Marketplace getMarket() {
        return market;
    }

    /*
     * Another ugly helper function for randomly choosing resources on each
     * planet. Feel free to adjust probabilities or rewrite entirely.
     */
    private static Resource randomResource() {
        double r = Math.random();
        int resources = Resource.values().length;
        return Resource.values()[(int) (r * (resources))];
//        if (0.0 <= r && r < 0.3) {
//            return Resource.NO_SPECIAL_RESOURCES; //30% chance
//        } else if (0.3 <= r && r < 0.35) {
//            return Resource.MINERAL_RICH; //5% chance
//        } else if (0.35 <= r && r < 0.4) {
//            return Resource.MINERAL_POOR; //5% chance
//        } else if (0.4 <= r && r < 0.5) {
//            return Resource.DESERT; //10% chance
//        } else if (0.5 <= r && r < 0.55) {
//            return Resource.LOTS_OF_WATER; //5% chance
//        } else if (0.55 <= r && r < 0.6) {
//            return Resource.RICH_SOIL; //5% chance
//        } else if (0.6 <= r && r < 0.65) {
//            return Resource.POOR_SOIL; //5% chance
//        } else if (0.65 <= r && r < 0.7) {
//            return Resource.RICH_FAUNA; //5% chance
//        } else if (0.7 <= r && r < 0.85) {
//            return Resource.LIFELESS; //15% chance
//        } else if (0.85 <= r && r < 0.87) {
//            return Resource.WEIRD_MUSHROOMS; //2% chance
//        } else if (0.87 <= r && r < 0.9) {
//            return Resource.LOTS_OF_HERBS; //3% chance
//        } else if (0.9 <= r && r < 0.95) {
//            return Resource.ARTISTIC; //5% chance
//        } else {
//            return Resource.WARLIKE; //5% chance
//        }
    }

    /**
     * @return The ship upgrade available on this planet.
     */
    public Upgradeplace getUpgrade() {
        return upgrade;
    }

    /**
     * @return the nitrogen
     */
    public boolean isNitrogen() {
        return nitrogen;
    }

    /**
     * @return the oxygen
     */
    public boolean isOxygen() {
        return oxygen;
    }

    /**
     * @return the carbon
     */
    public boolean isCarbon() {
        return carbon;
    }

    /**
     * @return the hydrogen
     */
    public boolean isHydrogen() {
        return hydrogen;
    }

    /**
     * @return the metals
     */
    public boolean isMetals() {
        return metals;
    }

    /**
     * @return the atmosphere
     */
    public int getAtmosphere() {
        return atmosphere;
    }

    /**
     * @return the water
     */
    public boolean isWater() {
        return water;
    }

    /**
     * @return the temperature
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * @return the supportsLife
     */
    public boolean isSupportsLife() {
        return supportsLife;
    }

    /**
     * @return The visible color of the planet from space.
     */
    public Color getColor() {
        return color;
    }
    
    public Image getDiffuseMap() {
        if (diffuseImage == null) {
            createDiffuseAndBumpMap();
        } 
        return diffuseImage;
    }
    
    public Image getBumpMap() {
        if (bumpMap == null) {
            createDiffuseAndBumpMap();
        }
        return bumpMap;
    }
    
    private static double clamp(double d) {
        return Math.max(Math.min(d, 1), 0);
    }
    
    private void createDiffuseAndBumpMap() {
        WritableImage diffuse = new WritableImage(256, 128);
        WritableImage bump = new WritableImage(256, 128);
        PixelWriter diffuseWriter = diffuse.getPixelWriter();
        PixelWriter bumpWriter = bump.getPixelWriter();
        
        if (color == null) {
            color = new Color(Math.random(), Math.random(), Math.random(), 1.0);
        }
        
        Color c = new Color(Math.random(), Math.random(), Math.random(), 1.0);
        
        for (int x = 0; x < diffuse.getWidth(); x++) {
            for (int y = 0; y < diffuse.getHeight(); y++) {
                double offset = Math.random() * 2 * Math.PI;
                int freq = 2 * (1 + (int)(Math.random() * 5));
                double x1 = freq * Math.PI * x / diffuse.getWidth() + offset;
                double y1 = freq * Math.PI * y / diffuse.getHeight() + offset;
                
                diffuseWriter.setColor(x, y, color.interpolate(c, Math.sin(x1 + Math.random()) * Math.cos(y1 + Math.random())));
                bumpWriter.setColor(x, y, c.interpolate(c.darker(), Math.random()).desaturate());
            }
        }
        
        bumpMap = bump;
        diffuseImage = diffuse;
    }
}
