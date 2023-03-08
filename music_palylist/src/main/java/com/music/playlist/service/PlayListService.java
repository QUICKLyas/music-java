package com.music.playlist.service;

import com.music.commons.pojo.Result;

import java.util.Map;

public interface PlayListService {
    Result<Map> getDefaultPlayList();
}
