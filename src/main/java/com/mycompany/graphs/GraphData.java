/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.graphs;

/**
 *
 * @author Administrator
 */
import java.util.Map;
import java.util.List;

public class GraphData {
    private String name;
    private int vertices;
    private int edgeCount;
    private Map<String, List<Integer>> edges;

    // Getters and setters
    public String getName(){
        return name;
    }
    
    public void setName(String newName) {
        name= newName;
    }
    
    public int getEdgeCount(){
        return edgeCount;
    }
    
    public void setEdgeCount(int n){
        edgeCount=n;
    }
    
    public int getVertices() {
        return vertices;
    }

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

    public Map<String, List<Integer>> getEdges() {
        return edges;
    }

    public void setEdges(Map<String, List<Integer>> edges) {
        this.edges = edges;
    }
    
    @Override
    public String toString() {
        return "GraphData{" +
               "name='" + name + '\'' +
               ", vertices=" + vertices +
               ", edges=" + edges +
               '}';
    }

}
