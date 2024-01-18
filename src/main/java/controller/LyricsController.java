package controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import persistence.model.Favorite;
import service.LyricsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Getter
@Setter
public class FavouritesController {

    private final Map<String, List<Favorite>> userFavourites = new HashMap<>();
    private final LyricsService lyricsService;

    @Autowired
    public FavouritesController(LyricsService lyricsService) {
        this.lyricsService = lyricsService;
    }

    @GetMapping("/favourites/lyrics")
    public ResponseEntity<List<Favorite>> getFavouritesWithLyrics(
            @RequestParam("uuid") String uuid,
            @RequestParam(value = "limit", required = false) Integer limit) {

        if (!userFavourites.containsKey(uuid)) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

        List<Favorite> favourites = userFavourites.get(uuid);

        if (limit != null && limit > 0) {
            favourites = favourites.stream().limit(limit).collect(Collectors.toList());
        }

        List<Favorite> favouritesWithLyrics = favourites.stream()
                .map(favourite -> {
                    String lyrics = lyricsService.getLyrics(favourite.getArtist(), favourite.getTitle());
                    favourite.setLyrics(lyrics);
                    return favourite;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(favouritesWithLyrics, HttpStatus.OK);
    }
}