package com.music.recommendation.controller;

import com.music.commons.pojo.Result;
import com.music.pojo.reqbody.RecommendationCondition;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
// recommend
@RequestMapping("/r/s")
public class RecommendSongController {
    /**
     * 一下两种情况的就是如果没有确定用户id那么直接返回随机的结果
     * @param recommendationCondition
     * @return
     */
    @PostMapping("/")
    public Result getUserSongRecommendation(@RequestBody RecommendationCondition recommendationCondition){
        // 生成推荐结果
        return null;
    }
    @PostMapping("/random")
    public Result getRandomSong(@RequestBody RecommendationCondition recommendationCondition){
        return null;
    }

}
