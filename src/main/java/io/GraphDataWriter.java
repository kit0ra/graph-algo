/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class GraphDataWriter {

    /**
     * Manually creates a JSON string from the given GraphData.
     * @param graphData The GraphData object to serialize.
     * @return A string representing the JSON of the graph data.
     */
    public String serializeGraph(GraphData graphData) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\n");
        jsonBuilder.append(String.format("  \"name\": \"%s\",\n", graphData.getName()));
        jsonBuilder.append(String.format("  \"weighted\": %b,\n", graphData.isWeighted()));
        jsonBuilder.append(String.format("  \"oriented\": %b,\n", graphData.isOriented()));
        jsonBuilder.append("  \"data\": {\n");
        jsonBuilder.append(String.format("    \"vertices\": %d,\n", graphData.getVertices()));
        jsonBuilder.append("    \"edges\": {\n");

        Map<String, Map<String, Double>> edges = graphData.getEdges();
        List<String> verticesKeys = new ArrayList<>(edges.keySet());
        for (int i = 0; i < verticesKeys.size(); i++) {
            String vertex = verticesKeys.get(i);
            Map<String, Double> connections = edges.get(vertex);
            jsonBuilder.append(String.format("      \"%s\": {", vertex));
            List<String> connectionKeys = new ArrayList<>(connections.keySet());
            for (int j = 0; j < connectionKeys.size(); j++) {
                String connectedVertex = connectionKeys.get(j);
                Double weight = connections.get(connectedVertex);
                jsonBuilder.append(String.format("\"%s\": %s", connectedVertex, weight != null ? weight.toString() : "null"));
                if (j < connectionKeys.size() - 1) {
                    jsonBuilder.append(", ");
                }
            }
            jsonBuilder.append("}");
            if (i < verticesKeys.size() - 1) {
                jsonBuilder.append(",\n");
            } else {
                jsonBuilder.append("\n");
            }
        }

        jsonBuilder.append("    }\n  }\n}");
        return jsonBuilder.toString();
    }
}

