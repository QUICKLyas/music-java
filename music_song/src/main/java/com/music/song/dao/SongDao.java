package com.music.song.dao;

import com.music.song.pojo.Song;

import java.util.List;
import java.util.Map;

// 系统中的数据访问接口
public interface SongDao {
    List<Map> getRandomSong();
    List<Map> getNextSongFromCollectionPlaylist(Long playlistId, Integer musicId);

    List<Map> getPreviousSongFromCollectionPlaylist(Long playlistId, Integer musicId);
}
