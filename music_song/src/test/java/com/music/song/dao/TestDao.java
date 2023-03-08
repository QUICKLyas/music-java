package com.music.song.dao;

import com.music.song.MusicSongServerApplication;
import com.music.song.pojo.Song;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {MusicSongServerApplication.class})
@RunWith(SpringRunner.class)
public class TestDao {
    @Autowired
    private SongDao songDao;
}
