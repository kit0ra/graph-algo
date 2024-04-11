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
    private int vertices;
    private int edgeCount;
    private Map<String, int[]> edges;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
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

    public Map<String, int[]> getEdges() { 
        return edges;
    }

    public void setEdges(Map<String, int[]> edges) { 
        this.edges = edges;
    }

    @Override
    public String toString() {
        StringBuilder edgesString = new StringBuilder("{");
        if (edges != null) {
            for (Map.Entry<String, int[]> entry : edges.entrySet()) {
                edgesString.append(entry.getKey())
                            .append("=")
                            .append(Arrays.toString(entry.getValue()))
                            .append(", ");
            }
            if (!edges.isEmpty()) {
                edgesString.setLength(edgesString.length() - 2);
            }
        }
        edgesString.append("}");
        
        return "GraphData{" +
               "name='" + name + '\'' +
               ", vertices=" + vertices +
               ", edgeCount=" + edgeCount +
               ", edges=" + edgesString +
               '}';
    }
}
