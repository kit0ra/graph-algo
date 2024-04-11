/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.graphs.Algorithms;

import java.util.Arrays;

/**
 *
 * @author Administrator
 */
public class GraphTraversal {
    public static int[] descLarge1(int[] fs, int[] aps) {
        int nb_som = aps[0];
        int[] dist = new int[nb_som + 1]; // Initialize distance array within the method
        Arrays.fill(dist, -1); // Fill with -1 to indicate unvisited nodes
        dist[0] = nb_som; // Store the number of vertices as the first element for consistency
        
        int[] fil = new int[nb_som + 1];
        fil[0] = nb_som; // Similarly store the number of vertices
        fil[1] = 1; // Assuming the traversal starts from node 1
        dist[1] = 0; // Distance to itself is 0
        
        int i = 0, j = 1, k = 0, ifin, s, t, it;
        
        while (i < j) { // While the queue is not empty
            k++;
            ifin = j;
            while (i < ifin) { // Traverse the current block
                i++;
                s = fil[i];
                it = aps[s];
                t = fs[it];
                while (t > 0) { // Traverse all successors of each vertex in the current block
                    if (dist[t] == -1) { // If the successor of the current vertex is not yet marked
                        j++;
                        fil[j] = t; // Place it in the next block in preparation and assign the current distance
                        dist[t] = k; // Variant: dist[t] = dist[fil[i]] + 1;
                    }
                    t = fs[++it];
                }
            }
        }
        
        return dist; // Return the distance array
    }
}
