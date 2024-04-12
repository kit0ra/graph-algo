/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Administrator
 */

import java.util.Map;

import com.mycompany.graphs.Graph;
import graphio.GraphData;
import com.mycompany.graphs.WeightedGraph;

public class GraphCreator {

     public static Graph createGraphFromGraphData(GraphData graphData) {
        int vertices = graphData.getVertices();
        int edgeCount = graphData.getEdgeCount();
        Map<Integer, Map<Integer, Integer>> edgesMap = graphData.getEdges();

        int[] fs = new int[vertices + edgeCount + 1]; // Enough space for all vertices and edges
        int[] aps = new int[vertices + 1]; // One entry per vertex, plus the first entry
        int[][] weights = null;

        if (graphData.isWeighted()) {
            weights = new int[vertices + 1][vertices + 1]; // Create weight matrix
        }

        fs[0] = vertices + edgeCount; // First entry of fs is the total number of vertices and edges
        aps[0] = vertices; // First entry of aps is the number of vertices

        int fsIndex = 1; // Index to track position in fs
        for (int vertex = 1; vertex <= vertices; vertex++) {
            aps[vertex] = fsIndex; // Point aps to the start of each vertex's edge list in fs
            if (edgesMap.containsKey(vertex)) {
                for (Map.Entry<Integer, Integer> entry : edgesMap.get(vertex).entrySet()) {
                    int succ = entry.getKey();
                    Integer weight = entry.getValue();
                    fs[fsIndex++] = succ; // Add successor to fs
                    if (graphData.isWeighted()) {
                        weights[vertex][succ] = (weight != null ? weight : 0); // Handle null weights
                    }
                }
            }
            fs[fsIndex++] = 0; // End marker for this vertex's list of edges
        }

        // Return appropriate graph based on whether it's weighted
        if (graphData.isWeighted()) {
            return new WeightedGraph(fs, aps, weights);
        } else {
            return new Graph(fs, aps);
        }
    }
}
