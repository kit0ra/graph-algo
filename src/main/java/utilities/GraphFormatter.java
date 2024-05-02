/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

/**
 *
 * @author Administrator
 */
public class GraphFormatter {
    // Utility method to format FS and APS arrays into a display string

    public static String formatFsAps(int[] fs, int[] aps) {
        StringBuilder builder = new StringBuilder();
        builder.append("FS: ");
        for (int f : fs) {
            builder.append(f).append(" ");
        }
        builder.append("\nAPS: ");
        for (int a : aps) {
            builder.append(a).append(" ");
        }
        return builder.toString();
    }

    public static String formatFpApp(int[][] fpApp) {
        StringBuilder builder = new StringBuilder("<html><body>");
        builder.append("<p>FP: ");
        for (int i = 0; i < fpApp[0].length; i++) {
            builder.append(fpApp[0][i]).append(" ");
        }
        builder.append("</p><p>APP: ");
        for (int i = 0; i < fpApp[1].length; i++) {
            builder.append(fpApp[1][i]).append(" ");
        }
        builder.append("</p></body></html>");
        return builder.toString();
    }

    public static String formatAdjMatrix(int[][] matrix) {
        StringBuilder builder = new StringBuilder("<html><body><table>");
        for (int i = 1; i < matrix.length; i++) {
            builder.append("<tr>");
            for (int j = 1; j < matrix[i].length; j++) {
                builder.append("<td style='border: 1px solid black; padding: 5px;'>").append(matrix[i][j]).append("</td>");
            }
            builder.append("</tr>");
        }
        builder.append("</table></body></html>");
        return builder.toString();
    }

    public static String formatWeightMat(double[][] matrix) {
        StringBuilder builder = new StringBuilder("<html><body><table>");
        for (int i = 1; i < matrix.length; i++) {
            builder.append("<tr>");
            for (int j = 1; j < matrix[i].length; j++) {
                builder.append("<td style='border: 1px solid black; padding: 5px;'>").append(matrix[i][j]).append("</td>");
            }
            builder.append("</tr>");
        }
        builder.append("</table></body></html>");
        return builder.toString();
    }

    public static String formatDDI(int[] ddi) {
        StringBuilder builder = new StringBuilder("<html><body><p>DDI: ");
        for (int value : ddi) {
            builder.append(value).append(" ");
        }
        builder.append("</p></body></html>");
        return builder.toString();
    }

    public static String formatDDE(int[] dde) {
        StringBuilder builder = new StringBuilder("<html><body><p>DDI: ");
        for (int value : dde) {
            builder.append(value).append(" ");
        }
        builder.append("</p></body></html>");
        return builder.toString();
    }

    public static String formatPruferCode(int[] pruferCode) {
        // Check if the Prufer code array is empty or has only one element which you don't want to process
        if (pruferCode.length <= 1) {
            return ""; // Return an empty string if there's nothing to format
        }

        StringBuilder builder = new StringBuilder();
        // Start from index 1 to skip the first element
        for (int i = 1; i < pruferCode.length; i++) {
            builder.append(pruferCode[i]).append(", ");
        }
        // Remove the last ", " from the string
        return builder.length() > 0 ? builder.substring(0, builder.length() - 2) : "";
    }

}
