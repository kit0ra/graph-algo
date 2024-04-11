/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.graphs;

import com.mycompany.graphs.Algorithms.GraphTraversal;
import com.mycompany.graphs.database.DatabaseHandler;
import java.util.ArrayList;
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
        DatabaseHandler dbHandler = new DatabaseHandler("C://Users//Administrator//Documents//graph_db.accdb");
       
        GraphData data = dbHandler.getGraphById(1);
        
       Graph g = GraphCreator.createGraphFromGraphData(data);
       //System.out.println(g);
       int [][] adjMatrix = GraphConverter.toAdjMatrix(g.getFS(), g.getAPS());
       //GraphPrinter.adjMatrixPrinter(adjMatrix);
       
       //convert back to fs_aps
       List<List<Integer>> result = GraphConverter.adjMatrixToFsAps(adjMatrix);
       //GraphPrinter.fsApsPrinter(result.get(0), result.get(1));
       
       //convert to fp app
       List<Integer> fsList = new ArrayList<>(Arrays.asList(14,2,5,0,3,0,3,4,6,0,6,0,3,0,0));
       List<Integer> apsList = new ArrayList<>(Arrays.asList(6,1,4,6,10,12,14));
       
         // Convert fsList to fs array
         int[] fs = fsList.stream().mapToInt(Integer::intValue).toArray();

         // Convert apsList to aps array
         int[] aps = apsList.stream().mapToInt(Integer::intValue).toArray();
         //GraphPrinter.fsApsPrinter(fs, aps);
        
         
         
         int []dist = GraphTraversal.descLarge1(fs, aps);
         System.out.println("distance array elements:");
         for(int i = 0; i < dist.length; i++) {
            System.out.println("distance[" + i + "] = " + dist[i]);
         }
       //List<List<Integer>> result2 = GraphConverter.fsApsToFpApp(g.getFS(), g.getAPS());
         }
}
