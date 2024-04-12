/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.graphs.Algorithms;

import com.mycompany.graphs.Graph;

/**
 *
 * @author Administrator
 */
public class Dijkstra {
    /**
     * Performs Dijkstra's algorithm on an unweighted graph to find the shortest paths from a start vertex.
     *
     * @param graph The unweighted graph on which to perform Dijkstra's algorithm.
     * @param s The start vertex.
     * @return A 2D array where the first sub-array is the distance from the start vertex to each other vertex,
     *         and the second sub-array is the predecessor of each vertex in the path from the start vertex.
     */
    public static int[][] dijkstra(Graph graph, int s) {
        if (graph.isWeighted()) {
            throw new IllegalArgumentException("Dijkstra's algorithm in this method requires an unweighted graph.");
        }

        int vertices = graph.getVertices();
        int[] d = new int[vertices + 1]; // Distance from start vertex to each vertex
        int[] pr = new int[vertices + 1]; // Predecessors in the shortest path tree

        // Initialize distance and predecessor arrays
        for (int i = 1; i <= vertices; i++) {
            d[i] = Integer.MAX_VALUE; // Infinity
            pr[i] = -1; // Undefined predecessor
        }

        // Algorithm implementation goes here

        return new int[][]{d, pr};
    }
}
