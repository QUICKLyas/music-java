package com.music.commons.pojo.resbody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultData<T> implements Serializable {
    private Integer status; // 返回正常时: 200
    @Nullable
    private Boolean success;
    @Nullable
    private String message;
    @Nullable
    private String errmsg;
    @Nullable
    private List<T> data;
}
