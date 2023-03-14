package com.music.search;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

// 添加禁止 mongodb 的自动注入
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class MusicSearchServerApplication {
    public static void main( String[] args ) {
        SpringApplication.run(MusicSearchServerApplication.class,args);
    }
}
