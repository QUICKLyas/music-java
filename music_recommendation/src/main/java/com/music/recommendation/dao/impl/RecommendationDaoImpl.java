package com.music.recommendation.dao.impl;

import com.music.pojo.SongDetail;
import com.music.recommendation.dao.RecommendationDao;
import com.music.recommendation.pojo.RecommendSong;
import com.music.recommendation.pojo.Recommendation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class RecommendationDaoImpl implements RecommendationDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public List<RecommendSong.Song> getRecommendationS(String userId, @Nullable Integer pageIndex, @Nullable Integer pageSize) {
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
        /*
            此处有两种推荐的结构
            一种是按循环的格式诸葛获取歌曲信息，
            一种是批量地获取数据然后推荐给用户使用
            推荐使用第二种方式，提高数据库操作的效率
         */

        Query query = Query.query(Criteria.where("id").in(songIdList));
        List<RecommendSong.Song>  songs=mongoTemplate.find(query, RecommendSong.Song.class);
//        for (RecommendSong.Song item : songs ) {
//            System.out.println("item = " + item);
//        }
        // 方法调用返回一个只包含指定对象的不可变列表。
        // return Collections.singletonList(songIdList);
        return songs;
    }

    @Override
    public List<Object> getRecommendationPL(String userId, @Nullable Integer pageIndex, @Nullable Integer pageSize) {
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
}
