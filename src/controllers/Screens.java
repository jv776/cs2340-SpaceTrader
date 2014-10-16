package controllers;

/**
 * @author Roi Atalla
 */
public enum Screens {
    MARKET("Market"),
    SOLAR_SYSTEM_MAP("SolarSystemMap"),
    UNIVERSE_MAP("UniverseMap"),
    WELCOME("Welcome");
    
    private String name;
    
    Screens(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
