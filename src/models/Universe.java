/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.Random;

/**
 *
 * @author Alex, John
 */
public class Universe {
    private static final String[] SOLAR_SYSTEM_NAMES =  {
        "Acamar",
        "Adahn",		// The alternate personality for The Nameless One in "Planescape: Torment"
        "Aldea",
        "Andevian",
        "Antedi",
        "Balosnee",
        "Baratas",
        "Brax",			// One of the heroes in Master of Magic
        "Bretel",		// This is a Dutch device for keeping your pants up.
        "Calondia",
        "Campor",
        "Capelle",		// The city I lived in while programming this game
        "Carzon",
        "Castor",		// A Greek demi-god
        "Cestus",
        "Cheron",		
        "Courteney",	// After Courteney Cox…
        "Daled",
        "Damast",
        "Davlos",
        "Deneb",
        "Deneva",
        "Devidia",
        "Draylon",
        "Drema",
        "Endor",
        "Esmee",		// One of the witches in Pratchett's Discworld
        "Exo",
        "Ferris",		// Iron
        "Festen",		// A great Scandinavian movie
        "Fourmi",		// An ant, in French
        "Frolix",		// A solar system in one of Philip K. Dick's novels
        "Gemulon",
        "Guinifer",		// One way of writing the name of king Arthur's wife
        "Hades",		// The underworld
        "Hamlet",		// From Shakespeare
        "Helena",		// Of Troy
        "Hulst",		// A Dutch plant
        "Iodine",		// An element
        "Iralius",
        "Janus",		// A seldom encountered Dutch boy's name
        "Japori",
        "Jarada",
        "Jason",		// A Greek hero
        "Kaylon",
        "Khefka",
        "Kira",			// My dog's name
        "Klaatu",		// From a classic SF movie
        "Klaestron",
        "Korma",		// An Indian sauce
        "Kravat",		// Interesting spelling of the French word for "tie"
        "Krios",
        "Laertes",		// A king in a Greek tragedy
        "Largo",
        "Lave",			// The starting system in Elite
        "Ligon",
        "Lowry",		// The name of the "hero" in Terry Gilliam's "Brazil"
        "Magrat",		// The second of the witches in Pratchett's Discworld
        "Malcoria",
        "Melina",
        "Mentar",		// The Psilon home system in Master of Orion
        "Merik",
        "Mintaka",
        "Montor",		// A city in Ultima III and Ultima VII part 2
        "Mordan",
        "Myrthe",		// The name of my daughter
        "Nelvana",
        "Nix",			// An interesting spelling of a word meaning "nothing" in Dutch
        "Nyle",			// An interesting spelling of the great river
        "Odet",
        "Og",			// The last of the witches in Pratchett's Discworld
        "Omega",		// The end of it all
        "Omphalos",		// Greek for navel
        "Orias",
        "Othello",		// From Shakespeare
        "Parade",		// This word means the same in Dutch and in English
        "Penthara",
        "Picard",		// The enigmatic captain from ST:TNG
        "Pollux",		// Brother of Castor
        "Quator",
        "Rakhar",
        "Ran",			// A film by Akira Kurosawa
        "Regulas",
        "Relva",
        "Rhymus",
        "Rochani",
        "Rubicum",		// The river Ceasar crossed to get into Rome
        "Rutia",
        "Sarpeidon",
        "Sefalla",
        "Seltrice",
        "Sigma",
        "Sol",			// That's our own solar system
        "Somari",
        "Stakoron",
        "Styris",
        "Talani",
        "Tamus",
        "Tantalos",		// A king from a Greek tragedy
        "Tanuga",
        "Tarchannen",
        "Terosa",
        "Thera",		// A seldom encountered Dutch girl's name
        "Titan",		// The largest moon of Jupiter
        "Torin",		// A hero from Master of Magic
        "Triacus",
        "Turkana",
        "Tyrus",
        "Umberlee",		// A god from AD&D, which has a prominent role in Baldur's Gate
        "Utopia",		// The ultimate goal
        "Vadera",
        "Vagra",
        "Vandor",
        "Ventax",
        "Xenon",
        "Xerxes",		// A Greek hero
        "Yew",			// A city which is in almost all of the Ultima games
        "Yojimbo",		// A film by Akira Kurosawa
        "Zalkon",
        "Zuul"			// From the first Ghostbusters movie
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

        if (0.0 <= r && r < 0.15) {
            return TechLevel.PRE_AGRICULTURE; //15% chance
        } else if (0.15 <= r && r < 0.3) {
            return TechLevel.AGRICULTURE; //15% chance
        } else if (0.3 <= r && r < 0.4) {
            return TechLevel.MEDIEVAL; //10% chance
        } else if (0.4 <= r && r < 0.5) {
            return TechLevel.RENAISSANCE; //10% chance
        } else if (0.5 <= r && r < 0.65) {
            return TechLevel.EARLY_INDUSTRIAL; //15% chance
        } else if (0.65 <= r && r < 0.85) {
            return TechLevel.POST_INDUSTRIAL; //20% chance
        } else {
            return TechLevel.HI_TECH; //15% chance
        }
    }
    
    /*
     * Another ugly helper function for randomly choosing resources on each
     * planet. Feel free to adjust probabilities or rewrite entirely.
     */
    private static Resource randomResource() {
        double r = Math.random();

        if (0.0 <= r && r < 0.3) {
            return Resource.NO_SPECIAL_RESOURCES; //30% chance
        } else if (0.3 <= r && r < 0.35) {
            return Resource.MINERAL_RICH; //5% chance
        } else if (0.35 <= r && r < 0.4) {
            return Resource.MINERAL_POOR; //5% chance
        } else if (0.4 <= r && r < 0.5) {
            return Resource.DESERT; //10% chance
        } else if (0.5 <= r && r < 0.55) {
            return Resource.LOTS_OF_WATER; //5% chance
        } else if (0.55 <= r && r < 0.6) {
            return Resource.RICH_SOIL; //5% chance
        } else if (0.6 <= r && r < 0.65) {
            return Resource.POOR_SOIL; //5% chance
        } else if (0.65 <= r && r < 0.7) {
            return Resource.RICH_FAUNA; //5% chance
        } else if (0.7 <= r && r < 0.85) {
            return Resource.LIFELESS; //15% chance
        } else if (0.85 <= r && r < 0.87) {
            return Resource.WEIRD_MUSHROOMS; //2% chance
        } else if (0.87 <= r && r < 0.9) {
            return Resource.LOTS_OF_HERBS; //3% chance
        } else if (0.9 <= r && r < 0.95) {
            return Resource.ARTISTIC; //5% chance
        } else {
            return Resource.WARLIKE; //5% chance
        }
    }
    
    public final int MAX_X = 500;
    public final int MAX_Y = 500;
    
    public SolarSystem[] solarSystems;
    
    public Universe() {
        solarSystems = new SolarSystem[SOLAR_SYSTEM_NAMES.length];
        Random r = new Random();
        
        for (int i = 0; i < SOLAR_SYSTEM_NAMES.length; i++) {
            solarSystems[i] = new SolarSystem(SOLAR_SYSTEM_NAMES[i],
                r.nextInt(MAX_X),
                r.nextInt(MAX_Y)
            );
        }

        // DELETE AFTER DEBUGGING
        for (SolarSystem s : solarSystems) {
            System.out.println(s);
        }
    }
}
