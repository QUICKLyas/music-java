package com.music.song.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "song")
public class Song implements Serializable {
    @Id
    private String _id;
    private Integer id;
    private Integer name;
    private Map<String,Object> songUrl;
    private Map<String,Object> songAble;

    private List<Object> tags;
    private List<Object> similaritySong;
}
