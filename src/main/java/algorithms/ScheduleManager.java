/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorithms;

/**
 *
 * @author Administrator
 */
import hepers.Task;
import hepers.TaskManager;
import java.util.ArrayList;

import java.util.List;

public class ScheduleManager {

    private final TaskManager taskManager;

    /*private int[] fs;
    private int[] aps;
    private int[] fp;
    private int[] app;
    private int[] rank;
    private int[] fpc;
    private int[] appc;
    private int[] lc;*/
    private List<Task> criticalTasks;
    private List<List<Task>> allCriticalPaths;

    public List<Task> getCriticalTasks() {
        return criticalTasks;
    }

    public List<List<Task>> getPossiblePaths() {
        return allCriticalPaths;
    }

    public ScheduleManager(TaskManager manager) {
        this.taskManager = manager;
        this.criticalTasks = new ArrayList<>();
        this.allCriticalPaths = new ArrayList<>();
    }

    public void calculateEarliestStartForTasks() {
        List<Task> tasks = taskManager.getTasks();
        if (tasks.isEmpty()) {
            return;
        }
        // Assuming the first task is always "DT" and it has no predecessors
        Task dt = tasks.get(0);
        dt.setEarliestStart(0);

        // Calculate earliest start for each task based on predecessors
        for (int i = 1; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            int earliestStart = 0;

            // Get the maximum earliest start time from all predecessors
            for (Task predecessor : currentTask.getPredecessors()) {
                int predecessorEnd = predecessor.getEarliestStart() + predecessor.getDuration();
                earliestStart = Math.max(earliestStart, predecessorEnd);
            }

            // Set the earliest start time for the current task
            currentTask.setEarliestStart(earliestStart);
        }

        // Print the results
        for (Task task : tasks) {
            System.out.println("Task " + task.getName() + " with duration " + task.getDuration()
                    + " has an earliest start time of " + task.getEarliestStart());
        }
    }

    // Assuming tasks are in topological order with 'FT' at the end
    public void calculateLatestStartForTasks() {
        List<Task> tasks = taskManager.getTasks();
        taskManager.populateSuccessors();

        // Assuming the last task is "FT" and its latestStart time is set to its earliestStart time
        Task ft = tasks.get(tasks.size() - 1);
        ft.setLatestStart(ft.getEarliestStart());
        System.out.println("Starting with FT: Latest Start set to " + ft.getLatestStart());

        for (int i = tasks.size() - 2; i >= 0; i--) {
            Task task = tasks.get(i);
            int minLatestStartOfSuccessors = Integer.MAX_VALUE;

            System.out.println("Processing Task " + task.getName() + ":");

            // Find the minimum latest start time of all successors
            for (Task successor : task.getSuccessors()) {
                int latestStartForSuccessor = successor.getLatestStart() - task.getDuration();
                minLatestStartOfSuccessors = Math.min(minLatestStartOfSuccessors, latestStartForSuccessor);
                System.out.println("  Successor " + successor.getName() + " has adjusted latest start " + latestStartForSuccessor);
            }

            if (minLatestStartOfSuccessors == Integer.MAX_VALUE) {
                // If no successors, latest start time is the same as the FT task's latest start time
                task.setLatestStart(ft.getLatestStart() - task.getDuration());
                System.out.println("  No successors found. Latest start set to FT's latest start minus duration: " + task.getLatestStart());
            } else {
                // Set the task's latest start time
                task.setLatestStart(minLatestStartOfSuccessors);
                System.out.println("  Setting latest start to minimum of successors: " + task.getLatestStart());
            }
        }

        // Display the tasks with their earliest and latest start times
        for (Task task : tasks) {
            System.out.println("Task " + task.getName() + " with duration " + task.getDuration()
                    + " has earliest start " + task.getEarliestStart() + " and latest start " + task.getLatestStart());
        }
    }

    public void calculateCriticalTasks() {
        for (Task task : taskManager.getTasks()) {
            if (task.getEarliestStart() == task.getLatestStart()) {
                // Check that the task is neither "DT" nor "FT"
                if (!task.getName().equals("DT") && !task.getName().equals("FT")) {
                    System.out.println(task.getName() + " is Critical");
                    criticalTasks.add(task);
                }
            }
        }
    }

    // Method to initiate finding all paths
    public void findAllCriticalPaths() {
        if (criticalTasks.isEmpty() || criticalTasks.size() < 2) {
            System.out.println("Not enough critical tasks to form a path.");
            return;
        }

        Task startTask = criticalTasks.get(0);
        Task endTask = criticalTasks.get(criticalTasks.size() - 1);
        List<Task> currentPath = new ArrayList<>();
        findPathsDFS(startTask, endTask, currentPath, criticalTasks); // Pass criticalTasks to the DFS

        if (allCriticalPaths.isEmpty()) {
            System.out.println("No paths found between the start and end tasks.");
        } else {
            printAllPaths();
        }
    }

    // DFS method to find all paths from 'start' to 'end'
    private void findPathsDFS(Task start, Task end, List<Task> currentPath, List<Task> criticalTasks) {
    currentPath.add(start); // Add current task to the path
    if (start.equals(end)) {
        allCriticalPaths.add(new ArrayList<>(currentPath)); // If at end, add copy of current path to all paths
    } else {
        for (Task successor : start.getSuccessors()) {
            if (criticalTasks.contains(successor) && !currentPath.contains(successor)) { // Only continue with critical successors
                findPathsDFS(successor, end, currentPath, criticalTasks);
            }
        }
    }
    currentPath.remove(currentPath.size() - 1); // Remove last element for backtracking
}


