package org.example.practice.impls;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Write a function that takes the content of the magazine and the content of the note to write
 * and return true if the note can be written or false otherwise.
 */
public class ContentChecker {

    public boolean canNoteBeWritten(String magazineContent, String noteContent) {
        if(Objects.isNull(noteContent)) {
            return true;
        }

        magazineContent = Optional.ofNullable(magazineContent).orElse("");

        Map<Character, Integer> magazineMap = createCharacterMap(magazineContent);
        Map<Character, Integer> noteMap = createCharacterMap(noteContent);

        for(Map.Entry<Character, Integer> entry : noteMap.entrySet()) {
            Integer magazineCharacterCount = magazineMap.get(entry.getKey());
            Integer noteCharacterCount = entry.getValue();

            if(Objects.isNull(magazineCharacterCount) || magazineCharacterCount < noteCharacterCount) {
                return false;
            }
        }

        return true;
    }

    private Map<Character, Integer> createCharacterMap(String content) {
        Map<Character, Integer> result = new HashMap<>();

        for(Integer i = 0; i < content.length(); i++) {
            Character current = content.charAt(i);

            if(!String.valueOf(current).matches("\\s")) {
                Integer count = result.getOrDefault(current, 0);
                result.put(current, count + 1);
            }
        }

        return result;
    }
}
