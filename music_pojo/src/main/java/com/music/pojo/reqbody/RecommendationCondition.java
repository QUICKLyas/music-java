package com.music.pojo.reqbody;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer pageSize = 20;
}
