/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphs;

import utilities.GraphPrinter;

/**
 *
 * @author Administrator
 */
public class WeightedGraph extends Graph {

    private double[][] weights; // Edge weights with double precision

    @Override
    public boolean isWeighted() {
        return true;
    }

    public WeightedGraph(int[] fs, int[] aps, double[][] weights) {
        super(fs, aps);
        this.weights = weights.clone();
    }

    public double[][] getWeights() {
        return weights.clone();
    }

    public void setWeights(double[][] newWeights) {
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

    private void appendMatrixToString(StringBuilder sb, double[][] matrix) {
        for (int i = 1; i < matrix.length; i++) { // Assuming the matrix uses 1-based indexing
            for (int j = 1; j < matrix[i].length; j++) {
                sb.append(String.format("%4.2f", matrix[i][j])).append(" ");  // Format for double precision
            }
            sb.append("\n"); // New line at the end of each row
        }
    }
}
