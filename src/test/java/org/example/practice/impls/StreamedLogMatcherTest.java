package org.example.practice.impls;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.example.practice.impls.StreamedLogMatcher.LOG_PREFIX;
import static org.example.practice.impls.StreamedLogMatcher.QUERY_PREFIX;

public class StreamedLogMatcherTest {

    @Test
    public void testStreamedLogMatcher() {
        StreamedLogMatcher streamedLogMatcher = new StreamedLogMatcher();

        String[] inputs = {
                LOG_PREFIX + "This is the 1 first log log",
                LOG_PREFIX + "This is the 2 second log log",
                LOG_PREFIX + "This is the 3 third log log",
                QUERY_PREFIX + "This is the log",
                LOG_PREFIX + "This is the 4 forth log",
                QUERY_PREFIX + "forth",
                QUERY_PREFIX + "fifth",
                LOG_PREFIX + "This is the 5 fifth log",
        };
        Map<String, List<String>> queryLogs = streamedLogMatcher.processInputs(Stream.of(inputs));
        Assertions.assertEquals(3, queryLogs.get("This is the log").size());
        Assertions.assertEquals(1, queryLogs.get("forth").size());
        Assertions.assertEquals(0, queryLogs.get("fifth").size());
    }

}
