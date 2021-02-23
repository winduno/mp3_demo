package com.example.demo.service.Song;

import com.example.demo.model.Song;
import com.example.demo.service.IService;

import java.util.List;

public interface ISongService extends IService<Song> {
    List<Song> getSongByLikeASC();
}
