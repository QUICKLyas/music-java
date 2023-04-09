package com.music.song.service;

import com.music.commons.pojo.resbody.Exits;
import com.music.commons.pojo.resbody.Result;
import com.music.commons.pojo.resbody.ResultData;
import com.music.pojo.SongDetail;
import com.music.commons.pojo.reqbody.Favorites;

import java.util.Map;

public interface CollectMusicService {
    Result<Map> collectSongToCollectionLike(Favorites jsonSrc);

    Exits getCollectSExist(Favorites jsonSrc);
    ResultData<SongDetail> getRandomMusic();
}
