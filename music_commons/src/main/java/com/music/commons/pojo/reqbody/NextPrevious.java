package com.music.commons.pojo.reqbody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NextPrevious {
    private Long playlistId;
    private Integer musicId;
}
