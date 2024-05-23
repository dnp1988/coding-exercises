package org.example.practice;

import org.junit.jupiter.api.Test;

public class CodingPracticeTest {

    @Test
    public void testWordCounts() {
        String[] inputs = { "b word", "a word", "a word", "c word", "c word" };

        int[] results = CodingPractice.wordCounts(inputs);
    }

    @Test
    public void testWordCounts2() {
        String[] inputs = { "b word", "a word", "a word", "c word", "c word" };

        int[] results = CodingPractice.wordCounts2(inputs);
    }

    @Test
    public void testAvTranslate() {
        String input = "faight";

        String result = CodingPractice.avTranslate(input);
    }

    @Test
    public void testMostPopularNumber() {
        int[] input = {34,31,34,77,77,82};

        Integer result = CodingPractice.mostPopularNumber(input, 6);
    }
}
