package com.music.search.service;

//import org.springframework.core.annotation.Order;

import com.music.commons.pojo.resbody.Result;
import com.music.search.pojo.SongDetail4ES;
import com.music.search.pojo.SongResult;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SongDetailService {
    void saveAll(List<SongDetail4ES> songDetails);
    SongDetail4ES findById(Integer id);
    void deleteById(Integer id);
    void updateById(SongDetail4ES songDetail);

    Result<SongResult> findSongByName(SongDetail4ES songDetail, Integer pageIndex, Integer pageSize);

    Page<SongDetail4ES> findList(SongDetail4ES songDetail, Integer pageIndex, Integer pageSize);
    Page<SongDetail4ES> findAll(Integer pageIndex, Integer pageSize);
    Page<SongDetail4ES> findSong(SongDetail4ES songDetail, Integer pageIndex, Integer pageSize);

}
