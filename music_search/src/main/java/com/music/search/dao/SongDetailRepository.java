package com.music.search.dao;

import com.music.search.pojo.SongDetail4ES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SongDetailRepository extends ElasticsearchRepository<SongDetail4ES,Integer> {
}
