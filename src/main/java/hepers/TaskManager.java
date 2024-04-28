/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hepers;

/**
 *
 * @author Administrator
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utilities.GraphPrinter;

public class TaskManager {

    public List<Task> tasks;
    private Map<String, Task> taskMap;
    private int[][] adjacencyMatrix;

    public Map<String, Task> getTaskMap() {
        return taskMap;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.taskMap = new HashMap<>();
    }

    public void addTask(String name, int duration, List<Task> predecessors, int index) {
        Task newTask = new Task(name, duration, predecessors, index);
        tasks.add(newTask);
        taskMap.put(name, newTask);  // Keep track of tasks by name
    }
    
    public void addTask(Task  newTask){
        tasks.add(newTask);
        taskMap.put(newTask.getName(), newTask);
    }

    public Task getTaskByName(String name) {
        return taskMap.get(name);
    }
    
    public Task getTaskById(int id ){
        return tasks.get(id);
    }
    
    public void createAdjacencyMatrix() {
        int vertices = calculateVertices(); // Number of tasks
        int edges = calculateEdges(); // Number of dependencies between tasks
        System.out.println(vertices);
        
        // Create a matrix with an extra row and column for metadata
        adjacencyMatrix = new int[vertices + 1][vertices + 1]; // This accounts for 1-based indexing 14

        // Initialize the matrix with zeros for actual task relationships
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }

        // Set metadata
        adjacencyMatrix[0][0] = vertices; // Store number of vertices at 0,0
        adjacencyMatrix[1][0] = vertices + edges; // Store total vertices + edges at 1,0

        // Populate the adjacency matrix based on task dependencies
        for (Task task : tasks) {
            int taskIndex = task.getIndex() + 1; // Ensure that index is 1-based
            System.out.println("task index: "+taskIndex);
            for (Task pred : task.getPredecessors()) {
                int predIndex = pred.getIndex() + 1; // Ensure that index is 1-based
                System.out.println("pred index: "+predIndex);
                adjacencyMatrix[predIndex][taskIndex] = 1;
            }
        }
         
    }
    
   public void populateSuccessors() {
        createAdjacencyMatrix(); // Create the adjacency matrix if not already created
        GraphPrinter.adjMatrixPrinter(adjacencyMatrix);
        
        for (int i = 1; i < adjacencyMatrix.length; i++) {
            for (int j = 1; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    // If there is an edge from task i to task j, add task j as a successor of task i
                    tasks.get(i-1).addSuccessor(tasks.get(j-1));
                    System.out.println(tasks.get(j-1).getName() + " is successsors of : "+tasks.get(i-1).getName());
                }
            }
        }
    }
   
   
   public int [][] getAdjacencyMatrix(){
       return adjacencyMatrix;
   }
    
    public void displayTasks() {
        for (Task task : tasks) {
            System.out.print("Task " + task.name + " with duration " + task.duration);
            System.out.print(" depends on ");
            for (Task pred : task.predecessors) {
                System.out.print(pred.name + " ");
            }
            System.out.print(" earliest Start "+ task.earliestStart);
            System.out.print(" latest Start "+ task.latestStart);
            System.out.println();
        }
    }
    
    // Method to calculate the number of vertices in the graph
    public int calculateVertices() {
        return tasks.size();
    }

    // Method to calculate the number of edges in the graph
    public int calculateEdges() {
        int edgeCount = 0;
        for (Task task : tasks) {
            edgeCount += task.getPredecessors().size();
        }
        return edgeCount;
    }
    
    


    
}
