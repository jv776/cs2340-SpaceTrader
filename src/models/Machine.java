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
public class Machine extends TradeGood {
    public Machine() {
        super(TechLevel.EARLY_INDUSTRIAL, TechLevel.RENAISSANCE,
                TechLevel.INDUSTRIAL, 900, -30, 5, 600, 800);
    }
}
