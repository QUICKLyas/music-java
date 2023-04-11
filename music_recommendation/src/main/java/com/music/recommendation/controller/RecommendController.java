package com.music.recommendation.controller;


import com.music.commons.pojo.resbody.Result;
import com.music.commons.pojo.reqbody.RecommendationCondition;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.*;

import static com.music.commons.utils.DataUtils.time;

@CrossOrigin
@RestController
@RequestMapping("/recommend") // recommend/playlist
public class RecommendController {
    @PostMapping("/song")
    public Result getUserPlayListRecommendation(@RequestBody RecommendationCondition recommendationCondition) {
        System.out.println("["+time()+"]: "+"request body's json = " + recommendationCondition);
        // 在service层检查数据
        return null;
    }
    @PostMapping("/playlist")
    public Result getRandomPlayList(@RequestBody RecommendationCondition recommendationCondition){
        return null;
    }
}

