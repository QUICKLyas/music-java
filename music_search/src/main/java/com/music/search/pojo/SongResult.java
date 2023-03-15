package com.music.search.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongResult {
    private Integer id;
    private String name;
    private String picUrl;
    private List<Object> tags;
    private Object ar;
}
