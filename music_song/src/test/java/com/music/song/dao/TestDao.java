package com.music.song.dao;

import com.music.song.MusicSongServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {MusicSongServerApplication.class})
@RunWith(SpringRunner.class)
public class TestDao {
    @Autowired
    private SongDao songDao;
    @Autowired
    private CollectMusicDao collectMusicDao;

    @Autowired
    private SongWithTagDao tag4SongDao;

    @Test
    public void TestCollectMusicService () {
//        Integer musicId, String userId
        collectMusicDao.getCollectSExist(3951888,"1a78b6d8b67911ed95dd00155dadb10b");
    }

    @Test
    public void TestTag4Song() {
        tag4SongDao.getRandomSongsFromMongo("ACG");
    }
}
