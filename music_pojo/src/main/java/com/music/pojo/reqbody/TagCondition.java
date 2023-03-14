package com.music.pojo.reqbody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagCondition {
    private String tag;
    private Integer pageIndex;
    private Integer pageSize = 10;
}
