/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helpers;

/**
 *
 * @author Administrator
 */

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class KruskalGraph {
    private int numberOfVertices;
    private int numberOfEdges;
    private List<KruskalEdge> edges;

    public KruskalGraph(int numberOfVertices, int numberOfEdges) {
        this.numberOfVertices = numberOfVertices;
        this.numberOfEdges = numberOfEdges;
        this.edges = new ArrayList<>();
    }
    
    // Method to set the entire list of edges
    public void setEdges(List<KruskalEdge> newEdges) {
        this.edges = newEdges;
        this.numberOfEdges = newEdges.size(); // Update the number of edges
    }
    
    public void addEdge(KruskalEdge edge) {
        edges.add(edge);
    }

    public List<KruskalEdge> getEdges() {
        return edges;
    }

    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    public void setNumberOfVertices(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
    }

    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    public void setNumberOfEdges(int numberOfEdges) {
        this.numberOfEdges = numberOfEdges;
    }

    // Sort edges by weight and then by vertices if weights are equal
    public void sortEdges() {
        Collections.sort(edges, (KruskalEdge e1, KruskalEdge e2) -> {
            // First compare by weight
            int weightComparison = Double.compare(e1.getWeight(), e2.getWeight());
            if (weightComparison != 0) {
                return weightComparison;
            }
            // If weights are equal, sort by start vertices
            int startComparison = Integer.compare(e1.getStartVertex(), e2.getStartVertex());
            if (startComparison != 0) {
                return startComparison;
            }
            // If start vertices are also equal, sort by end vertices
            return Integer.compare(e1.getEndVertex(), e2.getEndVertex());
        });
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("KruskalGraph{vertices=").append(numberOfVertices);
        sb.append(", edges=").append(numberOfEdges);
        sb.append(", edges list=\n");
        for (KruskalEdge edge : edges) {
            sb.append(edge.toString()).append("\n");
        }
        return sb.toString();
    }
    
    // Utility method to format and print all edges in the graph
    public void printEdges() {
        System.out.println("Edges of the graph:");
        for (KruskalEdge edge : edges) {
            System.out.println(edge);
        }
    }

    // Utility method to format edges into a string
    private String formatEdges() {
        StringBuilder sb = new StringBuilder();
        for (KruskalEdge edge : edges) {
            sb.append(edge.toString()).append("\n");
        }
        return sb.toString();
    }

}

