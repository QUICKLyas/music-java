package com.music.commons.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.List;

/**
 * 项目中的返回结果类型
 */
@Data
@NoArgsConstructor //无参构造
@AllArgsConstructor // 有参构造方法
public class Result<T> implements Serializable {
    private Integer status; // 返回正常时: 200
    @Nullable
    private Boolean success;
    @Nullable
    private Boolean isCollect;
    @Nullable
    private String message;
    @Nullable
    private List<T> result;
}
