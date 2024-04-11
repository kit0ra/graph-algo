/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.graphs;

import static com.mycompany.graphs.Algorithms.GraphRank.calculateRang;
import com.mycompany.graphs.database.DatabaseHandler;
import utils.GraphConverter;
import utils.GraphCreator;
import utils.GraphHelpers;
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
       // System.out.println(g);
       
       int [][] adjMatrix = GraphConverter.toAdjMatrix(g.getFS(), g.getAPS());
       //GraphPrinter.adjMatrixPrinter(adjMatrix);
       
       //convert back to fs_aps
       int[][] result = GraphConverter.adjMatrixToFsAps(adjMatrix);
       //GraphPrinter.fsApsPrinter(result[0], result[1]);
       
       int [] ddi = GraphHelpers.calculateHalfInteriorDegree(g.getFS(), g.getAPS());
       
       int [][]result2 = GraphConverter.fsApsToFpApp(g.getFS(), g.getAPS());
       //GraphPrinter.fpAppPrinter(result2[0], result2[1]);
        
       //calculer le rang
        int[] fs = {31, 2, 3, 0, 4, 9, 0, 4, 6, 8, 0, 8, 9, 11, 0, 3, 6, 0, 7, 8, 0, 8, 0, 10, 0, 0, 11, 0, 10, 12, 0, 0};
        int[] aps = {12, 1, 4, 7, 11, 15, 18, 21, 23, 25, 26, 28, 31};
        int[] rang = calculateRang(fs, aps);
        GraphPrinter.printRank(rang);
    }
}
