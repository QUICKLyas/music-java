package com.music.pojo.reqbody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCondition {
    private String name;
    private Integer pageIndex;
    private Integer pageSize = 20;
}
