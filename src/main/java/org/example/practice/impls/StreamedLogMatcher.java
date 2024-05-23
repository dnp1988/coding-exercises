package org.example.practice.impls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamedLogMatcher {

     static final String QUERY_PREFIX = "Q:";
     static final String LOG_PREFIX = "L:";

    public Map<String, List<String>> processInputs(Stream<String> inputs) {
        List<String> logHistory = new ArrayList<>();
        Map<String, List<String>> queryResultsMap = new HashMap<>();

        inputs.forEach(input -> {
            if(isLog(input)) {
                logHistory.add(input.replaceFirst(LOG_PREFIX, ""));
            } else if(isQuery(input)) {
                String query = input.replaceFirst(QUERY_PREFIX, "");
                queryResultsMap.put(query, runQuery(query, logHistory));
            }
        });

        return queryResultsMap;
    }

    private List<String> runQuery(String query, List<String> logHistory) {
        List<String> words = Stream.of(query.split("\\s+")).distinct().collect(Collectors.toList());

        StringBuffer patternBuilder = new StringBuffer("\\b(");
        patternBuilder.append(words.stream().collect(Collectors.joining("|")));
        patternBuilder.append(")\\b");

        Pattern pattern = Pattern.compile(patternBuilder.toString());

        return logHistory.stream()
                .filter(log -> {
                    Matcher matcher = pattern.matcher(log);
                    return matcher.results()
                            .map(MatchResult::group)
                            .distinct()
                            .count() == words.size();
                })
                .collect(Collectors.toList());
    }

    private Boolean isQuery(String input) {
        return input.startsWith(QUERY_PREFIX);
    }

    private Boolean isLog(String input) {
        return input.startsWith(LOG_PREFIX);
    }

}
