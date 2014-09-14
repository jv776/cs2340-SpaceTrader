/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author John Varela
 */
public class Robot extends TradeGood {
    public Robot() {
        super(TechLevel.POST_INDUSTRIAL, TechLevel.EARLY_INDUSTRIAL,
                TechLevel.HI_TECH, 5000, -150, 100, 3500, 5000);
    }
}
