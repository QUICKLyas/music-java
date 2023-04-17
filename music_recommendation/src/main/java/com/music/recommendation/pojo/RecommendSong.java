package com.music.recommendation.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class RecommendSong {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Document("song")
    public static class Song{
        @Id
        private String _id;
        private Integer id;
        private String name;
        private List<String> tags;
        private Map<String,String> songAble;
        private String songUrl;
        private Map<String,Object> data;
        private Long mark;
        private List<Integer> similaritySong;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Document("songdetail")
    public static class SongDetail {
        @Id
        private String _id;
        private Integer id;
        private String name;
        private Map<String,String> songs;
    }
}
