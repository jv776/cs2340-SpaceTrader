/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitchhikers.spacetrader.models;

import java.io.Serializable;

/**
 *
 * @author Alex
 */
public class Bank implements Serializable {
    private int playerCredits;
    private Loan loan;
    
    public Bank() {
        playerCredits = 0;
    }
    
    public int getCredits() {
        return playerCredits;
    }
    
    public void deposit(int amount) {
        playerCredits += amount;
    } 
    
    public void withdraw(int amount) {
        playerCredits -= amount;
    }
    
    public String getResponse(int amount, int worthOfPlayer, int bounty) {
        if (amount > 2 * worthOfPlayer) {
            return "You cannot apply for this high of a loan at this time.";
        } else if (bounty > 1000) {
            return "Your bounty is too high to take out a loan.";
        } else if (amount < 1000) {
            return "You cannot apply for a loan this small.";
        } else if (loan != null) {
            return "You already have a loan on record. Pay it back first.";
        } else {
            return "";
        }
    }
    
    public void addLoan(int amount, int dailyCost) {
        loan = new Loan(amount, dailyCost);
    }
    
    public int calculateCost() {
        if (loan.getTotal() - loan.getPaidOff() < loan.getDailyCost()) {
            return loan.getTotal() - loan.getPaidOff();
        } else {
            return loan.getDailyCost();
        }
    }
    
    public void makeLoanPayment(int amount) {
        loan.makePayment(amount);
    }
    
    public void closeLoan() {
        loan = null;
    }
    
    public boolean paidOff() {
        return loan.getPaidOff() == loan.getTotal();
    }
    
    public boolean hasOpenLoan() {
        return loan != null;
    }
    
    public Loan getLoan() {
        return loan;
    }
}
