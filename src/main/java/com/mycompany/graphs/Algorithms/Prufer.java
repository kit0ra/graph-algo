/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.graphs.Algorithms;

/**
 *
 * @author Administrator
 */
public class Prufer {
    
    public static int[] generatePruferCode(int[][] adjacencyMatrix) {
        int nb_som = adjacencyMatrix[0][0]; // Get number of vertices from the metadata
        // Adjust Prufer code array size to include an extra slot at the beginning for the number of vertices
        int[] pruferCode = new int[nb_som - 1]; // Include space for the number of vertices at index 0

        pruferCode[0] = nb_som; // Store the number of vertices at index 0 of the Prufer code array

        int[] degree = new int[nb_som + 1]; // Degree array, +1 for 1-based index
        for (int i = 1; i <= nb_som; i++) {
            for (int j = 1; j <= nb_som; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    degree[i]++;
                }
            }
        }

        int k = 1; // Start filling the Prufer code from index 1
        while (k < nb_som - 1) { // Adjust loop condition to fill up to nb_som - 1
            int leaf = -1;
            for (int i = 1; i <= nb_som; i++) {
                if (degree[i] == 1) {
                    leaf = i;
                    break;
                }
            }

            int neighbor = -1;
            for (int j = 1; j <= nb_som; j++) {
                if (adjacencyMatrix[leaf][j] == 1) {
                    neighbor = j;
                    break;
                }
            }

            pruferCode[k++] = neighbor; // Store the neighbor at the next available position in Prufer code array
            adjacencyMatrix[leaf][neighbor] = 0;
            adjacencyMatrix[neighbor][leaf] = 0;
            degree[leaf]--;
            degree[neighbor]--;
        }
        return pruferCode;
    }

    
  public static int[][] decodePruferCode(int[] prufer) {
    int n = prufer[0];  // Retrieve the number of vertices from the Prufer code's first element
    int[][] adjacencyMatrix = new int[n + 1][n + 1]; // Create adjacency matrix with n+1 vertices (1-indexed)

    int[] degree = new int[n + 1]; // Array to store the degree of each vertex
    for (int i = 1; i < n - 1; i++) {
        degree[prufer[i]]++; // Increment the degree for each vertex in the Prufer code
    }

    // Initialize degrees for all vertices: they start with 1 (each vertex is at least connected once)
    for (int i = 1; i <= n; i++) {
        degree[i]++;
    }

    // Reconstruct the tree from the Prufer code
    for (int i = 1; i <= n - 2; i++) {
        int leaf = -1;
        // Find the smallest numbered node that is not yet connected
        for (int j = 1; j <= n; j++) {
            if (degree[j] == 1) {
                leaf = j;
                break;
            }
        }
        
        // Connect the leaf to the corresponding vertex in the Prufer code
        int node = prufer[i];
        adjacencyMatrix[leaf][node] = 1;
        adjacencyMatrix[node][leaf] = 1;

        // Update degrees
        degree[leaf]--;
        degree[node]--;
    }

    // Connect the last two remaining vertices
    int last1 = -1, last2 = -1;
    for (int i = 1; i <= n; i++) {
        if (degree[i] == 1) {
            if (last1 == -1) {
                last1 = i;
            } else {
                last2 = i;
                break;
            }
        }
    }
    adjacencyMatrix[last1][last2] = 1;
    adjacencyMatrix[last2][last1] = 1;

    // Metadata: Number of vertices and edges
    adjacencyMatrix[0][0] = n; // Number of vertices
    adjacencyMatrix[1][0] = n + (n - 1); // Number of vertices + number of edges (n-1 edges in a tree)

    return adjacencyMatrix;
}

    
}
