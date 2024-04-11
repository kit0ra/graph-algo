/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.List;

/**
 *
 * @author Administrator
 */
public class GraphPrinter {
    
    public static void adjMatrixPrinter(int [][] matrix){
        for (int[] row : matrix) {
            for (int j = 1; j < row.length; j++) {
                System.out.print(row[j] + " ");
            }
        System.out.println(); 
       }
    }
    
    public static void fsApsPrinter(List<Integer> fs, List<Integer> aps){
        String fsString = fs.toString();
        String apsString = aps.toString();
        System.out.println("FS: " + fsString + "\nAPS: " + apsString);
    }
    
    public static String formatFsAps(List<Integer> fs, List<Integer> aps) {
        return "FS: " + fs.toString() + "\nAPS: " + aps.toString();
    }
    
}
