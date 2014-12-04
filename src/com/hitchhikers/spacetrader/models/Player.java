/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hitchhikers.spacetrader.models;

import java.io.Serializable;

/**
 * Class representing the player's character.
 *
 * @author Alex, John
 */
public class Player extends CrewMember implements Serializable {
    /**
     * The name of the player's character.
     */
    public final String name;
    private Ship ship;
    private SolarSystem currentSystem;
    private Planet currentPlanet;
    private Bank bank;
    private StockPortfolio stocks;
    private int credits;
    private int totalCredits;
    private int bounty;
    private boolean firstFight;

    /**
     * Create a new player with given name and skills.
     *
     * @param playerName The player's name.
     * @param pilotSP    The player's skill as a pilot.
     * @param fighterSP  The player's skill as a fighter.
     * @param traderSP   The player's skill as a trader.
     * @param engineerSP The player's skill as an engineer.
     * @param investorSP The player's skill as an investor.
     */
    public Player(String playerName, int pilotSP, int fighterSP, int traderSP,
                  int engineerSP, int investorSP) {
        super(pilotSP, fighterSP, traderSP, engineerSP, investorSP);
        name = playerName;

        ship = new Ship(Ship.Type.Dragonfly, this);
        
        /*ship.equipWeapon(new Weapon(Weapon.Type.Alien));
        ship.equipWeapon(new Weapon(Weapon.Type.Military));
        ship.equipWeapon(new Weapon(Weapon.Type.Beam));
        ship.equipWeapon(new Weapon(Weapon.Type.Pulse));
        
        ship.equipShield(new Shield(Shield.Type.Alien));
        ship.equipShield(new Shield(Shield.Type.Reflective));
        ship.equipShield(new Shield(Shield.Type.Energy));
        ship.equipShield(new Shield(Shield.Type.Overcharged));
        
        ship.equipGadget(new Gadget(Gadget.Type.SCATTER_SHOT));
        ship.equipGadget(new Gadget(Gadget.Type.REFLECTOR));
        ship.equipGadget(new Gadget(Gadget.Type.HOMING_SHOT));
        ship.equipGadget(new Gadget(Gadget.Type.PIERCING_SHOT));
        
        ship.addCrew(new Mercenary("Alex", "Pilot", 5, 250));
        ship.addCrew(new Mercenary("John", "Fighter", 5, 250));
        ship.addCrew(new Mercenary("Kevin", "Engineer", 5, 250));
        ship.addCrew(new Mercenary("Taylor", "Trader", 5, 250));
        ship.addCrew(new Mercenary("Roi", "Investor", 5, 250));
        
        for (int i = 0; i < 12; i++) {
            ship.getCargoHold().addItem(TradeGood.ROBOTS);
            ship.getCargoHold().addItem(TradeGood.WATER);
        }
        
        earn(10000000);*/

        stocks = new StockPortfolio();
        
        bank = new Bank();
        
        firstFight = true;
        
        bounty = 0;
    }

    /**
     * @return A string giving basic information about the player.
     */
    @Override
    public String toString() {
        return name + ": Pilot: " + pilotSkill + ", Fighter: "
                + fighterSkill + ", Trader: " + traderSkill
                + ", Engineer: " + engineerSkill + ", Investor: "
                + investorSkill + "\nCurrently in the "
                + currentPlanet.getSolarSystem().getName() + " system on planet "
                + currentPlanet.getName();
    }

    /**
     * @return The player's ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Set the ship currently owned and in use by the player.
     *
     * @param s The player's new ship.
     */
    public void setShip(Ship s) {
        this.ship = s;
    }

    /**
     * @return The planet on which the player is currently located
     */
    public Planet getCurrentPlanet() {
        return currentPlanet;
    }

    /**
     * @return The solar system in which the player is currently located
     */
    public SolarSystem getCurrentSystem() {
        return currentSystem;
    }

    /**
     * @return The number of credits currently possessed by the player
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Set the location of the player to a different planet.
     *
     * @param planet The player's new location
     */
    public void setCurrentPlanet(Planet planet) {
        currentPlanet = planet;
        currentSystem = planet.getSolarSystem();
    }

