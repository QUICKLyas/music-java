package com.music.recommendation.service.impl;

import com.music.commons.pojo.Result;
import com.music.recommendation.pojo.Recommendation;
import com.music.recommendation.service.RecommendationService;
import org.springframework.stereotype.Service;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    @Override
    public Result<Recommendation> getRecommendations(Integer userId) {
        return null;
    }
}
