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
@Document(collection = "songdetail")
public class SongDetail implements Serializable {
    @Id
    private String _id;
    private Integer id;
    private String name;
    private Map<String,Object> song;
}
