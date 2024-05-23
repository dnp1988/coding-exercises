package org.example.practice.impls;

import java.util.Optional;

public class Compress {

    public String compress(String s) {
        return compress(s, null);
    }

    public String compress(String s, Integer m) {
        StringBuilder stringBufferResult = new StringBuilder();
        String[] majors = s.split("/");

        for (int i = 0; i < majors.length; i++) {
            if (i != 0) {
                stringBufferResult.append("/");
            }

            String major = majors[i];
            String[] minors = major.split("\\.");

            Integer effectiveM = Optional.ofNullable(m).orElse(minors.length);
            Integer regularMinors = Math.min(effectiveM - 1, minors.length - 1);

            for (int j = 0; j < regularMinors; j++) {
                if (j < effectiveM - 1) {
                    if (j != 0) {
                        stringBufferResult.append(".");
                    }
                    String minor = compressPart(minors[j]);
                    stringBufferResult.append(minor);
                }
            }

            if(regularMinors > 0) {
                stringBufferResult.append(".");
            }

            if(effectiveM >= minors.length) {
                stringBufferResult
                        .append(compressPart(minors[minors.length - 1]));
            } else {
                stringBufferResult
                        .append(createLastMinor(minors, effectiveM));
            }
        }

        return stringBufferResult.toString();
    }

    private String compressPart(String s) {
        int len = s.length();
        if(len < 3) {
            return s;
        }
        return new StringBuilder()
                .append(s.charAt(0))
                .append(len - 2)
                .append(s.charAt(len - 1))
                .toString();
    }

    private String createLastMinor(String[] minors, Integer m) {
        String mMinor = minors[m - 1];
        String lastMinor = minors[minors.length - 1];

        char firstChar = mMinor.charAt(0);
        char lastChar =  lastMinor.charAt(lastMinor.length() - 1);
        int middleCount = 1;

        middleCount += mMinor.length() - 1;
        middleCount += lastMinor.length() - 1;

        for(Integer j = m; j < minors.length - 1; j++) {
            middleCount += minors[j].length() + 1;
        }

        return new StringBuilder()
                .append(firstChar)
                .append(middleCount)
                .append(lastChar)
                .toString();
    }
}
