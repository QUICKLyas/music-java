package com.music.recommendation.service.impl;

import com.music.commons.pojo.menu.CodeEnum;
import com.music.commons.pojo.resbody.Result;
import com.music.commons.pojo.reqbody.RecommendationCondition;
import com.music.recommendation.pojo.Recommendation;
import com.music.recommendation.service.RecommendationService;
import org.springframework.stereotype.Service;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    // 获得推荐的数据，在获取前直运行程序，运行程序路径，是写在运行的propeties中
    @Override
    public Result<Recommendation> getRecommendations(Integer userId) {
        return null;
    }

    /**
     * 检测条件
     * 启动python程序
     * ，检测结果，
     * @param recommendationCondition
     * @return
     */
    @Override
    public Result<Recommendation> getRecommendationS(RecommendationCondition recommendationCondition) {
        if(recommendationCondition.getUserId() == null) {
            // 返回错误报文
            return new Result<>(CodeEnum.BAD_REQUEST.getCode(),false,null,CodeEnum.BAD_REQUEST.getDesc(), null);
        }

        return null;
    }

    @Override
    public Result<Recommendation> getRecommendationRandomS(RecommendationCondition recommendationCondition) {
        return null;
    }

    @Override
    public Result<Recommendation> getRecommendationPL(RecommendationCondition recommendationCondition) {
        return null;
    }

}
