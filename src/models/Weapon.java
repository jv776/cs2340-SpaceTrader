package models;

/**
 * Model of a weapon that can be attached to a ship to deal damage
 *
 * @author Taylor
 */
public class Weapon implements Upgrade{
    public static enum Type {
        Pulse(5,1000,TechLevel.EARLY_INDUSTRIAL, "Plasma repeating laser, only able to fire in single bursts."),
        Beam(10,10000, TechLevel.INDUSTRIAL, "Plasma laser able to sustain fire for more than a few seconds."),
        Military(15,10000, TechLevel.POST_INDUSTRIAL, "Quantum laser built with top of the line military technology."),
        Alien(30,100000, TechLevel.HI_TECH, "The most advanced and powerful laser, more complex than you could ever comprehend.");


        public final int damage;
        public final TechLevel minTechLevel;
        public final String description;
        public final int price;

        Type(int damage, int price, TechLevel minTechLevel, String description) {
            this.damage = damage;
            this.minTechLevel = minTechLevel;
            this.description = description;
            this.price = price;
        }
    }
    public static enum Quality {
        Standard(0,1,1, "Standard-issue with no modifications."),
        Overclocked(1,10,2, "Standard with software modification for a little extra kick."),
        Kitted(2,100, 3, "Overclocked with some extra hardware modification for even more power."),
        Perfected(4, 1000,4, "Tuned and modified to absolute perfection.");


        public final int damage;
        public final int rarity;
        public final String description;
        public final int price;

        Quality(int damage, int rarity, int price, String description) {
            this.damage = damage;
            this.rarity = rarity;
            this.description = description;
            this.price = price;
        }
    }
    private Type type;
    private Quality quality;
    private String name;
    private int damage;
    private int price;
    public final double DAMAGE_MODIFIER = 1;

    public Weapon(Type type, Quality quality){
        this.type = type;
        name = quality +" " + type + " Laser";
        damage = (int)(DAMAGE_MODIFIER*(type.damage + quality.damage));
        price = type.price*quality.price;
    }
    public Weapon(Type type){
        this.type = type;
        double r = Math.random();
        if(r<.02){
            quality = Quality.Perfected;
        } else if(r<.15){
            quality = Quality.Kitted;
        } else if(r<.35){
            quality = Quality.Overclocked;
        } else {
            quality = Quality.Standard;
        }

        name = quality +" " + type + " Laser";
        damage = (int)(DAMAGE_MODIFIER*(type.damage + quality.damage));
        price = type.price*quality.price;
    }

    public int getDamage(){
        return damage;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return type.description +"\n\n" + quality.description+"\n\nDamage: "+damage;
    }
    public int getPrice(){
        return price;
    }
    public String getSlot(){
        return "weapon";
    }

    public TechLevel getTechLevel(){
        return type.minTechLevel;
    }


}