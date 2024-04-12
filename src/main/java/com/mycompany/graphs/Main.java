/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.graphs;

import GraphInput.GraphInputHelper;
import utils.GraphCreator;

/**
 *
 * @author Administrator
 */
public class Main {

    public static void main(String[] args) {
       //DatabaseHandler dbHandler = new DatabaseHandler("C://Users//Administrator//Documents//graph_db.accdb");
       //GraphData data = dbHandler.getGraphById(1);
       //Graph g = GraphCreator.createGraphFromGraphData(data);
       String dataString = """
                     {
                       "name": "UnweightedGraph1",
                       "weighted": false,
                       "data": {
                         "vertices": 5,
                         "edges": {
                           "1": [2, 3],
                           "2": [3, 4, 5],
                           "3": [2, 5],
                           "4": [],
                           "5": [3]
                         }
                       }
                     }
                     """;
       //Graph graph = GraphInputHelper.createGraphFromUserInput();
       
       Graph g = GraphCreator.createGraphFromGraphData(data);
        System.out.println(graph);
    }
}
