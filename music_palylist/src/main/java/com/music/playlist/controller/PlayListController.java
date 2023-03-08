package com.music.playlist.controller;

import com.music.commons.pojo.Result;
import com.music.playlist.service.PlayListService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class PlayListController {
    @Resource
    PlayListService playListService;

    @GetMapping(value = "/default")
    public Result getPlayList(){
        System.out.println("request body's json = " + null);
        return playListService.getDefaultPlayList() ;
    }
}

