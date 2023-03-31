package com.music.playlist.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.music.playlist.dao.PlayListDao;
import com.music.playlist.pojo.PlayList;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.music.commons.utils.DataUtils.timeUTC;

@Repository
public class PlayListDaoImpl implements PlayListDao {
    @Resource
    MongoTemplate mongoTemplate;
    @Value("${playlist.tag.size}")
    private Integer size;

    /**
     * 首先会有一个默认size值，确定需要获取的playlist 大小  15
     * 随机获取playlist
     *
     * @return
     */
    @Override
    public List<Map> getDefaultPlayListFromCollectionPlayLists() {
        List<Map> resultList = new ArrayList<>(size); // 默认是由一个返回的数据
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("_id","id","name","coverImgUrl",
                        "coverImgId","description","tags","playCount",
                        "creator","subscribers"),
                Aggregation.sample(size)
        );
        AggregationResults<PlayList> songPL = mongoTemplate.aggregate(aggregation,PlayList.class,PlayList.class);
        List<PlayList> playLists = songPL.getMappedResults();
        for (PlayList itemPlayList : playLists){
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(playLists.get(0));
            resultList.add(jsonObject);
        }
        return resultList;
    }

    /**
     * 获取歌单列表，需要考虑的是返回的数据的问题，
     * 首先是根据数据族中的数据随机获取，
     * 或者是直接从
     * @param tags
     * @param page
     * @return
     */
    @Override
    public List<PlayList> getPlayListCatFromMongo(List<String> tags, Integer page) {
        Pageable pageable = PageRequest.of(page==null? 0:page, size);
        Criteria criteria = new Criteria();
//        System.out.println(tags);
        criteria.and("tags").all(tags);
        if (page == null) return null;
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.skip(Long.valueOf(page)),
                Aggregation.match(criteria),
                Aggregation.sample(size));
        AggregationResults<PlayList> playLists = mongoTemplate.aggregate(aggregation,PlayList.class,PlayList.class);
        List<PlayList> playList = playLists.getMappedResults();
        System.out.println("["+timeUTC()+"]: "+"playList's size is "+playList.size());
        return playList;
    }
}
