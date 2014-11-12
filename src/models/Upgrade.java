package models;

/**
 * Interface for any upgrade that can be applied to a ship
 */
public interface Upgrade {
    /**
     * @return The tech level required to buy the upgrade.
     */
    TechLevel getTechLevel();

    /**
     * @return The price of the upgrade.
     */
    int getPrice();

    /**
     * @return The upgrade slot needed for the upgrade.
     */
    String getSlot();

    /**
     * @return The name of the upgrade.
     */
    String getName();

}
