package models;

/**
 * http://www.enchantedlearning.com/subjects/astronomy/stars/startypes.shtml
 *
 * @author Taylor
 */
public class Star {
    private final String name;
    private final int temperature; //In Kelvin * 10^3
    private final int radius; //based on the website above (relative to our sun)
    //private final StarType type;

    public Star(String name) {
        this.name = name;
        temperature = (int)(5 + (10 * (Math.pow(Math.random(), 2))));
        radius = (int)(3.2678 * Math.exp(0.0983 * temperature));
    }

    /**
     * @return The star's temperature in thousands of Kelvins
     */
    public int getTemperature() {
        return temperature;
    }
    
    /**
     * @return The radius of the star
     */
    public int getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return name + "\t " + temperature + "KE3";
    }

    public enum Type {
        B,
        A,
        F,
        G,
        K,
        M;
    }
}
