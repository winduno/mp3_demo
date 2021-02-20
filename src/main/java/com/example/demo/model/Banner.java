package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bannerId;

    private String image;

    private String content;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "songId")
    private Song song;
}
