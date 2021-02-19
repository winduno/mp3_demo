package com.example.demo.controller;

import com.example.demo.model.Album;
import com.example.demo.model.Song;
import com.example.demo.service.Song.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/song")
public class SongController {

    @Autowired
    private ISongService songService;

    @GetMapping
    public ResponseEntity<Iterable<Song>> getAllSong() {
        return new ResponseEntity<>(this.songService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        Optional<Song> optionalSong = this.songService.findById(id);
        return optionalSong.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Song> createSong(@RequestBody Song song){
        return new ResponseEntity<>(this.songService.save(song), HttpStatus.OK);
    }
}
