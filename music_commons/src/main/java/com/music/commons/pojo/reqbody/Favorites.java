package com.music.commons.pojo.reqbody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorites {
    private String userId;
    private Integer musicId;
    private List<String> tags;
}
