package models;

/**
 * Created by Taylor on 9/18/14.
 * <p/>
 * Moved the TechLevels enum out of Universe so that other classes won't have to
 * access it as Universe.Resources
 */
public enum Resource {
    NO_SPECIAL_RESOURCES,
    MINERAL_RICH,
    MINERAL_POOR,
    DESERT,
    LOTS_OF_WATER,
    RICH_SOIL,
    POOR_SOIL,
    RICH_FAUNA,
    LIFELESS,
    WEIRD_MUSHROOMS,
    LOTS_OF_HERBS,
    ARTISTIC,
    WARLIKE;
    
    @Override
    public String toString() {
        switch (this.ordinal()) {
            case 0: return "No Special Resources";
            case 1: return "Mineral-rich";
            case 2: return "Mineral-poor";
            case 3: return "Desert";
            case 4: return "Lots of Water";
            case 5: return "Rich Soil";
            case 6: return "Poor Soil";
            case 7: return "Rich Fauna";
            case 8: return "Lifeless";
            case 9: return "Weird Mushrooms";
            case 10: return "Lots of Herbs";
            case 11: return "Artistic";
            default: return "Warlike";
        }
    }
}
