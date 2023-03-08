package com.music.playlist.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.music.playlist.dao.PlayListDao;
import com.music.playlist.pojo.PlayList;
import jakarta.annotation.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PlayListDaoImpl implements PlayListDao {
    @Resource
    MongoTemplate mongoTemplate;

    /**
     * 首先会有一个默认size值，确定需要获取的playlist 大小  15
     * 随机获取playlist
     *
     * @return
     */
    @Override
    public List<Map> getDefaultPlayListFromCollectionPlayLists() {
        int size = 15;
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


    public Integer setSize(Integer size) {
        return size;
    }
    public Integer setSize() {
        return 15;
    }
}
