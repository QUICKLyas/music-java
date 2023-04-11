package com.music.recommendation.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public class RecomendSong {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Document("song")
    class Song{
        private Long _id;
        private Integer id;
        private String name;
        private List<String> tags;
        private Map<String,String> songAble;
        private Map<String,String> songUrl;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Document("songdetail")
    class SongDetail {
        private Long _id;
        private String name;
        private Map<String,String> song;
        private Integer mark;
    }
}
