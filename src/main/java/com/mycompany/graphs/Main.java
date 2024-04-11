/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.graphs;

import GraphInput.GraphInputHelper;

/**
 *
 * @author Administrator
 */
public class Main {

    public static void main(String[] args) {
       //DatabaseHandler dbHandler = new DatabaseHandler("C://Users//Administrator//Documents//graph_db.accdb");
       //GraphData data = dbHandler.getGraphById(1);
       //Graph g = GraphCreator.createGraphFromGraphData(data);
       Graph graph = GraphInputHelper.saisieGraphe();
        System.out.println(graph);
    }
}
