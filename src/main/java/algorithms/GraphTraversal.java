/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorithms;

import java.util.Arrays;

/**
 *
 * @author Administrator
 */
public class GraphTraversal {
    public static int[] distance(int[] fs, int[] aps) {
        int nb_som = aps[0];
        int[] dist = new int[nb_som + 1]; 
        Arrays.fill(dist, -1); 
        dist[0] = nb_som; 
        
        int[] fil = new int[nb_som + 1];
        fil[0] = nb_som; 
        fil[1] = 1;
        dist[1] = 0; 
        
        int i = 0, j = 1, k = 0, ifin, s, t, it;
        
        while (i < j) { 
            k++;
            ifin = j;
            while (i < ifin) { 
                i++;
                s = fil[i];
                it = aps[s];
                t = fs[it];
                while (t > 0) { 
                    if (dist[t] == -1) { 
                        j++;
                        fil[j] = t; 
                        dist[t] = k; 
                    }
                    t = fs[++it];
                }
            }
        }
        
        return dist; 
    }
}
