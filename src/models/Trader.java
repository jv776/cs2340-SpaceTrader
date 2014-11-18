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

    @Override
    public void equipForDifficulty(int difficulty) {
        for (int i = 0; i < difficulty; i++) {
            ship.equipWeapon(new Weapon(Weapon.Type.Beam));
            ship.equipShield(new Shield(Shield.Type.Reflective));
        }
        if (difficulty > 2) {
            ship.equipGadget(new Gadget(Gadget.Type.ENGINEER_INCREASE));
        }
        if (difficulty > 3) {
            ship.equipGadget(new Gadget(Gadget.Type.FIGHTER_INCREASE));
        } 
    }
}
