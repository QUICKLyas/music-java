package com.music.search.service;

//import org.springframework.core.annotation.Order;

import com.music.commons.pojo.Result;
import com.music.search.pojo.SongDetail;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SongDetailService {
    void savAll(List<SongDetail> songDetails);
    SongDetail findById(Integer id);
    void deleteById(Integer id);
    void updateById(SongDetail songDetail);

    Result<SongDetail> findSongByName(SongDetail songDetail, Integer pageIndex, Integer pageSize);

    Page<SongDetail> findList(SongDetail songDetail, Integer pageIndex, Integer pageSize);
    Page<SongDetail> findAll(Integer pageIndex,Integer pageSize);
    Page<SongDetail> findSong(SongDetail songDetail,Integer pageIndex,Integer pageSize);

}
