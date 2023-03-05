package com.music.song.dao.impl;

import com.music.commons.pojo.CodeEnum;
import com.music.song.pojo.Like;
import com.music.song.dao.SongDao;
import com.music.song.pojo.Song;
import com.music.song.pojo.SongDetail;
import jakarta.annotation.Resource;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.music.commons.utils.ListUtils.makeListKeyId;

@Repository
public class SongDaoImpl implements SongDao {
    @Resource
    MongoTemplate mongoTemplate;
    CodeEnum codeEnum;
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

    /**
     * 寻找数据，数据库中有该用户，就获取其中的歌曲list，
     * 判断歌曲list中是否有musicId，如果有就返回
     * @param userId
     * @param musicId
     * @return
     * important things
     * 返回的格式是一个list->map，确定有固定 2 个 K-V对
     * Map格式
     * {
     *     "code": 2101,2102。2200
     *     "message": "用户存在，请创建用户"，"歌曲已收藏"，"成功"
     * }
     */
    @Override
    public List<Map> insertSongToCollectionLike(String userId, Integer musicId) {
        Query query = Query.query(
                Criteria.where("id").is(userId)
        );
        Map<String,Object> resultMap = new HashMap<>(2);
        List<Map> resultList = new ArrayList<>(1); // 默认是由一个返回的数据
        List<Like> likeList = mongoTemplate.find(query,Like.class);
        Map<String,Object> musicMap = new HashMap<>();
        if (likeList.size() <= 0 ){
            resultMap.put("code",codeEnum.SUCCESS_BUT_NO_DATA.getCode());
            resultMap.put("desc",codeEnum.SUCCESS_BUT_NO_DATA.getDesc());
        } else {
            List<Integer> songList = makeListKeyId(likeList.get(0).getSongs());
            if (songList.contains(musicId)) {
                resultMap.put("code",codeEnum.SUCCESS_BUT_HAS_DATA.getCode());
                resultMap.put("desc",codeEnum.SUCCESS_BUT_HAS_DATA.getDesc());
            } else {
                // 根据songId 如何 完成
                Query musicQuery = Query.query(
                        Criteria.where("id").is(musicId)
                );
                Song song = mongoTemplate.findOne(musicQuery,Song.class);
                SongDetail songDetail = mongoTemplate.findOne(musicQuery,SongDetail.class);
                Like like = likeList.get(0);
                musicMap.put("id",musicId);
                musicMap.put("name",songDetail.getName());
                musicMap.put("union",song.getTags());
                like.getSongs().add(0,musicMap);
                // 将数据更新到数据库中
                Update update = new Update();
                update.set("songs",like.getSongs());
                Object object = mongoTemplate.upsert(query,update,"like");
                resultMap.put("code",codeEnum.SUCCESS.getCode());
                resultMap.put("decs",codeEnum.SUCCESS.getDesc());
                resultMap.put("AcknowledgedUpdateResult",object);

            }
        }
        resultList.add(resultMap);
        return resultList;
    }
}
