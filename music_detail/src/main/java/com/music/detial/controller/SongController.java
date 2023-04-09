package com.music.detial.controller;

import com.music.pojo.SongDetail;
import com.music.detial.service.SongService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class SongController {
    @Resource
    private SongService songService;
    @GetMapping("/all_song")
    public List<SongDetail> getAllSongDetails(){
        return songService.getAllSongDetail();
    }
}
