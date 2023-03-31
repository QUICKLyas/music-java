package com.music.song.controller;

import com.music.commons.pojo.Exits;
import com.music.commons.pojo.Result;
import com.music.commons.pojo.ResultMusic;
import com.music.pojo.reqbody.CollectMusic;
import com.music.song.service.CollectMusicService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.music.commons.utils.DataUtils.timeUTC;

@RestController
@CrossOrigin
@RequestMapping("/collect")
public class CollectMusicController {
    @Resource
    private CollectMusicService collectMusicService;
    @PostMapping("/exists")
    public Exits getCollectSongExist(@RequestBody CollectMusic collectMusic){
        System.out.println("["+timeUTC()+"]: "+"request body's json = " + collectMusic);
        return collectMusicService.getCollectSExist(collectMusic);
    }

    /**
     * 按照tag 获取歌曲信息,暂时不写
     * @return
     */
    @PostMapping("/t")
    public ResultMusic getRandomMusicBy() {
        return null;
    }
}
