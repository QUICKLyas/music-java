package com.music.song.service.impl;

import com.music.commons.pojo.CodeEnum;
import com.music.commons.pojo.Keys;
import com.music.commons.pojo.Result;
import com.music.song.dao.SongDao;
import com.music.song.pojo.reqbody.Favorites;
import com.music.song.pojo.reqbody.NextPrevious;
import com.music.song.service.SongService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SongServiceImpl implements SongService {
    @Resource
    private SongDao songDao;


    /**
     * 将歌曲信息加入到用户内部程序中
     * @param jsonSrc
     * @return
     */
    @Override
    public Result<Map> updateSongToCollectionLike(Favorites jsonSrc) {
        if (jsonSrc.getUser_id()== null||jsonSrc.getMusic_id()== null||jsonSrc.getTags()== null) {
            return new Result<>(CodeEnum.BAD_REQUEST.getCode(),false,CodeEnum.BAD_REQUEST.getDesc(),null);
        }
        List<Map> results = songDao.updateSongToCollectionLike(jsonSrc.getUser_id(),
                jsonSrc.getMusic_id(),
                jsonSrc.getTags());
        return results.get(0).get(Keys.KEY_ANSWER.getKey()).equals(CodeEnum.SUCCESS_BUT_NO_DATA.getDesc()) ?
                new Result<>(CodeEnum.SUCCESS_BUT_NO_DATA.getCode(),false, CodeEnum.SUCCESS_BUT_NO_DATA.getDesc(),results):
                new Result<>(CodeEnum.SUCCESS.getCode(), true,CodeEnum.SUCCESS.getDesc(), results);
    }

    /**
     * 本方法根据playlistId 确定是获取状态，如果playlistId没有数据，说明是要随机获取歌曲
     * 如果有内容，那么就要获取这首musicId 的下一首的id值，返回
     * @param jsonSrc
     * @return
     *
     */
    @Override
    public Result<Map> getNextSongFromCollectionPlaylist(NextPrevious jsonSrc) {
        List<Map> results = songDao.getNextSongFromCollectionPlaylist(jsonSrc.getPlaylistId(), jsonSrc.getMusicId());
        return results.get(0).keySet().contains(Keys.KEY_ANSWER.getKey())?
                new Result<>(CodeEnum.SUCCESS_BUT_NO_DATA.getCode(),false, CodeEnum.SUCCESS_BUT_NO_DATA.getDesc(),results):
                new Result<>(CodeEnum.SUCCESS.getCode(), true,CodeEnum.SUCCESS.getDesc(), results);
    }

    @Override
    public Result<Map> getPreviousSongFromCollectionPlayList(NextPrevious jsonSrc) {
        List<Map> results = songDao.getPreviousSongFromCollectionPlaylist(jsonSrc.getPlaylistId(), jsonSrc.getMusicId());
        return results.get(0).keySet().contains(Keys.KEY_ANSWER.getKey())?
                new Result<>(CodeEnum.SUCCESS_BUT_NO_DATA.getCode(), false,CodeEnum.SUCCESS_BUT_NO_DATA.getDesc(),results):
                new Result<>(CodeEnum.SUCCESS.getCode(), true, CodeEnum.SUCCESS.getDesc(), results);
    }

}
