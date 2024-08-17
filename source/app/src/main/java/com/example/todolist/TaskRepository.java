package com.example.todolist;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private static TaskRepository instance;
    private final List<String> tasks;

    private TaskRepository() {
        tasks = new ArrayList<>();
    }

    public static synchronized TaskRepository getInstance() {
        if (instance == null) {
            instance = new TaskRepository();
        }
        return instance;
    }

    public List<String> getTasks() {
        return new ArrayList<>(tasks);
    }

    public void addTask(String task) {
        tasks.add(task);
    }

    public void deleteTask(String task) {
        tasks.remove(task);
    }
}