    // Method to print all found paths
    private void printAllPaths() {
        System.out.println("All critical paths:");
        for (List<Task> path : allCriticalPaths) {
            path.forEach(task -> System.out.print(task.getName() + " -> "));
            System.out.println("End");
        }
    }

    /* -------------------------------------------------------------------------------*/
 /*public void calculateCriticalPath() {
        taskManager.createAdjacencyMatrix();
        createFsAps();
        createFpApp();
        createRank();

        int m = fs[0]; // Ensure this is properly set by createFpApp()
        int n = app[0]; // Ensure this is properly set by createFpApp()
        fpc = new int[m + 1]; // added buffer to avoid out of bounds
        appc = new int[n + 1];
        lc = new int[n + 1];

        appc[0] = n;
        fpc[0] = m;
        lc[0] = n;
        int kc = 1;
        lc[1] = 0;
        fpc[1] = 0;
        appc[1] = 0;
        int t, lg;

        for (int s = 2; s <= n; s++) {
            lc[s] = 0;
            appc[s] = kc + 1;

            System.out.println("Processing vertex: " + s);
            System.out.println("Starting KC index: " + kc);
            System.out.println("Appc at " + s + ": " + appc[s]);

            for (int k = app[s]; k < fs.length && fp[k] != 0; k++) {
                if (k >= fp.length) {
                    System.out.println("k index out of bounds for fp: " + k);
                    continue;
                }
                t = fp[k];
                if (t >= lc.length) {
                    System.out.println("t index out of bounds for lc: " + t);
                    continue;
                }
                lg = lc[t] + rank[t];
                System.out.println("Considering predecessor " + t + " with local gain " + lg);

                if (lg >= lc[s]) {
                    if (lg > lc[s]) {
                        lc[s] = lg;
                        kc = appc[s];
                        if (kc < fpc.length) { // check to avoid out of bounds
                            fpc[kc] = t;
                        }
                        System.out.println("Updated LC for " + s + " to " + lc[s] + ", updated fpc at " + kc);
                    } else {
                        kc++;
                        if (kc < fpc.length) { // check to avoid out of bounds
                            fpc[kc] = t;
                        }
                        System.out.println("Added to FPC at " + kc + ": " + t);
                    }
                }
            }
            kc++;
            if (kc < fpc.length) { // check before setting to 0
                fpc[kc] = 0;
            }
            System.out.println("Finished vertex " + s + " with kc at " + kc);
        }
        fpc[0] = kc;

        System.out.println("Final LC: " + Arrays.toString(lc));
        System.out.println("Final FPC: " + Arrays.toString(fpc));
        System.out.println("Final APPC: " + Arrays.toString(appc));
    }*/

 /*public void displayFpcAppc() {
        System.out.println("fpc: " + Arrays.toString(fpc));
        System.out.println("appc: " + Arrays.toString(appc));
        System.out.println("lc: " + Arrays.toString(lc));
    }

    public void displaySchedule() {
        System.out.println("Task Schedule:");
        List<Task> tasks = getTasks();
        for (Task task : tasks) {
            System.out.println(task);
            System.out.print("Depends on: " + task.getPredecessorsName());
            System.out.println();
        }
    }

    public void printCriticalPath() {
        int n = appc[0]; // Total number of tasks
        int projectDuration = Arrays.stream(lc).max().getAsInt(); // Max value in LC
        int currentTask = -1;

        // Find the task with the project duration
        for (int i = 1; i <= n; i++) {
            if (lc[i] == projectDuration) {
                currentTask = i;
                break;
            }
        }

        List<Integer> criticalPath = new ArrayList<>();
        criticalPath.add(currentTask);

        // Trace back from this task to the start of the project
        while (currentTask != 0) {
            int start = appc[currentTask]; // Start index in FPC for the current task
            if (start == 0) {
                break; // If start is 0, it means no predecessors
            }
            int nextTask = fpc[start]; // Get the first predecessor

            // Find the actual predecessor with matching LC time
            for (int k = start; k < fpc[0] && fpc[k] != 0; k++) {
                if (lc[fpc[k]] == lc[currentTask] - rank[currentTask]) {
                    nextTask = fpc[k];
                    break;
                }
            }

            criticalPath.add(0, nextTask); // Add to the beginning of the list
            currentTask = nextTask; // Move to the next task in the path
        }

        // Print the critical path
        System.out.println("Critical Path:");
        for (int taskId : criticalPath) {
            System.out.println("Task " + taskId + " Duration: " + lc[taskId]);
        }
    }

    public void createFsAps() {
        int[][] result = GraphConverter.adjMatrixToFsAps(taskManager.getAdjacencyMatrix());
        fs = result[0];
        aps = result[1];
        System.out.println("fs: " + Arrays.toString(fs));
        System.out.println("aps: " + Arrays.toString(aps));
    }

    public void createFpApp() {
        int[][] result = GraphConverter.fsApsToFpApp(fs, aps);
        fp = result[0];
        app = result[1];
        System.out.println("fp: " + Arrays.toString(fp));
        System.out.println("app: " + Arrays.toString(app));
    }

    public void createRank() {
        rank = GraphRank.calculateRank(fs, aps);
        System.out.println("rank: " + Arrays.toString(rank));
    }*/
}
