package com.music.search.controller;

import com.music.commons.pojo.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SearchController {
    /**
     * 获取es中的数据
     * @return
     */
    @GetMapping("/search")
    public Result findSong(String condition){
        return null;
    }
    /**
     * 初始化ES数据，从mongo中查询所有歌曲数据，并初始化到es中
     * @return
     */
    @GetMapping("/init")
    public String init(){
        return null;
    }
}
