package com.music.song.service.impl;

import com.music.commons.pojo.*;
import com.music.pojo.SongDetail;
import com.music.pojo.reqbody.Favorites;
import com.music.song.dao.CollectMusicDao;
import com.music.song.service.CollectMusicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CollectMusicServiceImpl implements CollectMusicService {

    @Resource
    private CollectMusicDao collectMusicDao;

    /**
     * 将歌曲信息加入到用户内部程序中
     * @param jsonSrc
     * @return
     */
    @Override
    public Result<Map> collectSongToCollectionLike(Favorites jsonSrc) {
        Result result = new Result<>();
        //  数据请求报文有问题，直接返回错误报文
        if (jsonSrc.getUserId()== null||jsonSrc.getMusicId()== null) {
            result.setStatus(CodeEnum.BAD_REQUEST.getCode());
            result.setSuccess(false);
            result.setIsCollect(false);
            result.setMessage(CodeEnum.BAD_REQUEST.getDesc());
            return result;
        }
        List<Map> results = collectMusicDao.updateSongToCollectionLike(jsonSrc.getUserId(),
                jsonSrc.getMusicId(),
                jsonSrc.getTags());
        // 数据正确返回得报文

        return results.get(0).get(Keys.KEY_ANSWER.getKey()).equals(CodeEnum.SUCCESS_BUT_NO_DATA.getDesc()) ?
                new Result<>(CodeEnum.SUCCESS_BUT_NO_DATA.getCode(),true,false, CodeEnum.SUCCESS_BUT_NO_DATA.getDesc(),results):
                new Result<>(CodeEnum.SUCCESS.getCode(), true,true,CodeEnum.SUCCESS.getDesc(), results);
    }

    @Override
    public Exits getCollectSExist(Favorites jsonSrc) {
        if (jsonSrc.getMusicId() == null || jsonSrc.getUserId() == null || jsonSrc.getUserId().equals(""))
            return new Exits(CodeEnum.BAD_REQUEST.getCode(), false, false, CodeEnum.BAD_REQUEST.getDesc());
        Boolean flag = collectMusicDao.getCollectSExist(jsonSrc.getMusicId(), jsonSrc.getUserId());
        // true false null
        return flag.equals(false) ?
                new Exits(CodeEnum.SUCCESS.getCode(), true, flag, CodeEnum.BAD_REQUEST_NOTFOUND.getDesc()) :
                new Exits(CodeEnum.SUCCESS.getCode(), true, flag, CodeEnum.SUCCESS.getDesc());
    }


    @Override
    public ResultData<SongDetail> getRandomMusic() {
        return null;
    }
}
