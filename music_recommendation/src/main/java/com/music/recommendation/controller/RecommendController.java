package com.music.recommendation.controller;


import com.music.commons.pojo.resbody.Result;
import com.music.commons.pojo.reqbody.RecommendationCondition;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/recommend") // recommend/playlist
public class RecommendController {
    @PostMapping("/song")
    public Result getUserPlayListRecommendation(@RequestBody RecommendationCondition recommendationCondition) {
        // 检查数据
        return null;
    }
    @PostMapping("/random")
    public Result getRandomPlayList(@RequestBody RecommendationCondition recommendationCondition){
        return null;
    }
}

