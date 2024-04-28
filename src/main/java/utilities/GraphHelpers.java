/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.util.List;

/**
 *
 * @author Administrator
 */
public class GraphHelpers {

    //Half Interior Degree(dde)
    public static int[] calculateHalfInteriorDegree(int[] fs, int[] aps) {
        int vertices = aps[0];
        int total = fs[0];

        int[] ddi = new int[vertices + 1]; // Arrays are initialized with 0 by default

        for (int i = 1; i <= total; i++) {
            ddi[fs[i]]++; // Increment the degree
        }

        return ddi;
    }

    //half exterior degree (dde)
    public static int[] calculateHalfExteriorDegree(int[] fs, int[] aps) {
        int vertices = aps[0];
        int total = fs[0];

        int[][] result = GraphConverter.fsApsToFpApp(fs, aps);
        int[] fp = result[0];

        int[] dde = new int[vertices + 1];

        for (int i = 1; i <= total; i++) {
            dde[fp[i]]++; // Increment the degree
        }

        return dde;
    }

}
