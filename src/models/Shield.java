package models;

/**
 * Created by Taylor on 11/4/14.
 */
public class Shield {
    public static enum Type {
        Energy(25,5,1000,TechLevel.EARLY_INDUSTRIAL, "Shield that creates an absorptive energy field around the ship."),
        Reflective(150,30,10000, TechLevel.INDUSTRIAL, "Shield that creates a reflective energy field around the ship.");

        public final int strength;
        public final TechLevel minTechLevel;
        public final int price;
        public final String description;

        Type(int strength,int chargeRate, int price, TechLevel minTechLevel, String description) {
            this.strength = strength;
            this.minTechLevel = minTechLevel;
            this.price = price;
            this.description = description;
        }
    }
//    public static enum Quality {
//        Weak(0, TechLevel.EARLY_INDUSTRIAL, ""),
//        Standard(25,TechLevel.INDUSTRIAL, ""),
//        Powerful(75, TechLevel.POST_INDUSTRIAL, ""),
//
//
//        public final int damage;
//        public final TechLevel techLevel;
//
//        Type(int damage, TechLevel techLevel, String description) {
//            this.damage = damage;
//            this.techLevel = techLevel;
//        }
//    }
    private int strength;
    private Type type;
    private int price;
    private String name;

    public Shield(Type type){
        this.type = type;
        strength = type.strength;
        price = type.price;
        name = type + " Shield";
    }
    public int getPrice(){
        return price;
    }
    public String getSlot(){
        return "weapon";
    }
    public String toString(){
        return type.description + "\n\nStrength: " + strength;
    }
    public TechLevel getTechLevel(){
        return type.minTechLevel;
    }
    public String getName(){
        return name;
    }

}
