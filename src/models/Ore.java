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
public class Ore extends TradeGood {
    public Ore() {
        super(TechLevel.MEDIEVAL, TechLevel.MEDIEVAL,
                TechLevel.RENAISSANCE, 350, 20, 10, 350, 420);
    }
}
