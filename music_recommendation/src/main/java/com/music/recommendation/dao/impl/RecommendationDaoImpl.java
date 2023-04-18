package com.music.recommendation.dao.impl;

import com.music.recommendation.dao.RecommendationDao;
import com.music.recommendation.pojo.RecommendPLayList;
import com.music.recommendation.pojo.RecommendSong;
import com.music.recommendation.pojo.Recommendation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import java.util.regex.Pattern;

import static com.music.commons.utils.ListUtils.*;

@Repository
public class RecommendationDaoImpl implements RecommendationDao {

    @Resource
    private MongoTemplate mongoTemplate;
    @Value("${song.tag.number}")
    int tagSize;
    @Value("${song.default.some}")
    private int someS;
    @Value("${playlist.default.some}")
    private int somePL;
    @Value("${song.recommend.mark.gte}")
    private int markRear;
    @Value("${song.recommend.mark.lte}")
    private long markTop;
    @Value("${playlist.recommend.subscribedCount.gte}")
    private int subscribedCountRear;
    @Value("${playlist.recommend.subscribedCount.lte}")
    private long subscribedCountTop;
    @Value("${playlist.recommend.playCount.gte}")
    private int playCountRear;
    @Value("${playlist.recommend.playCount.lte}")
    private long playCountTop;

    @Override
    public List<RecommendSong.Song> getRecommendationS(String userId,@Nullable Integer pageIndex, @Nullable Integer pageSize) {
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
    public List<RecommendPLayList.PlayList> getRecommendationPL(String userId,@Nullable Integer pageIndex, @Nullable Integer pageSize) {
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
        /*
            首先获取entrySet 本身数据量稳定的不超过100
         */
        List<String> tags = sortHashMapByValue(tagsRate,tagSize);
        // 生成匹配公式
        Pattern pattern = Pattern.compile(list2String(tags), Pattern.CASE_INSENSITIVE);
        Aggregation aggregationPL = Aggregation.newAggregation(
                // 查询包含该数据的
                Aggregation.match(Criteria.where("tag").regex(pattern)),
                Aggregation.sample(pageSize==null || pageSize == 0?somePL:pageSize)
        );
        AggregationResults<RecommendPLayList.PlayList> playListsA = mongoTemplate.aggregate(aggregationPL, RecommendPLayList.PlayList.class,RecommendPLayList.PlayList.class);
        return playListsA.getMappedResults();
    }

    @Override
    public List<RecommendSong.Song> getRecommendationRandomS(@Nullable Integer pageIndex, @Nullable Integer pageSize) {
        /*
           通过mark数量来获取数据，根据一定的分布获取数据
           不会传入用户的信息，直接就是从数据库中获取数据，按照mark值进行数据的排序和获取
         */

        // 实现数据的获取
        // 通过userId获取对应用户的信息
        Criteria criteria = new Criteria();
        criteria.and("mark").gte(markRear);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.sample(pageSize==null|| pageSize==0?someS:pageSize)
        );
        AggregationResults<RecommendSong.Song> recommendations = mongoTemplate.aggregate(aggregation, RecommendSong.Song.class, RecommendSong.Song.class);

        return recommendations.getMappedResults();
    }

    @Override
    public List<RecommendPLayList.PlayList> getRecommendationRandomPL(@Nullable Integer pageIndex, @Nullable Integer pageSize) {
        Criteria criteria = new Criteria();
        criteria.and("playCount").gte(playCountRear).lte(playCountTop).and("subscribedCount").gte(subscribedCountRear).lte(subscribedCountTop);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.sample(pageSize==null||pageSize == 0?someS:pageSize)
        );
        AggregationResults<RecommendPLayList.PlayList> recommendations = mongoTemplate.aggregate(aggregation, RecommendPLayList.PlayList.class, RecommendPLayList.PlayList.class);

        return recommendations.getMappedResults();
    }
}

