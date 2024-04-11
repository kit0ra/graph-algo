/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraphInput;

import com.mycompany.graphs.Graph;
import com.mycompany.graphs.WeightedGraph;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */

public class GraphInputHelper {

    public static Graph saisieGraphe() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Le graphe est-il pondéré? (oui/non): ");
        boolean isWeighted = scanner.nextLine().trim().equalsIgnoreCase("oui");

        // Common graph input code...
        System.out.print("Donner le nombre de sommets du graphe : ");
        int n = scanner.nextInt();
        int[] aps = new int[n + 1];
        aps[0] = n;

        System.out.print("Donner le nombre d'arcs du graphe : ");
        int m = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        int[] fs = new int[n + m + 1];
        fs[0] = n + m;
        int k = 0;

        int[][] p = isWeighted ? new int[n + 1][n + 1] : null;

        for (int i = 1; i <= n; i++) {
            System.out.printf("Donner le nombre de successeurs du sommet %d : ", i);
            int ns = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println("Entrer les successeurs et poids (si applicable):");

            for (int j = 1; j <= ns; j++) {
                int succ = scanner.nextInt();
                fs[++k] = succ;
                if (isWeighted) {
                    int weight = scanner.nextInt();
                    p[i][succ] = weight;
                }
            }
            fs[++k] = 0; // End marker
        }

        return isWeighted ? new WeightedGraph(fs, aps, p) : new Graph(fs, aps);
    }

}
