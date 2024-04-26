/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io;

/**
 *
 * @author Administrator
 */
import java.util.Map;

public class GraphData {
    private String name;
    private boolean isOriented;
    private boolean isWeighted;
    private int vertices;
    private int edgeCount;
    private Map<String, Map<String, Double>> edges;

    
    private double[][] weights; // Optional, only for weighted graphs

    public GraphData(String name, boolean isOriented, boolean isWeighted, int vertices, int edgeCount, Map<String, Map<String, Double>> edges) {
        this.name = name;
        this.isOriented = isOriented;
        this.isWeighted = isWeighted;
        this.vertices = vertices;
        this.edgeCount = edgeCount;
        this.edges = edges;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public boolean isOriented() {
        return isOriented;
    }

    public boolean isWeighted() {
        return isWeighted;
    }

    public void setWeights(double[][] weights) {
        if (isWeighted) {  // Check to ensure that weights should be set for weighted graphs only
            this.weights = new double[weights.length][];
            for (int i = 0; i < weights.length; i++) {
                this.weights[i] = weights[i].clone(); // Deep copying for safety
            }
        } else {
            throw new IllegalStateException("Cannot set weights on an unweighted graph.");
        }
    }

    public int getVertices() {
        return vertices;
    }

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public void setEdgeCount(int n) {
        this.edgeCount = n;
    }

    public Map<String, Map<String, Double>> getEdges() {
        return edges;
    }

    public void setEdges(Map<String, Map<String, Double>> edges) {
        this.edges = edges;
    }

    public double[][] getWeights() {
        return weights;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GraphData {");
        sb.append("\n  name: ").append(name)
          .append(",\n  oriented: ").append(isOriented)
          .append(",\n  weighted: ").append(isWeighted)
          .append(",\n  vertices: ").append(vertices)
          .append(",\n  edgeCount: ").append(edgeCount)
          .append(",\n  edges: ").append(edges)
          .append("\n}");
        return sb.toString();
    }
}

