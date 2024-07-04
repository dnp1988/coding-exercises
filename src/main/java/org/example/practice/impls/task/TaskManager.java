package org.example.practice.impls.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class TaskManager {

    public List<Task> calculateMostEfficientTaskOrder(List<Task> unorderedTasks) {
        Set<Task> visitedTasks = new LinkedHashSet<>();

        unorderedTasks.stream().sorted(Comparator.comparing(task -> task.getDependencies().size()))
                .forEach(task -> {
                    if(!visitedTasks.contains(task)) {
                        visitTask(task, visitedTasks, new HashSet<>());
                    }
                });

        return new ArrayList<>(visitedTasks);
    }

    private void visitTask(Task task, Set<Task> visitedTasks, Set<Task> path) {
        if(Objects.isNull(task)) {
            return;
        }

        path.add(task);

        for(Task dependency : task.getDependencies()) {
            if(path.contains(dependency)) {
                throw new IllegalArgumentException("There is a cycle in the path");
            }
            if(!visitedTasks.contains(dependency)) {
                visitTask(dependency, visitedTasks, new HashSet<>(path));
            }
        }

        visitedTasks.add(task);
    }

}
