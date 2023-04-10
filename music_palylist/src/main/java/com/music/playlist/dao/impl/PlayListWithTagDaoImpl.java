package com.music.playlist.dao.impl;

import com.music.playlist.dao.PlayListWithTagDao;
import com.music.playlist.pojo.PlayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class PlayListWithTagDaoImpl implements PlayListWithTagDao {
    @Resource
    private MongoTemplate mongoTemplate;
    @Override
    public List<PlayList> getPlayListFromMongo(String tag, Integer pageIndex, Integer pageSize) {
        Criteria criteria = new Criteria();
        criteria.and("tags").is(tag);
        Query query = new Query(criteria);
        long count = mongoTemplate.count(query, PlayList.class);
        Pageable pageable = PageRequest.of(pageIndex==null? 0:pageIndex, pageSize==null ? 20:pageSize);
        List<PlayList> playLists = mongoTemplate.find(query.with(pageable),PlayList.class);
//        List<Integer> playListIds = Object2BeanIntegerList(playLists);
        return playLists;
    }
}
