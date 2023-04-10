package com.music.song.service;

import com.music.commons.pojo.resbody.Result;
import com.music.commons.pojo.reqbody.NextPrevious;
import com.music.commons.pojo.resbody.ResultMap;
import com.music.song.pojo.Song;

import java.util.Map;

/**
 * Songs 服务接口
 */
public interface SongService {
    ResultMap<Song> getRandomSong();
    Result<Song> getRandomSomeSongs();
    Result<Map> getNextSongFromCollectionPlaylist(NextPrevious jsonSrc);
    Result<Map> getPreviousSongFromCollectionPlayList(NextPrevious jsonSrc);
}
