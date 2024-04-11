/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.graphs;

/**
 *
 * @author Administrator
 */
import utils.GraphPrinter;


public class Graph {
    private int[] fs;
    private int[] aps;

    public Graph(int[] fs, int[] aps) {
        this.fs = fs.clone();
        this.aps = aps.clone();
    }

    public int[] getFS() {
        return fs.clone();
    }

    public void setFS(int[] newFS) {
        fs = newFS.clone();
    }

    public int[] getAPS() {
        return aps.clone();
    }

    public void setAPS(int[] newAPS) {
        aps = newAPS.clone();
    }

    public int getVertices() {
        return this.aps[0];
    }

    public int getEdges() {
        return this.fs[0] - getVertices();
    }

    @Override
    public String toString() {
        // Assuming GraphPrinter.formatFsAps is modified to accept int[] parameters
        return GraphPrinter.formatFsAps(fs, aps);
    }
}
