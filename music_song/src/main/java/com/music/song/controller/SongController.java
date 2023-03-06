package com.music.song.controller;

import com.music.commons.pojo.Result;
import com.music.song.pojo.Favorites;
import com.music.song.pojo.NextPrevious;
import com.music.song.service.SongService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController // 自动增加ResponseBody
@CrossOrigin // 当前类型下所有方法支持跨域
public class SongController {
    @Resource
    private SongService songService;
    /**
     * 需要处理跨域处理
     * @return
     * {
     *     "status":200
     *     "results":[Object,Object,Object...]
     * }
     */
    @GetMapping(value = "/search")
    public Result getSongs(){
        List<Integer> songIds = new ArrayList<Integer>();
        songIds.add(3951888);
        songIds.add(16880808);
        return songService.getSongs(songIds);
    }

    @RequestMapping(value = "/favorites",method = RequestMethod.POST)
    public Result insertSongLike(@RequestBody Favorites jsons) {
        System.out.println("request body = " + jsons);
        return songService.updateSongToCollectionLike(jsons);
    }

    /**
     * 通过playlistdetail 表获取信息，同时根据musicId 获取其下一首歌曲的信息
     * @return
     */
    @RequestMapping(value="next",method = RequestMethod.POST)
    public Result getNextSong(@RequestBody NextPrevious jsons){
        System.out.println("request body = " + jsons);
        return songService.getNextSongFromCollectionPlaylist(jsons);
    }
}
