/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.graphs.Algorithms;

/**
 *
 * @author Administrator
 */
public class Danzig {

    private int[][] finalMatrix;  // Member to store the final matrix

    public void calculateShortestPath(int[][] weightMatrix) {
        int n = weightMatrix.length - 1;  // Assuming weightMatrix[0][0] is the number of vertices
        finalMatrix = new int[n + 1][n + 1];  // Initialize the final matrix

        // Copy initial weights to finalMatrix
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                finalMatrix[i][j] = weightMatrix[i][j];
            }
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (finalMatrix[i][k] + finalMatrix[k][j] < finalMatrix[i][j]) {
                        finalMatrix[i][j] = finalMatrix[i][k] + finalMatrix[k][j];
                    }
                }
            }
        }
    }

    public void printMatrix() {
        for (int i = 1; i < finalMatrix.length; i++) {
            for (int j = 1; j < finalMatrix[i].length; j++) {
                System.out.print((finalMatrix[i][j] == (Integer.MAX_VALUE / 2) ? "infinite" : finalMatrix[i][j])+" ");
            }
            System.out.println();
        }
    }
}
