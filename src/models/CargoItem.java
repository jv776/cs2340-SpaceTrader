/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Anything that can be held in a cargo hold.
 * 
 * @author Kevin Gibby
 */
public interface CargoItem {
    /**
     * Get the item's name.
     * @return The item's name.
     */
    abstract String getItemName();
}