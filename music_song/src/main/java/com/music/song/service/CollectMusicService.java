package com.music.song.service;

import com.music.commons.pojo.Exits;
import com.music.commons.pojo.ResultMusic;
import com.music.pojo.SongDetail;
import com.music.pojo.reqbody.CollectMusic;

public interface CollectMusicService {
    Exits getCollectSExist(CollectMusic jsonSrc);
    ResultMusic<SongDetail> getRandomMusic();
}