    /**
     * @return The player's name.
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the solar system in which the player is located. Be sure
     * to change the planet the player is located on as well (so that they
     * aren't on a planet in a different system).
     *
     * @param system The SolarSystem instance to be set.
     */
    public void setCurrentSystem(SolarSystem system) {
        currentSystem = system;
    }

    /**
     * @return A string representation of the type of ship owned by the player
     */
    public String shipType() {
        return ship.getType().toString().toLowerCase();
    }
    
   /**
     * Spend a certain amount of money.
     *
     * @param amount How much money to spend
     */
    public void spend(int amount) {
        credits -= amount;
    }

    /**
     * Earn money.
     *
     * @param amount The amount the player has earned
     */
    public void earn(int amount) {
        credits += amount;
        totalCredits += amount;
    }
    
    public void withdraw(int amount) {
        credits += amount;
    }
    
    public int getTotalCredits() {
        return totalCredits;
    }

    /**
     * @return The amount of damage the player's ship is capable of inflicting.
     */
    public int calculateAttack() {
        return ship.calculateAttack();
    }

    /**
     * Damage the player's current ship.
     *
     * @param damage The amount of damage to the ship.
     */
    public void takeDamage(int damage) {
        ship.takeDamage(damage);
    }

    /**
     * @return The current hull strength of the player's ship.
     */
    public int getHullStrength() {
        return ship.getHullStrength();
    }

    /**
     * @return The maximum hull strength of the player's ship
     */
    public int getMaxHullStrength() {
        return ship.getMaxHullStrength();
    }

    /**
     * @return True if the player's ship has been destroyed in combat,
     * otherwise false.
     */
    public boolean isDead() {
        return ship.isDead();
    }

    /**
     * @return True if the player has illegal goods, false if not.
     */
    public boolean hasIllegalGoods() {
        return ship.hasIllegalGoods();
    }

    /**
     * Destroy the player's ship and give them a new one.
     */
    public void die() {
        ship = new Ship(Ship.Type.Flea, this);
    }
    
    public int getBounty() {
        return bounty;
    }
    
    public void setBounty(int x) {
        bounty = x;
    }
    
    public int getDailyCost() {
        int sum = 0;
        for (Mercenary m : ship.getCrew()) {
            sum += m.getCost();
        }
        
        if (bank.hasOpenLoan()) {
            sum += bank.calculateCost();
            bank.makeLoanPayment(bank.calculateCost());
            
            if (bank.paidOff()) {
                bank.closeLoan();
            }
        }
        
        return sum;
    }
    
    public boolean isFirstFight() {
        return firstFight;
    }
    
    public void finishedFirstFight() {
        firstFight = false;
    }
    
    public void buyStocks(StockCorporation corp, int shares) {
        spend((int)stocks.buyStock(corp, shares));
    }
    
    public void sellStocks(StockCorporation corp, int shares) {
        earn((int)stocks.sellStock(corp, shares));
    }
    
    public boolean hasStock(StockCorporation corp) {
        return stocks.getShares(corp) != 0;
    }
    
    @Override
    public int getFighterSkillPoints() {
        int fighter = fighterSkill;
        for (Mercenary merc : ship.getCrew()) {
            fighter += merc.getFighterSkillPoints();
        }
        return fighter;
    }
    
    @Override
    public int getPilotSkillPoints() {
        int pilot = pilotSkill;
        for (Mercenary merc : ship.getCrew()) {
            pilot += merc.getPilotSkillPoints();
        }
        return pilot;
    }
     
    @Override
    public int getEngineerSkillPoints() {
        int engineer = engineerSkill;
        for (Mercenary merc : ship.getCrew()) {
            engineer += merc.getEngineerSkillPoints();
        }
        return engineer;
    }
      
    @Override
    public int getTraderSkillPoints() {
        int trader = traderSkill;
        for (Mercenary merc : ship.getCrew()) {
            trader += merc.getTraderSkillPoints();
        }
        return trader;
    }
       
    @Override
    public int getInvestorSkillPoints() {
        int investor = investorSkill;
        for (Mercenary merc : ship.getCrew()) {
            investor += merc.getInvestorSkillPoints();
        }
        return investor;
    }
    
    public Bank getBank() {
        return bank;
    }
    
    public void setBank(Bank b) {
        bank = b;
    }
    
    public StockPortfolio getStockPortfolio() {
        return stocks;
    }
}
