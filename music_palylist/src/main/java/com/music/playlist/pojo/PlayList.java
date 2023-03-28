package com.music.playlist.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import reactor.util.annotation.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "playlists")
public class PlayList {
    @Id
    private String _id;
    private Long id;
    private String name;
    private String coverImgUrl;
    private Long coverImgId;
    private String description;
    private List<String> tags;
    private Integer playCount;
    private HashMap<String,Object> creator;
    private List<Map<String,Object>> subscribers;
}
