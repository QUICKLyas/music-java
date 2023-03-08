package com.music.song.controller;

import com.music.commons.pojo.Result;
import com.music.song.pojo.reqbody.Favorites;
import com.music.song.pojo.reqbody.NextPrevious;
import com.music.song.service.SongService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@RestController // 自动增加ResponseBody
@CrossOrigin // 当前类型下所有方法支持跨域
public class SongController {
    @Resource
    private SongService songService;



    @RequestMapping(value = "/favorites",method = RequestMethod.POST)
    public Result insertSongLike(@RequestBody Favorites jsons) {
        System.out.println("request body's json= " + jsons);
        return songService.updateSongToCollectionLike(jsons);
    }

    /**
     * 通过playlistdetail 表获取信息，同时根据musicId 获取其下一首歌曲的信息
     * @return
     */
    @RequestMapping(value="/next",method = RequestMethod.POST)
    public Result getNextSong(@RequestBody NextPrevious jsons){
        System.out.println("request body's json = " + jsons);
        return songService.getNextSongFromCollectionPlaylist(jsons);
    }
    /**
     * 通过playlistdetail 表获取信息，同时根据musicId获取其下一首歌曲的信息
     *
     */
    @RequestMapping(value="/previous",method = RequestMethod.POST)
    public Result getPreviousSong(@RequestBody NextPrevious jsons) {
        System.out.println("request body's json = "+ jsons);
        return null;
    }
}
