package com.example.demo.controller;

import com.example.demo.model.Album;
import com.example.demo.model.Song;
import com.example.demo.service.Album.IAlbumService;
import com.example.demo.service.Song.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/album")
public class AlbumController {

    @Autowired
    private IAlbumService albumService;

    @Autowired
    private ISongService songService;

    @GetMapping
    public ResponseEntity<Iterable<Album>> getAllAlbum() {
        return new ResponseEntity<>(this.albumService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        Optional<Album> optionalAlbum = this.albumService.findById(id);
        return optionalAlbum.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album){
        return new ResponseEntity<>(this.albumService.save(album), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Song> addSongToAlbum(@PathVariable Long id, @RequestBody(required = false) Song song){
        Optional<Album> optionalAlbum = this.albumService.findById(id);
        Optional<Song> optionalSong = this.songService.findById(song.getId());
        if (optionalAlbum.isPresent() && optionalSong.isPresent()){
            Song saveSong = optionalSong.get();
            saveSong.setAlbum(optionalAlbum.get());
            return new ResponseEntity<>(this.songService.save(saveSong), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
