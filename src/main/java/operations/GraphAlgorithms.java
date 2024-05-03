/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations;

import algorithms.Danzig;
import algorithms.Dijkstra;
import algorithms.Rank;
import algorithms.Distance;
import algorithms.Prufer;
import algorithms.Tarjan;
import graphs.Graph;
import graphs.WeightedGraph;
import helpers.TaskManager;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
import ui.KruskalGraphInputFrame;
import ui.TaskManagerFrame;
import utilities.GraphConverter;
import utilities.GraphDisplay;
import utilities.GraphPrinter;

/**
 *
 * @author Administrator
 */
public class GraphAlgorithms {

    private Graph currentGraph;
    private static TaskManager taskManager;

    public static void setTaskManager(TaskManager manager) {
        taskManager = manager;
    }

    public GraphAlgorithms() {
    }

    public void setCurrentGraph(Graph currentGraph) {
        this.currentGraph = currentGraph;
    }

    public void performDistanceAlg() {
        if (currentGraph == null) {
            JOptionPane.showMessageDialog(null, "No graph selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int[] fs = currentGraph.getFS();
        int[] aps = currentGraph.getAPS();

        // Perform the distance calculation
        int[] distance = Distance.distance(fs, aps);

        // Format and display the results
        GraphDisplay.displayDistanceResults(distance);
    }

    public void performRankAlg() {
        if (currentGraph == null) {
            JOptionPane.showMessageDialog(null, "No graph selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int[] fs = currentGraph.getFS();
        int[] aps = currentGraph.getAPS();

        //calculate Rank
        int[] rank = Rank.calculateRank(fs, aps);
        // Format and display the results
        GraphDisplay.displayDistanceResults(rank);
    }

    public void performTarjanAlg() {
        if (currentGraph == null) {
            JOptionPane.showMessageDialog(null, "No graph selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //calculate CFC
        Tarjan tarj = new Tarjan(currentGraph);
        tarj.findStronglyConnectedComponents();
        GraphDisplay.displayTarjanResults(tarj);
    }

    public void performDijkstraAlg() {
        if (currentGraph == null) {
            JOptionPane.showMessageDialog(null, "No graph selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!(currentGraph instanceof WeightedGraph)) {
            JOptionPane.showMessageDialog(null, "The selected graph is not a weighted graph.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        WeightedGraph graph = (WeightedGraph) currentGraph;

        /*if (!graph.isWeighted()) {
            JOptionPane.showMessageDialog(null, "The selected graph is not weighted.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }*/
        // Check for negative weights in the adjacency matrix
        if (containsNegativeWeights(graph.getWeights())) {
            JOptionPane.showMessageDialog(null, "The graph contains negative weights. Please use the Bellman-Ford algorithm.", "Negative Weights Detected", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            double[][] result = Dijkstra.dijkstra(graph, 1); // Assuming 1 is the source vertex or this might also need to be dynamically selected or validated
            GraphDisplay.displayDijkstraResults(result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error during Dijkstra's algorithm execution: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

// Method to check if the weight matrix contains any negative weights
    private boolean containsNegativeWeights(double[][] weights) {
        for (int i = 1; i < weights.length; i++) { // Assuming index 0 is not used or contains vertex count depending on graph representation
            for (int j = 1; j < weights[i].length; j++) {
                if (weights[i][j] < 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void performDanzigAlg() {
        if (currentGraph == null) {
            JOptionPane.showMessageDialog(null, "No graph selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!(currentGraph instanceof WeightedGraph)) {
            JOptionPane.showMessageDialog(null, "The selected graph is not a weighted graph.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        WeightedGraph graph = (WeightedGraph) currentGraph;
        double[][] weightMatrix = graph.getWeights();

        if (weightMatrix == null) {
            JOptionPane.showMessageDialog(null, "weight Matrix is null.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double[][] results = Danzig.calculateShortestPath(weightMatrix);
        GraphDisplay.displayDantzigResults(results);
    }

    public void performPruferAlg() {
        if (currentGraph == null) {
            JOptionPane.showMessageDialog(null, "No graph selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Options for the user to select coding or decoding
        String[] options = {"Coding", "Decoding"};
        int choice = JOptionPane.showOptionDialog(null,
                "Choose an operation:",
                "Prufer Code Operation",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        if (choice == 0) { // Coding
            int[] fs = currentGraph.getFS();
            int[] aps = currentGraph.getAPS();
            int[][] adjMatrix = GraphConverter.toAdjMatrix(fs, aps);
            GraphPrinter.adjMatrixPrinter(adjMatrix);

            if (adjMatrix == null) {
                JOptionPane.showMessageDialog(null, "Adjacency Matrix is null.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int[] pruferCode = Prufer.generatePruferCode(adjMatrix);
            GraphDisplay.displayPruferCodeResults(pruferCode);

        } else if (choice == 1) { // Decoding
            String pruferCodeInput = JOptionPane.showInputDialog("Enter Prufer code, comma separated:");
            try {
                int[] pruferCode = parsePruferCode(pruferCodeInput);
                int[][] decodedMatrix = Prufer.decodePruferCode(pruferCode);
                GraphDisplay.displayPruferDecodeResults(decodedMatrix);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid Prufer code input. " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private int[] parsePruferCode(String input) throws NumberFormatException {
        String[] parts = input.split(",");
        int[] pruferCode = new int[parts.length + 1];
        pruferCode[0] = parts.length + 2;
        for (int i = 1; i < parts.length; i++) {
            pruferCode[i] = Integer.parseInt(parts[i].trim());
        }
        return pruferCode;
    }

    public void performScheduleManagerAlg() {
        EventQueue.invokeLater(() -> {
            TaskManagerFrame frame = new TaskManagerFrame(taskManager);
            frame.setVisible(true);
        });
    }

    public void performKruskalAlg() {
        KruskalGraphInputFrame frame = new KruskalGraphInputFrame();
        frame.setVisible(true);
    }

}
