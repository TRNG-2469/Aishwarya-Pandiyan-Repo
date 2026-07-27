package com.cohort.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TaskManager {
    private List<Task> allTasks = new ArrayList<>();
    private Set<String> uniqueTitles = new HashSet<>();
    private Map<String, Task> taskLookup = new HashMap<>();

    public boolean addTask(Task task) {
        if (uniqueTitles.contains(task.getTitle())) {
            System.out.println("Duplicate task blocked!");
            return false;
        }
        allTasks.add(task);
        uniqueTitles.add(task.getTitle());
        taskLookup.put(task.getTaskId(), task);
        return true;
    }

    public Task getTaskById(String id) {
        return taskLookup.get(id);
    }

    public void printRoster() {
        for (Task task : allTasks) {
            System.out.println(task);
        }
    }

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Task task1 = new Task("T1", "Write Primitives Lab");
        Task task2 = new Task("T2", "Configure GitIgnore");
        Task task3 = new Task("T3", "Write Primitives Lab");

        System.out.println("Add Task 1: " + manager.addTask(task1));
        System.out.println("Add Task 2: " + manager.addTask(task2));
        System.out.println("Add Task 3: " + manager.addTask(task3));

        System.out.println("\nFull Roster:");
        manager.printRoster();

        System.out.println("\nLookup T2: " + manager.getTaskById("T2"));
    }
}