package org.example.practice.impls;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ContentCheckerTest {

    private ContentChecker contentChecker = new ContentChecker();

    @ParameterizedTest
    @MethodSource("params")
    public void testCheckContent(String magazine, String note, boolean expected) {
        Assertions.assertEquals(expected, contentChecker.canNoteBeWritten(magazine, note));
    }

    public static Stream<Arguments> params() {
        String magazine = "Maga zine";
        return Stream.of(
                Arguments.of(magazine, "az eM", true),
                Arguments.of(magazine, "note", false),
                Arguments.of(magazine, " zaa ", true),
                Arguments.of(magazine, " zaaa ", false),
                Arguments.of(magazine, " zaa ine", true),
                Arguments.of(magazine, " zaaa ine", false),
                Arguments.of(magazine, " zaa\nine", true),
                Arguments.of(magazine, " zaaa\nine", false),
                Arguments.of(magazine, " zaa\tine", true),
                Arguments.of(magazine, " zaaa\tine", false),
                Arguments.of("", "", true),
                Arguments.of(magazine, null, true),
                Arguments.of(null, " ", true),
                Arguments.of(null, " ", true)
        );
    }
}
