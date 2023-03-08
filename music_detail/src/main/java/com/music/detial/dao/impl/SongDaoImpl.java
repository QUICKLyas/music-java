package com.music.detial.dao.impl;

import com.music.detial.dao.SongDao;
import com.music.pojo.SongDetail;
import jakarta.annotation.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class SongDaoImpl implements SongDao {
    @Resource
    private MongoTemplate mongoTemplate;

    final private Integer limit = 100;

    /**
     * page ,限制某个获取的信息的大小和位置
     * @return
     */
    @Override
    public List<SongDetail> findAllSongDetail() {
        Query query = new Query();
//         query.limit(100);
        return mongoTemplate.find(query.limit(this.limit), SongDetail.class);
    }
}
