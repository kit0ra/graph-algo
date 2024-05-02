/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorithms;

import helpers.KruskalEdge;
import helpers.KruskalGraph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kruskal {

    private KruskalGraph myGraph;
    private KruskalGraph mst;

    private int[] cfc;
    private int[] prem;
    private int[] nb;
    private int[] pilch;

    public Kruskal(KruskalGraph graph) {
        myGraph = graph;
        init();
        mst = new KruskalGraph(graph.getNumberOfVertices(), 0);
    }
    
    public KruskalGraph getMst(){
        return mst;
    }
    
    private void init() {
        int nbVertices = myGraph.getNumberOfVertices();
        prem = new int[nbVertices + 1];
        pilch = new int[nbVertices + 1];
        cfc = new int[nbVertices + 1];
        nb = new int[nbVertices + 1];

        for (int i = 1; i <= nbVertices; i++) {
            prem[i] = cfc[i] = i;
            pilch[i] = 0;
            nb[i] = 1;
        }
        
        prem[0]=cfc[0]=nb[0]=7;
        
        System.out.println("Initialization complete with vertices: " + nbVertices);
    }

    private void merge(int start, int end) {
        if (nb[start] < nb[end]) {
            int temp = start;
            start = end;
            end = temp;
        }
        int x = prem[end];
        cfc[x] = start;

        while (pilch[x] != 0) {
            x = pilch[x];
            cfc[x] = start;
        }

        pilch[x] = prem[start];
        prem[start] = prem[end];
        nb[start] += nb[end];
    }

    public void calculateMST() {
        myGraph.sortEdges();
        List<KruskalEdge> sortedEdges = myGraph.getEdges(); 
        List<KruskalEdge> mstEdges = new ArrayList<>();

        int vertexCount = myGraph.getNumberOfVertices();
        int edgeIndex = 0, mstIndex = 0;

        while (mstIndex < vertexCount - 1) {
            KruskalEdge edge = sortedEdges.get(edgeIndex);
            int s = cfc[edge.getStartVertex()];
            int e = cfc[edge.getEndVertex()];

            System.out.println("Considering edge from " + s + " to " + e + " with weight " + edge.getWeight());

            if (s != e) {
                mstEdges.add(edge);
                System.out.println("Adding edge to MST: " + edge);
                merge(s, e);
                mstIndex++;
            }
            edgeIndex++;
        }

        mst.setEdges(mstEdges); 
        System.out.println("MST created with edges:");
        mst.printEdges();
    }

    @Override
    public String toString() {
        return "Kruskal{\nCFC: " + Arrays.toString(cfc)
                + "\nPrem: " + Arrays.toString(prem)
                + "\nPilch: " + Arrays.toString(pilch)
                + "\nNB: " + Arrays.toString(nb)
                + "\n}";
    }
}
