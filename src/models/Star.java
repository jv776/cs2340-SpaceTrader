package models;

/**
 * http://www.enchantedlearning.com/subjects/astronomy/stars/startypes.shtml
 *
 * @author Taylor
 */
public class Star {
    private final String name;
    private final int temperature; //In Kelvin * 10e^3
    //private final StarType type;

    public Star(String name) {
        this.name = name;
        temperature = (int)(5 + (10 * (Math.pow(Math.random(), 2))));
    }

    public int getTemperature() {
        return temperature;
    }

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
