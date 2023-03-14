package com.music.song.dao;

import com.music.song.pojo.Song8Tag;
import reactor.util.annotation.Nullable;

import java.util.List;

public interface Tag4SongDao {
    List<Song8Tag> getSongFromMongo(String tag,@Nullable Integer pageIndex,@Nullable Integer pageSize);
    List<Song8Tag> getSongsFromMongo(String tag, Integer pageIndex,Integer pageSize);
}
