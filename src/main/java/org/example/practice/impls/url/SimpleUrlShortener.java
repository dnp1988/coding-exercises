package org.example.practice.impls.url;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SimpleUrlShortener {

    private final String baseUrl;
    private final Map<String, String> mapUrl;

    public SimpleUrlShortener(String baseUrl) {
        this.baseUrl = baseUrl;
        this.mapUrl = new HashMap<>();
    }

    public String saveUrl(String longUrl, String keyWord) {
        mapUrl.put(keyWord, longUrl);
        return baseUrl + "/" + keyWord;
    }

    public String saveUrlRandomKeyWord(String longUrl) {
        String keyWord = createRandomKeyWord();
        return saveUrl(longUrl, keyWord);
    }

    private String createRandomKeyWord() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for(Integer i = 0; i < 4; i++) {
            Integer randomNumber = random.nextInt(36);
            Character character;
            if(randomNumber <= 9) {
                character = (char) ('0' + randomNumber);
            } else {
                character = (char) ('a' + randomNumber - 10);
            }
            sb.append(character);
        }

        return sb.toString();
    }

    public String getUrl(String shortUrl) {
        String keyWord = shortUrl.replaceFirst(baseUrl + "/", "");
        return mapUrl.get(keyWord);
    }
}
