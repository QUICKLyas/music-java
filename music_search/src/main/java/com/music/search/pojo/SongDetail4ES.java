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

/**
 * 映射ES中所有的实体类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "songdetail")
public class SongDetail4ES implements Serializable {
    @Id
    private String _id;
    @Id
    private Long id;
    @Field(type= FieldType.Text, analyzer = "ik_max_word")
    private String name;
    @Field(type=FieldType.Keyword)
    private Map<String,Object> song;
}
