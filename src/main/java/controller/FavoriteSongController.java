package controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import persistence.UserRepository;
import response.FavoriteSong;
import response.User;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/favorites")
@Getter
@Setter
public class FavoriteSongController {


    @Autowired
    private UserRepository userRepository;



    @GetMapping
    public ResponseEntity<List<FavoriteSong>> getFavoriteSongs(@RequestParam String uuid) {
        List<User> users = userRepository.findByUuid(uuid);
        if (users.isEmpty()) {
            return ResponseEntity.ok().body(new ArrayList<>());
        }
        User user = users.get(0); // assuming uuid is unique, so we can get the first user
        return ResponseEntity.ok().body(user.getFavoriteSongs());
    }


    @PostMapping
    public ResponseEntity<List<FavoriteSong>> addFavoriteSongs(@RequestParam String uuid, @RequestBody List<FavoriteSong> favoriteSongs) {
        List<User> users = userRepository.findByUuid(uuid);
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = users.get(0);
        user.getFavoriteSongs().addAll(favoriteSongs);
        userRepository.save(user);
        return ResponseEntity.ok().body(user.getFavoriteSongs());
    }
    }
