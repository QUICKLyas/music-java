package com.music.detial.service.impl;

import com.music.detial.dao.SongDao;
import com.music.detial.service.SongService;
import com.music.pojo.SongDetail;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    @Resource
    private SongDao songDao;
    @Override
    public List<SongDetail> getAllSongDetail() {
        return songDao.findAllSongDetail();
    }


}
