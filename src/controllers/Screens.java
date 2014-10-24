package controllers;

/**
 * @author Roi Atalla
 */
public enum Screens {
    CUSTOMIZATION("Customization"),
    MARKET("Market"),
    PIRATE_EVENT("PirateEvent"),
    POLICE_EVENT("PoliceEvent"),
    RANDOM_EVENT("RandomEvent"),
    SHIP_YARD("ShipYard"),
    SOLAR_SYSTEM_MAP("SolarSystemMap"),
    SPACE_PORT("SpacePort"),
    TRADE_EVENT("TradeEvent"),
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
