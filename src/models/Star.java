package models;

//Types of stars - currently unused?
enum StarType { //http://www.enchantedlearning.com/subjects/astronomy/stars/startypes.shtml
    B,
    A,
    F,
    G,
    K,
    M;
}

/**
 * Class representing a solar system's star.
 * 
 * @author Taylor
 */
public class Star {
    private final String name;
    private final int temperature; //In KelvenE3
    //private final StarType type;

    public Star(String name) {
        this.name = name;
        temperature = (int) (5 + (10 * (Math.pow(Math.random(), 2))));
    }

    /**
     * @return The star's temperature
     */
    public int getTemperature() {
        return temperature;
    }

    public String toString() {
        return name + "\t " + temperature + "KE3";
    }

}
