/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hepers;

/**
 *
 * @author Administrator
 */

public class KruskalEdge {
    private int startVertex;
    private int endVertex;
    private double weight;

    public KruskalEdge(int startVertex, int endVertex, double weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }

    public int getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(int startVertex) {
        this.startVertex = startVertex;
    }

    public int getEndVertex() {
        return endVertex;
    }

    public void setEndVertex(int endVertex) {
        this.endVertex = endVertex;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return startVertex + " -- {" + weight + "} -- " + endVertex;
    }
}

