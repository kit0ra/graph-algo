/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.graphs;

/**
 *
 * @author Administrator
 */

import java.util.List;
import utils.GraphPrinter;

public class Graph {
    private List<Integer> fs ;
    private List<Integer> aps;

    public Graph(List<Integer> fs , List<Integer> aps) {
        this.fs = fs;
        this.aps = aps;
    }
    
    public List<Integer> getFS(){
        return fs;
    }

    public void setFS(List<Integer> newFS) {
        fs = newFS;
    }
    
     public List<Integer> getAPS(){
        return aps;
    }
     
    public void setAPS(List<Integer> newAPS){
        aps = newAPS;
    } 
    
    public int getVertices() {
        return this.aps.get(0);
    }

    public int getEdges() {
        return this.fs.get(0) - getVertices();
    }

    @Override
    public String toString(){
        return GraphPrinter.formatFsAps(fs, aps);
    }
}
