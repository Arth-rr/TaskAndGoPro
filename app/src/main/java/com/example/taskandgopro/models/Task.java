package com.example.taskandgopro.models;

import java.util.Objects;

public class Task {

    private String name;
    private Boolean wasDone;
    private String description;

    public Task(String name, Boolean wasDone, String description) {
        this.name = name;
        this.wasDone = wasDone;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getWasDone() {
        return wasDone;
    }

    public void setWasDone(Boolean wasDone) {
        this.wasDone = wasDone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name) && Objects.equals(wasDone, task.wasDone) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, wasDone, description);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", wasDone=" + wasDone +
                ", description='" + description + '\'' +
                '}';
    }
}
