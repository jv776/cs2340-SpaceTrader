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
public class Game extends TradeGood {
    public Game() {
        super(TechLevel.RENAISSANCE, TechLevel.AGRICULTURE,
                TechLevel.POST_INDUSTRIAL, 250, -10, 5, 160, 270);
    }
}
