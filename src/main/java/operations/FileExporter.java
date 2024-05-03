/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations;

import io.GraphData;
import io.GraphDataWriter;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class FileExporter {

    /**
     * Prompts the user to select a file name and location, and writes the graph
     * data to the selected file.
     *
     * @param graphData The graph data to be written.
     */
    public void saveGraphToFile(GraphData graphData) {
        if (graphData == null) {
            JOptionPane.showMessageDialog(null, "No graph data to save.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Graph");
        fileChooser.setApproveButtonText("Save");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JSON Files", "json"));

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            // Ensure the file has the correct extension
            if (!fileToSave.getAbsolutePath().endsWith(".json")) {
                fileToSave = new File(fileToSave + ".json");
            }
            writeGraphToFile(graphData, fileToSave.getAbsolutePath());
        }
    }

    /**
     * Writes the provided graph data to a file at the specified path.
     *
     * @param graphData The graph data to be written.
     * @param filePath The file path where the graph data will be saved.
     */
    /**
     * Writes the provided graph data to a file at the specified path.
     *
     * @param graphData The graph data to be written.
     * @param filePath The file path where the graph data will be saved.
     */
    private void writeGraphToFile(GraphData graphData, String filePath) {
        GraphDataWriter dataWriter = new GraphDataWriter();
        String jsonData = dataWriter.serializeGraph(graphData);

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(jsonData);  // Write the JSON string to file
            JOptionPane.showMessageDialog(null, "Graph saved successfully to " + filePath, "Export Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving the graph: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}
