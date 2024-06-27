package org.example.practice.exercises;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class WordExercisesTest {

    @Test
    public void testWordCounts() {
        String[] inputs = { "b word", "a word", "a word", "c word", "c word" };
        int[] expected = { 2, 1, 2 };

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
        String expected = "favaight";

        Assertions.assertEquals(expected, WordExercises.avTranslate(input));
    }

    @Test
    public void testReturnFirstNonRepeatedCharacter() {
        Assertions.assertEquals('b', WordExercises.firstNonRepeatedCharacter(Stream.of('a','a','b','c','d','d')));
    }
}
