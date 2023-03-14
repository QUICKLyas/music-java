package com.music.search.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "songdetail" ,shards = 1,replicas = 1)
public class SongDetail implements Serializable {
//    @Id
//    private String _id;
    @Id
    @Field(type = FieldType.Keyword)
    private Integer id;

    @Field(type = FieldType.Text , analyzer = "ik_smart",searchAnalyzer = "ik_max_word")
    private String name;

    private Map<String,Object> song;
}
