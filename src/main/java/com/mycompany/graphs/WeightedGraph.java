/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.graphs;

import utils.GraphPrinter;

/**
 *
 * @author Administrator
 */
public class WeightedGraph extends Graph {

    private int[][] p; // Edge weights
    
    public boolean isWeighted() {
        return true;
    }
    
    public WeightedGraph(int[] fs, int[] aps, int[][] p) {
        super(fs, aps);
        this.p = p.clone();
    }

    public int[][] getP() {
        return p.clone();
    }

    public void setP(int[][] newP) {
        this.p = newP.clone();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(GraphPrinter.formatFsAps(fs, aps)); 

        // Append weights matrix if this graph is weighted
        if (p != null) {
            sb.append("\nWeights Matrix):\n");
            appendMatrixToString(sb, p);  // Append formatted weight matrix
        }

        return sb.toString();
    }

    private void appendMatrixToString(StringBuilder sb, int[][] matrix) {
        for (int i = 1; i < matrix.length; i++) { // Assuming the matrix uses 1-based indexing
            for (int j = 1; j < matrix[i].length; j++) {
                sb.append(String.format("%4d", matrix[i][j])).append(" ");  // Adjust format as necessary
            }
            sb.append("\n"); // New line at the end of each row
        }
    }
}
