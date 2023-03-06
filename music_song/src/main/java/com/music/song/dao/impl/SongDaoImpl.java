package com.music.song.dao.impl;

import com.ctc.wstx.shaded.msv_core.relaxns.reader.IncludeGrammarState;
import com.music.commons.pojo.CodeEnum;
import com.music.commons.pojo.Keys;
import com.music.commons.utils.ListUtils;
import com.music.song.pojo.Like;
import com.music.song.dao.SongDao;
import com.music.song.pojo.PlaylistDetail;
import com.music.song.pojo.Song;
import com.music.song.pojo.SongDetail;
import jakarta.annotation.Resource;
import org.bson.types.Code;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.music.commons.utils.ListUtils.makeListKeyId;

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

    /**
     * 寻找数据，数据库中有该用户，就获取其中的歌曲list，
     * 判断歌曲list中是否有musicId，
     * 如果有说明需要取消收藏，取出该条数据
     * 如果没有就要加入数据
     * @param userId
     * @param musicId
     * @return
     * important things
     * 返回的格式是一个list->map，确定有固定 2 个 K-V对
     * Map格式
     * {
     *      "Action": "UnBookMArk/BookMark"
     *     "Answer": 返回操作结果信息
     * }
     */
    @Override
    public List<Map> updateSongToCollectionLike(String userId, Integer musicId,List<String> tags) {
        Query query = Query.query(
                Criteria.where("id").is(userId)
        );
        Query musicQuery = Query.query(
                Criteria.where("id").is(musicId)
        );
        Map<String,Object> resultMap = new HashMap<>();
        List<Map> resultList = new ArrayList<>(1); // 默认是由一个返回的数据
        List<Like> likeList = mongoTemplate.find(query,Like.class);

        List<SongDetail> songDetails = mongoTemplate.find(musicQuery,SongDetail.class);
        Map<String,Object> musicMap = new HashMap<>();
        if (likeList.size() <= 0 || songDetails.size() <= 0){
            resultMap.put("Answer", CodeEnum.SUCCESS_BUT_NO_DATA.getDesc());
        } else {
            List<Integer> songList = makeListKeyId(likeList.get(0).getSongs());
            Like like = likeList.get(0);
            // 判断程序中是否存在这首歌的信息
            if (songList.contains(musicId)) {
                // 存在就删除这个数据 1103906769200 2203418396876
                like.getSongs().remove(songList.indexOf(musicId));
                // 将新的数据更新进入数据库中
                Update update = new Update();
                update.set("songs",like.getSongs());
                Object object = mongoTemplate.upsert(query,update,"like");
                resultMap.put(Keys.KEY_ACTION.getKey(), "UnBookMark");
                resultMap.put(Keys.KEY_ANSWER.getKey(), object);
            } else {
                // 根据songId 如何 完成
                musicMap.put("id",musicId);
                musicMap.put("name",songDetails.get(0).getName());
                musicMap.put("union",tags);
                like.getSongs().add(0,musicMap);
                // 将数据更新到数据库中
                Update update = new Update();
                update.set("songs",like.getSongs());
                Object object = mongoTemplate.upsert(query,update,"like");
                resultMap.put(Keys.KEY_ACTION.getKey(), "BookMark");
                resultMap.put(Keys.KEY_ANSWER.getKey(),object);
            }
        }
        resultList.add(resultMap);
        return resultList;
    }

    /**
     * 当前的方法是为了从数据库中获取一首歌 的 下一首
     * 首先应该确定这个playlist是存在的
     * 之后存获取的数据中摘取歌曲的部分，并确定位置，然后获得歌曲下一首的位置
     *
     * @param playlistId
     * @param musicId
     * @return
     * Map格式
     * {
     *     "Answer": 返回操作结果信息
     * }
     */
    @Override
    public List<Map> getNextSongFromCollectionPlaylist(Long playlistId, Integer musicId) {
        Map<String,Object> resultMap = new HashMap<>(1);
        List<Map> resultList = new ArrayList<>(1); // 默认是由一个返回的数据
        Song song = null;
        if (playlistId == null ) { // 返回随机的歌曲
            // 随机获取一条数据，可能需要根据某个条件进行查询
            Aggregation aggregation = Aggregation.newAggregation(
                    // 查询不等于 的 值
                    Aggregation.match(Criteria.where("id").ne(musicId)),
                    Aggregation.sample(1)
            );
            AggregationResults<Song> songA = mongoTemplate.aggregate(aggregation,Song.class,Song.class);
            song = songA.getMappedResults().get(0);
            resultMap.put("id",song.getId());
            resultMap.put("name",song.getName());
            // 用不上，前端通过id 直接通过另一个服务获取
            // resultMap.put("songUrl",song.getSongUrl());
            resultMap.put("songAble",song.getSongAble());
            resultMap.put("tags",song.getTags());
            resultList.add(resultMap);
            return resultList;
        }
        // 如果上面的判断通过下面进行正式获取
        Query playlistQuery = Query.query(
                Criteria.where("id").is(playlistId)
        );
        List<PlaylistDetail> playlistDetail = mongoTemplate.find(playlistQuery, PlaylistDetail.class);
        List<Map> tracks = playlistDetail.get(0).getTrackIds();
        List<Integer> songIds = makeListKeyId(tracks);
        if (playlistDetail.size()<=0) {
            resultMap.put(Keys.KEY_ANSWER.getKey(), CodeEnum.SUCCESS_BUT_NO_DATA.getDesc());
        } else {
            int index;
            int length = playlistDetail.get(0).getTrackIds().size();
            if (musicId == null) {
                index = 0;
            } else {
                index = songIds.indexOf(musicId)+1;
            }
            // 获取下一首歌曲的musicId
            // 循环运算 index % length 当加 1 后的结果正好是 长度length ，通过这个计算式，回到 0
            Integer musicIdNext = (Integer)(tracks.get(index%length).get("id"));
            // 设定查询条件
            Query musicQuery = Query.query(
                    Criteria.where("id").is(musicIdNext)
            );
            // 获取 musicId 对应的内容信息
            List<Song> songs = mongoTemplate.find(musicQuery,Song.class);
            List<SongDetail> songDetails = mongoTemplate.find(musicQuery,SongDetail.class);
            if (songs.size() <= 0 || songDetails.size() <= 0 ) {
                resultMap.put(Keys.KEY_ANSWER.getKey(), CodeEnum.SUCCESS_BUT_NO_DATA.getDesc());
            } else {
                song = songs.get(0);
                resultMap.put("id",song.getId());
                resultMap.put("name",songDetails.get(0).getName());
                // 用不上，前端通过id 直接通过另一个服务获取
                // resultMap.put("songUrl",song.getSongUrl());
                resultMap.put("songAble",song.getSongAble());
                resultMap.put("tags",song.getTags());
            }
        }
        resultList.add(resultMap);
        return resultList;
    }
}
