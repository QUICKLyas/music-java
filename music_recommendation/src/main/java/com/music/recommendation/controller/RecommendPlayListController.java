package com.music.recommendation.controller;


import com.music.commons.pojo.resbody.Result;
import com.music.commons.pojo.reqbody.RecommendationCondition;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/r/p") // recommend/playlist
public class RecommendPlayListController {
    @PostMapping("/")
    public Result getUserPlayListRecommendation(@RequestBody RecommendationCondition recommendationCondition) {
        return null;
    }
    @PostMapping("/random")
    public Result getRandomPlayList(@RequestBody RecommendationCondition recommendationCondition){
        return null;
    }
}

