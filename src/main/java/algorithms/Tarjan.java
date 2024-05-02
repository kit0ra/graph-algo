/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorithms;

import graphs.Graph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Tarjan {
    private Graph graph;
    private int[] low;
    private int[] ids;
    private boolean[] onStack;
    private int[] tarj; // Manual stack
    private List<List<Integer>> components;
    private int id = 0;
    private int[] cfc; // Component identifiers for each vertex
    
    private int[] fsr; // Reduced graph successors
    private int[] apsr; // Reduced graph array of pointers
    
    List<Integer> br;

    public Tarjan(Graph graph) {
        this.graph = graph;
        int vertices = graph.getVertices();
        // Initialize with +1 for indexing convenience
        low = new int[vertices + 1];
        ids = new int[vertices + 1];
        onStack = new boolean[vertices + 1];
        tarj = new int[vertices + 2]; // Stack pointer is at index 0
        tarj[0] = 0; // Initialize stack pointer
        cfc = new int[vertices + 1];
        components = new ArrayList<>();
        Arrays.fill(ids, -1);
    }
    
    public int[] getCFC() {
        return cfc;
    }
    
    public List<Integer> getBr(){
        return br;
    }
    
    public int [] getPilch(){
        return tarj;
    }
    
    public int[] getFsr() {
        return fsr;
    }
    
      public int[] getApsr() {
        return apsr;
    }
      
    public List<List<Integer>> getComponents(){
        return components;
    }
    
     public void findStronglyConnectedComponents() {
        for (int i = 1; i <= graph.getVertices(); i++) {
            if (ids[i] == -1) {
                traverse(i);
            }
        }
    }


    private void traverse(int at) {
        if (cfc == null) {
            throw new IllegalStateException("Component ID array (cfc) has not been initialized.");
        }

        push(at);
        onStack[at] = true;
        ids[at] = low[at] = id++;

        int[] neighbors = getNeighbors(at);
        for (int to : neighbors) {
            if (ids[to] == -1) {
                traverse(to);
                low[at] = Math.min(low[at], low[to]);
            } else if (onStack[to]) {
                low[at] = Math.min(low[at], ids[to]);
            }
        }

        if (ids[at] == low[at]) {
            List<Integer> component = new ArrayList<>();
            int node;
            do {
                node = pop();
                onStack[node] = false;
                component.add(node);
                cfc[node] = components.size(); // Assign component id
            } while (node != at);
            components.add(component);
        }
        populateCFC();
    }

    private void push(int x) {
        tarj[++tarj[0]] = x; // Increment stack pointer and then push x
      
    }

    private int pop() {
        if (tarj[0] == 0) {
            throw new IllegalStateException("Attempted to pop from an empty stack.");
        }
        int result = tarj[tarj[0]--]; // Pop the top element and then decrement stack pointer
       
        return result;
    }
    

    private int[] getNeighbors(int at) {
        int start = graph.getAPS()[at];
        int end = graph.getFS().length;  // Default to the end of the array if no further info is available

        if (at < graph.getAPS().length - 1) {
            end = graph.getAPS()[at + 1];
        }

        // Ensure the end is not beyond the length of the fs array and adjust if necessary
        end = Math.min(end, graph.getFS().length);

        // Additional check to prevent illegal argument exception
        if (end < start) {
            return new int[0]; // Return an empty array if end index is less than start index
        }

        List<Integer> neighbors = new ArrayList<>();
        for (int i = start; i < end && graph.getFS()[i] != 0; i++) {
            neighbors.add(graph.getFS()[i]);
        }
        return neighbors.stream().mapToInt(i -> i).toArray();
    }

    private void populateCFC() {
        int componentId = 1;  // Start component IDs from 1
        for (List<Integer> component : components) {
            for (int vertex : component) {
                cfc[vertex] = componentId;
            }
            componentId++;  // Increment component ID for the next component
        }
    }

      public void createReducedGraph() {
        int numComponents = components.size();
        List<Integer> fsrList = new ArrayList<>();
        apsr = new int[numComponents + 1]; // Including a slot for the number of components
        apsr[0] = numComponents; // Store the number of components in the first position

        fsrList.add(0); // Placeholder for total number of edges, will be updated later
        boolean[] connected = new boolean[numComponents + 1]; // Track connections to avoid duplicates

        for (int i = 1; i <= numComponents; i++) {
            apsr[i] = fsrList.size();
            Arrays.fill(connected, false);
            for (int vertex : components.get(i - 1)) {
                int[] neighbors = getNeighbors(vertex);
                for (int neighbor : neighbors) {
                    int componentId = cfc[neighbor];
                    if (!connected[componentId] && componentId != i) { // Avoid self-loops
                        fsrList.add(componentId);
                        connected[componentId] = true;
                    }
                }
            }
            fsrList.add(0); // End of this component's adjacency list
        }

        fsrList.set(0, fsrList.size() - 1); // Update the first element with the correct number of edges
        fsr = fsrList.stream().mapToInt(i -> i).toArray(); // Convert list to array
        baseGreduit();
    }
    
    private void baseGreduit() {
        int nr = apsr[0]; // Number of components
        int[] ddir = new int[nr + 1]; // Degree of incoming edges for each component
        for (int kr = 1; kr < fsr[0]; kr++) {
            ddir[fsr[kr]]++;
        }
        br = new ArrayList<>(); // List to store base components
        for (int c = 1; c <= nr; c++) {
            if (ddir[c] == 0) {
                br.add(c);
            }
        }
    }
   
    public void editionBases() {
        if (br.isEmpty()) {
            System.out.println("No base components found.");
            return;
        }

        System.out.println("Base of the original graph:");
        for (int index : br) {
            List<Integer> component = components.get(index - 1);
            System.out.println("Component " + index + " contains vertices: " + component);
        }
    }


}



