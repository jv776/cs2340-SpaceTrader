package models;

/**
 * Model of a misc upgrade that can be applied to a ship
 *
 * @author Taylor
 */
public class Gadget implements Upgrade {
    public static enum Type {
        Cargo(5,0,0,0,false,200,TechLevel.EARLY_INDUSTRIAL, "Provides 5 extra cargo slots for your ship."),
        Navigation(0,5,0,0,false,1000,TechLevel.INDUSTRIAL, "Increases your piloting skill."),
        Targeting(0,0,5,0,false,1000,TechLevel.INDUSTRIAL, "Increases your combat skill."),
        Autorepair(0,0,0,5,false,1000,TechLevel.POST_INDUSTRIAL, "Increases your engineering skill."),
        Cloaking(0,0,0,0,true,10000,TechLevel.HI_TECH, "Allows you to travel undetected through space.");

        public final TechLevel minTechLevel;
        public final int price;
        public final String description;
        int cargo;
        int pilot;
        int combat;
        int engineering;
        boolean cloak;

        Type(int cargo,int pilot, int combat ,int engineering, boolean cloak,int price, TechLevel minTechLevel, String description) {
            this.cargo = cargo;
            this.cloak = cloak;
            this.pilot = pilot;
            this.combat = combat;
            this.engineering = engineering;
            this.minTechLevel = minTechLevel;
            this.price = price;
            this.description = description;
        }
    }
    private Type type;
    private int price;
    private String name;
    public Gadget(Type type){
        this.type = type;
        this.price = type.price;
        name = type + " Upgrade";
    }
    public int getPrice(){
        return price;
    }
    public String getSlot(){
        return "gadget";
    }
    public TechLevel getTechLevel(){
        return type.minTechLevel;
    }
    public String getName(){
        return name;
    }
    public String toString(){
        return type.description;
    }
}
