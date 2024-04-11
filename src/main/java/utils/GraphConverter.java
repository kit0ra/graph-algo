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
public class GraphConverter {
    
  public static int[][] toAdjMatrix(List<Integer> fs, List<Integer> aps) {
    int vertices = aps.get(0); 
    int edges = fs.get(0) - vertices; 

   
    int[][] matrix = new int[vertices][vertices + 1];

    // Initialize the adjacency matrix with 0s
    for (int i = 0; i < vertices; i++) {
        for (int j = 0; j <= vertices; j++) { 
            matrix[i][j] = 0;
        }
    }

    // Store metadata in the first column of the first two rows
    matrix[0][0] = vertices; 
    matrix[1][0] = edges; 

    // Populate the adjacency matrix
    for (int vertex = 1; vertex <= vertices; vertex++) {
        int index_fs = aps.get(vertex);
        // Populate starting from the second column to avoid overwriting metadata
        while (index_fs < fs.size() && fs.get(index_fs) > 0) {
            int successor = fs.get(index_fs);
            matrix[vertex - 1][successor] = 1; // Use successor as column index directly, starting from 1
            index_fs++;
        }
    }
    return matrix;
    }

   public static List<List<Integer>> adjMatrixToFsAps(int [][] matrix){
       int vertices = matrix[0][0];
       int edges = matrix[1][0];
       
       List<Integer> aps = new ArrayList<>(vertices+1);
       aps.add(vertices);
       
       List<Integer> fs = new ArrayList<>(vertices+edges+1);
       fs.add(vertices+edges);
       
       int i =1;
       for (int r=0; r<vertices;r++){
           aps.add(i);
           for (int c=1;c<=vertices;c++){
               if(matrix[r][c]==1){
                   fs.add(c);
                   i++;
               }
           }
           fs.add(i,0);
           i++;
       }
       
       List<List<Integer>> result = new ArrayList<>(2);
       result.add(fs);
       result.add(aps);
       return result;
   }
   
   public static List<List<Integer>> fsApsToFpApp(List<Integer> fs, List<Integer> aps){
       int vertices = aps.get(0);
       int total = fs.get(0);
       
       List<Integer> app = new ArrayList<>(vertices + 1);
       List<Integer> fp = new ArrayList<>(total);
       
       app.add(vertices);
       fp.add(total);
       
       List<Integer> ddi = GraphHelpers.calculateHalfInteriorDegree(fs, aps);
       
       int id = 1;
       for (int i=1;i<=vertices;i++){
           app.add(id);
           id += ddi.get(i)+1;
           fp.set(id-1,0);
           //fp.set(id-1, 0);
       }
       
       System.out.println("app: "+app);
       System.out.println("fp:" +fp);
       
       List<List<Integer>> result = new ArrayList<>(2);
       result.add(fp);
       result.add(app);
       return result;
   }
   
}
