package com.music.playlist.dao;

import com.music.playlist.MusicPlayListServerApplication;
import com.music.playlist.pojo.PlayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = {MusicPlayListServerApplication.class})
@RunWith(SpringRunner.class)
public class TestPlayListDao {
    @Autowired
    PlayListDao playListDao;
    @Autowired
    PlayListWithTagDao playListWithTagDao;
    @Test
    public void getDefaultDao(){

        List<Map> playLists =playListDao.getDefaultPlayListFromCollectionPlayLists();
        System.out.println(playLists.size());
        for (Map item : playLists) {
            System.out.println(item);
        }
    }
    @Test
    public void testGetPlayListCatFromMongo(){
        List<String> tags = new ArrayList<>();
        tags.add("ACG");
        tags.add("BLUE");
        playListDao.getPlayListCatFromMongo(tags,1);
    }
    @Test
    public void testPlayListWithTag(){
        String tag = "ACG";
        List<PlayList> playLists = playListWithTagDao.getPlayListFromMongo(tag,null,null);
        for (PlayList playList : playLists) {
            System.out.println(playList.toString());
        }
    }

}
