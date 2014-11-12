package models;

import java.io.Serializable;
import javafx.scene.paint.Color;

/**
 * http://www.enchantedlearning.com/subjects/astronomy/stars/startypes.shtml
 *
 * @author Taylor
 */
public class Star implements Serializable {
    //private final Type type;
    private final String name;
    private final int temperature; //In Kelvin * 10^3
    private final int radius; //based on the website above (relative to our sun)

    /**
     * Create a new star.
     *
     * @param name The name of the star.
     */
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

    /**
     * @return A string describing the star.
     */
    @Override
    public String toString() {
        return name + "\t " + temperature + "KE3";
    }

    /**
     * @return The color of the star, calculated based on its temperature.
     */
    public Color computeColor() {
        int r, g, b;
        //star coloration seems too pale
        if (0.0 <= temperature && temperature < 3.7) {
            r = 255;
            g = (int)lerp(temperature, 0, 140, 3.7, 110);
            b = (int)lerp(temperature, 0, 40, 3.7, 70);
        } else if (temperature < 5.2) {
            r = 255;
            g = (int)lerp(temperature, 3.7, 110, 5.2, 100);
            b = (int)lerp(temperature, 3.7, 60, 5.2, 90);
        } else if (temperature < 6) {
            r = (int)lerp(temperature, 5.2, 195, 6, 190);
            g = (int)lerp(temperature, 5.2, 125, 6, 170);
            b = (int)lerp(temperature, 5.2, 90, 6, 140);
        } else if (temperature < 7.5) {
            r = (int)lerp(temperature, 6, 190, 7.5, 140);
            g = (int)lerp(temperature, 6, 170, 7.5, 140);
            b = (int)lerp(temperature, 6, 140, 7.5, 195);
        } else if (temperature < 10) {
            r = (int)lerp(temperature, 7.5, 140, 10, 100);
            g = (int)lerp(temperature, 7.5, 140, 10, 120);
            b = 255;
        } else if (temperature < 20) {
            r = (int)lerp(temperature, 10, 100, 20, 75);
            g = (int)lerp(temperature, 10, 120, 20, 100);
            b = 255;
        } else if (temperature < 30) {
            r = (int)lerp(temperature, 20, 75, 30, 60);
            g = (int)lerp(temperature, 20, 100, 30, 75);
            b = 255;
        } else {
            r = 60;
            g = 70;
            b = 255;
        }
        return Color.rgb(r, g, b);
    } 
    
    private double lerp(double x, double x0, double y0, double x1, double y1) {
        return y0 + (y1 - y0) * (x - x0) / (x1 - x0);
    }

    /**
     * The classification of the star.
     */
    private enum Type {
        B,
        A,
        F,
        G,
        K,
        M;
    }
}
