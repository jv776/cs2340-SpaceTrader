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
<<<<<<< HEAD
    /**
     * The first screen displayed to the player.
     */
    WELCOME("Welcome"),

    /**
     * The screen to equip and unequip ship upgrades.
     */
    SHIP_CUSTOMIZATION("ShipCustomization"),

    /**
     * The new random event that includes a two dimensional minigame.
     */
    NEW_RANDOM_EVENT("NewRandomEvent"),

    /**
     * The traversable solar system controller. (Currently unused)
     */
    NEW_SOLAR_SYSTEM("NewSolarSystem");

    private String name;

    Screens(String name) {
        this.name = name;
    }

    /**
     * @return The name of the Screen.
     */
    public String getName() {
        return name;
    }
}
