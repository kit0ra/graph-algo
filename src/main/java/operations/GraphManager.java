/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operations;

/**
 *
 * @author Administrator
 */
import com.mycompany.graphs.Graph;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

public class GraphManager {

    private List<Graph> graphs = new ArrayList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private javax.swing.JLabel graphCountLabel;

    public GraphManager(javax.swing.JLabel graphCountLabel) {
        this.graphCountLabel = graphCountLabel;
    }

    public void addGraph(Graph graph) {
        graphs.add(graph);
        listModel.addElement(graph.getName());  // Assuming Graph has a getName() method
        updateGraphCountLabel();
    }

    public void removeGraph(String graphName) {
        graphs.removeIf(graph -> graph.getName().equals(graphName));
        listModel.removeElement(graphName);
        updateGraphCountLabel();
    }

    private void updateGraphCountLabel() {
        if (graphCountLabel != null) {
            graphCountLabel.setText(String.valueOf(graphs.size()));
        }
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    public Graph getGraph(String name) {
        return graphs.stream()
                .filter(g -> g.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<Graph> getGraphs() {
        return graphs;
    }
}
