/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.graphs;

import GraphInput.GraphInputHelper;
import com.mycompany.graphs.Algorithms.GraphRank;
import com.mycompany.graphs.Algorithms.GraphTraversal;
import graphio.GraphData;
import graphio.GraphDataParser;
import utils.GraphConverter;
import utils.GraphCreator;
import utils.GraphPrinter;

/**
 *
 * @author Administrator
 */
public class Main {

    public static void main(String[] args) {
       //DatabaseHandler dbHandler = new DatabaseHandler("C://Users//Administrator//Documents//graph_db.accdb");
       //GraphData data = dbHandler.getGraphById(1);
       //Graph g = GraphCreator.createGraphFromGraphData(data);
        String jsonDataWeighted = """
            {
              "name": "WeightedGraphExample",
              "weighted": true,
              "oriented": true,
              "data": {
                "vertices": 3,
                "edges": {
                  "1": {"2": 10, "3": 20},
                  "2": {"3": 5},
                  "3": {}
                }
              }
            }
            """;

        String jsonDataUnweighted = """
            {
              "name": "UnweightedGraphExample",
              "weighted": false,
              "oriented": true,
              "data": {
                "vertices": 3,
                "edges": {
                  "1": {"2": null, "3": null},
                  "2": {"3": null},
                  "3": {}
                }
              }
            }
            """;

       //Graph graph = GraphInputHelper.createGraphFromUserInput();
       
        //Graph g = GraphCreator.createGraphFromGraphData(data);
        //System.out.println(graph);
        try {
            // Test the weighted graph parsing
            GraphData graphDataWeighted = GraphDataParser.parseGraphData(jsonDataWeighted);

            // Test the unweighted graph parsing
            GraphData graphDataUnweighted = GraphDataParser.parseGraphData(jsonDataUnweighted);
           
            Graph g = GraphCreator.createGraphFromGraphData(graphDataUnweighted);
            int [][]m = GraphConverter.toAdjMatrix(g.getFS(), g.getAPS());
            int [][]r=GraphConverter.adjMatrixToFsAps(m);
            GraphPrinter.fsApsPrinter(r[0], r[1]);
        } catch (Exception e) {
            System.out.println("Error parsing graph data: " + e.getMessage());
            e.printStackTrace();
        }
        
    }
}
