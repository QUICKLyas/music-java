package com.music.playlist.controller;

import com.ctc.wstx.shaded.msv_core.grammar.relax.RELAXModule;
import com.music.commons.pojo.DataR;
import com.music.commons.pojo.Result;
import com.music.playlist.service.PlayListService;
import javax.annotation.Resource;

import com.music.pojo.reqbody.TagCondition;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.music.commons.utils.DataUtils.timeUTC;

@RestController
@CrossOrigin
@RequestMapping("/pl")
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
        System.out.println("["+timeUTC()+"]: "+"request body's json = " + tagCondition);

        return playListService.getPlayList8Cat(tagCondition);
    }
    @GetMapping(value = "/default")
    public Result getPlayList(){
        System.out.println("["+timeUTC()+"]: "+"request body's json = " + null);
        return playListService.getDefaultPlayList() ;
    }
}

