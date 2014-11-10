/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.io.Serializable;
import java.util.Random;

/**
 * A representation of the in-game universe.
 *
 * @author Alex, John
 */
public class Universe implements Serializable {
    private static final String[] SOLAR_SYSTEM_NAMES = {
            "Acamar",
            "Adahn",        // The alternate personality for The Nameless One in "Planescape: Torment"
            "Aldea",
            "Andevian",
            "Antedi",
            "Balosnee",
            "Baratas",
            "Brax",            // One of the heroes in Master of Magic
            "Bretel",        // This is a Dutch device for keeping your pants up.
            "Calondia",
            "Campor",
            "Capelle",        // The city I lived in while programming this game
            "Carzon",
            "Castor",        // A Greek demi-god
            "Cestus",
            "Cheron",
            "Courteney",    // After Courteney Cox…
            "Daled",
            "Damast",
            "Davlos",
            "Deneb",
            "Deneva",
            "Devidia",
            "Draylon",
            "Drema",
            "Endor",
            "Esmee",        // One of the witches in Pratchett's Discworld
            "Exo",
            "Ferris",        // Iron
            "Festen",        // A great Scandinavian movie
            "Fourmi",        // An ant, in French
            "Frolix",        // A solar system in one of Philip K. Dick's novels
            "Gemulon",
            "Guinifer",        // One way of writing the name of king Arthur's wife
            "Hades",        // The underworld
            "Hamlet",        // From Shakespeare
            "Helena",        // Of Troy
            "Hulst",        // A Dutch plant
            "Iodine",        // An element
            "Iralius",
            "Janus",        // A seldom encountered Dutch boy's name
            "Japori",
            "Jarada",
            "Jason",        // A Greek hero
            "Kaylon",
            "Khefka",
            "Kira",            // My dog's name
            "Klaatu",        // From a classic SF movie
            "Klaestron",
            "Korma",        // An Indian sauce
            "Kravat",        // Interesting spelling of the French word for "tie"
            "Krios",
            "Laertes",        // A king in a Greek tragedy
            "Largo",
            "Lave",            // The starting system in Elite
            "Ligon",
            "Lowry",        // The name of the "hero" in Terry Gilliam's "Brazil"
            "Magrat",        // The second of the witches in Pratchett's Discworld
            "Malcoria",
            "Melina",
            "Mentar",        // The Psilon home system in Master of Orion
            "Merik",
            "Mintaka",
            "Montor",        // A city in Ultima III and Ultima VII part 2
            "Mordan",
            "Myrthe",        // The name of my daughter
            "Nelvana",
            "Nix",            // An interesting spelling of a word meaning "nothing" in Dutch
            "Nyle",            // An interesting spelling of the great river
            "Odet",
            "Og",            // The last of the witches in Pratchett's Discworld
            "Omega",        // The end of it all
            "Omphalos",        // Greek for navel
            "Orias",
            "Othello",        // From Shakespeare
            "Parade",        // This word means the same in Dutch and in English
            "Penthara",
            "Picard",        // The enigmatic captain from ST:TNG
            "Pollux",        // Brother of Castor
            "Quator",
            "Rakhar",
            "Ran",            // A film by Akira Kurosawa
            "Regulas",
            "Relva",
            "Rhymus",
            "Rochani",
            "Rubicum",        // The river Ceasar crossed to get into Rome
            "Rutia",
            "Sarpeidon",
            "Sefalla",
            "Seltrice",
            "Sigma",
            "Sol",            // That's our own solar system
            "Somari",
            "Stakoron",
            "Styris",
            "Talani",
            "Tamus",
            "Tantalos",        // A king from a Greek tragedy
            "Tanuga",
            "Tarchannen",
            "Terosa",
            "Thera",        // A seldom encountered Dutch girl's name
            "Titan",        // The largest moon of Jupiter
            "Torin",        // A hero from Master of Magic
            "Triacus",
            "Turkana",
            "Tyrus",
            "Umberlee",        // A god from AD&D, which has a prominent role in Baldur's Gate
            "Utopia",        // The ultimate goal
            "Vadera",
            "Vagra",
            "Vandor",
            "Ventax",
            "Xenon",
            "Xerxes",        // A Greek hero
            "Yew",            // A city which is in almost all of the Ultima games
            "Yojimbo",        // A film by Akira Kurosawa
            "Zalkon",
            "Zuul"            // From the first Ghostbusters movie
    };

