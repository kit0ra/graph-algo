/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphs;

/**
 *
 * @author Administrator
 */
import io.GraphData;
import java.util.HashMap;
import java.util.Map;
import utilities.GraphPrinter;

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

    public GraphData getGraphData() {
        Map<String, Map<String, Double>> edges = new HashMap<>();
        int numVertices = getVertices();
        System.out.println("Converting Graph to GraphData...");
        System.out.println("Total vertices: " + numVertices);

        for (int i = 1; i <= numVertices; i++) {
            Map<String, Double> edgeMap = new HashMap<>();
            int start = aps[i];
            System.out.println("Processing vertex " + i + " starting at index " + start);

            for (int j = start; fs[j] != 0; j++) {
                if (isWeighted()) {
                    // Cast to WeightedGraph and call getWeightForEdge if the graph is weighted
                    WeightedGraph weightedGraph = (WeightedGraph) this;
                    double weight = weightedGraph.getWeightForEdge(i, fs[j]);
                    edgeMap.put(String.valueOf(fs[j]), weight);
                    System.out.println("Edge from " + i + " to " + fs[j] + " with weight " + weight);
                } else {
                    // Use null for unweighted edges
                    edgeMap.put(String.valueOf(fs[j]), null);
                    System.out.println("Edge from " + i + " to " + fs[j] + " (unweighted)");
                }
            }
            edges.put(String.valueOf(i), edgeMap);
        }

        GraphData graphData = new GraphData(name, type, isWeighted(), numVertices, getEdges(), edges);
        System.out.println("GraphData created: " + graphData);
        return graphData;
    }

    @Override
    public String toString() {
        return GraphPrinter.formatFsAps(fs, aps);
    }

}
