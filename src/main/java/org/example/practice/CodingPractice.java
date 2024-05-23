package org.example.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CodingPractice {

    public static int[] wordCounts(String[] words) {
        Map<String, Integer> counters = new HashMap<>();

        Arrays.stream(words)
                .peek(word -> System.out.println("Word " + word))
                .forEach(word -> {
                    counters.put(word, counters.getOrDefault(word,0) + 1);
                });

        return counters.values().stream().mapToInt(i -> i).toArray();
    }

    public static int[] wordCounts2(String[] words) {
        Map<String, Integer> results = new TreeMap<>(Comparator.naturalOrder());

        Arrays.stream(words)
                .forEachOrdered(word -> {
                    results.put(word, results.getOrDefault(word, 0) + 1);
                });

        return results.values().stream().mapToInt(i -> i).toArray();
    }

    public static String avTranslate(String text) {
        StringBuilder sb = new StringBuilder();
        
        text.chars().forEachOrdered(ch -> {
            Character character = (char) ch;
            
            if(isVowel(character) && !isVowel((char) sb.codePointAt(sb.length()-1))) {
                sb.append("av");
            }
            
            sb.append(character);
        });

        return sb.toString();
    }

    private static boolean isVowel(char ch) {
        return "aeiou".chars().anyMatch(i -> i == ch);
    }

    public static Integer mostPopularNumber(int[] numbers, int length) {
        Map<Integer, Integer> counters = new HashMap<>();
        for(Integer i = 0; i < length; i++) {
            Integer count = counters.getOrDefault(numbers[i],0);
            counters.put(numbers[i], count + 1);
        }
        return counters.entrySet().stream().sorted(comparator()).map(entry -> entry.getKey()).findFirst().get();
    }

    private static Comparator<Map.Entry<Integer, Integer>> comparator() {
        return ((entry1, entry2) -> {
            if(entry1.getValue() == entry2.getValue()) {
                return entry1.getKey().compareTo(entry1.getKey());
            } else {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });
    }
}
