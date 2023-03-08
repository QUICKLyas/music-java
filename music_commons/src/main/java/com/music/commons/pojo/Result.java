package com.music.commons.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Boolean success;
    private String message;
    private List<T> result;
}
