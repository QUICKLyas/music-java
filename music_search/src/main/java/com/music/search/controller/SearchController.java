package com.music.search.controller;

import com.music.commons.pojo.resbody.Result;
import com.music.commons.pojo.reqbody.SearchCondition;
import com.music.search.pojo.SongDetail4ES;
import com.music.search.service.SongDetailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.music.commons.utils.DataUtils.timeUTC;

@RestController
@CrossOrigin
public class SearchController {
    @Resource
    private SongDetailService songDetailService;
    @PostMapping("/search")
    public Result SearchSongByName(@RequestBody SearchCondition searchCondition) {
        System.out.println("["+timeUTC()+"]: "+"request body's json= " + searchCondition);
        SongDetail4ES songDetail = new SongDetail4ES();
        songDetail.setName(searchCondition.getMusicName());
        return songDetailService.findSongByName(songDetail,searchCondition.getPageIndex(),searchCondition.getPageSize());
    }


}
