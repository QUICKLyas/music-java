package com.music.search.dao;

import com.music.search.pojo.SongDetail4ES;

import java.util.List;

public interface SearchDao {
    void saveSong(List<SongDetail4ES> list);
}
