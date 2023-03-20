package com.music.song.service.impl;

import com.music.commons.pojo.CodeEnum;
import com.music.commons.pojo.Exits;
import com.music.commons.pojo.ResultMusic;
import com.music.pojo.SongDetail;
import com.music.pojo.reqbody.CollectMusic;
import com.music.song.dao.CollectMusicDao;
import com.music.song.service.CollectMusicService;
import org.bson.types.Code;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CollectMusicServiceImpl implements CollectMusicService {

    @Resource
    private CollectMusicDao collectMusicDao;

    @Override
    public Exits getCollectSExist(CollectMusic jsonSrc) {
        if (jsonSrc.getMusicId() == null || jsonSrc.getUserId() == null || jsonSrc.getUserId().equals(""))
            return new Exits(CodeEnum.BAD_REQUEST.getCode(), false, false, CodeEnum.BAD_REQUEST.getDesc());
        Boolean flag = collectMusicDao.getCollectSExist(jsonSrc.getMusicId(), jsonSrc.getUserId());
        // true false null
        return flag == null ?
                new Exits(CodeEnum.BAD_REQUEST_NOTFOUND.getCode(), false, false, CodeEnum.BAD_REQUEST_NOTFOUND.getDesc()) :
                new Exits(CodeEnum.SUCCESS.getCode(), true, flag, CodeEnum.SUCCESS.getDesc());
    }


    @Override
    public ResultMusic<SongDetail> getRandomMusic() {
        return null;
    }
}
