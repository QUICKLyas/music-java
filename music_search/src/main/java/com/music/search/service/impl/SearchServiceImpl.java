package com.music.search.service.impl;

import com.music.search.dao.SearchDao;
import com.music.search.service.SearchService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
    @Resource
    private SearchDao searchDao;

    /**
     *
     * @return
     */
    @Override
    public boolean init() {
        return false;
    }
}
