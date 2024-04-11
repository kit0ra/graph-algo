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

public class GraphCreator {

    public static Graph createGraphFromGraphData(GraphData graphData) {
        int vertices = graphData.getVertices();
        int edges = graphData.getEdgeCount();
        Map<String, int[]> edgesMap = graphData.getEdges();

        
        int[] fs = new int[vertices + edges + 1];
        int[] aps = new int[vertices + 1];

        
        fs[0] = vertices + edges; 
        aps[0] = vertices; 
        
        int fsIndex = 1; 
        int apsIndex = 1; 

        for (Map.Entry<String, int[]> entry : edgesMap.entrySet()) {
            aps[apsIndex++] = fsIndex; // Store the start index in fs for this vertex

            
            for (int toVertex : entry.getValue()) {
                fs[fsIndex++] = toVertex;
            }
            fs[fsIndex++] = 0; 
        }

        return new Graph(fs, aps);
    }
}
