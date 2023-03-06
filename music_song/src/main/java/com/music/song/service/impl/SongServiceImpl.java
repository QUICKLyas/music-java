package com.music.song.service.impl;

import com.music.commons.pojo.CodeEnum;
import com.music.commons.pojo.Keys;
import com.music.commons.pojo.Result;
import com.music.song.dao.SongDao;
import com.music.song.pojo.Favorites;
import com.music.song.pojo.NextPrevious;
import com.music.song.pojo.Song;
import com.music.song.service.SongService;
import jakarta.annotation.Resource;
import org.bson.types.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SongServiceImpl implements SongService {
    @Resource
    private SongDao songDao;
    /**
     * 调用dao ，远程MongoDB数据库，查询数据，
     * 返回结果，做随机排序，抽取条数据，不足5条的返回数据
     * @return
     */
    @Override
    public Result<Song> getSongs(List<Integer> songIds) {
        List<Integer> conditionSongs = songIds;
        // 分析Request内容，将数据分装为一个List<Integer>
        List<Song> songList = songDao.getSongs(conditionSongs);
        Collections.shuffle(songList);
        // 避免泄露
        List<Song> results = null;
        if(songList.size()<=0){
            results = new ArrayList<Song>(0);
        } else {
            results = new ArrayList<Song>(songList.size());
        }
        // 循环结束标记
        int end = songList.size() <= 0 ? 0:songList.size();
        // 循环处理结果，把数据存储数据中
        for (int i=0 ; i < end ; i++){
            Song song = songList.get(i);
            results.add(song);
        }
        return new Result<>(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getDesc(), results);
    }

    /**
     * 根据id 只获取一个值
     * @return
     */
    @Override
    public Result<Song> getSongDetail(Integer songId) {
        return null;
    }


    /**
     * 将歌曲信息加入到用户内部程序中
     * @param jsonSrc
     * @return
     */
    @Override
    public Result<Map> updateSongToCollectionLike(Favorites jsonSrc) {
        if (jsonSrc.getUser_id()== null||jsonSrc.getMusic_id()== null||jsonSrc.getTags()== null) {
            return new Result<>(CodeEnum.BAD_REQUEST.getCode(),CodeEnum.BAD_REQUEST.getDesc(),null);
        }
        List<Map> results = songDao.updateSongToCollectionLike(jsonSrc.getUser_id(),
                jsonSrc.getMusic_id(),
                jsonSrc.getTags());
        return results.get(0).get(Keys.KEY_ANSWER.getKey()).equals(CodeEnum.SUCCESS_BUT_NO_DATA.getDesc()) ?
                new Result<>(CodeEnum.SUCCESS_BUT_NO_DATA.getCode(), CodeEnum.SUCCESS_BUT_NO_DATA.getDesc(),results):
                new Result<>(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getDesc(), results);
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
                new Result<>(CodeEnum.SUCCESS_BUT_NO_DATA.getCode(), CodeEnum.SUCCESS_BUT_NO_DATA.getDesc(),results):
                new Result<>(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getDesc(), results);
    }
}
