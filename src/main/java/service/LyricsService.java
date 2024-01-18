package service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class LyricsService {

    private final WebClient webClient = WebClient.create("https://api.lyrics.ovh/v1");
    private final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();

    public String getLyrics(String artist, String title) {
        String cacheKey = artist + ":" + title;
        String cachedLyrics = cache.get(cacheKey);

        if (cachedLyrics != null) {
            return cachedLyrics;
        }

        try {
            String lyrics = webClient.get()
                    .uri("/{artist}/{title}", artist, title)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            cache.put(cacheKey, lyrics);
            cache.expireAfterWrite(1, TimeUnit.HOURS);

            return lyrics;
        } catch (WebClientResponseException e) {
            if (e.getRawStatusCode() == 404) {
                return null;
            }
            throw e;
        }
    }
}
