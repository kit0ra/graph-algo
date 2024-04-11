/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Administrator
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mycompany.graphs.Graph;
import com.mycompany.graphs.GraphData;

public class GraphCreator {
    
    public static Graph createGraphFromGraphData(GraphData graphData) {
        int vertices = graphData.getVertices();
        int edges = graphData.getEdgeCount();
        Map<String, List<Integer>> edgesMap = graphData.getEdges();
        
        // Prepare lists to hold fs and aps values
        List<Integer> fsList = new ArrayList<>();
        List<Integer> apsList = new ArrayList<>();
        
        // Populate fs and aps from edgesMap
        fsList.add(vertices+edges); // Assuming the first element represents the number of vertices
        apsList.add(vertices); // Placeholder for consistency; adjust as needed
        
        
        // Start converting from the first entry
        for (Map.Entry<String, List<Integer>> entry : edgesMap.entrySet()) {
            apsList.add(fsList.size()); // Store the start index in fs for this entry

            // Add all toVertices to fs, separate entries with 0
            for (Integer toVertex : entry.getValue()) {
                fsList.add(toVertex);
            }
            fsList.add(0); // Separate each entry by 0
        }
        
        return new Graph(fsList, apsList);
    }
}
