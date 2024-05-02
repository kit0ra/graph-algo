/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorithms;

import utilities.GraphHelpers;

/**
 *
 * @author Administrator
 */
public class Rank {
     private static void empiler(int s, int[] pilch) {
        pilch[s] = pilch[0];
        pilch[0] = s;
    }

    public static int[] calculateRank(int[] fs, int[] aps) {
        int n = aps[0], s, k, h, t;
        int[] rang = new int[n + 1];
        int[] pilch = new int[n + 1];
        int[] prem = new int[n + 1];

        // Calcul de ddi (Degree)
        int[] ddi = GraphHelpers.calculateHalfInteriorDegree(fs, aps);

        // Initialisation
        for (int i = 1; i <= n; i++) {
            rang[i] = -1; // Initialize rang with -1
            if (ddi[i] == 0) empiler(i, pilch);
        }

        k = -1;
        s = pilch[0];
        prem[0] = s;
        while (pilch[0] > 0) {
            k++;
            pilch[0] = 0;
            while (s > 0) {
                rang[s] = k;
                h = aps[s];
                t = fs[h];
                while (t > 0) {
                    ddi[t]--;
                    if (ddi[t] == 0) empiler(t, pilch);
                    h++;
                    t = fs[h];
                }
                s = pilch[s];
            }
            s = pilch[0];
            prem[k + 1] = s;
        }

        return rang;
    }
}
