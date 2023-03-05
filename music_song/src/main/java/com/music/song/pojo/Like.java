package com.music.song.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "like")
public class Like {
    @Id
    private String _id;
    private String id;
    private String name;
    private List<Integer> playlists;
    private List<Map> songs;
    private List<String> tags;
}
