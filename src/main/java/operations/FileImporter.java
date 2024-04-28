/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations;

import algorithms.Dijkstra;
import graphs.Graph;
import graphs.WeightedGraph;
import io.GraphData;
import io.GraphDataParser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import utilities.GraphCreator;

/**
 *
 * @author Administrator
 */
public class FileImporter {
    public static Graph importJsonFile(String jsonContent) throws Exception {
        // Parse and create the graph from JSON content
        GraphData data = GraphDataParser.parseGraphData(jsonContent);
        Graph graph = GraphCreator.createGraphFromGraphData(data);
        graph.setName(data.getName());  // Assume Graph has a setName method
        graph.setType(data.isOriented());
        System.out.println("Graph imported successfully: " + graph.getName());
        return graph;
    }

    public static String selectJsonFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a JSON file");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON Files", "json"));

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                return new String(Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath())));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error reading the file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;  // Return null if the operation is cancelled or fails
    }
}