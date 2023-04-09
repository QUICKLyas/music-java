package com.music.commons.pojo.reqbody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCondition {
    private String musicName;
    private Integer pageIndex = 0;
    private Integer pageSize = 20;
}
