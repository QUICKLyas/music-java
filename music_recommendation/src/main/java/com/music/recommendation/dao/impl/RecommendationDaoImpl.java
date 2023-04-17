package com.music.recommendation.dao.impl;

import com.music.recommendation.dao.RecommendationDao;
import com.music.recommendation.pojo.RecommendPLayList;
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
import java.util.*;
import java.util.Map.Entry;

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
        // 方法调用返回一个只包含指定对象的不可变列表。
        // return Collections.singletonList(songIdList);
        return songs;
    }

    @Override
    public List<RecommendPLayList.PlayList> getRecommendationPL(String userId, @Nullable Integer pageIndex, @Nullable Integer pageSize) {
        // 实现数据的获取
        // 通过userId获取对应用户的信息
        Criteria criteria = new Criteria();
        criteria.and("id").is(userId);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria)
        );
        AggregationResults<Recommendation> recommendations = mongoTemplate.aggregate(aggregation,Recommendation.class,Recommendation.class);
        Recommendation recommendation = recommendations.getMappedResults().get(0);
        HashMap<String,Double> tagsRate = recommendation.getTagsRate();
        System.out.println(tagsRate);
        /*
            首先获取entrySet 本身数据量稳定的不超过100
         */
        List<Double> returnResult = new LinkedList<Double>();
        Set<Entry<String,Double>> entrySet = tagsRate.entrySet();
        Iterator<Entry<String,Double>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            returnResult.add(iterator.next().getValue());
        }
        
//        System.out.println("\nAfter sorting ascending order......");
//        printMap(sortedMapAsc);
        return null;
    }

    @Override
    public List<RecommendSong.Song> getRecommendationRandomS(@Nullable Integer pageIndex, @Nullable Integer pageSize) {
        return null;
    }

    @Override
    public List<RecommendPLayList.PlayList> getRecommendationRandomPL(@Nullable Integer pageIndex, @Nullable Integer pageSize) {
        return null;
    }

}
//        first way 循环程序性
//        for(int key : map.keySet()){
//            System.out.println(key);
//        }
//        second way （推荐）
//        for(HashMap.Entry<String,Double> entry : tagsRate.entrySet()) {
//            System.out.println(entry.getKey());
//        }
//        third way 迭代器
//        Iterator<HashMap.Entry<Integer,Integer>> iterator = map.entrySet().iterator();
//        while(iterator.hasNext()){
//            System.out.println(iterator.next().getKey());
//        }
