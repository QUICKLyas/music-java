package com.music.search.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "song" ,shards = 1,replicas = 1)
public class Song4ES {
    @Id
    @Field(type = FieldType.Keyword)
    private Integer id;

    @Field(type = FieldType.Text , analyzer = "ik_smart",searchAnalyzer = "ik_max_word")
    private String name;

    private List<Object> tags;
    private List<Object> similaritySong;
    private Map<String, Object> songAble;
    private Map<String, Object> songUrl;
}
