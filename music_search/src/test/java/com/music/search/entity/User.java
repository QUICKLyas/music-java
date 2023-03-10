package com.music.search.entity;

import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;

@Data
//@Document(indexName = "user-demo")
public class User {
//    @Id
    private Integer id;
    private String name;
}
