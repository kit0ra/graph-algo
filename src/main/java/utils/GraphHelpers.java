/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class GraphHelpers {
    
    //Half Interior Degree(dde)
    public static List<Integer> calculateHalfInteriorDegree(List<Integer> fs, List<Integer> aps){
        int vertices = aps.get(0);
        int total = fs.get(0);
        List<Integer> ddi = new ArrayList<>(Collections.nCopies(vertices + 1, 0)); // Initialize ddi with zeros
        
        for (int i = 1; i <= total; i++) {
            int index = fs.get(i); // Assuming fs contains indices for ddi
            ddi.set(index,  ddi.get(index) + 1);
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
