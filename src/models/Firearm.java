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
public class Firearm extends TradeGood {
    public Firearm() {
        super(TechLevel.RENAISSANCE, TechLevel.AGRICULTURE,
                TechLevel.INDUSTRIAL, 1250, -75, 100, 600, 1100);
    }
}
