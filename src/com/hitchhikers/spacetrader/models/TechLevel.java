package com.hitchhikers.spacetrader.models;

/**
 * Created by limbic on 9/18/14.
 * Moved the TechLevels enum out of Universe so that other classes won't have to
 * access it as Universe.TechLevel (which seems a bit unintuitive to me).
 */
public enum TechLevel {
    /**
     * Pre-agricultural technology
     */
    PRE_AGRICULTURE,

    /**
     * Agricultural technology
     */
    AGRICULTURE,

    /**
     * Medieval technology
     */
    MEDIEVAL,

    /**
     * Renaissance period technology
     */
    RENAISSANCE,

    /**
     * Early industrial technology
     */
    EARLY_INDUSTRIAL,

    /**
     * Industrial technology
     */
    INDUSTRIAL,

    /**
     * Post-industrial technology
     */
    POST_INDUSTRIAL,

    /**
     * Very advanced technology
     */
    HI_TECH;
    
    @Override
    public String toString() {
        switch (this.ordinal()) {
            case 0: return "Preagriculture";
            case 1: return "Agriculture";
            case 2: return "Medieval";
            case 3: return "Renaissance";
            case 4: return "Early Industrial";
            case 5: return "Industrial";
            case 6: return "Post-Industrial";
            default: return "High Tech";
        }
    }
}
