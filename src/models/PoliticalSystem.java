package models;

/**
 * Represents various political structures
 * <p>
 * Created by Taylor on 9/18/14.
 */
enum PoliticalSystem {
    ANARCHY(.2,.8,0,0),
    CAPITALIST_STATE(.2,.8,.2,.4),
    COMMUNIST_STATE(0,1,.6,.4),
    CONFEDERACY(.2,.8,.2,.4),
    CORPORATE_STATE(.3,.5,0,.4),
    CYBERNETIC_STATE(.1,.2,.4,.5),
    DEMOCRACY(.2,.4,.2,.4),
    DICTATORSHIP(0,1,.7,.3),
    FASCIST_STATE(.3,.2,1,0),
    FEUDAL_STATE(.25,.5,0,.2),
    MILITARY_STATE(.1,.6,.6,.4),
    MONARCHY(.4,.2,.1,.3),
    PACIFIST_STATE(0,.5,0,0),
    SOCIALIST_STATE(.2,.5,.4,.4),
    STATE_OF_SATORI(0,.3,0,.5),
    TECHNOCRACY(.1,.9,.1,.9),
    THEOCRACY(.3,.3,.1,.5);
    public final double baseCrime;
    public final double crimeVar;
    public final double baseLaw;
    public final double lawVar;
    PoliticalSystem(double baseCrime, double crimeVar, double baseLaw,
                    double lawVar ){
        this.baseCrime = baseCrime;
        this.crimeVar = crimeVar;
        this.baseLaw = baseLaw;
        this.lawVar = lawVar;


    }
    @Override
    public String toString() {
        switch (this.ordinal()) {
            case 0: return "Anarchy";
            case 1: return "Capitalist";
            case 2: return "Communist";
            case 3: return "Confederacy";
            case 4: return "Corporate State";
            case 5: return "Cybernetic State";
            case 6: return "Democracy";
            case 7: return "Dictatorship";
            case 8: return "Fascist";
            case 9: return "Feudal";
            case 10: return "Military State";
            case 11: return "Monarchy";
            case 12: return "Pacifist State";
            case 13: return "Socialist";
            case 14: return "State Of Satori";
            case 15: return "Technocracy";
            default: return "Theocracy";
        }
    }
}
