package com.music.recommendation.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "recom")
public class Recommendation {
    @Id
    private String _id;
    private String id;
    private String name;
    private HashMap tagsRate;
    private List<String> recomUsers;
    private List<Integer> recomSong;
}
