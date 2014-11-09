package models;

/**
 * Interface for any upgrade that can be applied to a ship
 */
public interface Upgrade {
    /**
     * @return The tech level required to buy the upgrade.
     */
    public TechLevel getTechLevel();

    /**
     * @return The price of the upgrade.
     */
    public int getPrice();

    /**
     * @return The upgrade slot needed for the upgrade.
     */
    public String getSlot();

    /**
     * @return The name of the upgrade.
     */
    public String getName();

}
