package com.music.song.service;

import com.music.commons.pojo.resbody.Result;
import com.music.commons.pojo.reqbody.TagCondition;
import com.music.song.pojo.Song8Tag;

public interface SongWithTagService {
    Result<Song8Tag> getSong(TagCondition tagCondition);
    Result<Song8Tag> getRandomSongs(String tag);
}
