package controllers;

/**
 * @author Roi Atalla
 */
public enum Screens {
    /**
     * The customization screen displayed when creating a new character.
     */
    CUSTOMIZATION("Customization"),
    
    /**
     * The market screen, where the player can choose to buy and sell goods.
     */
    MARKET("Market"),
    
    /**
     * The screen displayed when the player encounters a pirate.
     */
    PIRATE_EVENT("PirateEvent"),
    
    /**
     * The screen displayed when the player encounters the police.
     */
    POLICE_EVENT("PoliceEvent"),
    
    /**
     * The generic random event screen.
     */
    RANDOM_EVENT("RandomEvent"),
    
    /**
     * The shipyard screen, where the player can buy/upgrade ships.
     */
    SHIP_YARD("ShipYard"),
    
    /**
     * The screen to display a map of each solar system.
     */
    SOLAR_SYSTEM_MAP("SolarSystemMap"),
    
    /**
     * The space port screen which is displayed upon landing on a planet.
     */
    SPACE_PORT("SpacePort"),
    
    /**
     * The screen displayed when the player encounters a trader while traveling.
     */
    TRADE_EVENT("TradeEvent"),
    
    /**
     * The screen to display a map of each solar system.
     */
    UNIVERSE_MAP("UniverseMap"),
    
    /**
     * The first screen displayed to the player.
     */
    WELCOME("Welcome"),
    
    /**
     * The screen for buying upgrades.
     */
    UPGRADE("Upgrade");
    
    private String name;
    
    Screens(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
