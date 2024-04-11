/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.graphs;

/**
 *
 * @author Administrator
 */

public class WeightedGraph extends Graph {
    private int[][] p; // Edge weights

    public WeightedGraph(int[] fs, int[] aps, int[][] p) {
        super(fs, aps); 
        this.p = p.clone(); 
    }

    public int[][] getP() {
        return p.clone(); 
    }

    public void setP(int[][] newP) {
        this.p = newP.clone();
    }
}
