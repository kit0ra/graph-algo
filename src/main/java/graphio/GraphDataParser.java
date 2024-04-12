/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphio;

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

        Type type = new TypeToken<Map<Integer, Map<Integer, Integer>>>() {
        }.getType();
        Map<Integer, Map<Integer, Integer>> edges = gson.fromJson(data.getAsJsonObject("edges"), type);

        // Calculate the total number of edges
        int edgeCount = 0;
        for (Map.Entry<Integer, Map<Integer, Integer>> entry : edges.entrySet()) {
            edgeCount += entry.getValue().size(); // Each entry in the inner map represents an edge
        }

        GraphData graphData = new GraphData(name, isOriented, isWeighted, vertices, edgeCount, edges);

        if (isWeighted) {
            JsonObject weightsJson = data.getAsJsonObject("weights");
            if (weightsJson != null) { // Check if weights JSON is provided
                int[][] weights = parseWeights(weightsJson, vertices);
                graphData.setWeights(weights);
            }
        }

        return graphData;
    }



    private static int[][] parseWeights(JsonObject weightsJson, int vertices) {
        int[][] weights = new int[vertices + 1][vertices + 1]; // Initialize weights matrix

        if (weightsJson == null) {
            return weights; // Return the initialized but empty weights matrix
        }

        for (Map.Entry<String, JsonElement> entry : weightsJson.entrySet()) {
            int from = Integer.parseInt(entry.getKey());
            JsonObject weightPairs = entry.getValue().getAsJsonObject();

            if (weightPairs != null) { // Additional check for null to prevent NullPointerException
                for (Map.Entry<String, JsonElement> pair : weightPairs.entrySet()) {
                    int to = Integer.parseInt(pair.getKey());
                    int weight = pair.getValue().getAsInt();
                    weights[from][to] = weight;
                }
            }
        }
        return weights;
    }

}
