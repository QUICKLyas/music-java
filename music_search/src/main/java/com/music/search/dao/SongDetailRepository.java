package com.music.search.dao;

import com.music.search.pojo.SongDetail;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SongDetailRepository extends ElasticsearchRepository<SongDetail,Integer> {
}
