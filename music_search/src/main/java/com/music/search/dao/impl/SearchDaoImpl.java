package com.music.search.dao.impl;

import com.music.search.dao.SearchDao;
import com.music.search.pojo.SongDetail4ES;
import jakarta.annotation.Resource;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SearchDaoImpl implements SearchDao {
    @Resource
    private ElasticsearchRestTemplate restTemplate;
    /**
     * 批量保存数据
     * @param list
     */
    @Override
    public void saveSong(List<SongDetail4ES> list) {
        // 迭代器iterable或者可变数组
        restTemplate.save(list);
    }
}
