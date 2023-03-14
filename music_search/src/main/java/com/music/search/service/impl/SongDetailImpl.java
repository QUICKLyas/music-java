package com.music.search.service.impl;

import com.music.commons.pojo.CodeEnum;
import com.music.commons.pojo.Result;
import com.music.search.dao.SongDetailRepository;
import com.music.search.pojo.SongDetail;
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
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SongDetailImpl implements SongDetailService {
    // 自动注入
    @Resource
    SongDetailRepository songDetailRepository ;
    @Resource
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    // 接口 "/search"
    @Override
    public Result<SongDetail> findSongByName(SongDetail songDetail, Integer pageIndex, Integer pageSize) {
        // 根据Name获取数据，限制数据量
        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria()
                .and(new Criteria("name").contains(songDetail.getName())))
                .setPageable(PageRequest.of(pageIndex,pageSize));
        SearchHits<SongDetail> searchHits = elasticsearchRestTemplate.search(criteriaQuery,SongDetail.class);
        List<SongDetail> results = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
        // 传递获得的数据结果
        return new Result<SongDetail>(CodeEnum.SUCCESS.getCode(),true,CodeEnum.SUCCESS.getDesc(),results);
    }
    @Override
    public void savAll(List<SongDetail> songDetails) {
        songDetailRepository.saveAll(songDetails);
    }

    @Override
    public SongDetail findById(Integer id) {
        return songDetailRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        songDetailRepository.deleteById(id);

    }

    @Override
    public void updateById(SongDetail songDetail) {
        songDetailRepository.save(songDetail);
    }


    @Override
    public Page<SongDetail> findList(SongDetail songDetail, Integer pageIndex, Integer pageSize) {
        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria()
                .and(new Criteria("name").contains(songDetail.getName()))
                .and(new Criteria("song").is(songDetail.getId())))
                .setPageable(PageRequest.of(pageIndex,pageSize));
        SearchHits<SongDetail> searchHits = elasticsearchRestTemplate.search(criteriaQuery,SongDetail.class);
        List<SongDetail> results = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
        return null;
    }

    @Override
    public Page<SongDetail> findAll(Integer pageIndex, Integer pageSize) {
        Page<SongDetail> page = songDetailRepository.findAll(PageRequest.of(pageIndex,pageSize));
        System.out.println(page);
        return null;
    }

    @Override
    public Page<SongDetail> findSong(SongDetail songDetail, Integer pageIndex, Integer pageSize) {
        if(songDetail == null) {
            System.out.println("NULL");
        }

        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria()
                .and(new Criteria("id").is(songDetail.getId()))
                .and(new Criteria("name").contains(songDetail.getName())))
                .setPageable(PageRequest.of(pageIndex,pageSize));

//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.field("id").field("name");
//        highlightBuilder.requireFieldMatch(false);
//        HighlightQuery highlightQuery = new HighlightQuery(highlightBuilder);
//        criteriaQuery.setHighlightQuery(highlightQuery);

//        SearchHits<SongDetail> searchHits = elasticsearchRestTemplate.search(criteriaQuery,SongDetail.class);
//        List<SongDetail> result = searchHits.get().map(e -> {
//            SongDetail element = e.getContent();
//            element.setHighlights
//        })
        return null;
    }
}
