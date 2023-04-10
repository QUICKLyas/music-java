package com.music.playlist.dao;

import com.music.playlist.pojo.PlayList;

import java.util.List;
import java.util.Map;

public interface PlayListDao {
    List<PlayList> getRandomPlayList();
    List<PlayList> getRandomSomePlayList();

    List<Map> getDefaultPlayListFromCollectionPlayLists();
    List<PlayList> getPlayListCatFromMongo(List<String> tags, Integer page);

}
