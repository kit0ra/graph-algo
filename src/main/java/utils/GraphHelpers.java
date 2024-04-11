/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class GraphHelpers {
    
    //Half Interior Degree(dde)
    public static int[] calculateHalfInteriorDegree(int[] fs, int[] aps) {
        int vertices = aps[0];
        int total = fs[0];
        
        int[] ddi = new int[vertices + 1]; // Arrays are initialized with 0 by default

        for (int i = 1; i <= total; i++) {
            ddi[fs[i]]++; // Increment the degree
        }

        return ddi;
    }
    
    //half exterior degree (dde)
    public static List<Integer> calculateHalfExteriorDegree(List<Integer> fs, List<Integer> aps) {
       return new ArrayList<>();
    }

    // Method for initializing or working with a chained stack (pilch)
    public static void initializeChainedStack(/* parameters */) {
        // Implementation
      
    }
}