    /*
     * Very ugly helper function to semi-randomly choose tech levels for
     * planets. Feel free to adjust the numbers (I chose them somewhat
     * arbitrarily). Chances of a certain tech level being chosen are rough
     * estimates based on the assumption that Math.random() is relatively
     * uniform on the interval [0, 1].
     */
    private static TechLevel randomTechLevel() {
        double r = Math.random();

        // Breaks PMD rule "OnlyOneReturn"
        // Justification: Most obvious implementation for this code.
        if (0.0 <= r && r < 0.25) {
            return TechLevel.PRE_AGRICULTURE; //25% chance
        } else if (0.25 <= r && r < 0.45) {
            return TechLevel.AGRICULTURE; //20% chance
        } else if (0.45 <= r && r < 0.6) {
            return TechLevel.MEDIEVAL; //15% chance
        } else if (0.6 <= r && r < 0.7) {
            return TechLevel.RENAISSANCE; //10% chance
        } else if (0.7 <= r && r < 0.8) {
            return TechLevel.EARLY_INDUSTRIAL; //10% chance
        } else if (0.8 <= r && r < 0.875) {
            return TechLevel.INDUSTRIAL; //7.5% chance
        } else if (0.875 <= r && r < 0.95) {
            return TechLevel.POST_INDUSTRIAL; //7.5% chance
        } else {
            return TechLevel.HI_TECH; //5% chance
        }
    }

    //Chooses a random government based on a given TechLevel
    private static PoliticalSystem randomGovernment(TechLevel level) {
        double r = Math.random();

        PoliticalSystem p;

        if (level == TechLevel.PRE_AGRICULTURE) {
            if (0.0 <= r && r < 0.9) {
                p = PoliticalSystem.ANARCHY;
            } else if (0.9 <= r && r < 0.98) {
                p = PoliticalSystem.PACIFIST_STATE;
            } else {
                p = PoliticalSystem.STATE_OF_SATORI;
            }
        } else if (level == TechLevel.AGRICULTURE) {
            if (0.0 <= r && r < 0.4) {
                p = PoliticalSystem.ANARCHY;
            } else if (0.4 <= r && r < 0.6) {
                p = PoliticalSystem.FEUDAL_STATE;
            } else if (0.6 <= r && r < 0.7) {
                p = PoliticalSystem.MILITARY_STATE;
            } else if (0.7 <= r && r < 0.8) {
                p = PoliticalSystem.PACIFIST_STATE;
            } else if (0.8 <= r && r < 0.98) {
                p = PoliticalSystem.THEOCRACY;
            } else {
                p = PoliticalSystem.STATE_OF_SATORI;
            }
        } else if (level == TechLevel.MEDIEVAL) {
            if (0.0 <= r && r < 0.05) {
                p = PoliticalSystem.ANARCHY;
            } else if (0.05 <= r && r < 0.15) {
                p = PoliticalSystem.DICTATORSHIP;
            } else if (0.15 <= r && r < 0.35) {
                p = PoliticalSystem.FEUDAL_STATE;
            } else if (0.35 <= r && r < 0.4) {
                p = PoliticalSystem.MILITARY_STATE;
            } else if (0.4 <= r && r < 0.8) {
                p = PoliticalSystem.MONARCHY;
            } else if (0.8 <= r && r < 0.9) {
                p = PoliticalSystem.PACIFIST_STATE;
            } else if (0.9 <= r && r < 0.99) {
                p = PoliticalSystem.THEOCRACY;
            } else {
                p = PoliticalSystem.STATE_OF_SATORI;
            }
        } else if (level == TechLevel.RENAISSANCE) {
            if (0.0 <= r && r < 0.15) {
                p = PoliticalSystem.CONFEDERACY;
            } else if (0.15 <= r && r < 0.25) {
                p = PoliticalSystem.DEMOCRACY;
            } else if (0.25 <= r && r < 0.35) {
                p = PoliticalSystem.DICTATORSHIP;
            } else if (0.35 <= r && r < 0.45) {
                p = PoliticalSystem.MILITARY_STATE;
            } else if (0.45 <= r && r < 0.8) {
                p = PoliticalSystem.MONARCHY;
            } else if (0.8 <= r && r < 0.9) {
                p = PoliticalSystem.PACIFIST_STATE;
            } else {
                p = PoliticalSystem.THEOCRACY;
            }
        } else if (level == TechLevel.EARLY_INDUSTRIAL) {
            if (0.0 <= r && r < 0.2) {
                p = PoliticalSystem.CAPITALIST_STATE;
            } else if (0.2 <= r && r < 0.3) {
                p = PoliticalSystem.COMMUNIST_STATE;
            } else if (0.3 <= r && r < 0.5) {
                p = PoliticalSystem.DEMOCRACY;
            } else if (0.5 <= r && r < 0.55) {
                p = PoliticalSystem.DICTATORSHIP;
            } else if (0.55 <= r && r < 0.6) {
                p = PoliticalSystem.FASCIST_STATE;
            } else if (0.6 <= r && r < 0.7) {
                p = PoliticalSystem.MILITARY_STATE;
            } else if (0.7 <= r && r < 0.8) {
                p = PoliticalSystem.PACIFIST_STATE;
            } else if (0.8 <= r && r < 0.9) {
                p = PoliticalSystem.SOCIALIST_STATE;
            } else {
                p = PoliticalSystem.TECHNOCRACY;
            }
        } else if (level == TechLevel.INDUSTRIAL) {
            if (0.0 <= r && r < 0.3) {
                p = PoliticalSystem.CAPITALIST_STATE;
            } else if (0.3 <= r && r < 0.4) {
                p = PoliticalSystem.COMMUNIST_STATE;
            } else if (0.4 <= r && r < 0.6) {
                p = PoliticalSystem.DEMOCRACY;
            } else if (0.6 <= r && r < 0.65) {
                p = PoliticalSystem.DICTATORSHIP;
            } else if (0.65 <= r && r < 0.7) {
                p = PoliticalSystem.FASCIST_STATE;
            } else if (0.7 <= r && r < 0.8) {
                p = PoliticalSystem.MILITARY_STATE;
            } else if (0.8 <= r && r < 0.85) {
                p = PoliticalSystem.PACIFIST_STATE;
            } else if (0.85 <= r && r < 0.95) {
                p = PoliticalSystem.SOCIALIST_STATE;
            } else {
                p = PoliticalSystem.TECHNOCRACY;
            }
        } else if (level == TechLevel.POST_INDUSTRIAL) {
            if (0.0 <= r && r < 0.25) {
                p = PoliticalSystem.CAPITALIST_STATE;
            } else if (0.25 <= r && r < 0.3) {
                p = PoliticalSystem.COMMUNIST_STATE;
            } else if (0.3 <= r && r < 0.6) {
                p = PoliticalSystem.DEMOCRACY;
            } else if (0.6 <= r && r < 0.65) {
                p = PoliticalSystem.DICTATORSHIP;
            } else if (0.65 <= r && r < 0.7) {
                p = PoliticalSystem.FASCIST_STATE;
            } else if (0.7 <= r && r < 0.75) {
                p = PoliticalSystem.MILITARY_STATE;
            } else if (0.75 <= r && r < 0.85) {
                p = PoliticalSystem.PACIFIST_STATE;
            } else if (0.85 <= r && r < 0.9) {
                p = PoliticalSystem.SOCIALIST_STATE;
            } else {
                p = PoliticalSystem.TECHNOCRACY;
            }
        } else {
            if (0.0 <= r && r < 0.1) {
                p = PoliticalSystem.CAPITALIST_STATE;
            } else if (0.1 <= r && r < 0.3) {
                p = PoliticalSystem.DEMOCRACY;
            } else if (0.3 <= r && r < 0.4) {
                p = PoliticalSystem.DICTATORSHIP;
            } else if (0.4 <= r && r < 0.45) {
                p = PoliticalSystem.MILITARY_STATE;
            } else if (0.45 <= r && r < 0.55) {
                p = PoliticalSystem.PACIFIST_STATE;
            } else if (0.55 <= r && r < 0.7) {
                p = PoliticalSystem.TECHNOCRACY;
            } else {
                p = PoliticalSystem.CYBERNETIC_STATE;
            }
        }

        return p;
    }

