package org.example.practice.impls.url;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class UrlShortenerTest {

    public static final String SHORT_URL_COM = "https://short-url.com";
    private UrlShortener urlShortener = new UrlShortener(SHORT_URL_COM);

    @Test
    public void testUrlShortener() {
        String inputUrl = "https://www.ascii-code.com/characters/ascii-alphabet-characters";
        String expectedShortenedUrl = SHORT_URL_COM + "/1";

        String shortenedUrl = urlShortener.saveUrl(inputUrl);

        Assertions.assertEquals(expectedShortenedUrl, shortenedUrl);
        Assertions.assertEquals(inputUrl, urlShortener.getLongUrl(shortenedUrl));
    }

}
