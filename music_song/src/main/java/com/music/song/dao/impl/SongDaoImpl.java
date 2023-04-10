package com.music.song.dao.impl;

import com.music.commons.pojo.menu.CodeEnum;
import com.music.commons.pojo.menu.Keys;
import com.music.song.dao.SongDao;
import com.music.song.pojo.PlaylistDetail;
import com.music.song.pojo.Song;
import com.music.song.pojo.SongDetail;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
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
    @Value("${song.default.one}")
    private Integer one;
    @Value("${song.default.some}")
    private Integer some;

    /**
     * 随机获取歌曲一首
     * @return
     */
    @Override
    public List<Song> getRandomSong() {
        // 随机获取一首歌曲
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.sample(one));
        AggregationResults<Song> songA = mongoTemplate.aggregate(aggregation,Song.class,Song.class);
        List<Song> resultSong = songA.getMappedResults();
        return resultSong;
    }

    /**
     * 随机获取一定数量的歌曲
     * @return
     */
    @Override
    public List<Song> getRandomSomeSongs() {
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.sample(some));
        AggregationResults<Song> songA = mongoTemplate.aggregate(aggregation,Song.class,Song.class);
        List<Song> resultSong = songA.getMappedResults();
        return resultSong;
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

    @Override
    public List<Map> getPreviousSongFromCollectionPlaylist(Long playlistId, Integer musicId) {
        Map<String,Object> resultMap = new HashMap<>(1);
        List<Map> resultList = new ArrayList<>(1); // 默认是由一个返回的数据
        Song song = null;
        if (playlistId == null ) { // 返回随机的歌曲
            // 因为这个时候必定是随机获取的一首歌曲
            // 通过上个方法中的一部分内容运行获取结果
            return getNextSongFromCollectionPlaylist(playlistId, musicId);
        } // 如果上面的判断通过下面进行正式获取
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
            if (musicId == null) { // 如果musicId 没有传输，所以仍然是存第一首开始播放
                index = 0;
            } else { // 有musicId
                index = songIds.indexOf(musicId)-1;
            }
            // 获取下一首歌曲的musicId
            // 循环运算 index % length 当加 1 后的结果正好是 长度length ，通过这个计算式，回到 0
            // 假设出现 -1 % length 此时结果不正确  使用 length + index % length
            Integer musicIdPrevious = (Integer)(tracks.get((length+index)%length).get("id"));
            // 设定查询条件
            Query musicQuery = Query.query(
                    Criteria.where("id").is(musicIdPrevious)
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