    /*
     * http://en.wikipedia.org/wiki/Van_der_Corput_sequence
     *
     * Compute the nth term in the Van der Corput sequence, which happens
     * to look pseudo-random and be non-repeating. The numbers from the
     * sequence can then be used to give solar systems unique and reasonably
     * distributed locations throughout the universe.
     */
    private double vanDerCorput(int n) {
        double result = 0;
        int denominator = 2;

        while (n > 0) {
            result += n % 2.0 / (denominator);
            denominator *= 2;
            n /= 2;
        }

        return result;
    }

    /**
     * The highest possible x-coordinate of a solar system.
     */
    public final int MAX_X = 590;
    
    /**
     * The highest possible y-coordinate of a solar system.
     */
    public final int MAX_Y = 390;

    private static final int NUM_SOLAR_SYSTEMS = SOLAR_SYSTEM_NAMES.length;
    
    /**
     * An array of every solar system in the universe.
     */
    public final SolarSystem[] solarSystems;

    /**
     * Creates a new universe and generates solar systems in the universe.
     */
    public Universe() {
        solarSystems = new SolarSystem[NUM_SOLAR_SYSTEMS];
        Random r = new Random();
        int offset = r.nextInt();


        for (int i = 0; i < NUM_SOLAR_SYSTEMS; i++) {

            TechLevel tech = randomTechLevel();
            int x = (int) (vanDerCorput(Math.abs(i + offset)) * MAX_X);
            int y = (int) (vanDerCorput(Math.abs(x + offset)) * MAX_Y);

            solarSystems[i] = new SolarSystem(
                    SOLAR_SYSTEM_NAMES[i],
                    x,
                    y,
                    tech,
                    randomGovernment(tech)
            );
        }
    }

    /**
     * This method gets a randomly-chosen planet from a random solar system
     * in the Universe from which it is called.
     * 
     * @return A randomly selected plan within the universe
     */
    public Planet getRandomPlanet() {
        Random r = new Random();
        SolarSystem s = solarSystems[r.nextInt(solarSystems.length)];
        return s.getRandomPlanet();
    }
}
