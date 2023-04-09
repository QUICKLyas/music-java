package com.music.song.service;

import com.music.commons.pojo.Exits;
import com.music.commons.pojo.Result;
import com.music.commons.pojo.ResultData;
import com.music.pojo.SongDetail;
import com.music.pojo.reqbody.Favorites;

import java.util.Map;

public interface CollectMusicService {
    Result<Map> collectSongToCollectionLike(Favorites jsonSrc);

    Exits getCollectSExist(Favorites jsonSrc);
    ResultData<SongDetail> getRandomMusic();
}
