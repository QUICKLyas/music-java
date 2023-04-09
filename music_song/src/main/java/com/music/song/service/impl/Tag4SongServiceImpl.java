package com.music.song.service.impl;

import com.music.commons.pojo.CodeEnum;
import com.music.commons.pojo.Result;
import com.music.pojo.reqbody.TagCondition;
import com.music.song.dao.Tag4SongDao;
import com.music.song.pojo.Song8Tag;
import com.music.song.service.Tag4SongService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Tag4SongServiceImpl implements Tag4SongService {
    @Resource
    Tag4SongDao songDao;

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
