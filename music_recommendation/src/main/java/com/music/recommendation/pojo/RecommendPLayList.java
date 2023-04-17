package com.music.recommendation.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

public class RecommendPLayList {
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Document("playlists")
    static class PlayList {
        @Id
        private String _id;
        private Integer id;
        private String name;
        private List<String> tags;
        private String coverImgUrl;
        private Long coverImgId;
        private String description;
        // 收藏的人数
        private Integer subscribedCount;
        // 播放的次数
        private Integer playCount;
        private Long createTime;
        private Long updateTime;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Document("playlistdetail")
    static class PlayListDetail{
        @Id
        private String _id;
        private Integer id;
        private String name;
        private List<String> tags;
        private String coverImgUrl;
        private Long coverImgId;
        private String description;
        // 收藏的人数
        private Integer subscribedCount;
        // 播放的次数
        private Integer playCount;
        private Long createTime;
        private Long updateTime;
    }
}
