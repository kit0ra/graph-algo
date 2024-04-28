/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author Administrator
 */

import com.google.gson.Gson;
import io.GraphData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DatabaseHandler {
    private String databasePath;

    public DatabaseHandler(String databasePath) {
        this.databasePath = databasePath;
    }

    // Establish a connection to the database
    private Connection connect() throws SQLException {
        String url = "jdbc:ucanaccess://" + this.databasePath;
        return DriverManager.getConnection(url);
    }    
       // Method to execute a query and process the ResultSet within
public <T> T executeQuery(String sql, Function<ResultSet, T> processor, int id) {
    T result = null;
    try (Connection conn = connect();
         PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setInt(1, id); // Set the ID parameter for the query
        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                result = processor.apply(rs);
            }
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return result;
}
    
     // Method to fetch a graph by its ID
    public GraphData getGraphById(int id) {
        String sql = "SELECT graph_name, graph_data FROM Graph WHERE id = ?";
        return executeQuery(sql, rs -> {
            try {
                // Assuming the graph_data is stored in JSON format in the database
                Gson gson = new Gson();
                String graphDataJson = rs.getString("graph_data");
                GraphData graphData = gson.fromJson(graphDataJson, GraphData.class);
                graphData.setName(rs.getString("graph_name")); // Set the name from the ResultSet
                return graphData;
            } catch (SQLException e) {
                System.out.println("Error processing result set: " + e.getMessage());
                return null;
            }
        }, id); // Pass the ID as a parameter to the query
    }
    
}
