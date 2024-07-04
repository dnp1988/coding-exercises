package org.example.practice.impls.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class TaskManagerTest {

    private final TaskManager taskManager = new TaskManager();

    @ParameterizedTest
    @MethodSource("args")
    public void testTaskManager(List<Task> expected, List<Task> input) {
        List<Task> orderedTasks = taskManager.calculateMostEfficientTaskOrder(input);
        Assertions.assertEquals(expected, orderedTasks);
    }

    public static Stream<Arguments> args() {
        Task task1 = new Task("task1", List.of());
        Task task2 = new Task("task2", List.of());
        Task task3 = new Task("task3", List.of(task2));
        Task task4 = new Task("task4", List.of(task3, task2));
        Task task5 = new Task("task5", List.of());
        Task task6 = new Task("task6", List.of(task5));

        return Stream.of(
                Arguments.of(
                        List.of(task5, task2, task1, task6, task3, task4),
                        List.of(task6, task5, task4, task3, task2, task1))
        );
    }
}
