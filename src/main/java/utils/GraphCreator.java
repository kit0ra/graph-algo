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
import io.GraphData;
import com.mycompany.graphs.WeightedGraph;

public class GraphCreator {

    public static Graph createGraphFromGraphData(GraphData graphData) {
        int vertices = graphData.getVertices();
        int edgeCount = graphData.getEdgeCount();
        Map<String, Map<String, Double>> edgesMap = graphData.getEdges();

        // Adjusted to handle vertices count properly
        int[] fs = new int[vertices + edgeCount + 1]; // Enough space for all vertices and edges
        int[] aps = new int[vertices + 1]; // One entry per vertex, plus the first entry
        double[][] weights = new double[vertices + 1][vertices + 1]; // Always initialize for safety

        fs[0] = vertices + edgeCount; // First entry of fs is the total number of vertices and edges
        aps[0] = vertices; // First entry of aps is the number of vertices

        int fsIndex = 1; // Index to track position in fs
        // Assuming vertex indexing from 1 to vertices in the graph data for proper mapping
        for (int vertex = 1; vertex <= vertices; vertex++) {
            String vertexKey = String.valueOf(vertex);
            aps[vertex] = fsIndex; // Point aps to the start of each vertex's edge list in fs
            if (edgesMap.containsKey(vertexKey)) {
                for (Map.Entry<String, Double> entry : edgesMap.get(vertexKey).entrySet()) {
                    int succ = Integer.parseInt(entry.getKey()); // Parse the string key to integer
                    double weight = entry.getValue();
                    fs[fsIndex++] = succ; // Add successor to fs
                    if (graphData.isWeighted()) {
                        weights[vertex][succ] = weight; // Assign weight
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
