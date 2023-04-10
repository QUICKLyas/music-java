package com.music.playlist.controller;


import com.music.commons.pojo.reqbody.TagCondition;
import com.music.commons.pojo.resbody.Result;
import org.springframework.web.bind.annotation.*;

import static com.music.commons.utils.DataUtils.time;

@RestController
@CrossOrigin
@RequestMapping("tag")
public class PlayListWithTagController {

    @PostMapping("/default")
    public Result getPlayListByTag(@RequestBody TagCondition tag) {
        System.out.println("["+time()+"]: "+"request body's json = " + tag);
        return null;
    }
    @PostMapping("/random")
    public Result getRandomPlayListByTag(@RequestBody TagCondition tag) {
        System.out.println("["+time()+"]: "+"request body's json = " + tag);
        return null;
    }
}
