package models;

/**
 * Created by limbic on 9/18/14.
 * Moved the TechLevels enum out of Universe so that other classes won't have to
 * access it as Universe.TechLevel (which seems a bit unintuitive to me).
 */
public enum TechLevel {
    PRE_AGRICULTURE,
    AGRICULTURE,
    MEDIEVAL,
    RENAISSANCE,
    EARLY_INDUSTRIAL,
    INDUSTRIAL,
    POST_INDUSTRIAL,
    HIGH_TECH;
    
    int toInt() {
        switch (this) {
            case PRE_AGRICULTURE: return 0;
            case AGRICULTURE: return 1;
            case MEDIEVAL: return 2;
            case RENAISSANCE: return 3;
            case EARLY_INDUSTRIAL: return 4;
            case INDUSTRIAL: return 5;
            case POST_INDUSTRIAL: return 6;
            default: return 7; //HI_TECH
        }
    }
    
    public String toString() {
        switch (this) {
            case PRE_AGRICULTURE: return "Pre-Agriculture";
            case AGRICULTURE: return "Agriculture";
            case MEDIEVAL: return "Medieval";
            case RENAISSANCE: return "Renaissance";
            case EARLY_INDUSTRIAL: return "Early Industrial";
            case INDUSTRIAL: return "Industrial";
            case POST_INDUSTRIAL: return "Post Industrial";
            default: return "High Tech"; //HI_TECH
        }
    }
}
