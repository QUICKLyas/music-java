package com.music.recommendation.service;

import com.music.commons.pojo.resbody.Result;
import com.music.commons.pojo.reqbody.RecommendationCondition;
import com.music.recommendation.pojo.Recommendation;

public interface RecommendationService {
    /**
     * 通过用户来获取对应用户的推荐结果
     * 测试阶段仅仅使用已经生成推荐结果的对象
     * 后面需要在每次获取用户推荐结果后，
     * 调用python程序为下次推荐进行计算
     * @param userId
     * @return
     */
//    Result
    Result<Recommendation> getRecommendations(Integer userId);
    Result<Recommendation> getRecommendationS(RecommendationCondition recommendationCondition);
    Result<Recommendation> getRecommendationPL(RecommendationCondition recommendationCondition);
}
