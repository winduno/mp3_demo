package com.example.demo.controller;

import com.example.demo.model.Album;
import com.example.demo.model.Song;
import com.example.demo.model.Subject;
import com.example.demo.model.Type;
import com.example.demo.service.Song.ISongService;
import com.example.demo.service.Type.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/type")
public class TypeController {

    @Autowired
    private ITypeService typeService;

    @Autowired
    private ISongService songService;

    @GetMapping
    public ResponseEntity<Iterable<Type>> getAllType() {
        return new ResponseEntity<>(this.typeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type> getTypeById(@PathVariable Long id) {
        Optional<Type> typeOptional = this.typeService.findById(id);
        return typeOptional.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Type> createAlbum(@RequestBody Type type){
        return new ResponseEntity<>(this.typeService.save(type), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Song> addSongToAlbum(@PathVariable Long id, @RequestBody(required = false) Song song){
        Optional<Type> optionalType = this.typeService.findById(id);
        Optional<Song> optionalSong = this.songService.findById(song.getId());
        if (optionalType.isPresent() && optionalSong.isPresent()){
            Song saveSong = optionalSong.get();
            saveSong.setTypes(optionalType.get());
            return new ResponseEntity<>(this.songService.save(saveSong), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
