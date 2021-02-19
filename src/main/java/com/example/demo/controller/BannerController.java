package com.example.demo.controller;

import com.example.demo.model.Album;
import com.example.demo.model.Banner;
import com.example.demo.service.Banner.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/banner")
public class BannerController {

    @Autowired
    private IBannerService bannerService;

    @GetMapping
    public ResponseEntity<Iterable<Banner>> getAllBanner() {
        return new ResponseEntity<>(this.bannerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Banner> getBannerById(@PathVariable Long id) {
        Optional<Banner> optionalBanner = this.bannerService.findById(id);
        return optionalBanner.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Banner> createAlbum(@RequestBody Banner banner){
        return new ResponseEntity<>(this.bannerService.save(banner), HttpStatus.OK);
    }
}
