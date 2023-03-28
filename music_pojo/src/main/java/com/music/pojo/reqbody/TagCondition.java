package com.music.pojo.reqbody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagCondition {
    private String tag;
    @Nullable
    private List<String> tags;
    private Integer pageIndex;
    private Integer page;
    private Integer pageSize = 10;
}
