package com.music.pojo.reqbody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectMusic {
    private String userId;
    private Integer musicId;
}
