/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Administrator
 */
public class GraphPrinter {

    public static void adjMatrixPrinter(int[][] matrix) {
        for (int[] row : matrix) {
            for (int j = 1; j < row.length; j++) {
                System.out.print(row[j] + " ");
            }
            System.out.println();
        }
    }

    public static void fsApsPrinter(int[] fs, int[] aps) {
        String fsString = arrayToString(fs);
        String apsString = arrayToString(aps);
        System.out.println("FS: " + fsString + "\nAPS: " + apsString);
    }
    
      public static void fpAppPrinter(int[] fp, int[] app) {
        String fpString = arrayToString(fp);
        String appString = arrayToString(app);
        System.out.println("FP: " + fpString + "\nAPP: " + appString);
    }

    public static String formatFsAps(int[] fs, int[] aps) {
        return "FS: " + arrayToString(fs) + "\nAPS: " + arrayToString(aps);
    }

    // Helper method to convert an array to a string format similar to List.toString()
    private static String arrayToString(int[] array) {
        if (array == null || array.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

