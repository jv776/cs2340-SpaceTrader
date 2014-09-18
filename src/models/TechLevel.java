package models;

/**
 * Created by limbic on 9/18/14.
 */ //Moved the TechLevels enum out of Universe so that other classes won't have to
//access it as Universe.TechLevel (which seems a bit unintuitive to me).
enum TechLevel {
    PRE_AGRICULTURE,
    AGRICULTURE,
    MEDIEVAL,
    RENAISSANCE,
    EARLY_INDUSTRIAL,
    POST_INDUSTRIAL,
    HI_TECH;
}
