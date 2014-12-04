package com.hitchhikers.spacetrader;

import java.io.InputStream;
import java.net.URL;

/**
 * @author Roi Atalla
 */
public class Utils {
    public static URL getResource(String res) {
        return Utils.class.getResource(res);
    }
    
    public static InputStream getResourceAsStream(String res) {
        return Utils.class.getResourceAsStream(res);
    }
}
