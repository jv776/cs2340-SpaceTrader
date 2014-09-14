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
public class Medicine extends TradeGood {
    public Medicine() {
        super(TechLevel.EARLY_INDUSTRIAL, TechLevel.AGRICULTURE,
                TechLevel.POST_INDUSTRIAL, 650, -20, 10, 400, 700);
    }
}
