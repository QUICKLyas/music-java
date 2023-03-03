package com.music.song.service.impl;

import com.music.commons.pojo.Result;
import com.music.song.dao.SongDao;
import com.music.song.pojo.Song;
import com.music.song.service.SongService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        return new Result<>(200,results);
    }

    /**
     * 根据id 只获取一个值
     * @return
     */
    @Override
    public Result<Song> getSongDetail(Integer songId) {
        return null;
    }
}
