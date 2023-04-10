package com.music.song.service.impl;

import com.music.commons.pojo.menu.CodeEnum;
import com.music.commons.pojo.menu.Keys;
import com.music.commons.pojo.resbody.Result;
import com.music.commons.pojo.resbody.ResultMap;
import com.music.song.dao.SongDao;
import com.music.commons.pojo.reqbody.NextPrevious;
import com.music.song.pojo.Song;
import com.music.song.service.SongService;
import javax.annotation.Resource;

import org.bson.types.Code;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SongServiceImpl implements SongService {
    @Resource
    private SongDao songDao;


    @Override
    public ResultMap<Song> getRandomSong() {
        List<Song> result = songDao.getRandomSong();
        return result.size() <= 0 ?
                new ResultMap<>(CodeEnum.SUCCESS.getCode(),true, null,CodeEnum.SUCCESS_BUT_NO_DATA.getDesc(),null):
                new ResultMap<>(CodeEnum.SUCCESS.getCode(), true,null,CodeEnum.SUCCESS.getDesc(), result.get(0));
    }

    @Override
    public Result<Song> getRandomSomeSongs() {
        List<Song> results = songDao.getRandomSomeSongs();
        return results.size() <=0 ?
                new Result<>(CodeEnum.SUCCESS_BUT_NO_DATA.getCode(),false,null,CodeEnum.SUCCESS_BUT_NO_DATA.getDesc(), null):
                new Result<>(CodeEnum.SUCCESS.getCode(), true,null,CodeEnum.SUCCESS.getDesc(), results);
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
        return results.get(0).containsKey(Keys.KEY_ANSWER.getKey())?
                new Result<>(CodeEnum.SUCCESS_BUT_NO_DATA.getCode(),false, null,CodeEnum.SUCCESS_BUT_NO_DATA.getDesc(),results):
                new Result<>(CodeEnum.SUCCESS.getCode(), true,null,CodeEnum.SUCCESS.getDesc(), results);
    }

    @Override
    public Result<Map> getPreviousSongFromCollectionPlayList(NextPrevious jsonSrc) {
        List<Map> results = songDao.getPreviousSongFromCollectionPlaylist(jsonSrc.getPlaylistId(), jsonSrc.getMusicId());
        return results.get(0).containsKey(Keys.KEY_ANSWER.getKey())?
                new Result<>(CodeEnum.SUCCESS_BUT_NO_DATA.getCode(), false,null,CodeEnum.SUCCESS_BUT_NO_DATA.getDesc(),results):
                new Result<>(CodeEnum.SUCCESS.getCode(), true,null, CodeEnum.SUCCESS.getDesc(), results);
    }

}
