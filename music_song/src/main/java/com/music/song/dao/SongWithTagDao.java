package com.music.song.dao;

import com.music.song.pojo.Song8Tag;
import reactor.util.annotation.Nullable;

import java.util.List;

public interface SongWithTagDao {
    List<Song8Tag> getSongFromMongo(String tag,@Nullable Integer pageIndex,@Nullable Integer pageSize);
    List<Song8Tag> getRandomSongsFromMongo(String tag);
}
