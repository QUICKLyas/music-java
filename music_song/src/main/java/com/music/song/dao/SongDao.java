package com.music.song.dao;

import com.music.song.pojo.Song;

import java.util.List;

// 系统中的数据访问接口
public interface SongDao {
    List<Song> getSongs(List<Integer> songIds);
    List<Song> getSongDetail(Integer songId);
}
