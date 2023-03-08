package com.music.song.dao;

import com.music.song.pojo.Song;

import java.util.List;
import java.util.Map;

// 系统中的数据访问接口
public interface SongDao {
    List<Map> updateSongToCollectionLike(String userId, Integer musicId,List<String> tags);
    List<Map> getNextSongFromCollectionPlaylist(Long playlistId, Integer musicId);

    List<Map> getPreviousSongFromCollectionPlaylist(Long playlistId, Integer musicId);
}
