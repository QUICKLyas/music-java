package com.music.playlist.controller;

import com.music.commons.pojo.DataR;
import com.music.commons.pojo.resbody.ResultData;
import com.music.commons.pojo.resbody.ResultMap;
import com.music.playlist.service.PlayListService;
import javax.annotation.Resource;

import com.music.commons.pojo.reqbody.TagCondition;
import org.springframework.web.bind.annotation.*;

import static com.music.commons.utils.DateUtils.time;

@RestController
@CrossOrigin
@RequestMapping("/playlist")
public class PlayListController {
    @Resource
    PlayListService playListService;

    /**
     * 通过标签获取获取数据
     * @param tagCondition
     * @return
     */
    @PostMapping("/cat")
    public DataR getPlayListCat(@RequestBody TagCondition tagCondition){
        System.out.println("["+time()+"]: "+"request body's json = " + tagCondition);
        return playListService.getPlayList8Cat(tagCondition);
    }
    @PostMapping(value = "/one")
    public ResultMap getRandomOnePlayList(){
        System.out.println("["+time()+"]: " +"catch one playlist from mongo...");
        return playListService.getRandomPlayList();
    }

    @PostMapping(value = "/some")
    public ResultData getRandomSomePlayLists(){
        System.out.println("["+time()+"]: " +"catch some playlists from mongo...");
        return playListService.getRandomSomePlayList();
    }
}

