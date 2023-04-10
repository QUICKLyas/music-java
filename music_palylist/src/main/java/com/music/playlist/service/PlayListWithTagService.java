package com.music.playlist.service;

import com.music.commons.pojo.reqbody.TagCondition;
import com.music.commons.pojo.resbody.Result;
import com.music.playlist.pojo.PlayList;

public interface PlayListWithTagService {
    Result<PlayList> getPlayListByTag(TagCondition tagCondition);
}
