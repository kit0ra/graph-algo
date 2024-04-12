/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.graphs;

/**
 *
 * @author Administrator
 */
import java.util.Arrays;
import java.util.Map;

public class GraphData {
    private String name;
    private final boolean isWeighted;
    private int vertices;
    private int edgeCount;
    private Map<Integer, int[]> edges;
    private int[][] weights;
    
    public GraphData(boolean isWeighted, int vertices, int edgeCount, Map<Integer, int[]> edges) {
        this.isWeighted = isWeighted;
        this.vertices = vertices;
        this.edgeCount = edgeCount;
        this.edges = edges;
        this.weights = isWeighted ? new int[vertices + 1][vertices + 1] : null;
    }
    
    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }
    
     public boolean isWeighted() {
        return isWeighted;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public void setEdgeCount(int n) {
        edgeCount = n;
    }

    public int getVertices() {
        return vertices;
    }

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

    public Map<Integer, int[]> getEdges() { 
        return edges;
    }

    public void setEdges(Map<Integer, int[]> edges) { 
        this.edges = edges;
    }
    
    public int[][] getWeights() {
        return weights;
    }

    public void setWeights(int[][] weights) {
        if (isWeighted) {
            this.weights = weights;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"name\":\"").append(name).append("\",")
          .append("\"weighted\":").append(isWeighted).append(",")
          .append("\"data\":{")
          .append("\"vertices\":").append(vertices).append(",");

        // Constructing the edges part
        sb.append("\"edges\":{");
        if (edges != null && !edges.isEmpty()) {
            for (Map.Entry<Integer, int[]> entry : edges.entrySet()) {
                sb.append("\"").append(entry.getKey()).append("\":[")
                  .append(Arrays.toString(entry.getValue()).replace("[", "").replace("]", ""))
                  .append("],");
            }
            sb.setLength(sb.length() - 1); // Remove trailing comma
        }
        sb.append("}");

        // Adding weights if the graph is weighted
        if (isWeighted && weights != null) {
            sb.append(",\"weights\":{");
            for (int i = 1; i < weights.length; i++) { // Assuming indexing starts at 1
                sb.append("\"").append(i).append("\":{");
                for (int j = 1; j < weights[i].length; j++) {
                    if (weights[i][j] != 0) { // Assuming 0 means no weight/connection
                        sb.append("\"").append(j).append("\":").append(weights[i][j]).append(",");
                    }
                }
                if (sb.charAt(sb.length() - 1) == ',') {
                    sb.setLength(sb.length() - 1); // Remove trailing comma
                }
                sb.append("},");
            }
            if (sb.charAt(sb.length() - 1) == ',') {
                sb.setLength(sb.length() - 1); // Remove trailing comma
            }
            sb.append("}");
        }

        sb.append("}}");
        return sb.toString();
    }
}
