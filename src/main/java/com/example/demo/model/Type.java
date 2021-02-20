package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;

    private String name;

    private String image;

    @JsonIgnore
    @ManyToMany(mappedBy = "types")
    private List<Subject> subjects;

    @JsonIgnore
    @OneToMany(mappedBy = "types")
    private List<Song> song;
}
