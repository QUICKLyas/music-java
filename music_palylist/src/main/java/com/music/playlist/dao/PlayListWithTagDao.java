package com.music.playlist.dao;

import com.music.playlist.pojo.PlayList;
import reactor.util.annotation.Nullable;

import java.util.List;

public interface PlayListWithTagDao {
    List<PlayList> getPlayListFromMongo(String tag, @Nullable Integer pageIndex, @Nullable Integer pageSize);

}
