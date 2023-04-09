package com.music.song.service;

import com.music.commons.pojo.Result;
import com.music.pojo.reqbody.Favorites;
import com.music.pojo.reqbody.NextPrevious;

import java.util.Map;

/**
 * Songs 服务接口
 */
public interface SongService {
    Result<Map> getNextSongFromCollectionPlaylist(NextPrevious jsonSrc);
    Result<Map> getPreviousSongFromCollectionPlayList(NextPrevious jsonSrc);
}
