/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitchhikers.spacetrader.models;

/**
 * Represents various ongoing events that affect the market of a planet.
 *
 * @author John Varela
 */
enum PriceEvent {
    DROUGHT,
    COLD,
    CROP_FAILURE,
    WAR,
    PLAGUE,
    LACK_OF_WORKERS,
    BOREDOM,
    NONE; // if nothing special is happening
}
