/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io;

/**
 *
 * @author Administrator
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class GraphDataParser {

    public static GraphData parseGraphData(String jsonData) throws Exception {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonData, JsonObject.class);

        String name = jsonObject.get("name").getAsString();
        boolean isWeighted = jsonObject.get("weighted").getAsBoolean();
        boolean isOriented = jsonObject.get("oriented").getAsBoolean();

        JsonObject data = jsonObject.getAsJsonObject("data");
        int vertices = data.get("vertices").getAsInt();

        // Define the correct type for edges
        Type type = new TypeToken<Map<String, Map<String, JsonElement>>>() {
        }.getType();
        Map<String, Map<String, JsonElement>> tempEdges = gson.fromJson(data.getAsJsonObject("edges"), type);

        Map<String, Map<String, Double>> edges = new HashMap<>();
        int edgeCount = 0;

        // Process edges depending on whether the graph is weighted
        for (Map.Entry<String, Map<String, JsonElement>> entry : tempEdges.entrySet()) {
            Map<String, Double> edgeMap = new HashMap<>();
            for (Map.Entry<String, JsonElement> edgeEntry : entry.getValue().entrySet()) {
                Double weight = isWeighted && !edgeEntry.getValue().isJsonNull() ? edgeEntry.getValue().getAsDouble() : 1.0;  // Use 1.0 as default weight for unweighted edges
                edgeMap.put(edgeEntry.getKey(), weight);
            }
            edges.put(entry.getKey(), edgeMap);
            edgeCount += edgeMap.size();
        }

        GraphData graphData = new GraphData(name, isOriented, isWeighted, vertices, edgeCount, edges);
        return graphData;
    }
}
