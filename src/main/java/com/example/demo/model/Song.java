package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    private String singer;

    private String link;

    private int likes;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "albumId")
    private Album album;

    @JsonIgnore
    @OneToOne(mappedBy = "song")
    private Banner banner;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "typeId")
    private Type types;

    @JsonIgnore
    @ManyToMany(mappedBy = "songs")
    private List<Playlist> playlists;
}
