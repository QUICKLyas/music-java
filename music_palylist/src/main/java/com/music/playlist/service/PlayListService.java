package com.music.playlist.service;

import com.music.commons.pojo.DataR;
import com.music.commons.pojo.resbody.Result;
import com.music.playlist.pojo.PlayList;
import com.music.commons.pojo.reqbody.TagCondition;

import java.util.Map;

public interface PlayListService {
    Result<Map> getDefaultPlayList();
    DataR<PlayList> getPlayList8Cat(TagCondition tagCondition);
}
