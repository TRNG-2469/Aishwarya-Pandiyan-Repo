package com.cohort.tasks;

public class Task {
    private String taskId;
    private String title;
    private boolean isCompleted;

    public Task(String taskId, String title) {
        this.taskId = taskId;
        this.title = title;
        this.isCompleted = false;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", title='" + title + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}