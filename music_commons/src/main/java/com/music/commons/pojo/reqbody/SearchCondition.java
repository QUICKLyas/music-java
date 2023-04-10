package com.music.commons.pojo.reqbody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCondition {
    private String musicName;
    private Integer pageIndex = 0;
    @Nullable
    private Integer pageSize = 20;
}
