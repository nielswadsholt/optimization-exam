/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea;

import java.util.ArrayList;

/**
 *
 * @author Niels Wadsholt
 */
public class Util {
    
    public static double getDistance(ArrayList<Double> point1, ArrayList<Double> point2) {
        assert point1.size() == point2.size() : "The given points have different dimensions";
        
        double dist = 0.0;
        
        for (int i = 0; i < point1.size(); i++) {
            dist += Math.pow(point1.get(i) - point2.get(i), 2);
        }
        
        return Math.sqrt(dist);
    }
}
