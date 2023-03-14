package com.music.song.controller;

import com.music.commons.pojo.Result;
import com.music.pojo.reqbody.TagCondition;
import com.music.song.pojo.Song8Tag;
import com.music.song.service.Tag4SongService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/song")
public class Tag4SongController {
    @Resource
    Tag4SongService tag4SongService;
    @PostMapping("/tag")
    public Result getSongByTag(@RequestBody TagCondition tag){
        System.out.println("request body's json = " + tag);
        Result<Song8Tag> result;
        result = tag4SongService.getSong(tag);
        return result;
    }
}
