package com.music.recommendation.dao.impl;

import com.music.recommendation.dao.RecommendationDao;
import com.music.recommendation.pojo.RecommendSong;
import com.music.recommendation.pojo.Recommendation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Repository
public class RecommendationDaoImpl implements RecommendationDao {

    @Resource
    private MongoTemplate mongoTemplate;
    @Override
    public List<Object> getRecommendationS(String userId, @Nullable Integer pageIndex, @Nullable Integer pageSize) {
        // 实现数据的获取
        // 通过userId获取对应用户的信息

        Criteria criteria = new Criteria();
        criteria.and("id").is(userId);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria)
        );
        AggregationResults<Recommendation> recommendations = mongoTemplate.aggregate(aggregation,Recommendation.class,Recommendation.class);
        Recommendation recommendation = recommendations.getMappedResults().get(0);
        List<Integer> songIdList = recommendation.getRecomSong();
        // 通过这些id，获得具体的歌曲信息

        return Collections.singletonList(songIdList);
    }

    @Override
    public List<Object> getRecommendationPL(String userId, @Nullable Integer pageIndex, @Nullable Integer pageSize) {
        return null;
    }

    @Override
    public List<Object> getRecommendationRandomS(@Nullable Integer pageIndex, @Nullable Integer pageSize) {
        return null;
    }

    @Override
    public List<Object> getRecommendationRandomPL(@Nullable Integer pageIndex, @Nullable Integer pageSize) {
        return null;
    }
//
//    private List<RecommendSong> getSongById(List<Integer> ids) {
//        return null;
//    }
}
