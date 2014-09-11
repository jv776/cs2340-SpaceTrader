package java.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Interface implemented by all Controllers. This interface allows each
 * screen to reference the parent ScreensController, which allows the 
 * ability to set, reset, load, and unload screens.
 * @author Alex
 */
public interface ControlledScreen {
   
    /**
     * Designed to allow Controller access to the ScreensController.
     * @param screenPage the master view controller
     */
    public void setScreenParent(ScreensController screenPage);
    
}
