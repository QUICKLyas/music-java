package com.music.song.service.impl;

import com.music.commons.pojo.menu.CodeEnum;
import com.music.commons.pojo.resbody.Result;
import com.music.commons.pojo.reqbody.TagCondition;
import com.music.song.dao.SongWithTagDao;
import com.music.song.pojo.Song8Tag;
import com.music.song.service.SongWithTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SongWithTagServiceImpl implements SongWithTagService {
    @Resource
    SongWithTagDao songDao;

    /**
     * 默认传输的可以是 空
     * @param tagCondition
     * @return
     */
    @Override
    public Result<Song8Tag> getSong(TagCondition tagCondition) {
        List<Song8Tag> results = songDao.getSongFromMongo(
                tagCondition.getTag(),tagCondition.getPageIndex(),tagCondition.getPageSize());
        return results.size() != 0
                ? new Result<>(CodeEnum.SUCCESS.getCode(), true,null,CodeEnum.SUCCESS.getDesc(),results)
                : new Result<>(CodeEnum.SUCCESS_BUT_NO_DATA.getCode(),false,null,CodeEnum.SUCCESS_BUT_NO_DATA.getDesc(),results);
    }

    /**
     * 原接口是random
     * @param tag
     * @return
     */
    @Override
    public Result<Song8Tag> getRandomSongs(String tag) {
        List<Song8Tag> results = songDao.getRandomSongsFromMongo(tag);
        return results.size() > 0
                ? new Result<>(CodeEnum.SUCCESS.getCode(), true,null,CodeEnum.SUCCESS.getDesc(), results)
                : new Result<>(CodeEnum.SUCCESS_BUT_NO_DATA.getCode(), false,null,CodeEnum.SUCCESS_BUT_NO_DATA.getDesc(), results);
    }
}
