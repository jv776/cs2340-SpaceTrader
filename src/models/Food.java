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
public class Food extends TradeGood {
    public Food() {
        super(TechLevel.AGRICULTURE, TechLevel.PRE_AGRICULTURE,
                TechLevel.AGRICULTURE, 100, 5, 5, 90, 160);
    }
}
