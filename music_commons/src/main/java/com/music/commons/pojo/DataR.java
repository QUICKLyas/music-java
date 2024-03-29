package com.music.commons.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor //无参构造
@AllArgsConstructor
public class DataR<T> implements Serializable {
    private Integer status; // 返回正常时: 200
    private Boolean success;
    private String message;
    private String errmsg;
    private List<T> data;
}
