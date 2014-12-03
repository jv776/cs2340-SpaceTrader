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
    WELCOME("Welcome"),
    SHIP_CUSTOMIZATION("ShipCustomization"),
    NEW_RANDOM_EVENT("NewRandomEvent"),
    NEW_SOLAR_SYSTEM("NewSolarSystem"),
    NEW_SPACE_PORT("NewSpacePort"),
    TAVERN("Tavern"),
    SHIP_STATUS("Status");
    
    private String name;
    
    Screens(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
