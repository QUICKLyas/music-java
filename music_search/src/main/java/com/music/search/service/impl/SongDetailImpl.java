package com.music.search.service.impl;

import com.music.commons.pojo.CodeEnum;
import com.music.commons.pojo.Result;
import com.music.commons.utils.ListUtils;
import com.music.commons.utils.ObjectUtils;
import com.music.search.dao.SongDetailRepository;
import com.music.search.pojo.Song4ES;
import com.music.search.pojo.SongDetail4ES;
import com.music.search.pojo.SongResult;
import com.music.search.service.SongDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;
import reactor.util.annotation.Nullable;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.music.commons.utils.DataUtils.timeUTC;

@Service
@Slf4j
public class SongDetailImpl implements SongDetailService {
    // 自动注入
    @Resource
    SongDetailRepository songDetailRepository ;

    @Resource
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    // 接口 "/search"

    /**
     *
     * @param songDetail
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public Result<SongResult> findSongByName(SongDetail4ES songDetail, @Nullable Integer pageIndex, @Nullable  Integer pageSize) {
        // 根据Name获取数据，限制数据量
        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria()
                .and(new Criteria("name").contains(songDetail.getName())))
                .setPageable(PageRequest.of(pageIndex == null ? 0:pageIndex,pageSize == null ? 20 : pageSize));
        SearchHits<SongDetail4ES> searchHits = elasticsearchRestTemplate.search(criteriaQuery, SongDetail4ES.class);
        List<SongDetail4ES> songDetails4ES = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
        // 传递获得的数据结果
        List<Map> SongDetails = new ArrayList<>(songDetails4ES.size());
        // 将对象转map
        for (SongDetail4ES item : songDetails4ES) {
            SongDetails.add(ObjectUtils.beanToMap(item));
        }
        List<Integer> songIdList = ListUtils.makeListKeyId(SongDetails);
        // 整理list并且获取其中id根据这些 id 从song获得具体的内容
        // 批量查询
        CriteriaQuery criteriaQuerySong = new CriteriaQuery(new Criteria()
                .and(new Criteria("id").in(songIdList)));
        SearchHits<Song4ES> song4ESSearchHits = elasticsearchRestTemplate.search(criteriaQuerySong,Song4ES.class);
        List<Song4ES> song4ES = song4ESSearchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
        List<SongResult> results = new ArrayList<>(songIdList.size());
        // 将结果整理出来 输出
        for (Song4ES item : song4ES) {
            // 考虑如果连个大小不一致的时候应该如何处理
            int index = songIdList.indexOf(item.getId());
            // 验证结果， songIdList 的id 位置等于songDetail4ES 的id 位置一致
            // 但是具体 song4ES 的id 位置不一致所以，我们需要解决这个问题，不适用song4ES获取数据而是item
            SongResult result = new SongResult();
            result.setId(item.getId());
            result.setName(songDetails4ES.get(index).getName());
            result.setTags(item.getTags());
            result.setAr(songDetails4ES.get(index).getSong().get("ar"));
            result.setPicUrl(songDetail.getPicUrl());
            results.add(result);
        }
        return new Result<SongResult>(CodeEnum.SUCCESS.getCode(),true,CodeEnum.SUCCESS.getDesc(),results);
    }
    @Override
    public void savAll(List<SongDetail4ES> songDetails) {
        songDetailRepository.saveAll(songDetails);
    }

    @Override
    public SongDetail4ES findById(Integer id) {
        return songDetailRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        songDetailRepository.deleteById(id);

    }

    @Override
    public void updateById(SongDetail4ES songDetail) {
        songDetailRepository.save(songDetail);
    }


    @Override
    public Page<SongDetail4ES> findList(SongDetail4ES songDetail, Integer pageIndex, Integer pageSize) {
        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria()
                .and(new Criteria("name").contains(songDetail.getName()))
                .and(new Criteria("song").is(songDetail.getId())))
                .setPageable(PageRequest.of(pageIndex,pageSize));
        SearchHits<SongDetail4ES> searchHits = elasticsearchRestTemplate.search(criteriaQuery, SongDetail4ES.class);
        List<SongDetail4ES> results = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
        return null;
    }

    @Override
    public Page<SongDetail4ES> findAll(Integer pageIndex, Integer pageSize) {
        Page<SongDetail4ES> page = songDetailRepository.findAll(PageRequest.of(pageIndex,pageSize));
        System.out.println("["+timeUTC()+"]: "+"page-"+page);
        return null;
    }

    @Override
    public Page<SongDetail4ES> findSong(SongDetail4ES songDetail, Integer pageIndex, Integer pageSize) {
        if(songDetail == null) {
            System.out.println("NULL");
        }

        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria()
                .and(new Criteria("id").is(songDetail.getId()))
                .and(new Criteria("name").contains(songDetail.getName())))
                .setPageable(PageRequest.of(pageIndex,pageSize));

        return null;
    }
}
