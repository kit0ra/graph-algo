/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.graphs;


import com.mycompany.graphs.Algorithms.ScheduleManager;
import hepers.Task;
import hepers.TaskManager;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Administrator
 */
public class Main {

    public static void main(String[] args) {
        //DatabaseHandler dbHandler = new DatabaseHandler("C://Users//Administrator//Documents//graph_db.accdb");
        //GraphData data = dbHandler.getGraphById(1);

        String jsonDataWeighted = """
            {
              "name": "WeightedGraphExample",
              "weighted": true,
              "oriented": true,
              "data": {
                "vertices": 3,
                "edges": {
                  "1": {"2": 10, "3": 20},
                  "2": {"3": 5},
                  "3": {}
                }
              }
            }
            """;

        String jsonDataUnweighted = """
            {
              "name": "UnweightedGraphExample",
              "weighted": false,
              "oriented": true,
              "data": {
                "vertices": 3,
                "edges": {
                  "1": {"2": null, "3": null},
                  "2": {"3": null},
                  "3": {}
                }
              }
            }
            """;

        //Graph graph = GraphInputHelper.createGraphFromUserInput();
        //Graph g = GraphCreator.createGraphFromGraphData(data);
        //System.out.println(graph);
        /*try {
            // Test the weighted graph parsing
            GraphData graphDataWeighted = GraphDataParser.parseGraphData(jsonDataWeighted);
            //System.out.println(graphDataWeighted);
           
            Graph g = GraphCreator.createGraphFromGraphData(graphDataWeighted);
            //System.out.println(g);
            
            int[][]djikestra = Dijkstra.dijkstra(g, 1);
            GraphPrinter.printDijkstraResult(djikestra);
            
        } catch (Exception e) {
            System.out.println("Error parsing graph data: " + e.getMessage());
            e.printStackTrace();
        }*/
        int[] fs = {
            // Number of vertices + number of arcs
            14, // 8 vertices + 6 arcs
            // Successors of each vertex followed by a 0
            2, 6, 0, // Successors of vertex 1
            3, 0, // Successors of vertex 2
            4, 0, // Successors of vertex 3
            5, 0, // Successors of vertex 4
            3, 0, // Successors of vertex 5
            7, 0, // Successors of vertex 6
            3, 8, 0, // Successors of vertex 7
            7, 0 // Successors of vertex 8
        };

        int[] aps = {
            // Number of vertices
            8,
            // Indices where each vertex's list starts
            1, // Vertex 1
            4, // Vertex 2
            6, // Vertex 3
            8, // Vertex 4
            10, // Vertex 5
            12, // Vertex 6
            14, // Vertex 7
            17 // Vertex 8
        };

        /*Graph graph = new Graph(fs,aps);
        //System.out.println(graph);
        Tarjan tarjan = new Tarjan(graph);
        List<List<Integer>> a = tarjan.findStronglyConnectedComponents();
 
        //System.out.println(graph);
        tarjan.findStronglyConnectedComponents();
        //tarjan.populateCFC();  // Populate cfc correctly
        tarjan.createReducedGraph();  // Create the reduced graph
        GraphPrinter.printTarjanResults(tarjan);*/
        int[][] adj = new int[8 + 1][8 + 1];
        adj[0][0] = 8;
        adj[1][0] = 8 + 7;
        adj[1][2] = 1;
        adj[1][3] = 1;
        adj[1][8] = 1; //1
        adj[2][1] = 1;
        adj[2][4] = 1;
        adj[2][5] = 1; //2
        adj[3][1] = 1; //3
        adj[4][2] = 1;//4
        adj[5][2] = 1;
        adj[5][6] = 1;
        adj[5][7] = 1; //5
        adj[6][5] = 1; //6
        adj[7][5] = 1; //7
        adj[8][1] = 1; //8

        /*int [] prufer = Prufer.generatePruferCode(adj);
        GraphPrinter.displayPruferCode(prufer);
        int [][] decode = Prufer.decodePruferCode(prufer);
        GraphPrinter.adjMatrixPrinter(decode);
        System.out.println(decode[0][0]);
        System.out.println(decode[1][0]);
        GraphPrinter.displayTreeConnections(decode);*/
 /*ScheduleManager manager = new ScheduleManager();
        
        // Mapping of task names to numeric indices
        Map<String, Integer> taskIndices = new HashMap<>();
        taskIndices.put("A", 1);
        taskIndices.put("B", 2);
        taskIndices.put("C", 3);
        taskIndices.put("D", 4);
        taskIndices.put("E", 5);
        taskIndices.put("F", 6);
        taskIndices.put("G", 7);
        taskIndices.put("H", 8);
        taskIndices.put("I", 9);
        taskIndices.put("J", 10);
        taskIndices.put("K", 11);

        
        manager.addTask("A", 7, new ArrayList<>()); // Task A has no predecessors
        manager.addTask("B", 4, Collections.singletonList(taskIndices.get("A"))); // Task B depends on A
        manager.addTask("C", 2, new ArrayList<>()); // Task C has no predecessors
        manager.addTask("D", 6, Arrays.asList(taskIndices.get("A"), taskIndices.get("C"))); // Task D depends on A and C
        manager.addTask("E", 2, Collections.singletonList(taskIndices.get("B"))); // Task E depends on B
        manager.addTask("F", 1, Arrays.asList(taskIndices.get("C"), taskIndices.get("D"), taskIndices.get("E"))); // Task F depends on C, D, E
        manager.addTask("G", 1, Arrays.asList(taskIndices.get("D"), taskIndices.get("E"))); // Task G depends on D, E
        manager.addTask("H", 1, Collections.singletonList(taskIndices.get("F"))); // Task H depends on F
        manager.addTask("I", 4, Arrays.asList(taskIndices.get("D"), taskIndices.get("E"))); // Task I depends on D, E
        manager.addTask("J", 2, Collections.singletonList(taskIndices.get("H"))); // Task J depends on H
        manager.addTask("K", 1, Arrays.asList(taskIndices.get("G"), taskIndices.get("I"), taskIndices.get("J"))); // Task K depends on G, I, J

        // Perform calculations and display the schedule
        //manager.calculateCriticalPath();
        manager.displaySchedule();
        manager.createFsAndAps();
        manager.convertToFpApp();
        //int[] fss = new int[] {27, 0, 2, 0, 0, 2, 4, 0, 3, 0, 4, 5, 6, 0, 5, 6, 0, 7, 0, 5, 6, 0, 9, 0, 8, 10, 11, 0};
       // int[] apss = new int[] {11, 1, 2, 4, 5, 8, 10, 14, 17, 19, 22, 24};
        //int rank[] = GraphRank.calculateRank(fss, apss);
       //GraphPrinter.printRank(rank);
    }*/
        TaskManager manager = new TaskManager();

// DT task with no predecessors, index 0
        Task dt = new Task("DT", 0, new ArrayList<>(), 0);
        manager.addTask(dt);

// Task A with no predecessors (assumed to start after DT), index 1
        List<Task> predsForA = new ArrayList<>();
        predsForA.add(dt);
        Task a = new Task("A", 7, predsForA, 1);
        manager.addTask(a);

        // Task B depends on A, index 2
        List<Task> predsForB = new ArrayList<>();
        predsForB.add(manager.getTaskByName("A"));
        Task b = new Task("B", 4, predsForB, 2);
        manager.addTask(b);

        // Task C with no predecessors, index 3
        List<Task> predsForC = new ArrayList<>();
        predsForC.add(dt);
        Task c = new Task("C", 2, predsForC, 3);
        manager.addTask(c);

// Task D depends on A and C, index 4
        List<Task> predsForD = new ArrayList<>();
        predsForD.add(manager.getTaskByName("A"));
        predsForD.add(manager.getTaskByName("C"));
        Task d = new Task("D", 6, predsForD, 4);
        manager.addTask(d);

// Task E depends on B, index 5
        List<Task> predsForE = new ArrayList<>();
        predsForE.add(manager.getTaskByName("B"));
        Task e = new Task("E", 2, predsForE, 5);
        manager.addTask(e);

// Task F depends on C, D, E, index 6
        List<Task> predsForF = new ArrayList<>();
        predsForF.add(manager.getTaskByName("C"));
        predsForF.add(manager.getTaskByName("D"));
        predsForF.add(manager.getTaskByName("E"));
        Task f = new Task("F", 1, predsForF, 6);
        manager.addTask(f);

// Task G depends on D, E, index 7
        List<Task> predsForG = new ArrayList<>();
        predsForG.add(manager.getTaskByName("D"));
        predsForG.add(manager.getTaskByName("E"));
        Task g = new Task("G", 1, predsForG, 7);
        manager.addTask(g);

// Task H depends on F, index 8
        List<Task> predsForH = new ArrayList<>();
        predsForH.add(manager.getTaskByName("F"));
        Task h = new Task("H", 1, predsForH, 8);
        manager.addTask(h);

// Task I depends on D, E, index 9
        List<Task> predsForI = new ArrayList<>();
        predsForI.add(manager.getTaskByName("D"));
        predsForI.add(manager.getTaskByName("E"));
        Task i = new Task("I", 4, predsForI, 9);
        manager.addTask(i);

// Task J depends on H, index 10
        List<Task> predsForJ = new ArrayList<>();
        predsForJ.add(manager.getTaskByName("H"));
        Task j = new Task("J", 2, predsForJ, 10);
        manager.addTask(j);

// Task K depends on G, I, J, index 11
        List<Task> predsForK = new ArrayList<>();
        predsForK.add(manager.getTaskByName("G"));
        predsForK.add(manager.getTaskByName("I"));
        predsForK.add(manager.getTaskByName("J"));
        Task k = new Task("K", 1, predsForK, 11);
        manager.addTask(k);

// FT task depends on K, index 12
        List<Task> predsForFT = new ArrayList<>();
        predsForFT.add(manager.getTaskByName("K"));
        Task ft = new Task("FT", 0, predsForFT, 12);
        manager.addTask(ft);

// Display tasks to verify proper setup
        manager.displayTasks();

// Assuming ScheduleManager and Task classes exist and are designed to interact properly
        ScheduleManager schedule = new ScheduleManager(manager);
        /*schedule.calculateEarliestStartForTasks();
        schedule.calculateLatestStartForTasks();
        manager.displayTasks();
        schedule.calculateCriticalTasks();
        schedule.findAllCriticalPaths();   */     
       //manager.populateSuccessors();

    }
}
