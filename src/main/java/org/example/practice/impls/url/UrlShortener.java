package org.example.practice.impls.url;

import java.util.ArrayList;
import java.util.List;

public class UrlShortener {

    private List<String> urls;
    private String baseUrl;
    private Base64IdKeyMapper mapper;

    public UrlShortener(String baseUrl) {
        this.baseUrl = baseUrl;
        this.urls = new ArrayList<>();
        this.mapper = new Base64IdKeyMapper();
    }

    public String saveUrl(String longUrl) {
        urls.add(longUrl);
        Integer id = urls.size();
        return baseUrl + "/" + mapper.toKey(id);
    }

    public String getLongUrl(String shortenedUrl) {
        String urlKey = shortenedUrl.replaceFirst(baseUrl + "/", "");
        return urls.get(mapper.toId(urlKey) - 1);
    }

}
