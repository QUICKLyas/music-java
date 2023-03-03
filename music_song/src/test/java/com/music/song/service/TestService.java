package com.music.song.service;

import com.music.commons.pojo.Result;
import com.music.song.MusicSongServerApplication;
import com.music.song.pojo.Song;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(classes = {MusicSongServerApplication.class})
@RunWith(SpringRunner.class)
public class TestService {
    @Autowired
    private SongService songService;
    @Test
    public void testSelect() {
        Result<Song> songResult =  songService.getSongs();
        System.out.println(songResult.getStatus() );
        System.out.println(songResult.getResults());
    }
}