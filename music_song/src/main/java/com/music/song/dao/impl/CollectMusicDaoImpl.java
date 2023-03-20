package com.music.song.dao.impl;

import com.music.song.dao.CollectMusicDao;
import com.music.song.pojo.Like;
import com.music.song.pojo.Song;
import com.music.song.pojo.SongDetail;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.music.commons.utils.ListUtils.makeListKeyId;

@Repository
public class CollectMusicDaoImpl implements CollectMusicDao {
    @Resource
    private MongoTemplate mongoTemplate;

    /**
     *
     * @param musicId
     * @param userId
     * @return
     */
    @Override
    public Boolean getCollectSExist(Integer musicId, String userId) {
        Query query = Query.query(
                Criteria.where("id").is(userId)
        );
        List<Like> likes= mongoTemplate.find(query,Like.class);
        List<Map> songs = likes.get(0).getSongs();
        List<Integer> songIds = makeListKeyId(songs);
        // 确保包含在用户收藏的结果中
        if (!songIds.contains(musicId)) return null;
        Query songQuery = Query.query(
                Criteria.where("id").is(musicId)
        );
        Song song = mongoTemplate.findOne(songQuery, Song.class);
        // 确保结果不是空
        if (song == null) return null;
        String success = String.valueOf(song.getSongAble().get("success"));
        return Objects.equals(success, "true");
    }
    @Override
    public List<SongDetail> getRandomMusic() {
        return null;
    }
}
