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
public class Water extends TradeGood {
    public Water() {
        super(TechLevel.PRE_AGRICULTURE, TechLevel.PRE_AGRICULTURE,
                TechLevel.MEDIEVAL, 30, 3, 4, 30, 50);
    }
}
