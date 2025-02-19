/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import algorithms.Tarjan;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class GraphPrinter {

    public static void adjMatrixPrinter(int[][] matrix) {
        int n = matrix.length;
        for (int i=1 ; i<n ;i++) {
            for (int j = 1; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void fsApsPrinter(int[] fs, int[] aps) {
        String fsString = arrayToString(fs);
        String apsString = arrayToString(aps);
        System.out.println("FS: " + fsString + "\nAPS: " + apsString);
    }

    public static void fpAppPrinter(int[] fp, int[] app) {
        String fpString = arrayToString(fp);
        String appString = arrayToString(app);
        System.out.println("FP: " + fpString + "\nAPP: " + appString);
    }

    public static String formatFsAps(int[] fs, int[] aps) {
        return "FS: " + arrayToString(fs) + "\nAPS: " + arrayToString(aps);
    }

    private static String arrayToString(int[] array) {
        if (array == null || array.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void printDistance(int[] distance) {
        for (int i = 1; i < distance.length; i++) {
            System.out.println("Vertex " + i + " distance: " + distance[i]);
        }
    }

    public static void printRank(int[] rank) {
        for (int i = 1; i < rank.length; i++) {
            System.out.println("Vertex " + i + " rank: " + rank[i]);
        }
    }

    public static void printDijkstraResult(double[][] result) {
        if (result == null || result.length < 2 || result[0].length == 0) {
            System.out.println("No results to display.");
            return;
        }

        double[] distance = result[0];
        double[] pred = result[1];

        System.out.println("Dijkstra's algorithm results:");
        System.out.println(String.format("%-10s %-10s %-10s", "Vertex", "Distance", "Predecessor"));

        // Start from index 1 if your vertices are 1-based indexed
        // Adjust starting index based on your specific indexing in your graph structure
        for (int i = 1; i < distance.length; i++) {
            String distanceStr = distance[i] == Integer.MAX_VALUE ? "Infinity" : Double.toString(distance[i]);
            String predStr = pred[i] == -1 ? "None" : Integer.toString((int) pred[i]);
            System.out.println(String.format("%-10d %-10s %-10s", i, distanceStr, predStr));
        }
    }

    public static void printTarjanResults(Tarjan tarjan) {
        // Retrieve the components and cfc array from the Tarjan object
        List<List<Integer>> components = tarjan.getComponents();
        int[] cfc = tarjan.getCFC();
        int[] fsr = tarjan.getFsr();
        int[] apsr = tarjan.getApsr();
        int[] pilch = tarjan.getPilch();
        List<Integer> br = tarjan.getBr();

        // Check for null or empty components
        if (components == null || components.isEmpty()) {
            System.out.println("No strongly connected components found.");
            return;
        }

        // Printing components as mapped to cfc
        System.out.println("Strongly connected components:");
        for (int i = 0; i < components.size(); i++) {
            List<Integer> component = components.get(i);
            System.out.print("Composante " + (i + 1) + " : {");
            for (int j = 0; j < component.size(); j++) {
                System.out.print(component.get(j) + (j < component.size() - 1 ? ", " : ""));
            }
            System.out.println("}");
        }

        // Printing the cfc array
        System.out.println("Component ID array (cfc): " + Arrays.toString(cfc));

        // Printing fsr and apsr arrays
        if (fsr != null && apsr != null) {
            System.out.println("Reduced graph FS (fsr): " + Arrays.toString(fsr));
            System.out.println("Reduced graph APS (apsr): " + Arrays.toString(apsr));
        } else {
            System.out.println("Reduced graph data not available.");
        }

        // Optionally print the pilch (stack) if needed
        System.out.println("Stack (pilch): " + Arrays.toString(pilch));

        // Printing Reduced Graph Bases
        System.out.println("Base components of the reduced graph: " + br);

        //Printing bases from the original graph
        tarjan.editionBases();
    }

    //Prufer 
    public static void displayPruferCode(int[] pruferCode) {
        if (pruferCode == null || pruferCode.length == 0) {
            System.out.println("Prufer code is empty or not initialized.");
            return;
        }

        System.out.print("Prufer Code: [");
        for (int i = 1; i < pruferCode.length; i++) {
            System.out.print(pruferCode[i]);
            if (i < pruferCode.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void displayTreeConnections(int[][] adjacencyMatrix) {

        int n = adjacencyMatrix[0][0];

        System.out.println("Tree Connections:");
        // Iterate through the matrix to find and print connections
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) { // Start from i+1 to avoid printing symmetrical connections twice
                if (adjacencyMatrix[i][j] == 1) {
                    System.out.println(i + " <--> " + j); // Print the connection
                }
            }
        }
    }

}
