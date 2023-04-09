package com.music.song.controller;

import com.music.commons.pojo.resbody.Result;
import com.music.commons.pojo.reqbody.NextPrevious;
import com.music.commons.pojo.resbody.ResultMap;
import com.music.song.service.SongService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import static com.music.commons.utils.DataUtils.time;


@RestController // 自动增加ResponseBody
@CrossOrigin // 当前类型下所有方法支持跨域
@RequestMapping("/song")
public class SongController {
    @Resource
    private SongService songService;

    /**
     * 随机获取一首歌曲
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResultMap getRandomSong() {
        System.out.println("["+time()+"]: " +"catch one song from mongo...");
        return songService.getRandomSong();
    }




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
