/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import algorithms.ScheduleManager;
import helpers.TaskManager;
import helpers.Task;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class TaskManagerFrame extends javax.swing.JFrame {

    private TaskManager manager;

    /**
     * Creates new form TaskManagerFramee
     *
     * @param taskManager
     */
    public TaskManagerFrame(TaskManager taskManager) {
        this.manager = taskManager;
        initComponents();
        populateTable();
    }

    // Method to populate the table with tasks from the TaskManager
    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) taskTable.getModel();
        model.setRowCount(0); // Clear existing rows

        for (Task task : manager.getTasks()) {
            model.addRow(new Object[]{task.getIndex(), task.getName(), task.getDuration(), task.getDependenciesString()});
        }
    }

    private void addRowToTable(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) taskTable.getModel();
        model.addRow(row);
    }

    private void addTask() {
        String name = taskName.getText().trim();
        String durationStr = taskDuration.getText().trim();

        // Input validation
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a task name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int duration;
        try {
            duration = Integer.parseInt(durationStr);
            if (duration <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a valid duration (a positive integer).", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid duration (a positive integer).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Task> dependencies = new ArrayList<>();

        int[] selectedRows = taskTable.getSelectedRows();

        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "Please select at least one dependency.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (int row : selectedRows) {
            String taskNameStr = (String) taskTable.getModel().getValueAt(row, 1); // Ensure the column index is correct
            Task task = manager.getTaskByName(taskNameStr);
            System.out.println(task);
            if (task != null) {
                dependencies.add(task);
            }
        }

        // More input validation omitted for brevity
        Task newTask = new Task(name, duration, dependencies, manager.getNextIndex());
        manager.addTask(newTask);

        // Add the task to the table model
        addRowToTable(new Object[]{newTask.getIndex(), newTask.getName(), newTask.getDuration(), newTask.getDependenciesString()});

        taskName.setText("");
        taskDuration.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        taskName = new javax.swing.JTextField();
        taskDuration = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        addTaskButton = new javax.swing.JButton();
        criticalPathButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taskTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Task name");

        jLabel2.setText("Task duration");

        taskName.setToolTipText("task name");

        taskDuration.setToolTipText("");

        jLabel3.setText("Task dependencies :");

        addTaskButton.setText("Add Task");
        addTaskButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                performAddTask(evt);
            }
        });

        criticalPathButton.setText("Critical Path");
        criticalPathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateCriticalPath(evt);
            }
        });

        jLabel4.setText("TASK MANAGER");

        taskTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "name", "duration", "dependencies"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        taskTable.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(taskTable);
        taskTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel5.setText("Choose one or more (hold Ctrl for multiple selection)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(addTaskButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(criticalPathButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(taskDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(taskName, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(97, 97, 97))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addComponent(taskName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(taskDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addTaskButton)
                    .addComponent(criticalPathButton))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void performAddTask(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_performAddTask
        addTask();
    }//GEN-LAST:event_performAddTask

    private void calculateCriticalPath(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateCriticalPath
        if (manager.getTaskByName("FT") == null) {
            //add task ft // update list
            List<Task> predsForFT = new ArrayList<>();

            // Assuming all tasks are added to the manager and tasks that need successors are already handled
            for (Task task : manager.getTasks()) {
                if (task.getSuccessors().isEmpty()) {
                    predsForFT.add(task);  // Add task as a predecessor if it has no successors
                }
            }
            Task ft = new Task("FT", 0, predsForFT, manager.getNextIndex());
            manager.addTask(ft);
            // Assuming addRowToTable adds a row to your table model directly
            addRowToTable(new Object[]{ft.getIndex(), ft.getName(), ft.getDuration(), ft.getDependenciesString()});
        } else {
            System.out.println("FT task already exists and won't be added again.");
        }

        ScheduleManager schedule = new ScheduleManager(manager);
        schedule.calculateEarliestStartForTasks();
        schedule.calculateLatestStartForTasks();
        schedule.calculateCriticalTasks();
        List<List<Task>> criticalPaths = schedule.getPossiblePaths();  // This should return a list of paths, each path being a list of tasks.

// Prepare the result string
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Critical Path Analysis Results:\n");
        resultBuilder.append("Earliest Start Times:\n");
        for (Task task : manager.getTasks()) {
            resultBuilder.append(task.getName()).append(": ").append(task.getEarliestStart()).append("\n");
        }
        resultBuilder.append("\nLatest Start Times:\n");
        for (Task task : manager.getTasks()) {
            resultBuilder.append(task.getName()).append(": ").append(task.getLatestStart()).append("\n");
        }
        resultBuilder.append("\nCritical Tasks:\n");
        for (Task task : schedule.getCriticalTasks()) {
            resultBuilder.append(task.getName()).append("\n");
        }

// Format and append the critical paths to the result
        resultBuilder.append("\nCritical Paths:\n");
        int pathIndex = 1;
        for (List<Task> path : criticalPaths) {
            resultBuilder.append("Path ").append(pathIndex++).append(": ");
            for (Task task : path) {
                resultBuilder.append(task.getName()).append(" -> ");
            }
            resultBuilder.delete(resultBuilder.length() - 4, resultBuilder.length()); // Remove the last arrow
            resultBuilder.append("\n");
        }

// Display the results in a JOptionPane
        JOptionPane.showMessageDialog(null, resultBuilder.toString(), "Critical Path Results", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_calculateCriticalPath


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addTaskButton;
    private javax.swing.JButton criticalPathButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField taskDuration;
    private javax.swing.JTextField taskName;
    private javax.swing.JTable taskTable;
    // End of variables declaration//GEN-END:variables
}
