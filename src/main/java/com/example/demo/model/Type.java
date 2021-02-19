package com.example.demo.model;

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

    @ManyToMany(mappedBy = "types")
    private List<Subject> subjects;

    @OneToOne(mappedBy = "type")
    private Song song;
}
