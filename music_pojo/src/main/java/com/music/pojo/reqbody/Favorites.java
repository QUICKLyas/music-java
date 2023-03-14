package com.music.pojo.reqbody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorites {
    private String user_id;
    private Integer music_id;
    private List<String> tags;
}
