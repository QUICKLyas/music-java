package com.music.commons.pojo.resbody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.List;

/**
 * 针对map类型的result
 * 或者是对象类型
 * @param <T>
 */
@Data
@NoArgsConstructor //无参构造
@AllArgsConstructor // 有参构造方法
public class ResultMap<T> implements Serializable {
    private Integer status; // 返回正常时: 200
    @Nullable
    private Boolean success;
    @Nullable
    private Boolean isCollect;
    @Nullable
    private String message;
    @Nullable
    private T result;
}
