package models;

/**
 * Interface for any upgrade that can be applied to a ship
 */
public interface Upgrade {
    TechLevel getTechLevel();

    int getPrice();

    String getSlot();

    String getName();

}
