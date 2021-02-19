package com.example.demo.controller;

import com.example.demo.model.Album;
import com.example.demo.model.Playlist;
import com.example.demo.service.Playlist.IPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/playlist")
public class PlaylistController {

    @Autowired
    private IPlaylistService playlistService;

    @GetMapping
    public ResponseEntity<Iterable<Playlist>> getAllPlaylist() {
        return new ResponseEntity<>(this.playlistService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylistByID(@PathVariable Long id) {
        Optional<Playlist> optionalPlaylist = this.playlistService.findById(id);
        return optionalPlaylist.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Playlist> createAlbum(@RequestBody Playlist playlist){
        return new ResponseEntity<>(this.playlistService.save(playlist), HttpStatus.OK);
    }
}
