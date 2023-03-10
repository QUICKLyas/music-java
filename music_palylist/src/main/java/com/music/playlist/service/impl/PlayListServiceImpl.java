package com.music.playlist.service.impl;

import com.music.commons.pojo.CodeEnum;
import com.music.commons.pojo.Result;
import com.music.playlist.dao.PlayListDao;
import com.music.playlist.service.PlayListService;
import javax.annotation.Resource;
import org.bson.types.Code;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlayListServiceImpl implements PlayListService {
    @Resource
    PlayListDao playListDao;
    @Override
    public Result<Map> getDefaultPlayList() {
        List<Map> results = playListDao.getDefaultPlayListFromCollectionPlayLists();
        return results.size() <= 0 ?
                new Result<>(CodeEnum.SUCCESS_BUT_NO_DATA.getCode(), false, CodeEnum.SUCCESS_BUT_NO_DATA.getDesc(), results) :
                new Result<>(CodeEnum.SUCCESS.getCode(), true, CodeEnum.SUCCESS.getDesc(), results);
    }
}