/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hepers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */

public class Task {
    String name;
    int duration;
    List<Task> predecessors;
    List<Task> successsors;
    int earliestStart = 0;
    int latestStart = 0;
    int index; // This field will store the index of the task

    public Task(String name, int duration, List<Task> predecessorsArray, int index) {
        this.name = name;
        this.duration = duration;
        this.predecessors = predecessorsArray;
        this.index = index;
        this.successsors = new ArrayList<Task>();
    }
    
    public Task getTask(){
        return this;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Task> getPredecessors() {
        return predecessors;
    }
    
    public List<Task> getSuccessors(){
        return successsors;
    }
    
    public void addSuccessor(Task newTask){
        successsors.add(newTask);
    }

    public void setPredecessors(List<Task> predecessors) {
        this.predecessors = predecessors;
    }

    public int getEarliestStart() {
        return earliestStart;
    }

    public void setEarliestStart(int earliestStart) {
        this.earliestStart = earliestStart;
    }

    public int getLatestStart() {
        return latestStart;
    }

    public void setLatestStart(int latestStart) {
        this.latestStart = latestStart;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    public String getPredName(Task task){
        return task.getName();
    }
    
    public String getPredecessorsName(){
        StringBuilder sb = new StringBuilder();
        for(Task pred:predecessors){
            sb.append(pred.getName());
        }
        return sb.toString();
    }
    
     // Method to get the number of predecessors
    public int getNumberOfPredecessors() {
        return this.predecessors.size();
    }

    public void addPredecessor(Task predecessor) {
        this.predecessors.add(predecessor);
    }

    @Override
    public String toString() {
        return "Task{" + "name=" + name + ", duration=" + duration + ", predecessors=" + predecessors + ", earliestStart=" + earliestStart + ", latestStart=" + latestStart + ", index=" + index + '}';
    }
    
}