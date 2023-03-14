package com.music.song.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song8Tag {
    @Id
    private Object _id;
    private Integer id;
    private String name;
    private List<Object> tags;
    private String picUrl;
    private Object ar;
}
