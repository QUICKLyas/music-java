package com.music.commons.pojo.reqbody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationCondition {

    private String userId;



    // 2 user
    // 1 song
    // 0 playlist
    @Nullable
    private Integer flag;
    private Integer pageIndex = 0;
    private Integer pageSize = 30;
}
