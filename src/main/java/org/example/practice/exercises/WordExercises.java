package org.example.practice.exercises;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordExercises {

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

    public static Character firstNonRepeatedCharacter(Stream<Character> characterStream) {
        Map<Character, Long> map = characterStream.collect(Collectors.groupingBy(
                character -> character,
                () -> new LinkedHashMap<>(),
                Collectors.counting()));

        return map.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .findFirst()
                .map(entry -> entry.getKey())
                .orElse(null);
    }
}
