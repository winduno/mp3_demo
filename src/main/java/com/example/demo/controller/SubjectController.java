package com.example.demo.controller;

import com.example.demo.model.Playlist;
import com.example.demo.model.Subject;
import com.example.demo.service.Subject.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;

    @GetMapping
    public ResponseEntity<Iterable<Subject>> getAllSubjects() {
        return new ResponseEntity<>(this.subjectService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long id) {
        Optional<Subject> optionalSubject = this.subjectService.findById(id);
        return optionalSubject.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Subject> createAlbum(@RequestBody Subject subject){
        return new ResponseEntity<>(this.subjectService.save(subject), HttpStatus.OK);
    }
}
