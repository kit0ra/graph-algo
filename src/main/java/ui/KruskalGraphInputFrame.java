/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author Administrator
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import algorithms.Kruskal;
import helpers.KruskalEdge;
import helpers.KruskalGraph;
import java.util.Arrays;

public class KruskalGraphInputFrame extends JFrame {

    private JTextField numberOfVerticesField;
    private JTextField numberOfEdgesField;
    private JButton createTableButton;
    private JButton calculateMSTButton;
    private JTable edgeTable;
    private DefaultTableModel edgeTableModel;

    public KruskalGraphInputFrame() {
        super("Enter Kruskal Graph Details");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        numberOfVerticesField = new JTextField();
        numberOfEdgesField = new JTextField();
        createTableButton = new JButton("Create Edge Table");
        calculateMSTButton = new JButton("Calculate MST");

        inputPanel.add(new JLabel("Number of Vertices:"));
        inputPanel.add(numberOfVerticesField);
        inputPanel.add(new JLabel("Number of Edges:"));
        inputPanel.add(numberOfEdgesField);
        inputPanel.add(createTableButton);
        inputPanel.add(calculateMSTButton);

        edgeTableModel = new DefaultTableModel(new Object[]{"Start Vertex", "End Vertex", "Weight"}, 0);
        edgeTable = new JTable(edgeTableModel);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(edgeTable), BorderLayout.CENTER);

        setupListeners();
    }

    private void setupListeners() {
        createTableButton.addActionListener(e -> initializeEdgeTable());
        calculateMSTButton.addActionListener(e -> createKruskalGraph());
    }

    private void initializeEdgeTable() {
        try {
            int numberOfEdges = Integer.parseInt(numberOfEdgesField.getText().trim());
            edgeTableModel.setRowCount(0); // Clear existing rows
            for (int i = 0; i < numberOfEdges; i++) {
                edgeTableModel.addRow(new Object[]{null, null, null}); // Add empty rows based on number of edges
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for edges.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createKruskalGraph() {
        try {
            int numberOfVertices = Integer.parseInt(numberOfVerticesField.getText().trim());
            KruskalGraph graph = new KruskalGraph(numberOfVertices, edgeTableModel.getRowCount());

            for (int i = 0; i < edgeTableModel.getRowCount(); i++) {
                int startVertex = Integer.parseInt(edgeTableModel.getValueAt(i, 0).toString());
                int endVertex = Integer.parseInt(edgeTableModel.getValueAt(i, 1).toString());
                double weight = Double.parseDouble(edgeTableModel.getValueAt(i, 2).toString());
                graph.addEdge(new KruskalEdge(startVertex, endVertex, weight));
            }

            graph.sortEdges(); // Sort edges before MST calculation
            Kruskal kruskal = new Kruskal(graph); // Assume this class is correctly implemented
            kruskal.calculateMST(); // Calculate MST and update graph

            displayKruskalGraph(kruskal);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error parsing table data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayKruskalGraph(Kruskal kruskal) {
        // Assuming Kruskal class has methods to get the properties you mentioned
        StringBuilder displayText = new StringBuilder("<html>Kruskal Results:<br>");
        displayText.append("CFC: ").append(Arrays.toString(kruskal.getCfc())).append("<br>");
        displayText.append("Prem: ").append(Arrays.toString(kruskal.getPrem())).append("<br>");
        displayText.append("Pilch: ").append(Arrays.toString(kruskal.getPilch())).append("<br>");
        displayText.append("NB: ").append(Arrays.toString(kruskal.getNb())).append("<br>");
        displayText.append("<br>MST Edges:<br>");

        // Fetch the MST edges and format them
        KruskalGraph mst = kruskal.getMst();
        for (KruskalEdge edge : mst.getEdges()) {
            displayText.append(edge.toString()).append("<br>");
        }
        displayText.append("</html>");

        JOptionPane.showMessageDialog(this, displayText.toString(), "MST Results", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new KruskalGraphInputFrame().setVisible(true);
        });
    }
}
