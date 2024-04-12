/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraphInput;

import com.mycompany.graphs.Graph;
import com.mycompany.graphs.GraphData;
import com.mycompany.graphs.WeightedGraph;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import utils.GraphCreator;

/**
 *
 * @author Administrator
 */

public class GraphInputHelper {

    public static Graph createGraphFromUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Le graphe a il des poids? (oui/non): ");
        boolean isWeighted = scanner.nextLine().trim().equalsIgnoreCase("oui");

        System.out.print("Donner le nombre de sommets du graphe : ");
        int n = scanner.nextInt();

        System.out.print("Donner le nombre d'arcs du graphe : ");
        int m = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Map<Integer, int[]> edges = new HashMap<>();
        int[][] weights = isWeighted ? new int[n + 1][n + 1] : null;

        for (int i = 1; i <= n; i++) {
            System.out.printf("Donner le nombre de successeurs du sommet %d : ", i);
            int ns = scanner.nextInt();
            int[] successors = new int[ns];

            if (isWeighted) {
                System.out.println("Entrer les successeurs et leurs poids:");
            } else {
                System.out.println("Entrer les successeurs:");
            }

            for (int j = 0; j < ns; j++) {
                int succ = scanner.nextInt();
                successors[j] = succ;

                if (isWeighted) {
                    int weight = scanner.nextInt();
                    weights[i][succ] = weight;
                }
            }

            edges.put(i, successors);
        }

        GraphData graphData = new GraphData(isWeighted, n, m, edges);
        if (isWeighted) {
            graphData.setWeights(weights);
        }

        // Utilize the GraphCreator method to create a graph based on the constructed GraphData
        return GraphCreator.createGraphFromGraphData(graphData);
    }

}
