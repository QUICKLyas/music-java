package com.music.song.controller;

import com.music.commons.pojo.resbody.Result;
import com.music.commons.pojo.reqbody.TagCondition;
import com.music.song.service.SongWithTagService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.music.commons.utils.DateUtils.time;

@RestController
@CrossOrigin
@RequestMapping("/tag")
public class SongWithTagController {
    @Resource
    SongWithTagService tag4SongService;
    @PostMapping("/default")
    public Result getSongByTag(@RequestBody TagCondition tag){
        System.out.println("["+time()+"]: "+"request body's json = " + tag);
        return tag4SongService.getSong(tag);
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
