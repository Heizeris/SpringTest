package controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import response.Artist;
import response.ArtistResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@Validated
@RestController
@RequestMapping("/artist")
@RequiredArgsConstructor
public class ArtistController {


    private final ArtistController artistService;


    @GetMapping
    public ArtistResponse getSongsByArtist(
            @RequestParam @NotBlank String artist

    ) throws IOException, InterruptedException{
        log.debug("Called get Artist works endpoint with Artist: {}", artist);

        return artistService.getSongsByArtist(artist);
    }

    public void editArtist(
            @RequestBody Artist artist
            ){
        repository.save(artist);
    }

}
