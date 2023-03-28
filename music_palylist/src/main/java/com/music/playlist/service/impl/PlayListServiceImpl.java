package com.music.playlist.service.impl;

import com.music.commons.pojo.CodeEnum;
import com.music.commons.pojo.DataR;
import com.music.commons.pojo.Result;
import com.music.playlist.dao.PlayListDao;
import com.music.playlist.pojo.PlayList;
import com.music.playlist.service.PlayListService;
import javax.annotation.Resource;

import com.music.pojo.reqbody.TagCondition;
import org.bson.types.Code;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PlayListServiceImpl implements PlayListService {
    @Resource
    private PlayListDao playListDao;


    @Override
    public Result<Map> getDefaultPlayList() {
        List<Map> results = playListDao.getDefaultPlayListFromCollectionPlayLists();
        return results.size() <= 0 ?
                new Result<>(CodeEnum.SUCCESS_BUT_NO_DATA.getCode(), false, CodeEnum.SUCCESS_BUT_NO_DATA.getDesc(), results) :
                new Result<>(CodeEnum.SUCCESS.getCode(), true, CodeEnum.SUCCESS.getDesc(), results);
    }

    @Override
    public DataR<PlayList> getPlayList8Cat(TagCondition tagCondition) {
        if ((tagCondition.getTags() == null || tagCondition.getTags().size()==0)&& tagCondition.getTag() != null) {
            tagCondition.setTags(new ArrayList<>());
            tagCondition.getTags().add(tagCondition.getTag());
        }
        if (tagCondition.getTags().size() == 0 || tagCondition.getTags() == null || tagCondition.getPage() == null) {
            return new DataR<>(CodeEnum.BAD_REQUEST_ILLEGAL_PARAM.getCode(), false,null ,CodeEnum.BAD_REQUEST_ILLEGAL_PARAM.getDesc() ,null);
        }
        List<PlayList> result = playListDao.getPlayListCatFromMongo(tagCondition.getTags(),tagCondition.getPage());
        if (result.size() > 0) {
            return new DataR<>(CodeEnum.SUCCESS.getCode(), true, CodeEnum.SUCCESS.getDesc(),null, result);
        }
        return new DataR<>(CodeEnum.SUCCESS_BUT_NO_DATA.getCode(), false, CodeEnum.SUCCESS_BUT_NO_DATA.getDesc(),null, result);
    }
}