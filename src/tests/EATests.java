/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import ea.Util;
import java.util.ArrayList;

/**
 *
 * @author Niels Wadsholt
 */
public class EATests {
    
    public static void testGetDistance() {
        ArrayList<Double> point1 = new ArrayList<>();
        ArrayList<Double> point2 = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            point1.add(0.0);
            point2.add(0.0 + i);
            System.out.println(point1 + " " + point2 + " " + Util.getDistance(point1, point2));
        }
    }
    
}
