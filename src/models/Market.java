/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Alex
 */
public class Market {
    
    private Planet planet;
    private SolarSystem solarSystem;
    
    public Market(Planet p) {
        planet = p;
        solarSystem = p.getSolarSystem();
    }
   
    /**
     *
     */
    public TradeGood[] getBuyableGoods() {
        ArrayList<TradeGood> list = new ArrayList(Arrays.asList(TradeGood.values()));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMinTechLevelBuy().ordinal() 
              > solarSystem.getTechLevel().ordinal()) {
                list.remove(i);
                i--;
            }
        }
        return list.toArray(new TradeGood[]{});
    }
    
    public TradeGood[] getSellableGoods() {
        ArrayList<TradeGood> list = new ArrayList(Arrays.asList(TradeGood.values()));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMinTechLevelSell().ordinal() 
              > solarSystem.getTechLevel().ordinal()) {
                list.remove(i);
                i--;
            }
        }
        return list.toArray(new TradeGood[]{});
    }
}
