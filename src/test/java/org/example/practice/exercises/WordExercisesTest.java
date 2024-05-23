package org.example.practice.exercises;

import org.junit.jupiter.api.Test;

public class WordExercisesTest {

    @Test
    public void testWordCounts() {
        String[] inputs = { "b word", "a word", "a word", "c word", "c word" };

        int[] results = WordExercises.wordCounts(inputs);
    }

    @Test
    public void testWordCounts2() {
        String[] inputs = { "b word", "a word", "a word", "c word", "c word" };

        int[] results = WordExercises.wordCounts2(inputs);
    }

    @Test
    public void testAvTranslate() {
        String input = "faight";

        String result = WordExercises.avTranslate(input);
    }
}
