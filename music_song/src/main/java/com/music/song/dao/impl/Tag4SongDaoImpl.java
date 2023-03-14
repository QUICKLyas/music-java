package com.music.song.dao.impl;

import com.music.commons.utils.ListUtils;
import com.music.commons.utils.ObjectUtils;
import com.music.song.dao.Tag4SongDao;
import com.music.song.pojo.Song;
import com.music.song.pojo.Song8Tag;
import com.music.song.pojo.SongDetail;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.util.annotation.Nullable;

import javax.annotation.Resource;
import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class Tag4SongDaoImpl implements Tag4SongDao {
    @Resource
    MongoTemplate mongoTemplate;

    /**
     * 根据tag 内容，随机获取歌曲的信息，不需要具体准确的结果
     * @param tag
     * @return
     */
    @Override
    public List<Song8Tag> getSongFromMongo(String tag, @Nullable Integer pageIndex,@Nullable Integer pageSize) {
        // 通过tag 获取歌曲的id
        Criteria criteria = new Criteria();
        criteria.and("tags").is(tag);
        Query query= Query.query(criteria);
        long count = mongoTemplate.count(query, Song.class);
        Pageable pageable = PageRequest.of(pageIndex==null? 0:pageIndex, pageSize==null ? 20:pageSize);
        List<Song> songList = mongoTemplate.find(query.with(pageable), Song.class);
        System.out.println(songList);
        System.out.println(songList.size());
        List<Map> results = new ArrayList<>(songList.size());
//        for (Song item : songList) {
//            results.add(ObjectUtils.beanToMap(item));
//        }
        List<Integer> songIdList = ListUtils.makeListKeyId(results);
        System.out.println(songIdList.size());
        // 通过这群id获得歌曲的其他信息
        Query querySongDetail = Query.query(Criteria.where("id").in(songIdList));
        List<SongDetail> songDetails = mongoTemplate.find(query,SongDetail.class);
        System.out.println(songDetails.size());
        List<Song8Tag> song8Tags = null;
        for (SongDetail item : songDetails) {
            Song8Tag song8Tag = new Song8Tag();
            System.out.println(item.getId());
            System.out.println(songIdList.indexOf(item.getId()));
            System.out.println(songList.get(songIdList.indexOf(item.getId())));
            System.out.println(songList.get(songIdList.indexOf(item.getId())).getId());
//            song8Tag.setTags(songList.get(songIdList.indexOf(item.getId()));

            System.out.println(item);
        }
        return null;
    }

    @Override
    public List<Song8Tag> getSongsFromMongo(String tag, Integer pageIndex, Integer pageSize) {
        return null;
    }

//    @Override
//    public List<Song8Tag> getSongsFromMongo(String tag, Integer pageIndex,Integer pageSize) {
//        return null;
//    }
}
