package com.music.recommendation.service.impl;

import com.music.commons.pojo.menu.CodeEnum;
import com.music.commons.pojo.resbody.Result;
import com.music.commons.pojo.reqbody.RecommendationCondition;
import com.music.recommendation.dao.RecommendationDao;
import com.music.recommendation.pojo.RecommendPLayList;
import com.music.recommendation.pojo.RecommendSong;
import com.music.recommendation.pojo.Recommendation;
import com.music.recommendation.service.RecommendationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Resource
    private RecommendationDao recommendationDao;
    // 获得推荐的数据，在获取前直运行程序，运行程序路径，是写在运行的propeties中

    /**
     * 这个方法，会检查flag来确定是什么情况，假设
     * 非随机的推荐，根据用户传入的数据进行数据的推荐，
     * 一种是根据tag的占比进行有占比特征的正太分布式的推荐
     * 一种是更具协同过滤算法进行的相似用户之间额度推荐，党数据达到一定量的时候通过tag和用户进行对歌曲的推荐
     *
     * @param recommendationCondition
     * @return
     */
    @Override
    public Result getRecommendations(RecommendationCondition recommendationCondition) {

        /*
            song --> 1
            playlist -> 0
         */
        if(recommendationCondition.getFlag() == 0) {
            // 获取用户收藏歌曲的比列，然后按照了比例获取一定数量的歌单

            List<RecommendPLayList.PlayList> result = recommendationDao.getRecommendationPL(recommendationCondition.getUserId());
            return result==null || result.size() < 1 ?
                    new Result<>(CodeEnum.SUCCESS_BUT_NO_DATA.getCode(),true,false, CodeEnum.SUCCESS_BUT_NO_DATA.getDesc(),null):
                    new Result<>(CodeEnum.SUCCESS.getCode(), true,true,CodeEnum.SUCCESS.getDesc(),result);
        } else if (recommendationCondition.getFlag() == 1) {
            List<RecommendSong.Song> result = recommendationDao.getRecommendationS(recommendationCondition.getUserId());
            return result==null || result.size() < 1 ?
                    new Result<>(CodeEnum.SUCCESS_BUT_NO_DATA.getCode(),true,false, CodeEnum.SUCCESS_BUT_NO_DATA.getDesc(),null):
                    new Result<>(CodeEnum.SUCCESS.getCode(), true,true,CodeEnum.SUCCESS.getDesc(),result);
        }
        return new Result<>(CodeEnum.BAD_REQUEST_ILLEGAL_PARAM.getCode(), false,false,CodeEnum.BAD_REQUEST_ILLEGAL_PARAM.getDesc(),null);
    }

    /**
     * 本方法是随机获取一定数量的数据，我们呢建议使用正态分布的概率，获取以mark值排列的数据，保证收藏数较为中间数量的向用户推荐歌曲
     * 随机推荐，直接根据数据的订阅数量来进行推荐
     *
     * @param recommendationCondition
     * @return
     */

    @Override
    public Result getRecommendationsRandom(RecommendationCondition recommendationCondition) {
        return null;
    }


}
