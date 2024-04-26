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

        // Define the correct type for edges considering both keys and values are String -> Double mappings
        Type type = new TypeToken<Map<String, Map<String, Double>>>() {
        }.getType();
        Map<String, Map<String, Double>> edges = gson.fromJson(data.getAsJsonObject("edges"), type);

        // Calculate the total number of edges
        int edgeCount = 0;
        for (Map.Entry<String, Map<String, Double>> entry : edges.entrySet()) {
            edgeCount += entry.getValue().size(); // Each entry in the inner map represents an edge
        }

        GraphData graphData = new GraphData(name, isOriented, isWeighted, vertices, edgeCount, edges);
        return graphData;
    }

    private static double[][] parseWeights(JsonObject weightsJson, int vertices) {
        double[][] weights = new double[vertices + 1][vertices + 1]; // Initialize weights matrix

        for (Map.Entry<String, JsonElement> entry : weightsJson.entrySet()) {
            int from = Integer.parseInt(entry.getKey());
            JsonObject weightPairs = entry.getValue().getAsJsonObject();

            for (Map.Entry<String, JsonElement> pair : weightPairs.entrySet()) {
                int to = Integer.parseInt(pair.getKey());
                double weight = pair.getValue().getAsDouble();
                weights[from][to] = weight;
            }
        }
        return weights;
    }
}
