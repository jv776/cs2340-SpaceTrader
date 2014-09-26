/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Interface used by anything which can be bought (not just trade goods, but
 * ships, upgrades, etc. as well).
 *
 * @author John Varela
 */
public interface Purchasable {
    public int computeCost(SolarSystem marketLocation);
}
