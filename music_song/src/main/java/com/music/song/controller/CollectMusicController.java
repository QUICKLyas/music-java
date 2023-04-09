package com.music.song.controller;

import com.music.commons.pojo.Exits;
import com.music.commons.pojo.Result;
import com.music.commons.pojo.ResultData;
import com.music.pojo.reqbody.Favorites;
import com.music.song.service.CollectMusicService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.music.commons.utils.DataUtils.time;

@RestController
@CrossOrigin
@RequestMapping("/collect")
public class CollectMusicController {
    @Resource
    private CollectMusicService collectMusicService;
    @PostMapping("/exists")
    public Exits getCollectSongExist(@RequestBody Favorites jsons){
        System.out.println("["+time()+"]: "+"request body's json = " + jsons);
        return collectMusicService.getCollectSExist(jsons);
    }
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Result insertSongLike(@RequestBody Favorites jsons) {
        System.out.println("["+time()+"]: "+"request body's json = " + jsons);
        return collectMusicService.collectSongToCollectionLike(jsons);
    }
    /**
     * 按照tag 获取歌曲信息,暂时不写
     * @return
     */
    @PostMapping("/song")
    public ResultData getRandomMusicBy() {


    }
}
