package com.example.taskandgopro.models;

import java.io.Serializable;
import java.util.Objects;

public class Task implements Serializable {

    private String name;
    private Boolean wasDone;
    private String description;
    private String type;

    public Task(String name, Boolean wasDone, String description, String type) {
        this.name = name;
        this.wasDone = wasDone;
        this.description = description;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name) && Objects.equals(wasDone, task.wasDone) && Objects.equals(description, task.description) && Objects.equals(type, task.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, wasDone, description, type);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", wasDone=" + wasDone +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
