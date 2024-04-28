/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

/**
 *
 * @author Administrator
 */
public class GraphConverter {

    public static int[][] toAdjMatrix(int[] fs, int[] aps) {
        int vertices = aps[0];
        int total = fs[0];

        // Initialize the adjacency matrix
        int[][] matrix = new int[vertices][vertices + 1];

        // First cell of matrix contains the number of vertices
        matrix[0][0] = vertices;
        matrix[1][0] = total;

        // Populate the adjacency matrix
        for (int i = 1; i <= vertices; i++) {
            int start = aps[i]; // Start index in fs for the i-th vertex's successors
            for (int j = start; fs[j] != 0; j++) { // Iterate through successors until a block separator (0) is found
                int successor = fs[j];
                matrix[i - 1][successor] = 1; // Mark the edge presence with 1
            }
        }

        return matrix;
    }

    public static int[][] adjMatrixToFsAps(int[][] matrix) {
        // Extract the number of vertices and total number from metadata stored in matrix
        int vertices = matrix[0][0]; // Total vertices are stored at matrix[0][0]
        int total = matrix[1][0];   // Total vertices plus edges are stored at matrix[1][0]

        // Prepare the FS and APS arrays with appropriate sizes
        int[] fs = new int[total + 1]; // +1 for FS[0] which stores total count
        int[] aps = new int[vertices + 1]; // +1 for APS[0] which stores number of vertices

        // Initialize FS and APS
        fs[0] = total; // Total count of elements in FS
        aps[0] = vertices; // Total count of vertices

        int fsIndex = 1; // Start filling FS from index 1
        for (int r = 1; r <= vertices; r++) { // Start from 1 to skip metadata row
            aps[r] = fsIndex; // Record the start index of successors for each vertex in APS
            for (int c = 1; c <= vertices; c++) { // Start from 1 to skip metadata column
                if (matrix[r][c] == 1) { // If there's an edge from r to c (1-based index)
                    fs[fsIndex++] = c; // Store successor (c) in FS, adjust because FS is 1-based for vertices
                }
            }
            fs[fsIndex++] = 0; // Separator after listing all successors for vertex r
        }

        // Return the two arrays packed in a 2D array for easy handling
        return new int[][]{fs, aps};
    }

    public static int[][] fsApsToFpApp(int[] fs, int[] aps) {
        int vertices = aps[0];
        int total = fs[0];

        int[] app = new int[vertices + 1];
        int[] fp = new int[total + 1];

        app[0] = vertices;
        fp[0] = total;

        // calculate ddi
        int[] ddi = new int[vertices + 1];
        ddi = GraphHelpers.calculateHalfInteriorDegree(fs, aps);

        int id = 1;
        for (int i = 1; i <= vertices; i++) {
            app[i] = id;
            id += ddi[i] + 1;
            fp[id - 1] = 0;
        }

        for (int i = 1; i <= vertices; i++) {
            ddi[i] = app[i];
        }

        int node = 1;
        for (int i = 1; i <= total; i++) {
            if (fs[i] == 0) {
                node++;
            } else {
                fp[ddi[fs[i]]++] = node;
            }
        }

        return new int[][]{fp, app};
    }

}
