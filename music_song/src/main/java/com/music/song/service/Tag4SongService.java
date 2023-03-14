package com.music.song.service;

import com.music.commons.pojo.Result;
import com.music.pojo.reqbody.TagCondition;
import com.music.song.pojo.Song8Tag;

import javax.annotation.Resource;

public interface Tag4SongService {
    Result<Song8Tag> getSong(TagCondition tagCondition);
    Result<Song8Tag> getSongs(TagCondition tagCondition);
}
