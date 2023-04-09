package com.music.song.service;

import com.music.song.MusicSongServerApplication;
import com.music.commons.pojo.reqbody.NextPrevious;
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
//    @Test
//    public void testSelect() {
//        Result<Song> songResult =  songService.getSongs();
//        System.out.println(songResult.getStatus() );
//        System.out.println(songResult.getResults());
//    }
    @Test
    public void testInsertService(){
//        Map<String,Object> params = new HashMap<String,Object>();
//        params.put("music_id",3951888);
//        params.put("user_id",13215313);
//        List<String> tags= new ArrayList<>();
//        tags.add("ACG");
//        tags.add("BLUE");
//        params.put("tags",tags);
//        songService.insertSongToCollectionLike(params);
    }
    @Test
    public void testGetNextService(){
//        Map<String,Object> json = new HashMap<>();
//        json.put("playlistId","6666112560");
//        json.put("musicId",3951888);
//        6666112560
//        3951888
        NextPrevious nextPrevious = new NextPrevious(Long.parseLong("6666112560"),null);
        songService.getNextSongFromCollectionPlaylist(nextPrevious);
    }
}