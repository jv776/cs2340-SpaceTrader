package models;

/**
 * Created by Taylor on 9/18/14.
 * <p/>
 * Moved the TechLevels enum out of Universe so that other classes won't have to
 * access it as Universe.Resources
 */
enum Resource {
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
}
