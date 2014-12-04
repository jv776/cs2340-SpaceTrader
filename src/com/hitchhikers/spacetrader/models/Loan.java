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
public class Loan implements Serializable {
    private final int total;
    private int paidOff;
    private final int dailyCost;
    
    public Loan(int total, int dailyCost) {
        this.total = total;
        this.paidOff = 0;
        this.dailyCost = dailyCost;
    }
    
    public void makePayment() {
        paidOff += dailyCost;
    }
    
    public void makePayment(int amount) {
        paidOff += amount;
    }
    
    public int getTotal() {
        return total;
    }
    
    public int getPaidOff() {
        return paidOff;
    }
    
    public int getDailyCost() {
        return dailyCost;
    }
}
