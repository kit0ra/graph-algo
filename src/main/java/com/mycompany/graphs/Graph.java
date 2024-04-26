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
    protected String name;

    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }
    
    protected boolean type;

    public void setType(boolean isDirected) {
        this.type = isDirected;
    }

    public boolean getType() {
        return type;
    }
    
    protected int[] fs;
    protected int[] aps;

    public Graph(int[] fs, int[] aps) {
        this.fs = fs.clone();
        this.aps = aps.clone();
        this.name = "Untitled";
        this.type = true;
    }
    
    public boolean isWeighted() {
        return false;
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
        return GraphPrinter.formatFsAps(fs, aps);
    }
}
