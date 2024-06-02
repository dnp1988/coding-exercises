package org.example.practice.impls.url;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class SimpleUrlShortenerTest {

    private static final String BASE_URL = "https://short-url.com";
    private final SimpleUrlShortener shortener = new SimpleUrlShortener(BASE_URL);

    @Test
    public void testSimpleUrlShortener() {
        String longUrl = "https://www.ascii-code.com/characters/ascii-alphabet-characters";
        String keyWord = "tinyUrl";
        String shortUrl = shortener.saveUrl(longUrl, keyWord);

        Assertions.assertEquals(BASE_URL + "/" + keyWord, shortUrl);
        Assertions.assertEquals(longUrl, shortener.getUrl(shortUrl));
    }

    @Test
    public void testSimpleUrlShortenerWithRandomKeyWord() {
        String longUrl = "https://www.ascii-code.com/characters/ascii-alphabet-characters";
        String shortUrl = shortener.saveUrlRandomKeyWord(longUrl);
        String keyWord = shortUrl.replaceFirst(BASE_URL + "/", "");

        Assertions.assertEquals(4, keyWord.length());
        Assertions.assertTrue(keyWord.chars().allMatch(character -> ('0' <= character && character <= '9') || ('a' <= character && character <= 'z')));
        Assertions.assertEquals(longUrl, shortener.getUrl(shortUrl));
    }
}
