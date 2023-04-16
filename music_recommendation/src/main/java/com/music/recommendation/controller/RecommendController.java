package com.music.recommendation.controller;


import com.music.commons.pojo.resbody.Result;
import com.music.commons.pojo.reqbody.RecommendationCondition;
import com.music.recommendation.service.RecommendationService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.music.commons.utils.DataUtils.time;

/**
 * ps
 * 程序输入状况
 * userId 为 null ， 没有用户传入，那么这种情况就是直接使用
 * userId 不为null ， 表示根据用户的信息进行推荐，
 * 前后两种状况使用的service层的方法是不同的
 */
@CrossOrigin
@RestController
@RequestMapping("/recommend") // recommend/playlist
public class RecommendController {
    @Resource
    private RecommendationService recommendationService;

    /**
     * 从用户的推荐结果中获取需要的推荐信息，并且实时运行推荐结果，更新推荐信息
     * @param recommendationCondition
     */
    @PostMapping("/song")
    public Result getUserSongRecommendation(@RequestBody RecommendationCondition recommendationCondition) {
        System.out.println("["+time()+"]: "+"request body's json = " + recommendationCondition);
        /*
          在service层检查数据，首先检查我们的flag，
          设置flag为对应的值
          song --> 1
         */
        recommendationCondition.setFlag(1);
        if (recommendationCondition.getUserId() ==null ) {
             // 根据mark值推荐数据
            return  recommendationService.getRecommendationsRandom(recommendationCondition);
        }
        return recommendationService.getRecommendations(recommendationCondition);
    }

    @PostMapping("/playlist")
    public Result getUserPlayListRecommendation(@RequestBody RecommendationCondition recommendationCondition){
        System.out.println("["+time()+"]: "+"request body's json = " + recommendationCondition);
        /**
         * 在service层检查数据，首先检查我们的flag，
         * 设置flag 为 对应的值
         * playlist --> 0
         */
        recommendationCondition.setFlag(0);
        if (recommendationCondition.getUserId() == null) {
            return recommendationService.getRecommendationsRandom(recommendationCondition);
        }
        return recommendationService.getRecommendations(recommendationCondition);
    }
}

