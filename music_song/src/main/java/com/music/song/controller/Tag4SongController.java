package com.music.song.controller;

import com.music.commons.pojo.resbody.Result;
import com.music.commons.pojo.reqbody.TagCondition;
import com.music.song.pojo.Song8Tag;
import com.music.song.service.Tag4SongService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.music.commons.utils.DataUtils.time;

@RestController
@CrossOrigin
@RequestMapping("/tag")
public class Tag4SongController {
    @Resource
    Tag4SongService tag4SongService;
    @PostMapping("/")
    public Result getSongByTag(@RequestBody TagCondition tag){
        System.out.println("["+time()+"]: "+"request body's json = " + tag);
        Result<Song8Tag> result;
        result = tag4SongService.getSong(tag);
        return result;
    }

    /**
     * 原接口名字为random，随机获取一定数量的某个tag 的 歌曲
     * @param tag
     * @return
     */
    @PostMapping("/random")
    public Result getRandomSong8Tag(@RequestParam String tag){
        System.out.println("["+time()+"]: "+"request body's json = " + tag);
        return tag4SongService.getRandomSongs(tag);
    }
}
