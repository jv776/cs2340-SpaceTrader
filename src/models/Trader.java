package models;

/**
 * Class representing a traveling Trader
 *
 * @author Taylor
 */
public class Trader extends Encounterable {
    protected String name;
    private Planet origin;

    protected String escapeText;

    public Trader(String name, Planet origin){
        this.name = name;
        this.origin = origin;
        escapeText = "Is that all, stranger?";
        welcomeText = "What're ya buy'in?";
    }
    public String getName(){
        return name;
    }

    public Planet getOrigin(){
        return origin;
    }

}
