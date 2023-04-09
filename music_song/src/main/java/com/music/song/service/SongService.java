package com.music.song.service;

import com.music.commons.pojo.resbody.Result;
import com.music.commons.pojo.reqbody.NextPrevious;
import com.music.commons.pojo.resbody.ResultMap;

import java.util.Map;

/**
 * Songs 服务接口
 */
public interface SongService {
    ResultMap<Map> getRandomSong();
    Result<Map> getNextSongFromCollectionPlaylist(NextPrevious jsonSrc);
    Result<Map> getPreviousSongFromCollectionPlayList(NextPrevious jsonSrc);
}
