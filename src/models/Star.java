package models;

/**
 * Created by Taylor on 9/18/14.
 */
enum StarType { //http://www.enchantedlearning.com/subjects/astronomy/stars/startypes.shtml
    B,
    A,
    F,
    G,
    K,
    M;
}

public class Star {
    private final String name;
    private final int temperature; //In KelvenE3
    //private final StarType type;

    public Star(String name){
        this.name = name;
        temperature = (int)( 5+(10*(Math.pow(Math.random(), 2))));
    }

    public int getTemperature(){
        return temperature;
    }

    public String toString(){
        return name + "\t " + temperature + "KE3";
    }

}
