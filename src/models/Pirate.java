package models;

/**
 * Class representing a space-pirate
 *
 * @author Taylor
 */
public class Pirate extends Encounterable{

    public Pirate(String name){
//        super(0,0,0,0,0);
        this.name = name;
        welcomeText= "Yarr, surrender yer Credits or we'll take 'em by force!";
        deathText = "Arrr! You win this time, but I'll be back!";
        fleeFailedText = "Ye have no escape, ye scallywag!";
        fleeSuccessfulText = "Aye, ye might 'ave escaped this time, but I'll find yea, ye scurvey dog!";
        winText = "Ye should 'ave given up while yea had a chance";
    }

    public String getName(){
        return name;
    }

    public boolean isDead(){
        return ship.isDead();
    }
    public void takeDamage(int damage){
        ship.takeDamage(damage);
    }
    public int calculateAttack(){
        return ship.calculateAttack();
    }
    public int getHullStrength(){
        return ship.getHullStrength();
    }

    public int getMaxHullStrength(){
        return ship.getMaxHullStrength();
    }
}
