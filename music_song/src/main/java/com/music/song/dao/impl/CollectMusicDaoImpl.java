package com.music.song.dao.impl;

import com.music.commons.pojo.menu.CodeEnum;
import com.music.commons.pojo.menu.Keys;
import com.music.song.dao.CollectMusicDao;
import com.music.song.pojo.Like;
import com.music.song.pojo.SongDetail;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

import static com.music.commons.utils.ListUtils.makeListKeyId;

@Repository
public class CollectMusicDaoImpl implements CollectMusicDao {
    @Resource
    private MongoTemplate mongoTemplate;

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
     * 本方法是为了确认歌曲是否已经被收藏收藏
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
        return songIds.contains(musicId);
//        Query songQuery = Query.query(
//                Criteria.where("id").is(musicId)
//        );
//        Song song = mongoTemplate.findOne(songQuery, Song.class);
//        // 确保结果不是空，如果为空，那么返回false，表示获取失败
//        if (song == null) return false;
//        String success = String.valueOf(song.getSongAble().get("success"));
//        return Objects.equals(success, "true");
    }


    /**
     *
     * @return
     */
    @Override
    public List<SongDetail> getRandomMusic() {

        return null;
    }
}
