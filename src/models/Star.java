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
    
    public Color computeColor() {
        int r, g, b;
        //star coloration seems too pale
        if (0.0 <= temperature && temperature < 3.7) {
            r = 255;
            g = (int)lerp(temperature, 0, 170, 3.7, 140);
            b = (int)lerp(temperature, 0, 70, 3.7, 100);
        } else if (temperature < 5.2) {
            r = 255;
            g = (int)lerp(temperature, 3.7, 140, 5.2, 130);
            b = (int)lerp(temperature, 3.7, 90, 5.2, 120);
        } else if (temperature < 6) {
            r = (int)lerp(temperature, 5.2, 225, 6, 220);
            g = (int)lerp(temperature, 5.2, 155, 6, 200);
            b = (int)lerp(temperature, 5.2, 120, 6, 170);
        } else if (temperature < 7.5) {
            r = (int)lerp(temperature, 6, 220, 7.5, 170);
            g = (int)lerp(temperature, 6, 200, 7.5, 170);
            b = (int)lerp(temperature, 6, 170, 7.5, 225);
        } else if (temperature < 10) {
            r = (int)lerp(temperature, 7.5, 170, 10, 130);
            g = (int)lerp(temperature, 7.5, 170, 10, 150);
            b = 255;
        } else if (temperature < 20) {
            r = (int)lerp(temperature, 10, 130, 20, 105);
            g = (int)lerp(temperature, 10, 150, 20, 130);
            b = 255;
        } else if (temperature < 30) {
            r = (int)lerp(temperature, 20, 105, 30, 90);
            g = (int)lerp(temperature, 20, 130, 30, 105);
            b = 255;
        } else {
            r = 80;
            g = 90;
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
    public enum Type {
        B,
        A,
        F,
        G,
        K,
        M;
    }
}
