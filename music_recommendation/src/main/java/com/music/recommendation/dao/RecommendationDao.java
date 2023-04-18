package com.music.recommendation.dao;

import com.music.recommendation.pojo.RecommendPLayList;
import com.music.recommendation.pojo.RecommendSong;

import javax.annotation.Nullable;
import java.util.List;

public interface RecommendationDao {
    // 获取推荐的歌曲
    List<RecommendSong.Song> getRecommendationS(String userId,@Nullable Integer pageIndex, @Nullable Integer pageSize);
    List<RecommendPLayList.PlayList> getRecommendationPL(String userId,@Nullable Integer pageIndex, @Nullable Integer pageSize);
    List<RecommendSong.Song> getRecommendationRandomS(@Nullable Integer pageIndex,@Nullable Integer pageSize);
    List<RecommendPLayList.PlayList> getRecommendationRandomPL(@Nullable Integer pageIndex,@Nullable Integer pageSize);


}
