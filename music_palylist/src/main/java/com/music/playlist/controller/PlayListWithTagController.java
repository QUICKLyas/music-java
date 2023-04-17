package com.music.playlist.controller;


import com.music.commons.pojo.reqbody.TagCondition;
import com.music.commons.pojo.resbody.Result;
import com.music.playlist.pojo.PlayList;
import com.music.playlist.service.PlayListWithTagService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.music.commons.utils.DateUtils.time;

@RestController
@CrossOrigin
@RequestMapping("tag")
public class PlayListWithTagController {
    @Resource
    private PlayListWithTagService playListWithTagService;
    @PostMapping("/default")
    public Result getPlayListByTag(@RequestBody TagCondition tag) {
        System.out.println("["+time()+"]: "+"request body's json = " + tag);
        Result<PlayList> results = playListWithTagService.getPlayListByTag(tag);
        int size = results.getResult()== null ? 0:results.getResult().size();
        System.out.println("["+time()+"]: "+"result's size = " + size);
        return results;
    }
    @PostMapping("/random")
    public Result getRandomPlayListByTag(@RequestBody TagCondition tag) {
        System.out.println("["+time()+"]: "+"request body's json = " + tag);
        return null;
    }
}
