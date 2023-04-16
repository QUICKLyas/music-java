package com.music.recommendation.dao;

import javax.annotation.Nullable;
import java.util.List;

public interface RecommendationDao {
    // 获取推荐的歌曲
    List<Object> getRecommendationS(String userId, @Nullable Integer pageIndex,@Nullable Integer pageSize);
    List<Object> getRecommendationPL(String userId, @Nullable Integer pageIndex,@Nullable Integer pageSize);
    List<Object> getRecommendationRandomS(@Nullable Integer pageIndex,@Nullable Integer pageSize);
    List<Object> getRecommendationRandomPL(@Nullable Integer pageIndex,@Nullable Integer pageSize);


}
