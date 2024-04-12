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
import com.mycompany.graphs.GraphData;
import com.mycompany.graphs.WeightedGraph;

public class GraphCreator {

    public static Graph createGraphFromGraphData(GraphData graphData) {
        int vertices = graphData.getVertices();
        int edgeCount = graphData.getEdgeCount();
        Map<Integer, int[]> edgesMap = graphData.getEdges();

        int[] fs = new int[vertices + edgeCount + 1];
        int[] aps = new int[vertices + 1];

        fs[0] = vertices + edgeCount;
        aps[0] = vertices;

        int fsIndex = 1;
        for (int vertex = 1; vertex <= vertices; vertex++) {
            aps[vertex] = fsIndex;
            if (edgesMap.containsKey(vertex)) {
                for (int succ : edgesMap.get(vertex)) {
                    fs[fsIndex++] = succ;
                }
            }
            fs[fsIndex++] = 0; // End marker
        }

        return graphData.isWeighted() ? new WeightedGraph(fs, aps, graphData.getWeights()) : new Graph(fs, aps);
    }
}
