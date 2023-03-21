package com.music.commons.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultMusic<T> implements Serializable {
    private Integer status; // 返回正常时: 200
    private Boolean success;
    private String message;
    private List<T> musics;
}