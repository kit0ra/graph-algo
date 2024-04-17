/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.graphs;

import GraphInput.GraphInputHelper;
import com.mycompany.graphs.Algorithms.Dijkstra;
import com.mycompany.graphs.Algorithms.GraphRank;
import com.mycompany.graphs.Algorithms.GraphTraversal;
import com.mycompany.graphs.Algorithms.Prufer;
import com.mycompany.graphs.Algorithms.Tarjan;
import graphio.GraphData;
import graphio.GraphDataParser;
import java.util.Arrays;
import java.util.List;
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
        /*try {
            // Test the weighted graph parsing
            GraphData graphDataWeighted = GraphDataParser.parseGraphData(jsonDataWeighted);
            //System.out.println(graphDataWeighted);
           
            Graph g = GraphCreator.createGraphFromGraphData(graphDataWeighted);
            //System.out.println(g);
            
            int[][]djikestra = Dijkstra.dijkstra(g, 1);
            GraphPrinter.printDijkstraResult(djikestra);
            
        } catch (Exception e) {
            System.out.println("Error parsing graph data: " + e.getMessage());
            e.printStackTrace();
        }*/
        
        int[] fs = {
            // Number of vertices + number of arcs
            14, // 8 vertices + 6 arcs
            // Successors of each vertex followed by a 0
            2, 6, 0, // Successors of vertex 1
            3, 0, // Successors of vertex 2
            4, 0, // Successors of vertex 3
            5, 0, // Successors of vertex 4
            3, 0, // Successors of vertex 5
            7, 0, // Successors of vertex 6
            3, 8, 0, // Successors of vertex 7
            7, 0 // Successors of vertex 8
        };

        int[] aps = {
            // Number of vertices
            8,
            // Indices where each vertex's list starts
            1, // Vertex 1
            4, // Vertex 2
            6, // Vertex 3
            8, // Vertex 4
            10, // Vertex 5
            12, // Vertex 6
            14, // Vertex 7
            17 // Vertex 8
        };

        
        /*Graph graph = new Graph(fs,aps);
        //System.out.println(graph);
        Tarjan tarjan = new Tarjan(graph);
        List<List<Integer>> a = tarjan.findStronglyConnectedComponents();
 
        //System.out.println(graph);
        tarjan.findStronglyConnectedComponents();
        //tarjan.populateCFC();  // Populate cfc correctly
        tarjan.createReducedGraph();  // Create the reduced graph
        GraphPrinter.printTarjanResults(tarjan);*/
        
        int [][] adj = new int [8+1][8+1];
        adj[0][0] = 8;
        adj[1][0] = 8+7;
        adj[1][2]=1; adj[1][3]=1; adj[1][8]=1; //1
        adj[2][1]=1;  adj[2][4]=1;  adj[2][5]=1; //2
        adj[3][1]=1; //3
        adj[4][2]=1;//4
        adj[5][2]=1; adj[5][6]=1; adj[5][7]=1; //5
        adj[6][5]=1; //6
        adj[7][5]=1; //7
        adj[8][1]=1; //8
        
        /*int [] prufer = Prufer.generatePruferCode(adj);
        GraphPrinter.displayPruferCode(prufer);
        int [][] decode = Prufer.decodePruferCode(prufer);
        GraphPrinter.adjMatrixPrinter(decode);
        System.out.println(decode[0][0]);
        System.out.println(decode[1][0]);
        GraphPrinter.displayTreeConnections(decode);*/
    }
}
