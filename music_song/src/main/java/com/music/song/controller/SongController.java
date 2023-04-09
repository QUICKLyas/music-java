package com.music.song.controller;

import com.music.commons.pojo.Result;
import com.music.pojo.reqbody.Favorites;
import com.music.pojo.reqbody.NextPrevious;
import com.music.song.service.SongService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import static com.music.commons.utils.DataUtils.time;
import static com.music.commons.utils.DataUtils.timeUTC;


@RestController // 自动增加ResponseBody
@CrossOrigin // 当前类型下所有方法支持跨域
@RequestMapping("/song")
public class SongController {
    @Resource
    private SongService songService;





    /**
     * 通过playlistdetail 表获取信息，同时根据musicId 获取其下一首歌曲的信息
     * @return
     */
    @RequestMapping(value="/next",method = RequestMethod.POST)
    public Result getNextSong(@RequestBody NextPrevious jsons){
        System.out.println("["+time()+"]: "+"request body's json = " + jsons);
        return songService.getNextSongFromCollectionPlaylist(jsons);
    }
    /**
     * 通过playlistdetail 表获取信息，同时根据musicId获取其下一首歌曲的信息
     *
     */
    @RequestMapping(value="/previous",method = RequestMethod.POST)
    public Result getPreviousSong(@RequestBody NextPrevious jsons) {
        System.out.println("["+time()+"]: "+"request body's json = "+ jsons);
        return songService.getPreviousSongFromCollectionPlayList(jsons);
    }
}
