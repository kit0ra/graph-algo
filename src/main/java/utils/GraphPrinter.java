/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Administrator
 */
public class GraphPrinter {

    public static void adjMatrixPrinter(int[][] matrix) {
        for (int[] row : matrix) {
            for (int j = 1; j < row.length; j++) {
                System.out.print(row[j] + " ");
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
    
    public static void printDistance(int [] distance){
         for (int i = 1; i < distance.length; i++) {
            System.out.println("Vertex " + i + " distance: " + distance[i]);
        }
    }
    
    public static void printRank(int [] rank){
         for (int i = 1; i < rank.length; i++) {
            System.out.println("Vertex " + i + " rank: " + rank[i]);
        }
    }
    
    public static void printDijkstraResult(int[][] result) {
        if (result == null || result.length < 2 || result[0].length == 0) {
            System.out.println("No results to display.");
            return;
        }

        int[] distance = result[0];
        int[] pred = result[1];

        System.out.println("Dijkstra's algorithm results:");
        System.out.println(String.format("%-10s %-10s %-10s", "Vertex", "Distance", "Predecessor"));

        // Start from index 1 if your vertices are 1-based indexed
        // Adjust starting index based on your specific indexing in your graph structure
        for (int i = 1; i < distance.length; i++) {
            String distanceStr = distance[i] == Integer.MAX_VALUE ? "Infinity" : Integer.toString(distance[i]);
            String predStr = pred[i] == -1 ? "None" : Integer.toString(pred[i]);
            System.out.println(String.format("%-10d %-10s %-10s", i, distanceStr, predStr));
        }
    }
}

