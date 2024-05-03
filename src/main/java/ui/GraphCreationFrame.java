/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author Administrator
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Administrator
 */
import graphs.Graph;
import graphs.WeightedGraph;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GraphCreationFrame extends JFrame {

    private JTextField graphNameField;
    private JCheckBox orientedCheckBox;
    private JCheckBox weightedCheckBox;
    private JTextField verticesField;
    private JTextField edgesField;
    private JButton createTableButton;
    private JButton addRowButton; // Button to add rows manually
    private JButton finalizeGraphButton;
    private JTable adjacencyMatrixTable;
    private DefaultTableModel model;

    public GraphCreationFrame() {
        setTitle("Create New Graph");
        setSize(600, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel for graph properties
        JPanel propertiesPanel = new JPanel();
        propertiesPanel.setLayout(new BoxLayout(propertiesPanel, BoxLayout.Y_AXIS));

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        graphNameField = new JTextField(20);
        namePanel.add(new JLabel("Graph Name:"));
        namePanel.add(graphNameField);

        JPanel checkboxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        orientedCheckBox = new JCheckBox("Oriented");
        weightedCheckBox = new JCheckBox("Weighted");
        checkboxPanel.add(new JLabel("Properties:"));
        checkboxPanel.add(orientedCheckBox);
        checkboxPanel.add(weightedCheckBox);

        JPanel vertexEdgePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        verticesField = new JTextField(5);
        edgesField = new JTextField(5);
        vertexEdgePanel.add(new JLabel("Vertices:"));
        vertexEdgePanel.add(verticesField);
        vertexEdgePanel.add(new JLabel("Edges:"));
        vertexEdgePanel.add(edgesField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        createTableButton = new JButton("Create Table");
        addRowButton = new JButton("Add Row");
        finalizeGraphButton = new JButton("Finalize Graph");
        buttonPanel.add(createTableButton);
        buttonPanel.add(addRowButton);
        buttonPanel.add(finalizeGraphButton);

        propertiesPanel.add(namePanel);
        propertiesPanel.add(checkboxPanel);
        propertiesPanel.add(vertexEdgePanel);
        propertiesPanel.add(buttonPanel);

        add(propertiesPanel, BorderLayout.NORTH);

        // Table for adjacency matrix
        model = new DefaultTableModel();
        adjacencyMatrixTable = new JTable(model);
        add(new JScrollPane(adjacencyMatrixTable), BorderLayout.CENTER);

        setupActions();
    }

    private void setupActions() {
        createTableButton.addActionListener(this::initializeTable);
        addRowButton.addActionListener(this::addEmptyRow); // Listener for adding rows
        finalizeGraphButton.addActionListener(this::finalizeGraph);
    }

    private void initializeTable(ActionEvent e) {
        try {
            int vertices = Integer.parseInt(verticesField.getText().trim());
            int edges = Integer.parseInt(edgesField.getText().trim());
            boolean weighted = weightedCheckBox.isSelected();

            // Define table columns
            String[] columns = weighted ? new String[]{"Vertex", "Successor", "Weight"} : new String[]{"Vertex", "Successor"};
            model.setColumnIdentifiers(columns);
            model.setRowCount(vertices + edges); // Initialize with rows for vertices and edges
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for vertices and edges.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addEmptyRow(ActionEvent e) {
        boolean weighted = weightedCheckBox.isSelected();
        model.addRow(new Object[]{null, null, weighted ? "" : null}); // Add an empty row for further input
    }

    private void finalizeGraph(ActionEvent e) {
        try {
            String name = graphNameField.getText();
            boolean isOriented = orientedCheckBox.isSelected();
            boolean isWeighted = weightedCheckBox.isSelected();
            int vertices = Integer.parseInt(verticesField.getText());
            int edges = Integer.parseInt(edgesField.getText());

            int[] fs = new int[vertices + edges + 1]; // including space for storing total vertices and edges at the start
            int[] aps = new int[vertices + 1];        // including space for storing the start address of the first successor

            Graph graph;
            if (isWeighted) {
                double[][] weights = new double[vertices][vertices]; // example initialization
                graph = new WeightedGraph(fs, aps, weights);
            } else {
                graph = new Graph(fs, aps);
            }

            graph.setName(name);
            graph.setType(isOriented);

            System.out.println("Adding graph to manager: " + graph);

            // Add the graph to the manager via MainFrame method
            MainFrame.addGraphToManager(graph);

            JOptionPane.showMessageDialog(this, "Graph creation finalized and added.", "Graph Finalization", JOptionPane.INFORMATION_MESSAGE);

            this.dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error parsing numbers. Please check your input.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error finalizing the graph: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GraphCreationFrame().setVisible(true));
    }
}
