package com.music.detial.dao;

import com.music.pojo.SongDetail;

import java.util.List;

public interface SongDao {
    List<SongDetail> findAllSongDetail();
}
