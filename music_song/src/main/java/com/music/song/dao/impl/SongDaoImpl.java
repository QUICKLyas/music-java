package com.music.song.dao.impl;

import com.music.song.dao.SongDao;
import com.music.song.pojo.Song;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@Repository
public class SongDaoImpl implements SongDao {
    @Resource
    MongoTemplate mongoTemplate;
    /**
     *
     * @return
     */
    @Override
    public List<Song> getSongs(List<Integer> songIds) {
        Query query =Query.query(
                Criteria.where("id").in(songIds)
        );
        return mongoTemplate.find(query,Song.class);
    }

    @Override
    public List<Song> getSongDetail(Integer songId) {
        return null;
    }
}
