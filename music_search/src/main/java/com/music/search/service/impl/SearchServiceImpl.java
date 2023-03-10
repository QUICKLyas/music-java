package com.music.search.service.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.music.commons.pojo.Result;
import com.music.search.dao.SearchDao;
import com.music.search.pojo.SongDetail4ES;
import com.music.search.service.SearchService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
    @Resource
    private SearchDao searchDao;

    public void searchContent(String keyword) {
//        QueryStringQueryBuilder queryStringQueryBuilder
    }
    /**
     *
     * @return
     */
//    @Override
//    public boolean init() {
//        return false;
//    }

//    @Override
//    public Result<SongDetail4ES> search(String condition, int page) {
//        return null;
//    }
}
