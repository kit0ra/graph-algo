/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.graphs.Algorithms;

import com.mycompany.graphs.WeightedGraph;
import java.util.Arrays;

/**
 *
 * @author Administrator
 */
public class Dijkstra {

    /**
     * Performs Dijkstra's algorithm on an unweighted graph to find the shortest
     * paths from a start vertex.
     *
     * @param graph The unweighted graph on which to perform Dijkstra's
     * algorithm.
     * @param s The start vertex.
     * @return A 2D array where the first sub-array is the distance from the
     * start vertex to each other vertex, and the second sub-array is the
     * predecessor of each vertex in the path from the start vertex.
     */
    public static double[][] dijkstra(WeightedGraph graph, int s) {
        int vertices = graph.getVertices();
        int[] fs = graph.getFS();
        int[] aps = graph.getAPS();
        double[][] weights = graph.getWeights();

        double[] d = new double[vertices + 1]; // Distance from the start vertex
        int[] pr = new int[vertices + 1]; // Predecessors
        boolean[] inS = new boolean[vertices + 1]; // Whether a vertex is still to be processed

        Arrays.fill(d, Double.MAX_VALUE); // Initialize distances to infinity
        Arrays.fill(pr, -1); // Initialize predecessors to -1 (undefined)
        Arrays.fill(inS, true); // Initially, all vertices are in the set to be processed

        d[s] = 0; // Distance to itself is zero
        pr[s] = 0; // The starting vertex has no predecessor

        for (int count = 0; count < vertices; count++) {
            int u = -1;
            double minDistance = Double.MAX_VALUE;
            for (int i = 1; i <= vertices; i++) {
                if (inS[i] && d[i] < minDistance) {
                    minDistance = d[i];
                    u = i;
                }
            }

            if (u == -1) {
                break; // No vertex left to process or remaining vertices are unreachable
            }
            inS[u] = false; // Mark this vertex as processed

            // Update the distance of the adjacent vertices of the picked vertex.
            for (int v = 1; v <= vertices; v++) {
                // Only consider vertices still in set S and where there is an edge u-v
                if (inS[v] && weights[u][v] != 0 && d[u] != Double.MAX_VALUE && d[u] + weights[u][v] < d[v]) {
                    d[v] = d[u] + weights[u][v];
                    pr[v] = u;
                }
            }
        }

        return new double[][]{d, Arrays.stream(pr).asDoubleStream().toArray()};
    }

}
