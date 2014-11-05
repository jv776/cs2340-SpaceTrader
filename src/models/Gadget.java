package models;

/**
 * Created by Taylor on 11/4/14.
 */
public class Gadget {
    public static enum Type {
        Cargo(0,0,0,0,false,1000,TechLevel.EARLY_INDUSTRIAL, "Shield that creates an absorptive energy field around the ship."),
        Navigation(0,0,0,0,false,1000,TechLevel.EARLY_INDUSTRIAL, "Shield that creates an absorptive energy field around the ship."),
        Targeting(0,0,0,0,false,1000,TechLevel.EARLY_INDUSTRIAL, "Shield that creates an absorptive energy field around the ship."),
        Autorepair(0,0,0,0,false,1000,TechLevel.EARLY_INDUSTRIAL, "Shield that creates an absorptive energy field around the ship."),
        Cloaking(0,0,0,0,false,1000,TechLevel.EARLY_INDUSTRIAL, "Shield that creates an absorptive energy field around the ship.");

        public final TechLevel techLevel;
        public final int price;
        public final String description;
        int cargo;
        int pilot;
        int combat;
        int engineering;
        boolean cloak;

        Type(int cargo,int pilot, int combat ,int engineering, boolean cloak,int price, TechLevel techLevel, String description) {
            this.cargo = cargo;
            this.cloak = cloak;
            this.pilot = pilot;
            this.combat = combat;
            this.engineering = engineering;
            this.techLevel = techLevel;
            this.price = price;
            this.description = description;
        }
    }
    private Type type;
    public Gadget(Type type){
        this.type = type;
    }
}
