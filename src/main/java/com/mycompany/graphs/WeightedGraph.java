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

    private int[][] weights; // Edge weights
    
    @Override
    public boolean isWeighted() {
        return true;
    }
    
    public WeightedGraph(int[] fs, int[] aps, int[][] weights) {
        super(fs, aps);
        this.weights = weights.clone();
    }

    public int[][] getWeights() {
        return weights.clone();
    }

    public void setWeights(int[][] newWeights) {
        this.weights = newWeights.clone();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(GraphPrinter.formatFsAps(fs, aps)); 

        // Append weights matrix if this graph is weighted
        if (weights != null) {
            sb.append("\nWeights Matrix):\n");
            appendMatrixToString(sb, weights);  // Append formatted weight matrix
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
