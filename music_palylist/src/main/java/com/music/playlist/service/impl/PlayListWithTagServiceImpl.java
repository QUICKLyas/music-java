package com.music.playlist.service.impl;

import com.music.commons.pojo.menu.CodeEnum;
import com.music.commons.pojo.reqbody.TagCondition;
import com.music.commons.pojo.resbody.Result;
import com.music.playlist.dao.impl.PlayListWithTagDaoImpl;
import com.music.playlist.pojo.PlayList;
import com.music.playlist.service.PlayListWithTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PlayListWithTagServiceImpl implements PlayListWithTagService {
    @Resource
    private PlayListWithTagDaoImpl playListWithTagDao;
    @Override
    public Result<PlayList> getPlayListByTag(TagCondition tagCondition) {
        if (tagCondition.getTag() == null) {

            return new Result<>(CodeEnum.BAD_REQUEST.getCode(),false,null,CodeEnum.BAD_REQUEST.getDesc(),null);
        }
        List<PlayList> results = playListWithTagDao.getPlayListFromMongo(tagCondition.getTag(),tagCondition.getPageIndex(),tagCondition.getPageSize());

        return results.size() <= 0 ?
                new Result<>(CodeEnum.SUCCESS_BUT_NO_DATA.getCode(), false, null, CodeEnum.SUCCESS_BUT_NO_DATA.getDesc(), null):
                new Result<>(CodeEnum.SUCCESS.getCode(), true, null,CodeEnum.SUCCESS.getDesc(), results);
    }

}
