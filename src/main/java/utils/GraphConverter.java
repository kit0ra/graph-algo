/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class GraphConverter {

    public static int[][] toAdjMatrix(int[] fs, int[] aps) {
        int vertices = aps[0];
        int total = fs[0];

        // Initialize the adjacency matrix
        int[][] matrix = new int[vertices][vertices + 1];

        // First cell of matrix contains the number of vertices
        matrix[0][0] = vertices;
        matrix[1][0] = total;

        // Populate the adjacency matrix
        for (int i = 1; i <= vertices; i++) {
            int start = aps[i]; // Start index in fs for the i-th vertex's successors
            for (int j = start; fs[j] != 0; j++) { // Iterate through successors until a block separator (0) is found
                int successor = fs[j];
                matrix[i - 1][successor] = 1; // Mark the edge presence with 1
            }
        }

        return matrix;
    }

    public static int[][] adjMatrixToFsAps(int[][] matrix) {
        int vertices = matrix[0][0];
        int total = matrix[1][0];
        
        int[] fs = new int[total+1]; 
        int[] aps = new int[vertices + 1];
        
        fs[0] = total; // First element is the total count of fs
        aps[0] = vertices; // First element is the count of vertices

        int fsIndex = 1;
        for (int r = 0; r < vertices; r++) {
            aps[r + 1] = fsIndex;
            for (int c = 1; c <= vertices; c++) {
                if (matrix[r][c] == 1) {
                    fs[fsIndex++] = c;
                }
            }
            fs[fsIndex++] = 0; // Add separator after listing all successors
        }

        return new int[][]{fs, aps};
    }
   
   public static int[][] fsApsToFpApp(int[] fs, int[] aps){
       int vertices = aps[0];
       int total = fs[0];
       
       int[] app = new int[vertices+1];
       int[] fp = new int[total+1];
       
       app[0]=vertices;
       fp[0]=total;
       
       // calculate ddi
       int [] ddi = new int [vertices + 1];
       ddi = GraphHelpers.calculateHalfInteriorDegree(fs, aps);
       
       int id =1;
       for(int i=1; i<=vertices;i++){
           app[i] = id;
           id +=ddi[i]+1;
           fp[id-1]=0;
       }
       
       return new int [][]{fp,app};
   }
   
}
