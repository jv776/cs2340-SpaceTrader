package models;

/**
 * Class representing a traveling Trader
 *
 * @author Taylor
 */
public class Trader extends Encounterable {

    private final Planet origin;

    private static final String escapeText = "Is that all, stranger?";

    /**
     * Create a new trader to be encountered while traveling through a
     * solar system.
     * 
     * @param traderName The trader's name.
     * @param traderOrigin The trader's home planet.
     */
    public Trader(String traderName, Planet traderOrigin) {
        name = traderName;
        origin = traderOrigin;
        welcomeText = "What're ya buy'in?";
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * @return The home planet of the trader.
     */
    public Planet getOrigin() {
        return origin;
    }

}
