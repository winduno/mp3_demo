package com.example.demo.model;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "albumId")
    private Album album;

    @OneToOne(mappedBy = "song")
    private Banner banner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "typeId")
    private Type type;

    @ManyToMany(mappedBy = "songs")
    private List<Playlist> playlists;
}
