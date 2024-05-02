/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import algorithms.Tarjan;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Administrator
 */
public class GraphDisplay {

    // Static method to display distance results
    public static void displayDistanceResults(int[] distance) {
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        StringBuilder results = new StringBuilder();
        for (int i = 1; i < distance.length; i++) {
            results.append("Vertex ").append(i).append(" distance: ").append(distance[i]).append("\n");
        }
        textArea.setText(results.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scrollPane, "Distance Results", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void displayRankResults(int[] ranks) {
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        StringBuilder results = new StringBuilder("Vertex Rankings:\n");

        // Loop through the ranks array to build the results string
        for (int i = 1; i < ranks.length; i++) {
            results.append("Vertex ").append(i).append(" rank: ").append(ranks[i]).append("\n");
        }

        textArea.setText(results.toString());

        // Create a JScrollPane containing the JTextArea
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Display the scroll pane in a dialog box
        JOptionPane.showMessageDialog(null, scrollPane, "Vertex Ranks", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void displayTarjanResults(Tarjan tarjan) {
        if (tarjan == null) {
            JOptionPane.showMessageDialog(null, "Tarjan algorithm results not available.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTextArea textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        StringBuilder results = new StringBuilder();

        results.append("Components (Strongly Connected Components):\n");
        List<List<Integer>> components = tarjan.getComponents();
        for (int i = 0; i < components.size(); i++) {
            results.append("Component ").append(i + 1).append(": ").append(components.get(i)).append("\n");
        }

        results.append("\nComponent IDs (CFC):\n").append(arrayToString(tarjan.getCFC()));
        results.append("\nReduced Graph Successors (FSR):\n").append(arrayToString(tarjan.getFsr()));
        results.append("\nReduced Graph Pointers (APSR):\n").append(arrayToString(tarjan.getApsr()));
        results.append("\nBase of the Reduced Graph (BR):\n");
        if (tarjan.getBr() != null && !tarjan.getBr().isEmpty()) {
            results.append(tarjan.getBr().toString());
        } else {
            results.append("No base components or calculation not performed");
        }

        textArea.setText(results.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scrollPane, "Tarjan Algorithm Results", JOptionPane.INFORMATION_MESSAGE);
    }

    private static String arrayToString(int[] array) {
        if (array == null) {
            return "Not Available";
        }
        StringBuilder builder = new StringBuilder();
        for (int value : array) {
            builder.append(value).append(" ");
        }
        return builder.toString().trim();
    }

    public static void displayDijkstraResults(double[][] result) {
        if (result == null || result.length < 2 || result[0].length == 0) {
            JOptionPane.showMessageDialog(null, "No results to display.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retrieve distance and predecessor arrays
        double[] distances = result[0];
        double[] predecessors = result[1];

        // Creating a JTextArea to show results in a scrollable dialog
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        StringBuilder results = new StringBuilder();
        results.append(String.format("%-10s %-20s %-20s\n", "Vertex", "Distance", "Predecessor"));

        // Start from index 1 if your vertices are 1-based indexed
        for (int i = 1; i < distances.length; i++) {
            String distanceStr = distances[i] == Double.MAX_VALUE ? "Infinity" : String.format("%.5f", distances[i]);
            String predStr = (int) predecessors[i] == -1 ? "None" : Integer.toString((int) predecessors[i]);
            results.append(String.format("%-10d %-20s %-20s\n", i, distanceStr, predStr));
        }

        textArea.setText(results.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scrollPane, "Dijkstra's Algorithm Results", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void displayDantzigResults(double[][] results) {
        if (results == null || results.length == 0) {
            JOptionPane.showMessageDialog(null, "No results to display.", "Display Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Building a HTML string for nicer formatting, without the vertex column
        StringBuilder builder = new StringBuilder("<html><body>");
        builder.append("<h1>Dantzig's Algorithm Results:</h1>");
        builder.append("<table border='1'>");

        // Assuming results are structured in a matrix format
        for (int i = 1; i < results.length; i++) {
            builder.append("<tr>");
            for (int j = 1; j < results[i].length; j++) {
                builder.append("<td>").append(String.format("%.2f", results[i][j])).append("</td>");
            }
            builder.append("</tr>");
        }
        builder.append("</table></body></html>");

        JOptionPane.showMessageDialog(null, builder.toString(), "Dantzig's Algorithm Results", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void displayPruferCodeResults(int[] pruferCode) {
        if (pruferCode == null || pruferCode.length == 0){
            JOptionPane.showMessageDialog(null, "No results to display.", "Display Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String pruferCodeStr = GraphFormatter.formatPruferCode(pruferCode);
        JOptionPane.showMessageDialog(null, pruferCodeStr, "Generated Prufer Code", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void displayPruferDecodeResults(int[][] decodedMatrix) {
        if (decodedMatrix == null || decodedMatrix.length == 0) {
            JOptionPane.showMessageDialog(null, "No results to display.", "Display Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String matrixStr = GraphFormatter.formatAdjMatrix(decodedMatrix);
        JOptionPane.showMessageDialog(null, matrixStr, "Decoded Tree (Adjacency Matrix)", JOptionPane.INFORMATION_MESSAGE);
    }

}
