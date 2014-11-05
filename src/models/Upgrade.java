package models;

/**
 * Interface for any upgrade that can be applied to a ship
 */
public interface Upgrade {
    public TechLevel getTechLevel();
    public int getPrice();
    public String getSlot();
    public String getName();

}
