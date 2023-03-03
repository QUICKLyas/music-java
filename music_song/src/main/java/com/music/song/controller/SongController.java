package com.music.song.controller;

import com.music.commons.pojo.Result;
import com.music.song.service.SongService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController // 自动增加ResponseBody
@CrossOrigin // 当前类型下所有方法支持跨域
public class SongController {
    @Resource
    private SongService songService;
    /**
     * 需要处理跨域处理
     * @return
     * {
     *     "status":200
     *     "results":[Object,Object,Object...]
     * }
     */
    @GetMapping("/song")
    public Result getSongs(){
        List<Integer> songIds = new ArrayList<Integer>();
        songIds.add(3951888);
        songIds.add(16880808);
        return songService.getSongs(songIds);
    }
}
