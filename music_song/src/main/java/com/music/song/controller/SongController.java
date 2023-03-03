package com.music.song.controller;

import com.music.commons.pojo.Result;
import com.music.song.service.SongService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



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
    @GetMapping("/songs")
    public Result getSongs(){
        return songService.getSongs();
    }
}
