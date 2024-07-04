package org.example.practice.impls.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Task {
    private String id;
    private List<Task> dependencies;

    public Task(String id, List<Task> dependencies) {
        this.id = id;
        this.dependencies = new ArrayList<>(dependencies);
    }

    public String getId() {
        return id;
    }

    public List<Task> getDependencies() {
        return dependencies;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Task)) return false;
        Task task = (Task) object;
        return Objects.equals(getId(), task.getId()) && Objects.equals(getDependencies(), task.getDependencies());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                '}';
    }
}
