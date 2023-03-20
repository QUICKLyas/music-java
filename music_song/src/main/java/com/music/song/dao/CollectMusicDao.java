package com.music.song.dao;

import com.music.song.pojo.SongDetail;

import java.util.List;

public interface CollectMusicDao {
    Boolean getCollectSExist(Integer MusicId,String UserId);
    List<SongDetail> getRandomMusic();
}
