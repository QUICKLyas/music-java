package com.music.song.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "playlistdetail")
public class PlaylistDetail implements Serializable {
    @Id
    private String _id;
    private Long id;
    private String name;
    private List<Map> trackIds;


}
