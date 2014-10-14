/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Represents various ongoing events that affect the market
 * of a planet
 *
 * @author John Varela
 */
public enum PriceEvent {
    DROUGHT,
    COLD,
    CROP_FAILURE,
    WAR,
    PLAGUE,
    LACK_OF_WORKERS,
    BOREDOM,
    NONE; // if nothing special is happening
}
