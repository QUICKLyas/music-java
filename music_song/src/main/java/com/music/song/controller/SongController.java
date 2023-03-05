package com.music.song.controller;

import com.music.commons.pojo.Result;
import com.music.song.service.SongService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


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

    @RequestMapping(value = "/favorites", method = RequestMethod.POST)
    public Result insertSongLike(HttpServletRequest request) {
        System.out.println("request = " + request);
        Map<String,String[]> map = request.getParameterMap();
        Map<String,Object> params = new HashMap<String,Object>();
        int length;
        for (Map.Entry<String,String[]> entry : map.entrySet()) {
            length = entry.getValue().length;
            if(length == 1 ){
                params.put(entry.getKey(), Arrays.toString(entry.getValue()));
            } else if (length > 1){
                params.put(entry.getKey(),entry.getValue());
            }
        }
        return songService.insertSongToCollectionLike(params);
    }
}
