package com.music.song;

import com.netflix.discovery.shared.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicSongServerApplication {
    public static void main(String []args){
        SpringApplication.run(MusicSongServerApplication.class,args);
    }
}
