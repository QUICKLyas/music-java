package com.music.recommendation.service.impl;

import com.music.commons.pojo.menu.CodeEnum;
import com.music.commons.pojo.resbody.Result;
import com.music.commons.pojo.reqbody.RecommendationCondition;
import com.music.recommendation.dao.RecommendationDao;
import com.music.recommendation.pojo.Recommendation;
import com.music.recommendation.service.RecommendationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Resource
    private RecommendationDao recommendationDao;
    // 获得推荐的数据，在获取前直运行程序，运行程序路径，是写在运行的propeties中

    /**
     * 这个方法，会检查flag来确定是什么情况，假设
     * @param recommendationCondition
     * @return
     */
    @Override
    public Result<Recommendation> getRecommendations(RecommendationCondition recommendationCondition) {

        /*
           song -> 0
           playlist -> 1
         */
        if(recommendationCondition.getFlag() == 0) {
            return null;
        } else if (recommendationCondition.getFlag() == 1) {
            return null;
        }
//        if(recommendationCondition.getUserId() == null) {
//            // 返回错误报文
//            return new Result<>(CodeEnum.BAD_REQUEST.getCode(),false,null,CodeEnum.BAD_REQUEST.getDesc(), null);
//        }
        return null;
    }

    /**
     * 本方法是随机获取一定数量的数据，我们呢建议使用正态分布的概率，获取以mark值排列的数据，保证收藏数较为中间数量的向用户推荐歌曲
     * ，检测结果，
     * @param recommendationCondition
     * @return
     */
    @Override
    public Result<Recommendation> getRecommendationsRandom(RecommendationCondition recommendationCondition) {
        return null;
    }


}
