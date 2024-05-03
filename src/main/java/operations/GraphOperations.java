/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations;

import graphs.Graph;
import graphs.WeightedGraph;
import io.GraphDataWriter;
import javax.swing.JOptionPane;
import utilities.GraphConverter;
import utilities.GraphFormatter;
import utilities.GraphHelpers;

/**
 *
 * @author Administrator
 */
public class GraphOperations {

    private Graph currentGraph;

    public GraphOperations() {
    }

    public void setCurrentGraph(Graph currentGraph) {
        this.currentGraph = currentGraph;
    }

    public void performFsApsOperation() {
        if (currentGraph == null) {
            System.out.println("No graph selected.");
            return;
        }
        String fsAps = GraphFormatter.formatFsAps(currentGraph.getFS(), currentGraph.getAPS());
        JOptionPane.showMessageDialog(null, fsAps, "FS and APS", JOptionPane.INFORMATION_MESSAGE);
    }

    public void performFpAppOperation() {
        if (currentGraph == null) {
            JOptionPane.showMessageDialog(null, "No graph selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int[][] fpApp = GraphConverter.fsApsToFpApp(currentGraph.getFS(), currentGraph.getAPS());
        String fpAppFormatted = GraphFormatter.formatFpApp(fpApp);
        JOptionPane.showMessageDialog(null, fpAppFormatted, "FP and APP", JOptionPane.INFORMATION_MESSAGE);
    }

    public void performAdjMatrixOperation() {
        if (currentGraph == null) {
            JOptionPane.showMessageDialog(null, "No graph selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int[][] adjMatrix = GraphConverter.toAdjMatrix(currentGraph.getFS(), currentGraph.getAPS());
        String formattedMatrix = GraphFormatter.formatAdjMatrix(adjMatrix);
        JOptionPane.showMessageDialog(null, formattedMatrix, "Adjacency Matrix", JOptionPane.INFORMATION_MESSAGE);
    }

    public void performWeightMatrixOperation() {
        if (currentGraph == null) {
            JOptionPane.showMessageDialog(null, "No graph selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!currentGraph.isWeighted()) {
            JOptionPane.showMessageDialog(null, "The selected graph is not weighted", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        WeightedGraph weightedGraph = (WeightedGraph) currentGraph;
        double[][] weightMat = weightedGraph.getWeights();
        String formattedMatrix = GraphFormatter.formatWeightMat(weightMat);
        JOptionPane.showMessageDialog(null, formattedMatrix, "Weight Matrix", JOptionPane.INFORMATION_MESSAGE);
    }

    public void performDDIOperation() {
        if (currentGraph == null) {
            JOptionPane.showMessageDialog(null, "No graph selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int[] ddi = GraphHelpers.calculateHalfInteriorDegree(currentGraph.getFS(), currentGraph.getAPS());
        String formattedDDI = GraphFormatter.formatDDI(ddi);
        JOptionPane.showMessageDialog(null, formattedDDI, "Half Interior Degree", JOptionPane.INFORMATION_MESSAGE);
    }

    public void performDDEOperation() {
        if (currentGraph == null) {
            JOptionPane.showMessageDialog(null, "No graph selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int[] dde = GraphHelpers.calculateHalfExteriorDegree(currentGraph.getFS(), currentGraph.getAPS());
        String formattedDDE = GraphFormatter.formatDDE(dde);
        JOptionPane.showMessageDialog(null, formattedDDE, "Half Exterior Degree", JOptionPane.INFORMATION_MESSAGE);
    }

    public void performExportGraph() {
        if (currentGraph != null) {
            FileExporter exporter = new FileExporter();
            exporter.saveGraphToFile(currentGraph.getGraphData());
        } else {
            JOptionPane.showMessageDialog(null, "No graph selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
