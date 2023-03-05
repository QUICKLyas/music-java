package com.music.song.service;

import com.music.commons.pojo.Result;
import com.music.song.pojo.Song;

import java.util.List;
import java.util.Map;

/**
 * Songs 服务接口
 */
public interface SongService {
    Result<Song> getSongs(List<Integer> songIds);
    Result<Song> getSongDetail(Integer songId);
    Result<Map> insertSongToCollectionLike(Map jsonSrc);
}