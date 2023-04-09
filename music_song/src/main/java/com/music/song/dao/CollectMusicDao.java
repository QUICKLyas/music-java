package com.music.song.dao;

import com.music.song.pojo.SongDetail;

import java.util.List;
import java.util.Map;

public interface CollectMusicDao {

    List<Map> updateSongToCollectionLike(String userId, Integer musicId, List<String> tags);
    Boolean getCollectSExist(Integer MusicId,String UserId);
    List<SongDetail> getRandomMusic();
}
