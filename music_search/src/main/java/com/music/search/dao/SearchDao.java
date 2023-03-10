package com.music.search.dao;

import com.music.search.pojo.SongDetail4ES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Map;
public interface SearchDao extends ElasticsearchRepository<SongDetail4ES,Long> {
}
